package caralibro.integrator;

import java.util.ArrayList;
import java.util.List;

public class Feed {

	public static final int FB_POST = 0;
	public static final int FB_COMMENT = 1;
	
	private String id = null; // It's a string like "sourceId_postId", being both numbers
	private String userId;
	private String text = null;
	private ArrayList<String> photoUrls = null;
	private ArrayList<String> videoUrls = null;
	private ArrayList<String> linkUrls = null;	
	private Long updateTime = null;
	private Long creationTime = null;
	private int type;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<String> getPhotoUrls() {
		return photoUrls;
	}
	public void setPhotoUrls(ArrayList<String> photoUrls) {
		this.photoUrls = photoUrls;
	}
	public List<String> getVideoUrls() {
		return videoUrls;
	}
	public void setVideoUrls(ArrayList<String> videoUrls) {
		this.videoUrls = videoUrls;
	}
	public List<String> getLinkUrls() {
		return linkUrls;
	}
	public void setLinkUrls(ArrayList<String> linkUrls) {
		this.linkUrls = linkUrls;
	}
	public Long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	public Long getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
