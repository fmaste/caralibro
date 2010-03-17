package caralibro.model.data;

/* 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public interface Party {
	
	// The user ID is a 64-bit int datatype. 
	// If you're storing it in a MySQL database, use the BIGINT unsigned datatype. 
	// For Facebook Connect for iPhone, format the user ID as a long long.
	public Long getId();
	
	public void setId(Long id);
	
	public String getName();

	public void setName(String name);
	
}
