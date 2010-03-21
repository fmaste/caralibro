package caralibro.model.data.stream;

import java.util.Collection;

public class Link {
	private Collection<String> web;
	private Collection<String> photo;
	private Collection<String> video;
	
	public Link() {
	}
	
	public Collection<String> getWeb() {
		return web;
	}
	public void setWeb(Collection<String> web) {
		this.web = web;
	}
	public Collection<String> getPhoto() {
		return photo;
	}
	public void setPhoto(Collection<String> photo) {
		this.photo = photo;
	}
	public Collection<String> getVideo() {
		return video;
	}
	public void setVideo(Collection<String> video) {
		this.video = video;
	}
	
}
