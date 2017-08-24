package com.hsy.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hsy.ssm.dao.UserMapper;
import com.hsy.ssm.dao.VideoCategoryMapper;
import com.hsy.ssm.service.VideoCategoryService;
import com.hsy.ssm.vo.ZtreeNode;


@Service("videoCategoryService")
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


	public void updateVideoCategory(ZtreeNode videoCategory) {
		  this.videoCategoryMapper.updateVideoCategory(videoCategory);
		
	}

}
