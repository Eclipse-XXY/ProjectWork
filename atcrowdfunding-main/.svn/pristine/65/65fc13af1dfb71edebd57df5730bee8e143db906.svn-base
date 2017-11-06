function showMenu(){
				//获取地址栏全部的字符串http://localhost/
				var url=window.location.href;
			    //客户端主机
				var host=window.location.host;
			    //项目的模块名称
			//	var contextPath = "${APP_PATH}";
				var length=url.indexOf(host);
				var path=url.substring(length+host.length);
				//去掉了项目名称
				//path=path.substring(contextPath.length);
				//得到后面/每个模块/index.htm  :user/index.htm
				//模糊匹配链接的href当中包含index相同的url 的a标签
				
				//这个的做法是得到请求的参数
				if(path.indexOf("?")!=-1){
					path = path.substring(0, path.indexOf("?"));
				}
				var contextItem=$(".list-group a[href*=\'"+path+"\']");
				contextItem.css("color","Red");
				contextItem.parent().parent().show();
				contextItem.parent().parent().parent().removeClass("tree-closed");
			}
			  showMenu(); 