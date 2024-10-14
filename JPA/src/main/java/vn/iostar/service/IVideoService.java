package vn.iostar.service;

import java.util.List;

import vn.iostar.entity.Video;

public interface IVideoService {
	void insert(Video video);

	List<Video> findAll();

	Video findByVideoname(String name) throws Exception;
}
