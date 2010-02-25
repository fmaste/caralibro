package caralibro.model;

public class Page {
	private Long id;
	private String name;
	
	public Page(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return "Page " + name + " " + id;
	}
}
