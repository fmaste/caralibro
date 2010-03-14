package caralibro.model.data;

public class User implements Party {
	private Long id = null;
	private String name = null;
	
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
	
}
