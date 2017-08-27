<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-3.0.0.js"></script>
	<!-- <script type="text/javascript" src="http://a7e4fc62.ngrok.io/ssm/static/jquery-3.0.0.js"></script> -->
</head>
<body>
	<video id="video" width="320" height="240" autoplay></video>
	<button id="snap" class="sexyButton">拍照</button>
	<canvas id="canvas" width="320" height="240" style="display:none"></canvas>
	<img id="snapimg" width="320" height="240"></img>
</body>
<script type="text/javascript">
		function uploadPhoto(imgData){//上传拍照的图片
			/*  var base64Data =imgData;//$("#snapimg").attr("src");
				base64Data = base64Data.substr(22);
				base64Data = base64Data.replace(/\+/g, "%2B"); //对参数中的+号编码，防止丢失 
				var canvas=
			 */
			imgData=imgData.replace("data:image/png;base64,","");
				
			$.ajax({ 
					type:"POST", 
					url:"${pageContext.request.contextPath}/uploadPhoto.do", 
					dataType:"json", 
					data:{img:imgData},
					success:function(data){ 
						
						alert("上传完毕！");
					} 
		     });
		}
		
		
		
		$(function(){
		
			var canvas =document.getElementById("canvas");
			context = canvas.getContext("2d"),
			video = document.getElementById("video");
			videoObj = { "video": true };
			
			var errBack=function(error){
					alert("Video capture error: ", error.code);	
			};
		
			/* if (navigator.getUserMedia){//报错但是能得到？？？
					alert("navigator.getUserMedia");
					navigator.getUserMedia(videoObj,function (stream){
						        alert("stream="+stream);
								video.src = stream;
								video.play();
						},errBack);
			}else  */if (navigator.webkitGetUserMedia){
					navigator.webkitGetUserMedia(videoObj,function (stream){
								video.src = window.webkitURL.createObjectURL(stream);
								video.play();
						},errBack);
			}
		
			$("#snap").click(function(){
					context.drawImage(video, 0, 0, 320, 240);
					var dataUrl = canvas.toDataURL("image/png");
					$("#snapimg").attr("src",dataUrl);
					uploadPhoto(dataUrl);
			});
		
		});
</script>
</html>