package com.hsy.ssm.dao;

import java.util.ArrayList;

import com.hsy.ssm.pojo.Video;

public interface VideoMapper {

	int addVideo(Video video);

	ArrayList<Video> listVideos();

	Video getVideoByVideoId(String videoId);

}
