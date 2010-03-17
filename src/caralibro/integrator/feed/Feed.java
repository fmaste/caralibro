package caralibro.integrator.feed;

import java.util.List;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public interface Feed {
	
	public String getId();

	public String getUserId();

	public String getText();

	public List<String> getPhotoUrls();

	public List<String> getVideoUrls();
	
	public List<String> getLinkUrls();

	public String getPermaLink();
	
	/*
	 * @return Unix time in seconds, not milliseconds
	 */
	public Long getCreationTime();
	
	// TODO: When there is no update time, use the creation time or null to differ when there are no updates ?
	/*
	 * @return Unix time in seconds, not milliseconds
	 */
	public Long getUpdateTime();
		
}
