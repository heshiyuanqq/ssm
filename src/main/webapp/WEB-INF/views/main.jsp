<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Insert title here</title>
	
	<link rel="StyleSheet" href="${pageContext.request.contextPath}/static/easyui/themes/default/easyui.css" type="text/css" />
	<link rel="StyleSheet" href="${pageContext.request.contextPath}/static/easyui/themes/icon.css" type="text/css" />
	<link rel="StyleSheet" href="${pageContext.request.contextPath}/static/easyui/themes/icon-all.css" type="text/css" />
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-2.0.3.js"  charset="utf-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/easyui/datagrid-scrollview.js"></script>
	
	<link href="${pageContext.request.contextPath}/static/css3_accordion_menu/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css3_accordion_menu/css/style.css" media="screen" type="text/css" />
	
	<style type="text/css">
	</style>
</head>
<body class="easyui-layout" style="font-size: 20px">
    <div data-options="region:'north'" style="height:120px;background-color: gray;padding-bottom: 0px;">
    		<img alt="" src="${pageContext.request.contextPath}/static/img/logo.jpg" style="height: 100px;width: 200px;"/>
    		[${sessionScope.loginUser.username }], 欢迎你!</h1>
    </div>
    <div data-options="region:'west'" style="width:220px;padding-top: 0px;background-color: gray">
	    	<ul id="accordion" class="accordion" style="width: 198px">
				<li>
					<div class="link"><i class="fa fa-paint-brush"></i>学院类别<i class="fa fa-chevron-down"></i></div>
					<ul class="submenu">
						<li><a href="#" title="查看类别" url="${pageContext.request.contextPath}/viewCategory.do" onclick="openTab(this)">查看类别</a></li>
						<li><a href="#" title="编辑类别" url="${pageContext.request.contextPath}/views/editCategory.jsp" onclick="openTab(this)">编辑类别</a></li>
					</ul>
				</li>
				<li>
					<div class="link"><i class="fa fa-automobile"></i>视频管理<i class="fa fa-chevron-down"></i></div>
					<ul class="submenu">
						<li><a href="#" title="视频列表" url="${pageContext.request.contextPath}/listVideos.do" onclick="openTab(this)">视频列表</a></li>
						<li><a href="#" title="添加视频" url="${pageContext.request.contextPath}/videoAddPage.do" onclick="openTab(this)">添加视频</a></li>
					</ul>
				</li>
			</ul>

    </div>
    <div data-options="region:'center'" style="padding:0px">
    		<div id="tt" class="easyui-tabs" style="width:100%;height:100%;padding: 0px;">
			</div>
    </div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/css3_accordion_menu/js/index.js"></script>
<script type="text/javascript">
		var webappName="${pageContext.request.contextPath}";
		
		
		function openTab(this_){
				var title=$(this_).attr("title");
				var url=$(this_).attr("url");
				
				if($("#tt").tabs("exists",title)){
						$("#tt").tabs("select",title)
						//如果是“视频播放”，则还要刷新
						if(title=="视频播放"){
								 var ifream_videoPlay= $("iframe[src^='${pageContext.request.contextPath}/playVideo.do']");
								 if(ifream_videoPlay.attr("src")!=url){
									 ifream_videoPlay.attr("src",url);
								 }
						}
						return;
				}
				
				$('#tt').tabs('add',{
					    title:title,
					    content:' <iframe  src="'+url+'" style="width: 100%;height: 100%;border: 0px"></iframe>',
					    closable:true
				});
		}
		
</script>
</html>

