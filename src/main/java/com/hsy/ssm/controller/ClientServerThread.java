package com.hsy.ssm.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientServerThread extends Thread{
	
	private Socket clientSocket;
	
	public static AtomicInteger threadNum = new AtomicInteger(0);  
	
	public ClientServerThread(Socket clientSocket) {
		 this.clientSocket=clientSocket;
	}
	
	public  String getStrFromInpuStrea(InputStream input){
		/*if(input!=null){
			 try{
					 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
					 StringBuffer sb = new StringBuffer();
					 String line = new String("");
					 while (true){
						 line = bufferedReader.readLine();
						 if(line==null||line.length()==0){
							 break;
						 }
						 sb.append(line);
					 }
					 return sb.toString();
				 }catch (Exception e){
					 e.printStackTrace();
				 }
			 	 return "";
		}
		return null;*/
	    byte[] buf = new byte[2048];  
	    int i;
		try {
				i = input.read(buf);
				String msg = new String(buf,0,i);  
				return msg;
		} catch (IOException e) {
				e.printStackTrace();
		}  
	    return null;
	}
	
	
	
	@Override
	public void run() {
			String clientInfo="";
		  	try {
		  			System.out.println("线程："+Thread.currentThread().getId()+"开启，threadNum="+threadNum.addAndGet(1));
		  			clientInfo=clientSocket.getInetAddress()+"["+clientSocket.getPort()+"]";
		  			System.out.println("接收到客户端"+clientInfo+"的请求...");
		  			InputStream input = clientSocket.getInputStream();
		  			String msg_request = getStrFromInpuStrea(input);
		  			
		  			
		  			int beginByte=0;
					
		  			String[] lines = msg_request.split("\n");
		  			for (String line : lines) {
		  				   if(line.contains("Range")){
		  						int index1=line.indexOf("bytes=");
		  			  			int index2=line.indexOf("-");
		  			  			String rangeBytes=line.substring(index1+6,index2);
		  			  			beginByte=Integer.parseInt(rangeBytes);
		  				   }
					}
		  			
					System.out.println("请求从第"+beginByte+"个字节开始....="+(beginByte/1024)+"k="+(beginByte/1024/1024)+"M");
		  			
		  			OutputStream output = clientSocket.getOutputStream();
		  			
		  			
		  			FileInputStream fis = new FileInputStream(new File("D:/mp4/xxx.mp4"));
		  			int fileLen=fis.available();
		  			fis.skip(beginByte);//从第beginByte个字节(包含该位置处的字节)开始读取
		  			
		  			
		  			StringBuilder responseHeader=new StringBuilder("");
		  			
		  			responseHeader.append("HTTP/1.1 206\n");
		  			responseHeader.append("Server:  Apache-Coyote/1.1\n");
		  			responseHeader.append("Accept-Ranges: bytes\n");
		  			responseHeader.append("Last-Modified: Thu, 24 Aug 2017 05:39:57 GMT\n");
		  			responseHeader.append("Content-Range: bytes "+beginByte+"-"+(fileLen-1)+"/"+fileLen+"\n");
		  			responseHeader.append("Content-Type: video/mp4\n");
		  			responseHeader.append("Content-Length: "+(fileLen-beginByte)+"\n");
		  			responseHeader.append("Date: Thu, 24 Aug 2017 05:40:31 GMT\n\n");
		  			output.write(responseHeader.toString().getBytes());
		  			output.flush();
		  			
		  			
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
						    output.write(bufferArr, 0, len);//java.net.SocketOutputStream-
		  			}
		  			
		  			output.flush();
		  			output.close();
		  			input.close();
		  			fis.close();

		  			System.out.println("客户端"+clientInfo+"的请求处理完毕...");
		  			
			} catch (IOException e) {
					System.out.println("错误信息="+e.getMessage());
			}finally{
					System.out.println("线程："+Thread.currentThread().getId()+"结束，threadNum="+threadNum.decrementAndGet());
			}
	}
	
	
	public static void main(String[] args) {
			String str="bytes=42565632-";
		
			
			str=str.substring(6,2);
			System.out.println(str);
		
	}

}
