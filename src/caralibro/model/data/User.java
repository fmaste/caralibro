package caralibro.model.data;

/* 
 * A Facebook user can be a stream source or a stream author.
 * 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */ 
public class User implements Source, Author {
	private Long id = null;
	private String name = null;
	private String gender = null;
	
	public User() {
	}
	
	@Override
	public Long getId() {
		return this.id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "User [gender=" + gender + ", id=" + id + ", name=" + name + "]";
	}
}
