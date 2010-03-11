package caralibro.integrator.feed;

import java.util.List;

public interface Feed {
	
	public String getId();

	public String getUserId();

	public String getText();

	public List<String> getPhotoUrls();

	public List<String> getVideoUrls();
	
	public List<String> getLinkUrls();

	public String getPermaLink();
	
	public Long getCreationTime();
	
	// TODO: When there is no update time, use the creation time or null to differ when there are no updates ?
	public Long getUpdateTime();
		
}
