package vn.iostar.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor 
@Data
@Entity

@Table(name = "categories")

@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")

public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "CategoryId")

	private int categoryId;

	@Column(name = "Categorycode")

	private String categorycode;

	@Column(name = "Categoryname")

	private String categoryname;

	@Column(name = "Images")

	private String images;

	@Column(name = "Status")

	private boolean status;

	// bi-directional many-to-one association to Video

	@OneToMany(mappedBy = "category")

	private List<Video> videos;
	
	public Video addVideo(Video video) {
		getVideos().add(video);
		video.setCategory(this);
		return video;
	}
	
	public Video updateVideo(Video video)
	{
		getVideos().add(video);
		video.setCategory(this);
		return video ; 
	}

	public Video removeVideo(Video video) {
		getVideos().remove(video);
		video.setCategory(null);
		return video;
	}
	
	
}
