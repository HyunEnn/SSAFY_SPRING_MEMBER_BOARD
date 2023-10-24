package edu.ssafy.spring.dto;

public class FileDto {

	private String fid;
	private String name;
	private String path;
	
	@Override
	public String toString() {
		return "FileDto [fid=" + fid + ", name=" + name + ", path=" + path + "]";
	}
	
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public FileDto(String fid, String name, String path) {
		super();
		this.fid = fid;
		this.name = name;
		this.path = path;
	}
	
	
}
