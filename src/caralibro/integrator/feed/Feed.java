package caralibro.integrator.feed;

import java.util.Collection;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public interface Feed {
	
	public String getId();

	public String getAuthorId();

	public String getText();

	public Collection<String> getPhotoUrls();

	public Collection<String> getVideoUrls();
	
	public Collection<String> getLinkUrls();

	public String getPermaLink();
	
	/*
	 * @return Unix time in seconds, not milliseconds
	 */
	public Long getCreationTime();
	
	/*
	 * @return Unix time in seconds, not milliseconds
	 */
	public Long getUpdateTime();
		
}
