<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		.videoArea{
			width: 200px;
			height: 120px;
			background-color: white;
			text-align: center;
			float: left;
			margin-left: 25px;
			margin-top: 10px;
			margin-bottom: 10px;
		}
		
		.coverImg{
			width: 200px;
			height: 100px;
		}
		
		.shenglue{
			text-overflow:ellipsis;
			word-space:nowrap;
			overflow: hidden;
			;margin-top: 0px;
		}
	</style>
</head>
<body style="background-color: gray;">
		<c:forEach items="${videoList }"  var="video">
			<div class="videoArea">
				<img class="coverImg" alt="" src="${pageContext.request.contextPath}\videoCover.do?videoCoverId=${video.videoCoverId}">
				<span></span>
				<p style="width: 200px;" class="shenglue">
					<nobr>
						<a href="${pageContext.request.contextPath}/playVideo.do?videoId=${video.videoId}"  title="${video.videoTitle }">${video.videoTitle }</a>
					</nobr>
				</p>
			</div>
		</c:forEach>
</body>
</html>