package com.hsy.ssm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hsy.ssm.pojo.Video;
import com.hsy.ssm.service.VideoService;
import com.hsy.ssm.util.VideoFileOperate;

@Controller
public class VideoController {
	
	@Autowired
	private VideoService videoService;
	
	AtomicInteger atomicInteger = new AtomicInteger(1);  
	AtomicInteger servletNum = new AtomicInteger(0);  
	AtomicInteger servletSum = new AtomicInteger(0);  
	
	@RequestMapping("/demo_throw.do")
	public void demo_throw(HttpServletRequest request ,HttpServletResponse response) throws IOException{
			//return "demo";
			
			int num = servletNum.addAndGet(1);
			System.out.println("servlet "+num+" begin[当前servlet总数："+servletSum.addAndGet(1)+"]!");
			FileInputStream fis = new FileInputStream("D:/mp4/yyy.mp4");
			
			int len=0;
			byte[] buffer=new byte[1024];
			ServletOutputStream sos = response.getOutputStream();
			while((len=fis.read(buffer))!=-1){
				  sos.write(buffer, 0, len);
			}
			
			sos.flush();
			sos.close();
			fis.close();
			System.out.println("servlet "+num+" end[当前servlet总数："+servletSum.decrementAndGet()+"]!");
	}
	
	@RequestMapping("/demo_try_catch.do")
	public void demo_try_catch(HttpServletRequest request ,HttpServletResponse response){
			int num=0;
			ServletOutputStream sos =null;
			FileInputStream fis =null;
			try {
					num = servletNum.addAndGet(1);
					System.out.println("servlet "+num+" begin[当前servlet总数："+servletSum.addAndGet(1)+"]!");
					fis= new FileInputStream("D:/mp4/yyy.mp4");
					
					int len=0;
					byte[] buffer=new byte[1024];
					sos= response.getOutputStream();
					while((len=fis.read(buffer))!=-1){
							sos.write(buffer, 0, len);
					}
			} catch (IOException e) {
					System.out.println("出错了："+e.getMessage());
			}finally{
					try {
							sos.flush();
							sos.close();
							fis.close();
					} catch (IOException e) {
							e.printStackTrace();
					}
					System.out.println("servlet "+num+" end[当前servlet总数："+servletSum.decrementAndGet()+"]!");
			}
	}
	
	@RequestMapping("/requestVideo_try_catch.do")
	public void requestVideo_try_catch(HttpServletRequest request ,HttpServletResponse response){
				int count=0;
				FileInputStream fis =null;
				ServletOutputStream output=null;
				try {
						count=atomicInteger.getAndIncrement();
						System.out.println();
						System.out.println("["+count+"]开始:servletNum="+servletNum.addAndGet(1));
						
						String rangeStr = request.getHeader("Range");
						rangeStr = rangeStr.substring(rangeStr.indexOf("=")+1, rangeStr.indexOf("-"));
						
						int beginByte=Integer.parseInt(rangeStr);
						System.out.println("请求从第"+beginByte+"个字节开始....="+(beginByte/1024)+"k="+(beginByte/1024/1024)+"M");
						
						fis = new  FileInputStream(new File("D:/mp4/xxx.mp4"));
						
						int videoLen = fis.available();
						
						output = response.getOutputStream();
						
						response.setStatus(206);
						response.setHeader("Accept-Ranges","bytes");
						response.setHeader("Content-Type","video/mp4");
						response.setHeader("Content-Length",videoLen-beginByte+"");
						response.setHeader("Content-Range","bytes "+beginByte+"-"+(videoLen-1)+"/"+videoLen);
						
						//接下来从beginByte开始读取(包含此处)指定长度个字节
						fis.skip(beginByte);//从第beginByte个字节(包含该位置处的字节)开始读取
						
						int sendLen=4*1024*1024;//每次发送的大小(4M)
						int byteArrLen=1024;
						int len=0; 
						byte[] bufferArr=new byte[byteArrLen];
						
						int loopTime=sendLen/byteArrLen;
						for(int i=0;i<loopTime;i++){
								len=fis.read(bufferArr);
								if(len==-1){
										break;
								}
							    output.write(bufferArr, 0, len);
						}
				} catch (Exception e) {
						System.out.println("错误信息："+e.getMessage());
				}finally{
						try {
								output.flush();
								output.close();
								fis.close();
						} catch (IOException e) {
								System.out.println("错误信息："+e.getMessage());
						}
						
						System.out.println("["+count+"]结束:servletNum="+servletNum.decrementAndGet());
						System.out.println();
				}
	}
	
	
	@RequestMapping("/requestVideo_throw.do")
	public void requestVideo_throw(HttpServletRequest request ,HttpServletResponse response) throws Exception{
			int count=0;
			FileInputStream fis =null;
			ServletOutputStream output=null;
		
			count=atomicInteger.getAndIncrement();
			System.out.println();
			System.out.println("["+count+"]开始:servletNum="+servletNum.addAndGet(1));
			
			String rangeStr = request.getHeader("Range");
			rangeStr = rangeStr.substring(rangeStr.indexOf("=")+1, rangeStr.indexOf("-"));
			
			int beginByte=Integer.parseInt(rangeStr);
			System.out.println("请求从第"+beginByte+"个字节开始....="+(beginByte/1024)+"k="+(beginByte/1024/1024)+"M");
			
			fis = new  FileInputStream(new File("D:/mp4/xxx.mp4"));
			
			int videoLen = fis.available();
			
			output = response.getOutputStream();
			
			response.setStatus(206);
			response.setHeader("Accept-Ranges","bytes");
			response.setHeader("Content-Type","video/mp4");
			response.setHeader("Content-Length",videoLen-beginByte+"");
			response.setHeader("Content-Range","bytes "+beginByte+"-"+(videoLen-1)+"/"+videoLen);
			
			//接下来从beginByte开始读取(包含此处)指定长度个字节
			fis.skip(beginByte);//从第beginByte个字节(包含该位置处的字节)开始读取
			
			int sendLen=4*1024*1024;//每次发送的大小(4M)
			int byteArrLen=1024;
			int len=0; 
			byte[] bufferArr=new byte[byteArrLen];
			
			int loopTime=sendLen/byteArrLen;
			for(int i=0;i<loopTime;i++){
					len=fis.read(bufferArr);
					if(len==-1){
							break;
					}
					output.write(bufferArr, 0, len);
			}
			output.flush();
			output.close();
			fis.close();
		
			System.out.println("["+count+"]结束:servletNum="+servletNum.decrementAndGet());
			System.out.println();
	}
	
	
	
	//tomcat中的DefaultServlet中的断点续传(看看为什么不阻塞？？？测试一下看看 是不是真不阻塞，用jvisulvm看看servlet的个数是不是只增不减？？？)不阻塞的奥秘??
 	public void copyRange(FileInputStream fis,ServletOutputStream sos,long start, long end) throws Exception {
			long skipped = 0;
			try {
					skipped = fis.skip(start);
			} catch (IOException e) {
					return;
			}
			if (skipped < start) {
				    return;
			}
			//0 1 2 3 4 5 6(3-6)
			long len_bytesToRead = end - start + 1;
			
			byte buffer[] = new byte[2048];
			int len = buffer.length;
			while ( (len_bytesToRead > 0) && (len >= buffer.length)) {
					try {
							len = fis.read(buffer);
							if (len_bytesToRead >= len) {
									sos.write(buffer, 0, len);
									len_bytesToRead -= len;
							} else {
									sos.write(buffer, 0, (int) len_bytesToRead);
									len_bytesToRead = 0;
							}
					} catch (IOException e) {
							len = -1;
					}
					if (len < buffer.length){
							break;
					}
			}
	}

	
	
	@SuppressWarnings("resource")
	public static void mainq(String[] args) throws IOException {
		
			/*	ServerSocket serverSocket = new ServerSocket(8888);
				
				System.out.println("serverSocket  start in port 8888....");
				
				while(true){
						Socket clientSocket = serverSocket.accept();
						new ClientServerThread(clientSocket).start();//每接收到一个客户端请求就开启一个专用线程处理
				}		*/
		
		
		FileInputStream fis = new FileInputStream("D:/xxxxxxxxxxxx.txt");
		System.out.println(fis.skip(100));
				
				
	}
	
	
	@RequestMapping("/videoAddPage.do")
	public String videoAddPage(){
		System.out.println("videoAddPage");
		return "addVideo";
	}
	
	@RequestMapping(value = "/uploadVideo.do")  
    public String uploadVideo(@RequestParam(value = "video", required = false) MultipartFile videoFile,String freeTime, HttpServletRequest request, ModelMap model) throws Exception {  
	        String wholeVideoPath ="D:\\tomcat7\\video\\wholeVideo";
	        String cutVideoPath = "D:\\tomcat7\\video\\prevCutVideo";
	        String videoCoverPath ="D:\\tomcat7\\video\\coverImg";
	        
	        String videoId=UUID.randomUUID().toString();
	        String videoUrl=UUID.randomUUID().toString()+".mp4";
	        String cutVideoUrl=UUID.randomUUID().toString()+".mp4";
	        String videoCoverUrl=UUID.randomUUID().toString()+".jpg";
	        
	        String videoFileName = videoFile.getOriginalFilename();  
	        
	      
	        try {//保存  wholeVideo
	            	videoFile.transferTo(new File(wholeVideoPath, videoUrl));  
	        } catch (Exception e) {  
	        		e.printStackTrace();  
	        }  
	        
	        VideoFileOperate fileOperate = new VideoFileOperate();
	        String videoTime = fileOperate.getVideoTime(new File(wholeVideoPath, videoUrl));
	        int videoDuration = fileOperate.parseTimeToSecond(videoTime);
	        
	        
	        //组装即将存入数据库的Video实体对象
	        Video video = new Video();
	        video.setVideoId(videoId);
	        video.setVideoUrl(videoUrl);
	        video.setVideoTitle(videoFileName);
	        video.setVideoDuration(videoDuration);
	        
	        boolean cut=fileOperate.cutVideo(wholeVideoPath+"\\"+videoUrl, 
	        								 0, 
	        								 Integer.parseInt(freeTime),
	        								 cutVideoPath+"\\"+cutVideoUrl);
	        if(cut){//截取了开头的短视频文件
	        	  video.setPrevCutVideoUrl(cutVideoUrl);
	        	  video.setVideoFreeDuration(Integer.parseInt(freeTime));
	        }
	        
	        fileOperate.cutImgInTheSeconds(wholeVideoPath+"\\"+videoUrl,
	        							   "1",
	        							   null,
	        							   videoCoverPath+"\\"+videoCoverUrl);
	        video.setVideoCoverUrl(videoCoverUrl);
	        
	        int insertCount=this.videoService.addVideo(video);//真正的存储数据库
	        
	        model.addAttribute("msg", "“"+videoFileName+"”添加成功！");  
	        return "addVideo";  
    }  
	
	@RequestMapping("/listVideos.do")
	public String listVideos(ModelMap model){
		ArrayList<Video>  videoList=this.videoService.listVideos();
		model.addAttribute("videoList", videoList);
		return "videoList";
	}
	
	@RequestMapping("/videoCover.do")
	public void videoCover(HttpServletRequest request,HttpServletResponse response,String videoCoverId) throws ServletException, IOException{
			System.out.println("videoCoverId="+videoCoverId);
			//return "/videoCover/"+videoCoverId+".jpg";
			request.getRequestDispatcher("/videoCover/"+videoCoverId+".jpg").forward(request, response);
	}
	
	
	@RequestMapping("/playVideo.do")
	public String playVideo(String videoId,ModelMap model) throws ServletException, IOException{
			//根据videoId查询videoprev_cut_video_url播放前video_free_duration
			Video video=videoService.getVideoByVideoId(videoId);
			model.addAttribute("video", video);
			return "videoPlay";
	}
	@RequestMapping("/buyVideo.do")
	public String buyVideo(String videoId,ModelMap model) throws ServletException, IOException{
		return "buyVideo";
	}
	
}
