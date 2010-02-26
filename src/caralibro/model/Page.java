package caralibro.model;

public class Page {
	private Long id = null;
	private String name = null;
	
	public Page() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "Page " + name + " " + id;
	}
	
}
