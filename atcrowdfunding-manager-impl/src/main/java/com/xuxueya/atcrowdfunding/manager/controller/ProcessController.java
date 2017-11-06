package com.xuxueya.atcrowdfunding.manager.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xuxueya.atcrowdfunding.util.AjaxResult;
import com.xuxueya.atcrowdfunding.util.Page;

@Controller
@RequestMapping("/process")
public class ProcessController {
@Autowired  
private RepositoryService repositoryService;
@RequestMapping("/index")
public String toindex() {
	
	return "process/index";
}
@RequestMapping("/showImg")
public String showImg() {
	
	return "process/show";
}
/**
 * 必须自定义封装activiti5框架查询的数据因为
 * 它自身查询的数据封装的Json数据的格式
 * 和jQuery解析的不一致可能回报输出流的错误
 * @param
 * @author 徐雪娅
 */
@ResponseBody
@RequestMapping("/doindex")
public Object doindex(@RequestParam(value="pageNo",defaultValue="1",required=false)Integer pageNo,
		@RequestParam(value="pageSize",defaultValue="10",required=false)Integer pageSize) {
	AjaxResult result=new AjaxResult();
	try {
	ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
 int startindex=(pageNo-1)*pageSize;
	List<ProcessDefinition> listPage = processDefinitionQuery.listPage(startindex, pageSize);
	List<Map<String, Object>> pagelist=new ArrayList<>();
	Map<String, Object> map=new HashMap<String, Object>();
	for (ProcessDefinition processDefinition : listPage) {
		map.put("id", processDefinition.getId());
		map.put("name", processDefinition.getName());
		map.put("key", processDefinition.getKey());
		map.put("version", processDefinition.getVersion());
		map.put("deploymentId", processDefinition.getDeploymentId());
		pagelist.add(map);
	}
//	包装的数据类型对应的方法比较多建议使用包装数据类型定义变量
	Long count = processDefinitionQuery.count();
	Page<ProcessDefinition> page = new Page<>(pageNo, pageSize);
	page.setTotalSize(count.intValue());
    page.setList(pagelist);
     result.setPage(page);
     result.setSuccess(true);
	} catch (Exception e) {
	result.setSuccess(false);
	e.printStackTrace();
	}
	return result;
}
@ResponseBody
@RequestMapping("/doProDef")
public Object doProDef(HttpServletRequest request) throws IOException {
	AjaxResult result=new AjaxResult();
	try {
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		MultipartFile file = multipartHttpServletRequest.getFile("procDefFile");
		String deployName = file.getOriginalFilename();
		InputStream inputStream = file.getInputStream();
		repositoryService.createDeployment().addInputStream(deployName, inputStream).deploy();
		result.setSuccess(true);
	} catch (Exception e) {
		result.setSuccess(false);
		e.printStackTrace();
	}
	return result;
}
@ResponseBody
@RequestMapping("/doDelete")
public Object doDelete(String id)  {
	AjaxResult result=new AjaxResult();
	try {
		repositoryService.deleteDeployment(id, true);//这个地方的true表示级联删除
		result.setSuccess(true);
	} catch (Exception e) {
		result.setSuccess(false);
		e.printStackTrace();
	}
	return result;
}
/**
 * 以流的形式把数据库的图片用于界面展示
 * 因为图片保存在数据库当中所以不使用下载到本地
 * 直接传输到客户端就行了交个浏览器去解析
 * IOuilt工具流的使用方式
 * @param
 * @author 徐雪娅
 */

@RequestMapping("/queryProcDefImg")
public void queryProcDefImg(String id,HttpServletResponse response)  {

	try {
	 ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();
     //得到数据库图片的resourceName
	 String imgname = processDefinition.getDiagramResourceName();
     String deploymentId = processDefinition.getDeploymentId();
    InputStream inputStream = repositoryService.getResourceAsStream(deploymentId, imgname);
//因为数据库存储的是二进制的图片文件所以使用字节流不使用getwrite字符流
    ServletOutputStream outputStream = response.getOutputStream();
    IOUtils.copy(inputStream, outputStream);
	} catch (Exception e) {
	
		e.printStackTrace();
	}

}
}
