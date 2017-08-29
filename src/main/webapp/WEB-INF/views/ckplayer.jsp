<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


<div id="a1"></div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ckplayer/ckplayer.js" charset="utf-8"></script>
<script type="text/javascript">
    var flashvars={
        f:'${pageContext.request.contextPath}/video/xxxxx.flv',
        c:0
    };
    var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'transparent'};
    CKobject.embedSWF('${pageContext.request.contextPath}/static/ckplayer/ckplayer.swf','a1','ckplayer_a1','600','400',flashvars,params);
</script>
</html>