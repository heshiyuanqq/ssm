package com.hsy.ssm.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64ImgUtil {

	
	 public static void main(String[] args){  
	        String strImg = getBase64ImgString("D://a0c27b99hbdc9ec99b523&690.jpg");  
	        System.out.println(strImg.substring(0, 200));  
	        generateImage(strImg, "D://xxxx.jpg");  
     }  
	 
	 
    //图片转化成base64字符串  
    public static String getBase64ImgString(String imgFilePath)  {
	    	//将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
	        InputStream in = null;  
	        byte[] data = null;  
	        //读取图片字节数组  
	        try{  
		            in = new FileInputStream(imgFilePath);          
		            data = new byte[in.available()];  
		            in.read(data);  
		            in.close();  
	        } catch (IOException e) {  
	        		e.printStackTrace();  
	        }  
	        //对字节数组Base64编码  
	        BASE64Encoder encoder = new BASE64Encoder();  
	        return encoder.encode(data);//返回Base64编码过的字节数组字符串  
    }  
      
    //base64字符串转化成图片  
    public static boolean generateImage(String base64ImgString,String imgFilePath) {   
	    	//对字节数组字符串进行Base64解码并生成图片  
	        if (base64ImgString == null||base64ImgString.length()<=100){ //图像数据为空  
	            	return false;  
	        }
	        BASE64Decoder decoder = new BASE64Decoder();  
	        try{  
		            //Base64解码  
		            byte[] b = decoder.decodeBuffer(base64ImgString);  
		            for(int i=0;i<b.length;++i) {  
			                if(b[i]<0) {//调整异常数据  ???什么意思
			                    	b[i]+=256;  
			                }
		            }  
		            //生成图片  
		            OutputStream out = new FileOutputStream(imgFilePath);      
		            out.write(b);  
		            out.flush();  
		            out.close();  
		            return true;  
	        } catch (Exception e) {  
	        		return false;  
	        }  
    }  

}
