package com.xuxueya.atcrowdfunding.bean;
/**
 * 四种状态表示广告的状态 0表示数据库默认状况，1代表上传广告、2审核状态、3代表审核通过
 *广告的实体类的封装
 * @param
 * @author 徐雪娅
 */
public class Advert {
private Integer id;
private String name;
private String iconpath;
private String status="0";
private String url;
private Integer userid;
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
public String getIconpath() {
	return iconpath;
}
public void setIconpath(String iconpath) {
	this.iconpath = iconpath;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public Integer getUserid() {
	return userid;
}
public void setUserid(Integer userid) {
	this.userid = userid;
}

}
