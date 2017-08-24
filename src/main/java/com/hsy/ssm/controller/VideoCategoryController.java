package com.hsy.ssm.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hsy.ssm.service.VideoCategoryService;
import com.hsy.ssm.util.VideoFileOperate;
import com.hsy.ssm.vo.ZtreeNode;
import com.sun.org.apache.xml.internal.resolver.Catalog;


@Controller
public class VideoCategoryController {
	
	@Resource
	private VideoCategoryService videoCategoryService;
	
	@RequestMapping("/viewCategory.do")
	public String viewCategory(){
		return "viewCategory";
	}
	
	
	@RequestMapping("/loadCategoryByPid.do")
	public void  loadCategoryByPid(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
	}
	
	
	@RequestMapping("/loadCategorys.do")
	@ResponseBody
	public List<ZtreeNode>  loadCategorys(HttpServletRequest request,HttpServletResponse response) throws IOException{
		   List<ZtreeNode> list = this.videoCategoryService.loadCategorys();
		   return list;
	}
	
	
	@RequestMapping("/addVideoCategory.do")
	@ResponseBody
	public int  addVideoCategory(int parentId,String videoCategoryName) throws IOException{
		
		int id=this.videoCategoryService.addVideoCategory(parentId,videoCategoryName);
		return id;
	}
	@RequestMapping("/updateVideoCategory.do")
	@ResponseBody
	public int  updateVideoCategory(int id,String videoCategoryName) throws IOException{
		ZtreeNode videoCategory = new ZtreeNode();
		videoCategory.setId(id);
		videoCategory.setName(videoCategoryName);
		this.videoCategoryService.updateVideoCategory(videoCategory);
		return 0;
	}
	
	
	
	/**
	 * 1.从视频中生成gif图片：ffmpeg -i D:/mp4/xxx.mp4 -ss 450(起始秒数)-t 5(持续秒数) -s 320x240 -pix_fmt rgb8 D:/xxx.gif
	 * 2.截取视频指定部分：ffmpeg -y -ss 0(开始秒数) -t 600(持续秒数) -i D:\\mp4\\烟花爆炸效果.mp4  -codec copy D:\\xxxxx.mp4
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		  VideoFileOperate videoFileOperate = new VideoFileOperate();
		long t1 = System.currentTimeMillis();
		  
		  try {
			videoFileOperate.cutVideo("D:\\mp4\\美女舍宾教练_标清.mp4", 240, 196, "D:\\烟花爆炸效果_前300秒.mp4");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			System.out.println("耗时："+( System.currentTimeMillis()-t1)+"毫秒！");
		}
		  
		  
	}
	
	

}
