package com.hsy.ssm.dao;

import java.util.List;

import com.hsy.ssm.vo.ZtreeNode;

public interface VideoCategoryMapper {
	
	public List<ZtreeNode> loadCategorys();

	public int addVideoCategory(ZtreeNode videoCategory);

	public void updateVideoCategory(ZtreeNode videoCategory);

}
