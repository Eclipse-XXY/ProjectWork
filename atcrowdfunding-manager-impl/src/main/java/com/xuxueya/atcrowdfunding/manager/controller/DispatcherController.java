package com.xuxueya.atcrowdfunding.manager.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuxueya.atcrowdfunding.bean.Member;
import com.xuxueya.atcrowdfunding.bean.Permission;
import com.xuxueya.atcrowdfunding.bean.User;
import com.xuxueya.atcrowdfunding.manager.service.UserService;
import com.xuxueya.atcrowdfunding.potal.service.MemberService;
import com.xuxueya.atcrowdfunding.util.AjaxResult;
import com.xuxueya.atcrowdfunding.util.Const;
import com.xuxueya.atcrowdfunding.util.MD5Util;

/**
 * 如果想知道核心控制器是否生成IOC容器的额=对象就需要给添加注解得类一个构造器然后打印一句话看界面调用了没有 做登录的操作
 * 
 * @param
 * @author 徐雪娅
 */
@Controller
public class DispatcherController {
	public DispatcherController() {
		System.out.println("DispatcherController() --------------->>");
	}

	@Autowired
	private UserService userService;
	@Autowired
	private MemberService memberService;

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpSession session) {
		// 表示默認都需要登陆
		boolean needLogin = true;
		String usertype = null;
		// 表示获取客户端浏览器的所有cookie信息
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			// 如果浏览器客户端存入的cookie不为空再找和登录有关的cookie的信息
			for (Cookie cookie : cookies) {
				if ("logininfo".equals(cookie.getName())) {
					// 通过cookie的key获取值的信息
					String memberinfo = cookie.getValue();
					String[] splitmemberinfo = memberinfo.split("&");
					if (splitmemberinfo.length == 3) {
						String loginacct = splitmemberinfo[0].split("=")[1];
						String userpswd = splitmemberinfo[1].split("=")[1];
						usertype = splitmemberinfo[2].split("=")[1];
						if ("member".equals(usertype)) {
							Map<String, Object> paramMap = new HashMap<>();
							paramMap.put("loginacct", loginacct);
							paramMap.put("userpswd", userpswd);
							Member member = memberService
									.queryMemberForLogin(paramMap);
							if (member != null) {
								session.setAttribute(Const.LOGIN_MEMBER, member);
								needLogin = false;
							}
						}
						if ("user".equals(usertype)) {
							Map<String, Object> paramMap = new HashMap<>();
							paramMap.put("loginacct", loginacct);
							paramMap.put("userpswd", userpswd);
							User user = userService.queryUserForLogin(paramMap);
							if (user != null) {
								session.setAttribute(Const.LOGIN_USER, user);
								needLogin = false;
							}
						}

					}
				}
			}

		}
		if (needLogin) {
			return "login";
		} else {
			if ("user".equals(usertype)) {
				return "redirect:/main.htm";
			} else if ("member".equals(usertype)) {
				return "redirect:/member.htm";
			}
		}
		return "login";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(Const.LOGIN_MEMBER);
		// 删除以前的session同时将session进行初始化
		session.invalidate();
		return "index";
	}

	@RequestMapping("/member")
	public String memberlogin() {
		return "member/member";
	}

	@RequestMapping("/reg")
	public String reg() {
		return "reg";
	}

	@RequestMapping("/main")
	public String main() {
		return "main";
	}

	@RequestMapping("/errorauth")
	public String errorauth() {
		return "error/errorauth";
	}

	/**
	 * 异步的请求方式
	 * 
	 * @param
	 * @author 徐雪娅
	 */
	// @ResponseBody 表示采用框架底层的HttpMessageConverter进行内容类型转换
	// 如果引用了Jackson组件,返回实体对象或集合,框架就会将对象或集合转换为 JSON格式字符串,
	// 然后以流的形式将字符串响应给客户端.
	// 做管理员登录的模块
	@ResponseBody
	@RequestMapping("/doLogin")
	public Object doLogin(String loginacct, String userpswd, String usertype,
			String flag, HttpServletResponse response, HttpSession session) {
		AjaxResult result = new AjaxResult();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loginacct", loginacct);
		System.out.println(MD5Util.digest(userpswd));
		paramMap.put("userpswd", MD5Util.digest(userpswd));
		User user = userService.queryUserForLogin(paramMap);

		if (user == null) {
			result.setErrorMessage("用户名或密码不正确");
			result.setSuccess(false);

		} else {
			if ("1".equals(flag)) {
				// 在Tomcat6当中需要转义一下一个字符串但是在7的当中加不加都行
				String logininfo = "\"loginacct=" + user.getLoginacct()
						+ "&userpswd=" + user.getUserpswd()
						+ "&usertype=user\"";
				Cookie cookie = new Cookie("logininfo", logininfo);
				cookie.setMaxAge(60 * 60 * 24 * 14);// 2个星期
				cookie.setPath("/");// 表示对于所有路径下的资源都可以进行访问
				// 将cookcie放到response域当用户点击时就放入浏览器当中 然后在login的方法当中取cookie的值
				response.addCookie(cookie);
			}
			/**
			 * 下面是全县的分配
			 */
			// 通过用户id查询到用户的所有的权限
			List<Permission> permissionlistList = userService
					.queryUserPermissionByUserId(user.getId());
			Map<Integer, Permission> map = new HashedMap();
			Set<String> allUserPermissionURis = new HashSet<>();
			// 获取用户的所有URI然后放到session域当中
			// 查询到所有的权限之后进行权限的组合找出根节点
			for (Permission permission : permissionlistList) {
				map.put(permission.getId(), permission);
				allUserPermissionURis.add("/" + permission.getUrl());
			}
			Permission root = null;
			for (Permission permission : permissionlistList) {
				if (permission.getPid() == null) {
					root = permission;
					// root.setOpen(true);
				} else {

					Permission children = permission;
					Permission permission2 = map.get(children.getPid());
					permission2.getChildren().add(children);
					// permission2.setOpen(true);
				}
			}
			result.setSuccess(true);
			session.setAttribute(Const.ALL_USER_PERMISSIONURI,
					allUserPermissionURis);
			session.setAttribute(Const.ROOT_PERMISSION, root);
		}
		session.setAttribute(Const.LOGIN_USER, user);

		return result;
	}

	/**
	 * 处理会员登录的验证同时设置cook的信息当用户选择记住我的时候不用登录直接跳转界面
	 * 
	 * @param
	 * @author 徐雪娅
	 */
	@ResponseBody
	@RequestMapping("/doMemberLogin")
	public Object doMemberLogin(String loginacct, String userpswd,
			String usertype, String flag, HttpSession session,
			HttpServletResponse response) {
		AjaxResult result = new AjaxResult();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loginacct", loginacct);
		System.out.println(MD5Util.digest(userpswd));
		paramMap.put("userpswd", MD5Util.digest(userpswd));
		Member member = memberService.queryMemberForLogin(paramMap);
		if (member == null) {
			result.setErrorMessage("会员名或密码不正确");
			result.setSuccess(false);
		} else {
			// 选中记住我 拼接字符串放入cookie中
			if ("1".equals(flag)) {
				// 在Tomcat6当中需要转义一下一个字符串但是在7的当中加不加都行
				String logininfo = "\"loginacct=" + member.getLoginacct()
						+ "&userpswd=" + member.getUserpswd()
						+ "&usertype=member\"";
				Cookie cookie = new Cookie("logininfo", logininfo);
				cookie.setMaxAge(60 * 60 * 24 * 14);// 2个星期
				cookie.setPath("/");// 表示对于所有路径下的资源都可以进行访问
				// 将cookcie放到response域当用户点击时就放入浏览器当中 然后在login的方法当中取cookie的值
				response.addCookie(cookie);
			}
			result.setSuccess(true);
		}
		session.setAttribute(Const.LOGIN_MEMBER, member);

		return result;
	}

	/**
	 * 处理同步的请求方式
	 * 
	 * @SuppressWarnings("unchecked")
	 * @RequestMapping("/doLogin") public String doLogin(String loginacct,
	 *                             String userpswd, String usertype, Map map) {
	 *                             Map<String, Object> paramMap = new
	 *                             HashMap<String, Object>();
	 *                             paramMap.put("loginacct", loginacct);
	 *                             paramMap.put("userpswd", userpswd); User user
	 *                             = userService.queryUserForLogin(paramMap); if
	 *                             (user == null) { map.put("errorMessage",
	 *                             "用户名或密码不正确"); return "login"; } else {
	 * 
	 *                             if ("member".equals(usertype)) { //
	 *                             redirect:/ 表示webcontext目录下的index.jsp然后进行转发
	 *                             return "redirect:/"; } else if
	 *                             ("user".equals(usertype)) { //
	 *                             转发的话无法再域当中传输数据做回显也就是不携带数据源 //
	 *                             从定向回去的界面不走核心控制器所以需要加上后缀让它在web.xml中做资源的映射 //
	 *                             .htm结尾的代表不走业务层的代码
	 *                             System.out.println("main界面还未设计完成"); return
	 *                             "redirect:/main.htm"; } else { throw new
	 *                             RuntimeException("非法登录"); } }
	 * 
	 *                             }
	 * @param
	 * @author 徐雪娅
	 */

}
