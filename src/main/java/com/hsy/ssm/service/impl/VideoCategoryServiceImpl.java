package com.hsy.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsy.ssm.dao.VideoCategoryMapper;
import com.hsy.ssm.service.VideoCategoryService;
import com.hsy.ssm.vo.ZtreeNode;


@Service("videoCategoryService")
@Transactional
public class VideoCategoryServiceImpl implements VideoCategoryService{

	@Resource
	private VideoCategoryMapper videoCategoryMapper;
	
	
	public List<ZtreeNode> loadCategorys() {
		return this.videoCategoryMapper.loadCategorys();
	}


	public int addVideoCategory(int parentId, String videoCategoryName) {
		ZtreeNode videoCategory = new ZtreeNode();
		videoCategory.setpId(parentId);
		videoCategory.setName(videoCategoryName);
		
		this.videoCategoryMapper.addVideoCategory(videoCategory);
		return videoCategory.getId();
	}
	
	
	public void addVideoCategory_testTransaction( int a,int  b) {
		ZtreeNode videoCategory1 = new ZtreeNode();
		videoCategory1.setpId(1);
		videoCategory1.setName("xxxxxxxxxxxxxxxxxxxxxxxx");
		
		ZtreeNode videoCategory2 = new ZtreeNode();
		videoCategory2.setpId(1);
		videoCategory2.setName("yyyyyyyyyyyyyyyyyyyyyyyyyyyy");
		
		this.videoCategoryMapper.addVideoCategory(videoCategory1);
		
		int c=a/b;
		this.videoCategoryMapper.addVideoCategory(videoCategory2);
	}


	public void updateVideoCategory(ZtreeNode videoCategory) {
		  this.videoCategoryMapper.updateVideoCategory(videoCategory);
		
	}

}
