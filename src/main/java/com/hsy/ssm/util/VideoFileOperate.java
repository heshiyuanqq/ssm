package com.hsy.ssm.util;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ffmpeg -ss 25 -t 10 -i D:\Media\bear.wmv -f gif D:\a.gif

意思是：将D:\Media目录下的源文件bear.wmv，从第25秒的位置开始，截取10秒长度的视频转成GIF文件，保存为D:\a.gif。
 * 视频文件分割操作类
 * 
 * 截取指定秒数开始处的第一帧图片：ffmpeg -y -i D:\\mp4\\美女舍宾教练_标清.mp4 -vframes 1 -ss 1(开始秒数) -f mjpeg -s 200*300(默写默认为视频分辨率)  -an D:\\xxxxxx.jpg
 */
public class VideoFileOperate {
    //分割视频的大小，装包动作为了避免越界。long应该够使了。。。
//    private long blockSize = 1 * new Long(1024) * 1024 * 1024;
    private long blockSize = 400 * 1024 * 1024;
    private loadingListener mListener;
    private boolean ffmpegWorkingFlag = false;

    //获取视频文件时长(格式hh:MM:ss)
    public String getVideoTime(File file) throws FileNotFoundException {
	        if (!file.exists()) {
	            	throw new FileNotFoundException(file.getAbsolutePath() + "不存在");
	        }
	        List<String> commands = new ArrayList<String>();
	        commands.add("D:\\install\\ffmpeg\\bin\\ffmpeg");
	        commands.add("-i");
	        commands.add(file.getAbsolutePath());
	        CmdResult result = runCommand(commands);
	        String msg = result.getMsg();
	        if (result.isSuccess()) {
		            Pattern pattern = Pattern.compile("\\d{2}:\\d{2}:\\d{2}");
		            Matcher matcher = pattern.matcher(msg);
		            String time = "";
		            while (matcher.find()) {
		            		time = matcher.group();
		            }
		            return time;
	        } else {
	            	return "";
	        }
    }

     //获取文件大小(单位：byte)
    public long getVideoFileLength(File file) throws FileNotFoundException {
	        if (!file.exists()) {
	        		throw new FileNotFoundException(file.getAbsolutePath() + "不存在");
	        }
	        return file.length();
    }
    
    //截取指定秒数开始处的第一帧图片:ffmpeg -y -i D:\\mp4\\美女舍宾教练_标清.mp4 -vframes 1 -ss 1(开始秒数) -f mjpeg -s 200*300(默写默认为视频分辨率)  -an D:\\xxxxxx.jpg
    public void cutImgInTheSeconds(String videoPath,String beginSeconds,String size,String targetPath){
    	  	ArrayList<String> commands = new ArrayList<String>();
	        commands.add("D:\\install\\ffmpeg\\bin\\ffmpeg");
	        commands.add("-y");
	        commands.add("-i");
	        commands.add(videoPath);
	        commands.add("-vframes");
	        commands.add("1");
	        commands.add("-ss");
	        commands.add(beginSeconds);
	        commands.add("-f");
	        commands.add("mjpeg");
	        if(size!=null){
	        	commands.add("-s");
	        	commands.add(size);
	        }
	        commands.add("-an");
	        commands.add(targetPath);
	        runCommand(commands);
    }

    /* @param filePath 要处理的文件路径
       @return 分割后的文件路径*/
    public boolean cutVideo(String sourcePath,int startTime,int duration,String targetPath) throws Exception {
	        File file = new File(sourcePath);
	        if (!file.exists()) {
	            	throw new FileNotFoundException(sourcePath + "文件不存在");
	        }
	        if (!sourcePath.endsWith(".mp4")) {
	            	throw new Exception("暂时只支持mp4文件格式！");
	        }
	        
	        String videoTimeString = getVideoTime(file);//时长(00:00:00)
	        int videoSecond = parseTimeToSecond(videoTimeString);//时长(秒数)
	        long fileLength = getVideoFileLength(file);//视频文件的大小(byte)
	        
	        
	        if(videoSecond<=duration){//视频根本不够截取
	        	return false;//没有截
	        }
	        
	        //ffmpeg -y -ss 0(开始秒数) -t 600(持续秒数) -i D:\\mp4\\烟花爆炸效果.mp4  -codec  copy D:\\xxxxx.mp4
	        ArrayList<String> commands = new ArrayList<String>();
	        commands.add("D:\\install\\ffmpeg\\bin\\ffmpeg");
	        commands.add("-y");
	        commands.add("-ss");
	        commands.add(startTime+"");
	        commands.add("-t");
	        commands.add(duration+"");
	        commands.add("-i");
	        commands.add(sourcePath);
	        commands.add("-codec");
	        commands.add("copy");
	        commands.add(targetPath);
	        runCommand(commands);
	        return true;
    }

     //执行Cmd命令方法
    public synchronized CmdResult runCommand(List<String> command) {
	        CmdResult cmdResult = new CmdResult(false, "");
	        
	        ProcessBuilder builder = new ProcessBuilder(command);
            builder.redirectErrorStream(true);
	      
	        try {
		            Process process = builder.start();
		            final StringBuilder stringBuilder = new StringBuilder();
		            final InputStream inputStream = process.getInputStream();
		            
		            new Thread(new Runnable() {//启动新线程为异步读取缓冲器，防止线程阻塞
			                public void run() {
			                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			                    String line;
			                    try {
			                        while ((line = reader.readLine()) != null) {
			                            stringBuilder.append(line);
			                        }
			                        reader.close();
			                    } catch (IOException e) {
			                        e.printStackTrace();
			                    }
			                }
		            }).start();
		            
		            process.waitFor();
		            cmdResult.setSuccess(true);
		            cmdResult.setMsg(stringBuilder.toString());
	        } catch (Exception e) {
	            	throw new RuntimeException("ffmpeg执行异常" + e.getMessage());
	        }
	        return cmdResult;
    }
    
    
    //将字符串时间格式转换为整型，以秒为单位
    public int parseTimeToSecond(String timeString) {
	        Pattern pattern = Pattern.compile("\\d{2}:\\d{2}:\\d{2}");
	        Matcher matcher = pattern.matcher(timeString);
	        if (!matcher.matches()) {
		            try {
		            		throw new Exception("时间格式不正确");
		            } catch (Exception e) {
		                	e.printStackTrace();
		            }
	        }
	        String[] time = timeString.split(":");
	        return Integer.parseInt(time[0]) * 3600 + Integer.parseInt(time[1]) * 60 + Integer.parseInt(time[2]);
    }

    
    //将秒表示时长转为00:00:00格式
    public String parseTimeToString(int second) {
	        int end = second % 60;
	        int mid = second / 60;
	        if (mid < 60) {
	        		return mid + ":" + end;
	        } else if (mid == 60) {
	        		return "1:00:" + end;
	        } else {
		            int first = mid / 60;
		            mid = mid % 60;
		            return first + ":" + mid + ":" + end;
	        }
    }

    interface loadingListener {
        void isLoading(boolean loading);
    }

    // 用于判断ffmpeg是否在工作（ 暂时无法验证是否准确）
	/*public boolean isFFmpegWorking() {
	       mListener = new loadingListener() {
	           public void isLoading(boolean loading) {
	               ffmpegWorkingFlag = loading;
	           }
	       };
	       return ffmpegWorkingFlag;
	}*/
}