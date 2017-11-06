package com.xuxueya.atcrowdfunding.bean;

import java.util.ArrayList;
import java.util.List;

public class Permission {
private Integer id;
private String name;
private String url;
private String icon;
private boolean open;
private Integer pid;
private boolean checked=false;
public boolean isChecked() {
	return checked;
}
public void setChecked(boolean checked) {
	this.checked = checked;
}
public Integer getPid() {
	return pid;
}
public void setPid(Integer pid) {
	this.pid = pid;
}

private List<Permission>  children=new ArrayList<>();
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getIcon() {
	return icon;
}
public void setIcon(String icon) {
	this.icon = icon;
}
public boolean isOpen() {
	return open;
}
public void setOpen(boolean open) {
	this.open = open;
}
public List<Permission> getChildren() {
	return children;
}
public void setChildren(List<Permission> children) {
	this.children = children;
}
public Permission(String name) {
	super();
	this.name = name;
}
public Permission() {
	super();
	// TODO Auto-generated constructor stub
}

}
