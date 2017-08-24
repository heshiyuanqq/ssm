package com.hsy.ssm.controller;

import java.io.File;
import java.io.FileInputStream;
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
	
	@RequestMapping("/demo.do")
	public void demo(HttpServletRequest request ,HttpServletResponse response) throws Exception{
		//return "demo";
		
		request.getRequestDispatcher("/video/yyy.mp4").forward(request, response);
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
	public static void main(String[] args) throws Exception {
		
				ServerSocket serverSocket = new ServerSocket(8888);
				
				System.out.println("serverSocket  start in port 8888....");
				
				while(true){
						Socket clientSocket = serverSocket.accept();
						new ClientServerThread(clientSocket).start();//每接收到一个客户端请求就开启一个专用线程处理
				}		
				
				
	}
	
	
	@RequestMapping("/videoAddPage.do")
	public String videoAddPage(){
		System.out.println("videoAddPage");
		return "addVideo";
	}
	
	@RequestMapping(value = "/uploadVideo.do")  
    public String uploadVideo(@RequestParam(value = "video", required = false) MultipartFile videoFile,String freeTime, HttpServletRequest request, ModelMap model) throws Exception {  
	        String fullVideoPath = request.getSession().getServletContext().getRealPath("fullVideo");  
	        String cutVideoPath = request.getSession().getServletContext().getRealPath("cutVideo");  
	        String videoCoverPath = request.getSession().getServletContext().getRealPath("videoCover");  
	        System.out.println("fullVideoPath="+fullVideoPath);//D:\install\Tomcat-7.0.6\webapps\ssm\fullVideo
	        System.out.println("cutVideoPath="+cutVideoPath);
	        
	        String videoId=UUID.randomUUID().toString();
	        String cutVideoId=UUID.randomUUID().toString();
	        String videoCoverId=UUID.randomUUID().toString();
	        
	        String videoFileName = videoFile.getOriginalFilename();  
	        System.out.println("videoFileName="+videoFileName);
	        
	        File folder = new File(fullVideoPath);  
	        if(!folder.exists()){  
	        	folder.mkdirs();  
	        }  
	      
	        try {//保存  
	            	videoFile.transferTo(new File(fullVideoPath, videoId+".mp4"));  
	        } catch (Exception e) {  
	        		e.printStackTrace();  
	        }  
	        
	        //截取钱几分钟的短视频
	        VideoFileOperate fileOperate = new VideoFileOperate();
	        int videoDuration = fileOperate.parseTimeToSecond(fileOperate.getVideoTime(new File(fullVideoPath, videoId+".mp4")));
	        
	        //添加到数据库
	        Video video = new Video();
	        video.setVideoId(videoId);
	        video.setVideoTitle(videoFileName);
	        video.setVideoDuration(videoDuration);
	        
	        folder = new File(cutVideoPath);
	        if(!folder.exists()){
	        		folder.mkdirs();
	        }
	        boolean cut=fileOperate.cutVideo(fullVideoPath+"\\"+videoId+".mp4", 
	        								 0, 
	        								 Integer.parseInt(freeTime),
	        								 cutVideoPath+"\\"+cutVideoId+".mp4");
	        if(cut){//
	        	  video.setPrevCutVideoId(cutVideoId);
	        	  video.setVideoFreeDuration(Integer.parseInt(freeTime));
	        }
	        
	        //截取视频封面：
	        folder=new File(videoCoverPath);
	        if(!folder.exists()){
        			folder.mkdirs();
	        }
	        fileOperate.cutImgInTheSeconds(fullVideoPath+"\\"+videoId+".mp4",
	        							   "1",
	        							   null,
	        							   videoCoverPath+"\\"+videoCoverId+".jpg");
	        video.setVideoCoverId(videoCoverId);//保存封面图片的地址
	        
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
	public void playVideo(HttpServletRequest request,HttpServletResponse response,String videoCoverId) throws ServletException, IOException{
		//todo
	}
	
}
