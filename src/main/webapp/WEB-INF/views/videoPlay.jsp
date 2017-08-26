<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link href="${pageContext.request.contextPath}/static/jplayer/jPlayer.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/static/jplayer/jplayer.pink.flag.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-3.0.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jplayer/jquery.jplayer.js"></script> 
	
	<style type="text/css">
	</style>
</head>
<body>

		<h1>${video.videoTitle}</h1>
		<h1>总时长：${video.videoDuration}秒，你只能免费观看前${video.videoFreeDuration}秒，点击<a href="${pageContext.request.contextPath}/buyVideo.do">立即购买！</a></h1>
		<div id="jp_container" class="jp-video jp-video-270p" role="application" aria-label="media player">
			<div class="jp-type-single">
				<div id="jquery_jplayer" class="jp-jplayer"></div>
				<div class="jp-gui">
					<div class="jp-video-play">
						<button class="jp-video-play-icon" role="button" tabindex="0">play</button>
					</div>
					<div class="jp-interface">
						<div id="xxx" class="jp-progress" >
								<div id="yyy" class="jp-seek-bar">
										<div class="jp-play-bar"></div>
								</div>
						</div>
						<div class="jp-current-time" role="timer" aria-label="time">&nbsp;</div><!-- 显示当前时间 -->
						<div class="jp-duration" role="timer" aria-label="duration">&nbsp;</div><!-- 显示总时长 -->
						<div class="jp-details">
							<div class="jp-title" aria-label="title">&nbsp;</div>
						</div>
						<div class="jp-controls-holder">
							<div class="jp-volume-controls">
								<button class="jp-mute" role="button" tabindex="0">mute</button>
								<button class="jp-volume-max" role="button" tabindex="0">max volume</button>
								<div class="jp-volume-bar">
									<div class="jp-volume-bar-value"></div>
								</div>
							</div>
							<div class="jp-controls">
								<button class="jp-play" role="button" tabindex="0">play</button>
								<button class="jp-stop" role="button" tabindex="0">stop</button>
							</div>
							<div class="jp-toggles">
								<button class="jp-repeat" role="button" tabindex="0">repeat</button>
								<button class="jp-full-screen" role="button" tabindex="0">full screen</button>
							</div>
						</div>
					</div>
				</div>
				<div class="jp-no-solution">
					<span>Update Required</span>
					To play the media you will need to either update your browser to a recent version or update your <a href="http://get.adobe.com/flashplayer/" target="_blank">Flash plugin</a>.
				</div>
			</div>
		</div>
</body>

<script type="text/javascript">

		$(document).ready(function(){
			
			var prevCutVideoUrl="${video.prevCutVideoUrl}";
			var videoCoverUrl="${video.videoCoverUrl}";
			var videoTitle="${video.videoTitle}";
			
			var videoFreeDuration="${video.videoFreeDuration}";
			var videoDuration="${video.videoDuration}";
			
			videoDuration=parseInt(videoDuration);
			
			
			prevCutVideoUrl="${pageContext.request.contextPath}/video/prevCutVideo/"+prevCutVideoUrl;
			videoCoverUrl="${pageContext.request.contextPath}/video/coverImg/"+videoCoverUrl;
			
			
			var player=$("#jquery_jplayer").jPlayer({
					ready: function () {
							$(this).jPlayer("setMedia", {
									title: videoTitle,
									m4v: prevCutVideoUrl,
									poster: videoCoverUrl
							});
					},
					play: function() { // To avoid multiple jPlayers playing together.
							$(this).jPlayer("pauseOthers");
					},
					ended: function () {
						 // $(this).jPlayer("play");//循环播放
							window.location.href="${pageContext.request.contextPath}/buyVideo.do";//去购买
					},
					/* swfPath: "http://jplayer.org/latest/dist/jplayer", */
					supplied: "webmv, ogv, m4v",
					cssSelectorAncestor: "#jp_container",
					globalVolume: true,
					useStateClassSkin: true,
					autoBlur: false,
					smoothPlayBar: false,
					remainingDuration:false,//true:显示剩余时长，否则显示总时长
					customeShowDuration:videoDuration, //自定义显示出来的总时长
					keyEnabled: true
			});
			
			
			
	});
		
		
		$("#yyy").click(function(event){
		    event.stopPropagation()
		});
		
		
		$("#xxx").click(function(){
			  window.location.href="${pageContext.request.contextPath}/buyVideo.do";
			
		});
</script>
</html>