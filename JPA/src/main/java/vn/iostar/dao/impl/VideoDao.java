package vn.iostar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iostar.config.JPAConfig;
import vn.iostar.dao.IVideoDao;
import vn.iostar.entity.Video;

public class VideoDao implements IVideoDao  {

	@Override
	public void insert(Video video) {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();
		try {

			trans.begin();
			enma.persist(video);
			trans.commit();

		} catch (Exception e) {

			e.printStackTrace();

			trans.rollback();

			throw e;

		} finally {

			enma.close();

		}
	}

	@Override
	public List<Video> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();

		TypedQuery<Video> query= enma.createNamedQuery("Video.findAll", Video.class);

		return query.getResultList();
	}

	@Override
	public Video findByVideoname(String name) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();

		String sql = "Select v from Video v Where v.title =: videoname";
		 try {
			 TypedQuery<Video> query= enma.createQuery(sql, Video.class);
				query.setParameter("videoname", name);
				Video video= query.getSingleResult();
			 if(video==null) {

				 throw new Exception("Video Name khong tim thay ");
			 }
			 return video;

			 }
		finally {
			 enma.close();
			 }
	}

}
