package com.hsy.ssm.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsy.ssm.dao.VideoMapper;
import com.hsy.ssm.pojo.Video;
import com.hsy.ssm.service.VideoService;
@Service("videoService")
public class VideoServiceImpl implements VideoService{
	
	
	@Autowired
	private VideoMapper videoMapper;

	public int addVideo(Video video) {
		int count=this.videoMapper.addVideo(video);
		return count;
	}

	public ArrayList<Video> listVideos() {
		return this.videoMapper.listVideos();
	}

}
