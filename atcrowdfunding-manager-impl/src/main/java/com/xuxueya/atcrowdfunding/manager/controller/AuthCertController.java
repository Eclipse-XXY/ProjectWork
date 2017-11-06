package com.xuxueya.atcrowdfunding.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuxueya.atcrowdfunding.bean.Member;
import com.xuxueya.atcrowdfunding.bean.Ticket;
import com.xuxueya.atcrowdfunding.manager.service.MemberCertService;
import com.xuxueya.atcrowdfunding.potal.service.MemberService;
import com.xuxueya.atcrowdfunding.potal.service.TicketService;
import com.xuxueya.atcrowdfunding.util.AjaxResult;
import com.xuxueya.atcrowdfunding.util.Const;
import com.xuxueya.atcrowdfunding.util.Page;
/**
 * 该控制器用于封装多个表当中想要的数据封装到map当中
 * @param
 * @author 徐雪娅
 */
@Controller
@RequestMapping("/auth_cert")
public class AuthCertController {
	@Autowired
	private TaskService taskService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private MemberService memberService ;
	@Autowired
	private TicketService ticketService  ;
	@Autowired
	private MemberCertService memberCertService ;
	@RequestMapping("/index")
	public String toIndex() {
		return "auth-cert/index";
	}
	/**
	 * 查询多张表可以封装成为一个实体对象也可以把多个表的字段封装成一个map
	 * 再把map或者实体对象放到list集合当中做遍历的时候使用
	 * @param
	 * @author 徐雪娅
	 */
	@RequestMapping("/show")
	public String toshow(Integer memberid,Map<String , Object> map) {
		Member member=memberService.queryMemberByid(memberid);
		List<Map<String, Object>> dataList=memberCertService.queryMemberCertbyMebId(memberid);
		map.put("dataList", dataList);
		map.put("member", member);
		return "auth-cert/show";
	}
	@ResponseBody
	@RequestMapping("/queryPage")
public Object doqueryPage(@RequestParam(value="pageno",defaultValue="1",required=false)Integer pageno,
		@RequestParam(value="pagesize",defaultValue="10",required=false)Integer pagesize) {
	AjaxResult result=new AjaxResult();
	try {
	
	List<Map<String , Object>> data=new ArrayList<>();
		//写代码一般都是从后往前推都是倒着写同时查询的时候从后往前查询也是最方便的额
		TaskQuery taskQuery = taskService.createTaskQuery();
		//通过组查询所有的流程实例也就是工作任务
		List<Task> listPage =taskQuery.taskCandidateGroup("backuser").listPage((pageno-1)*pagesize, pagesize);
		for (Task task : listPage) {
	Map<String , Object> map=new HashMap<String , Object>();
	map.put("taskName", task.getName());
	map.put("taskid", task.getId());
	String processDefinitionId = task.getProcessDefinitionId();
	ProcessDefinition processDefinition = repositoryService.getProcessDefinition(processDefinitionId);
	map.put("processDefVersion", processDefinition.getVersion());
	map.put("processDefName",processDefinition.getName());
String piid = task.getProcessInstanceId();
 Ticket ticket=ticketService.queryTicketByPIId(piid);
 Member member = memberService.queryMemberByid(ticket.getMemberid());
	map.put("membername", member.getUsername());
	map.put("memberid", member.getId());
	data.add(map);
	}
		Page<List<Map<String , Object>>> page = new Page<>(pageno, pagesize);
	page.setList(data);
		//返回这个组的所有任务
		Long count = taskQuery.taskCandidateGroup("backuser").count();
		page.setTotalSize(count.intValue());
		result.setPage(page);
		result.setSuccess(true);
	} catch (Exception e) {
		result.setSuccess(true);
	}
	 return result; 
}
	@ResponseBody
	@RequestMapping("/passAuthCert")
	public Object dopassAuthCert(String taskid,Integer memberid) {
		AjaxResult result=new AjaxResult();
		try {
		taskService.setVariable(taskid, "flag", true);
		taskService.setVariable(taskid, "memberid", memberid);
       taskService.complete(taskid);		
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(true);
		}
		return result; 
	}

@ResponseBody
@RequestMapping("/refuseAuthCert")
public Object dorefuseAuthCert(String taskid,Integer memberid) {
	AjaxResult result=new AjaxResult();
	try {
		
		taskService.setVariable(taskid, "flag",false);
		taskService.setVariable(taskid, "memberid", memberid);
	       taskService.complete(taskid);		
	
		result.setSuccess(true);
	} catch (Exception e) {
		result.setSuccess(true);
	}
	return result; 
}

}
