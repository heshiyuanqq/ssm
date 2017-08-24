<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-3.0.0.js"></script>
</head>
<body>

	<form action="${pageContext.request.contextPath}/uploadVideo.do" method="post"  enctype="multipart/form-data">
			选择视频：<input type="file"  name="video"/><br/>
			免费时长(秒)：<input type="text"  name="freeTime"/><br/>
			<button type="submit">提交</button>
			<button type="reset">重置</button>
	</form>

</body>

<script type="text/javascript">

			$(function(){
				var msg='${msg}';
				if(msg){
					alert(msg);
				}
				
			});
	
</script>
</html>