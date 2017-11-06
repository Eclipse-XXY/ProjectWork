package com.xuxueya.atcrowdfunding.manager.controller;

import java.io.File;
import java.util.Map;
import java.util.UUID;

import javax.mail.Session;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xuxueya.atcrowdfunding.bean.Advert;
import com.xuxueya.atcrowdfunding.bean.User;
import com.xuxueya.atcrowdfunding.manager.service.AdvertService;
import com.xuxueya.atcrowdfunding.util.AjaxResult;
import com.xuxueya.atcrowdfunding.util.Const;
import com.xuxueya.atcrowdfunding.util.Datas;
import com.xuxueya.atcrowdfunding.util.Page;
import com.xuxueya.atcrowdfunding.util.StringUtil;

@Controller
@RequestMapping("/advert")
public class AdvertController {
	@Autowired
	private AdvertService advertService;
	@RequestMapping("/index")
	public String toIndex() {
		return "advert/index";
	}
	@RequestMapping("/add")
	public String toAdd() {
		return "advert/add";
	}
	@RequestMapping("/edit")
	public String toedit(Integer id ,Map<String, Object> map) {
		Advert advert=advertService.queryAdvertById(id);
		map.put("advert", advert);
		return "advert/edit";
	}
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object dopageQuery(@RequestParam(value="pageNo",defaultValue="1",required=false)Integer pageNo,
			@RequestParam(value="pageSize",defaultValue="1",required=false)Integer pageSize,String queryText) {
		AjaxResult result=new AjaxResult();
		Map<String , Object> paramMap=new HashedMap();
		paramMap.put("pageNo", pageNo);
		paramMap.put("pageSize", pageSize);
		if (StringUtil.isNotEmpty(queryText)) {
			queryText=queryText.replace("%", "\\\\%");
		}
		paramMap.put("queryText", queryText);
		try {
			Page<Advert> page=advertService.queryPage(paramMap);
		
			result.setPage(page);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/doAdd")
	public Object doAdd(HttpServletResponse response,HttpServletRequest request,Advert advert,HttpSession session) {
		AjaxResult result=new AjaxResult();
	
		try {
			//将请求的request转换成文件上传的请求对象
			MultipartHttpServletRequest mRequest=(MultipartHttpServletRequest) request;
			//接收完文件上传的请求后再获取想要上传的文件信息
			//通过前端上传的pic文件通过id获取文件
			//得到上传的文件
			MultipartFile file = mRequest.getFile("advpic");
			//获取源文件的名字
			String filename=file.getOriginalFilename();
			int ext=filename.lastIndexOf(".");
			//获取文件的扩展名.JPG/.png
		String extname=filename.substring(ext);
		//然后在生成一个新的图片的名称因为图片的名字可能重复就像生成订单号一样使用UUID
		//因为UUID每一次生成的序列号都不一样且单位较长防止文件重名的问题
		String iconPath=UUID.randomUUID().toString()+extname;
	//获取域对象，在使用域对象将上传完的数据找个地方进行存储
		ServletContext servletContext = session.getServletContext();
		//我的图片就直接存储在项目的pic目录下面
		String realPath = servletContext.getRealPath("/pic");
		//这个是代表在Pic/adv/XX.jpg文件
		String path=realPath+"\\adv\\"+iconPath;
		//创建完目录后将文件另存为在刚才指定的目录下面
		file.transferTo(new File(path));
		//通过session域获取是哪个用户上传的图片
		User user=(User)session.getAttribute(Const.LOGIN_USER);
	     /**
		 * 把刚才得到的全部的信息封装成advert对象 保存导数据库当中
		 * 前端用户传过来两个现在自己封装了三个总共的一个advert对象封装完成可以进行数据库的保存操作
		 */
		 advert.setUserid(user.getId());
	     advert.setStatus("1");
	     advert.setIconpath(iconPath);
	 	int count =advertService.saveAdvert(advert);
	     result.setSuccess(count>0);
		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/update")
	public Object doupdate(Advert advert) {
		AjaxResult result=new AjaxResult();
		int count =advertService.updateAdvert(advert);
		try {
			result.setSuccess(count>0);
		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/delete")
	public Object dodelete(Integer id) {
		AjaxResult result=new AjaxResult();
		int count =advertService.deleteAdvert(id);
		try {
			result.setSuccess(count>0);
		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/batchDelete")
	public Object dobatchDelete(Datas datas) {
		AjaxResult result=new AjaxResult();
		int count =advertService.batchDelete(datas.getDatas());
		try {
			result.setSuccess(count==datas.getDatas().size());
		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}
}
