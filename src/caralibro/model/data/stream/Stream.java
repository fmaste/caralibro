package caralibro.model.data.stream;

/*
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */
public interface Stream {

	// Posts and comments have a string id.
	public String getId();
	
	public void setId(String id);
	
	// public Long getAuthorId();
	
	// public void setAuthorId(Long id);
	
	public String getText();
	
	public void setText(String text);
	
}
