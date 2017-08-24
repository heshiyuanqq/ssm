package com.hsy.ssm.service;

import java.util.ArrayList;

import com.hsy.ssm.pojo.Video;

public interface VideoService {

	int addVideo(Video video);

	ArrayList<Video> listVideos();

}
