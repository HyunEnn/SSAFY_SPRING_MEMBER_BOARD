package edu.ssafy.spring.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.ssafy.spring.dto.FileDto;

@Controller
public class FileController {
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private DataSource source;
	
	@GetMapping("/filelist")
	public ModelAndView fileList(ModelAndView mav) throws SQLException {
		Connection conn = source.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append( "select fid, name, path from files " );
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		List<FileDto> list = new ArrayList();
		while(rs.next()) {
			list.add(new FileDto(rs.getString("fid"), rs.getString("name"), rs.getString("path")));
		}
		conn.close();
		mav.addObject("files",list);
		mav.setViewName("filepage");
		
		return mav;
	}
	
	@PostMapping("/fileupload")
	public String uploadFile(@RequestParam("upfile") MultipartFile[] files) throws IllegalStateException, IOException, SQLException {
		//System.out.println(Arrays.toString(files));
		// 1.저장할 폴더 결정
		// 2.저장할 파일명 결정
		// 3.파일을 전송받는다
		String realPath = servletContext.getRealPath("/upload");
		System.out.println(realPath);
		String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String saveFolder = realPath+File.separator+today;
		File folder = new File(saveFolder);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		Connection conn = source.getConnection();
		StringBuilder sql = new StringBuilder();
		for (MultipartFile mfile : files) {
			String originalFilename = mfile.getOriginalFilename();
			
			sql.append(" insert into files (name, path) ");
			sql.append( "values(?,?) ");
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, originalFilename);
			pstmt.setString(2, folder.toString()+File.separator+originalFilename);
			pstmt.executeUpdate();
			
			mfile.transferTo(new File(folder,originalFilename));
			sql.setLength(0);
		}
		
		conn.close();
		
		return "redirect:/filelist";
	}
	
	@GetMapping("filedownload")
	public ModelAndView fileDownLoad(ModelAndView mav, @RequestParam("fid") String fid) throws SQLException {
		Connection conn = source.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append( "select fid, name, path from files " );
		sql.append(" where fid = ? ");
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1, fid);
		ResultSet rs = pstmt.executeQuery();
		FileDto dto = null;
		while(rs.next()) {
			dto = new FileDto(rs.getString("fid"), rs.getString("name"), rs.getString("path"));
		}
		conn.close();
		System.out.println(dto);
		File file = new File(dto.getPath());
		mav.addObject("downloadFile",file);
		mav.setViewName("filedownload");
		
		return mav;
	}
	                  
}
