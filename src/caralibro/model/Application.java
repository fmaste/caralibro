package caralibro.model;

public class Application {
	private Long id;
	private String key;
	private String secret;
	
	public Application(Long id, String key, String secret) {
		this.id = id;
		this.key = key;
		this.secret = secret;
	}
	
	public Long getId() {
		return id;
	}

	public String getKey() {
		return key;
	}

	public String getSecret() {
		return secret;
	}
	
}
