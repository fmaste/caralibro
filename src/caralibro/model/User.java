package caralibro.model;

public class User {
	// The user ID is a 64-bit int datatype. 
	// If you're storing it in a MySQL database, use the BIGINT unsigned datatype. 
	// For Facebook Connect for iPhone, format the user ID as a long long.
	private Long id;
	
	public User(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
}
