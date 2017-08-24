package com.hsy.ssm.service;

import java.util.List;

import com.hsy.ssm.vo.ZtreeNode;

public interface VideoCategoryService {
	
	public List<ZtreeNode>  loadCategorys();

	public int addVideoCategory(int parentId, String videoCategoryName);

	public void updateVideoCategory(ZtreeNode videoCategory);

}
