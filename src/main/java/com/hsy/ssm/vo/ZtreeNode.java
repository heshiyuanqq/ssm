package com.hsy.ssm.vo;

public class ZtreeNode {
	
/*	 { id:1, pId:0, name:"parentNode 1", open:true},   
     { id:11, pId:1, name:"parentNode 11"},   
     { id:111, pId:11, name:"leafNode 111"},   
     { id:112, pId:11, name:"leafNode 112"},   
     { id:113, pId:11, name:"leafNode 113"},   
     { id:114, pId:11, name:"leafNode 114"},   
     { id:12, pId:1, name:"parentNode 12"},   
     { id:121, pId:12, name:"leafNode 121"},   
     { id:122, pId:12, name:"leafNode 122"},   
     { id:123, pId:12, name:"leafNode 123"},   
     { id:13, pId:1, name:"parentNode 13", isParent:true},   
     { id:2, pId:0, name:"parentNode 2", isParent:true}   */
	
	private int id;
	private int pId=-1;//-1表示默认没有父节点
	private String name;
	private boolean open=false;//默认关闭
	private boolean parent=false;//默认没有子节点
	
	
	
	
	
	public ZtreeNode() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ZtreeNode(int id, int pId, String name, boolean open,
			boolean parent) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.open = open;
		this.parent = parent;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isParent() {
		return parent;
	}
	public void setParent(boolean parent) {
		this.parent = parent;
	}
	
	
	
	

}
