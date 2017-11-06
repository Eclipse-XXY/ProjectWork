package com.xuxueya.atcrowdfunding.potal.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.h2.util.New;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xuxueya.atcrowdfunding.bean.AccountCertType;
import com.xuxueya.atcrowdfunding.bean.Cert;
import com.xuxueya.atcrowdfunding.bean.Member;
import com.xuxueya.atcrowdfunding.bean.MemberCert;
import com.xuxueya.atcrowdfunding.bean.Ticket;
import com.xuxueya.atcrowdfunding.manager.service.CertService;
import com.xuxueya.atcrowdfunding.potal.listener.AuthPassListener;
import com.xuxueya.atcrowdfunding.potal.listener.AuthRefuseListener;
import com.xuxueya.atcrowdfunding.potal.service.MemberService;
import com.xuxueya.atcrowdfunding.potal.service.TicketService;
import com.xuxueya.atcrowdfunding.util.AjaxResult;
import com.xuxueya.atcrowdfunding.util.Const;
import com.xuxueya.atcrowdfunding.util.Datas;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
private MemberService memberService;
	@Autowired
private TicketService ticketService;
	@Autowired
private CertService  certService;
	@Autowired
private RepositoryService repositoryService;
	@Autowired
private RuntimeService runtimeService ;
	@Autowired
private TaskService taskService ;
	@RequestMapping("/member")
	public String toindex() {
		return "member/member";
	}

	//第一个流程界面
	@RequestMapping("/accttype")
	public String toAccttype() {
		return "member/accttype";
	}

	//第二个流程界面
	@RequestMapping("/basicinfo")
	public String toapply() {
		return "member/apply";
	}
	//第三个流程界面
	@RequestMapping("/applypic")
	public String toapplypic() {
		return "member/apply-1";
	}
	@RequestMapping("/apply-2")
	public String toapply2() {
		return "member/apply-2";
	}
	@RequestMapping("/authemail")
	public String toauthemail() {
		return "member/apply-3";
	}
	/**
	 * 带有记忆功能的申请流程
	 * @param
	 * @author 徐雪娅
	 */
	@RequestMapping("/applyproc")
	public String toremberstep(HttpSession session,Map<String , Object> map) {
		Member member = (Member) session.getAttribute(Const.LOGIN_MEMBER);
		Integer id = member.getId();
		 Ticket ticket=ticketService.queryTicketByMemberId(id);
		 if (ticket==null) {
			Ticket ticket2 = new Ticket();
	        ticket2.setMemberid(id);
			//0表示整个的流程还没有走完，1表示整个的流程都已近走完下次再有同一个用户话证明是第二次申请
			ticket2.setStatus("0");
			ticketService.saveTicket(ticket2);
			return "member/accttype";
		 }else {
			 if ("accttype".equals(ticket.getPstep())) {
				 return "member/apply";
			}else if ("apply".equals(ticket.getPstep())) {
				List<Cert> certList=certService.queryAcctCert(member.getAccttype());
				System.out.println(certList);
				for (Cert cert : certList) {
					System.out.println(cert.getId());
				}
				map.put("certList", certList);
				return "member/apply-1";
			}else if ("apply-1".equals(ticket.getPstep())) {
				return "member/apply-2";
			}
			else if ("apply-2".equals(ticket.getPstep())) {
				return "member/apply-3";
			}else {
				return "member/accttype";
			}
		}
	}
	@ResponseBody
	@RequestMapping("/updateAcctType")
	public Object doupdateAcctType(String accttype,HttpSession session) {
		AjaxResult result=new AjaxResult();
		try {
			Member member = (Member) session.getAttribute(Const.LOGIN_MEMBER);
			member.setAccttype(accttype);
	
		int count=memberService.updateAcctType(member);
		 Ticket ticket=ticketService.queryTicketByMemberId(member.getId());
		 ticket.setPstep("accttype");
		int count1= ticketService.updateTicketProcessStep(ticket);
			result.setSuccess((count==1) &&(count1==1));
		} catch (Exception e) {
          e.printStackTrace();
          result.setSuccess(false);
		}
		return result;
	
}
	/**
	 * member  用于接受表单提交的额数据
	 * Loginmember session域当中的登录的会员账号
	 * 跟新数据库的时候最好使用session域当中的对象进行数据的更新
	 *  那样数据库当中的信息改变的同时前端的用户的界面刷新也可以看到相应信息的更新
	 * @param
	 * @author 徐雪娅
	 */
	@ResponseBody
	@RequestMapping("/updatebasicinfo")
	public Object doupdatebasicinfo(Member member ,HttpSession session) {
		AjaxResult result=new AjaxResult();
		try {
			Member Loginmember = (Member) session.getAttribute(Const.LOGIN_MEMBER);
		Loginmember.setTel(member.getTel());
		Loginmember.setRealname(member.getRealname());
		Loginmember.setCardnum(member.getCardnum());
			int count=memberService.doupdatebasicinfo(Loginmember);
			 Ticket ticket=ticketService.queryTicketByMemberId(Loginmember.getId());
			 ticket.setPstep("apply");
			 int count1= ticketService.updateTicketProcessStep(ticket);
			 result.setSuccess((count==1) &&(count1==1));
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
		
	}
	@ResponseBody
	@RequestMapping("/checkemail")
	public Object docheckemail(String email ,HttpSession session) {
		AjaxResult result=new AjaxResult();
		
		try {
			Member Loginmember = (Member) session.getAttribute(Const.LOGIN_MEMBER);
			//判断邮箱是否一致
			if (!email.equals(Loginmember.getEmail())) {
				Loginmember.setEmail(email);
				memberService.updateEmail(email);
			}
			// 准备流程变量的参数入参
			Map<String ,Object> variables=new HashMap<String, Object>();
			String authcode="";
			//把这个数据类型当做一个工具类来使用
			StringBuilder randcode=new StringBuilder();
			for (int i = 0; i < 4; i++) {
				//生成一个从0到9的随机数
				randcode.append( new Random().nextInt(10));
			}
			authcode=randcode.toString();
			variables.put("authcode", authcode);
			variables.put("toEmail", email);
			variables.put("loginacct", Loginmember.getLoginacct());
			variables.put("passListener", new AuthPassListener());
			variables.put("refuseListener", new AuthRefuseListener());
			// 启动审批流程
			ProcessDefinitionQuery definitionQuery = repositoryService.createProcessDefinitionQuery();
			ProcessDefinition processDefinition = definitionQuery.processDefinitionKey("auth").latestVersion().singleResult();
			ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(), variables);
		
			// 更新流程审批的步骤
		
			Ticket ticket=ticketService.queryTicketByMemberId(Loginmember.getId());
			ticket.setPstep("apply-2");
			ticket.setAuthcode(authcode);
			ticket.setPiid(processInstance.getId());
			int count= ticketService.updateTicket(ticket);
			result.setSuccess(count==1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
		
	}
	@ResponseBody
	@RequestMapping("/uploadimg")
	public Object douploadimg(Datas datas ,HttpSession session) {
		AjaxResult result=new AjaxResult();
		try {
Member Loginmember = (Member) session.getAttribute(Const.LOGIN_MEMBER);
List<MemberCert> memberCerts = datas.getMemberCert();
ServletContext context = session.getServletContext();
String realPath = context.getRealPath("pic");
for (MemberCert memberCert : memberCerts) {
	memberCert.setMemberid(Loginmember.getId());
	System.out.println(memberCert.getCertid());
	MultipartFile multipartFile = memberCert.getMultipartFile();
	String realfilename = multipartFile.getOriginalFilename();
	String iconpath=UUID.randomUUID().toString()+realfilename.substring(realfilename.lastIndexOf('.'));
	memberCert.setIconpath(iconpath);
	String newfilepath=realPath+"/cert"+iconpath;
	multipartFile.transferTo(new File(newfilepath));
}

			int count=memberService.uploadimg(datas.getMemberCert());
			Ticket ticket=ticketService.queryTicketByMemberId(Loginmember.getId());
			ticket.setPstep("apply-1");
			int count1= ticketService.updateTicketProcessStep(ticket);
			result.setSuccess((count==1) &&(count1==1));
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
		
	}
	@ResponseBody
	@RequestMapping("/dofinsh")
	public Object dofinsh(String authcode,HttpSession session) {
		AjaxResult result=new AjaxResult();
		try {
			Member Loginmember = (Member) session.getAttribute(Const.LOGIN_MEMBER);
	     Ticket ticket = ticketService.queryTicketByMemberId(Loginmember.getId());
				if (authcode.equals(ticket.getAuthcode())) {
					// 修改会员的申请状态
					//修改域当中的用户和修改数据库当中的用户
					Loginmember.setAuthstatus("1");
					memberService.upateAuthStatus(Loginmember);
					// 让申请流程继续执行
					
					TaskQuery taskQuery = taskService.createTaskQuery();
					Task task = taskQuery.processInstanceId(ticket.getPiid()).taskAssignee(Loginmember.getLoginacct()).singleResult();
			         taskService.complete(task.getId());
					result.setSuccess(true);
				}else {
					result.setErrorMessage("认证失败，查询有不合法项请从新申请");
					result.setSuccess(false);
				}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
}
