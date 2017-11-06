package com.xuxueya.atcrowdfunding.util;

import java.util.ArrayList;
import java.util.List;
import com.xuxueya.atcrowdfunding.bean.MemberCert;
import com.xuxueya.atcrowdfunding.bean.User;
/**
 * 该类用于接受批处理的参数
 * 可以这样理解  一个list是一个集合
 * User虽然是一个对象但是这个对象在集合里面 我就可以理解为它是一个User集合
 * 而User对象有多个属性   也可以把list理解为有多个属性的集合
 * 
 * 
 * user{对象1，对象2.....}
 * id{1,2,3.....}
 * name{"tom","user"}这样的意思
 * 
 * 而前端一般用遍历得到一个个对象    我可以把每个对象的主键数据或其他数据赋值给我集合集合里面对象的属性
 * n 是前端的对象
 * n.id  -->  User.id
 * n 集合中对象   -->  User集合中的对象 
 * datas集合    --->  list集合
 * dataObj 类 ---> Datas 类
 * 	dataObjs["datas["+i+"].id"]=n.value;
 * Datas类           list集合  User集合中的对象    User对象当中的属性
 *  dataObjs["datas["+i+"].name"]="111";
 *  在mybatis处理数据中不管你最后传的是集合还是对象最后都会根据属性进行精确匹配
 * @param
 * @author 徐雪娅
 */
public class Datas {
private List<User> datas=new ArrayList<>();
private List<Integer> ids;
/**
 * 对应数据库的会员上传的资质表数据的封装
 * @param
 * @author 徐雪娅
 */
private List<MemberCert> memberCert;

public List<MemberCert> getMemberCert() {
	return memberCert;
}
public void setMemberCert(List<MemberCert> memberCert) {
	this.memberCert = memberCert;
}
public List<User> getDatas() {
	return datas;
}
public void setDatas(List<User> datas) {
	this.datas = datas;
}
public List<Integer> getIds() {
	return ids;
}
public void setIds(List<Integer> ids) {
	this.ids = ids;
}

}
