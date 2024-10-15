package vn.iostar.service.impl;

import java.util.List;

import vn.iostar.dao.IVideoDao;
import vn.iostar.dao.impl.VideoDao;
import vn.iostar.entity.Video;
import vn.iostar.service.IVideoService;

public class VideoServiceImpl implements IVideoService {

	public IVideoDao vdDao = new VideoDao();


	@Override
	public void insert(Video video){
		Video vd = null;
		try {
			vd = this.findByVideoname(video.getTitle());
		} catch (Exception e) {
			e.printStackTrace();
		}
		 if(vd==null) {

			 vdDao.insert(video);

		 }
	}

	@Override
	public List<Video> findAll() {
		return vdDao.findAll();

	}

	@Override
	public Video findByVideoname(String name) throws Exception {
		try {

			 return vdDao.findByVideoname(name);

			 } catch (Exception e) {

			 e.printStackTrace();

			 }

			 return null;
	}

}
