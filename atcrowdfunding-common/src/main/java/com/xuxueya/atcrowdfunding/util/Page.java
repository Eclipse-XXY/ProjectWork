package com.xuxueya.atcrowdfunding.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * 如果一个类当中定义了一个泛型的方法或者属性的时候
 * 需要给类也定义成泛型的
 * @param
 * @author 徐雪娅
 */
public class Page<T> {
private List<T> data=new ArrayList<T>();
private int totalNo;
private int totalSize;
private int pageNo;
private List list=new ArrayList<>();
public List getList() {
	return list;
}
public void setList(List list) {
	this.list = list;
}
private int pageSize;
public Page(int pageNo,int pageSize){
	if (pageNo<=0) {
		this.pageNo=1;
	}else {
		this.pageNo=pageNo;
	}
	if (pageSize<=0) {
		this.pageSize=10;
	}else {
		this.pageSize=pageSize;
	}
}
public List<T> getData() {
	return data;
}
public void setData(List<T> data) {
	this.data = data;
}
//因为总的页数知道所以用私有的访问修饰符
public int getTotalNo() {
	 return totalNo;
}
//private void setTotalNo(Integer totalNo) {
//this.totalNo=totalNo;
//}
public int getTotalSize() {
	return totalSize;
}
public void setTotalSize(Integer totalSize) {
	
	this.totalSize = totalSize;
	this.totalNo=((totalSize%pageSize)>0 )? (totalSize/pageSize)+1:totalSize/pageSize;
}
public int getPageNo() {
	return pageNo;
}
public void setPageNo(Integer pageNo) {
	this.pageNo = pageNo;
}
public int getPageSize() {
	return pageSize;
}
public void setPageSize(Integer pageSize) {
	this.pageSize = pageSize;
}
public int getStartIndex() {
	return (pageNo-1)*pageSize;
}

}
