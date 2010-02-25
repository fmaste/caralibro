package caralibro.model;

public class Session {
	private String key;
	private String secret;
	private User user;
	private Long expirationTime; // In Unix time
	
	public Session(String key, String secret, User user, Long expirationTime) {
		this.key = key;
		this.secret = secret;
		this.user = user;
		this.expirationTime = expirationTime;
	}
	
	public String getKey() {
		return key;
	}

	public String getSecret() {
		return secret;
	}

	public User getUser() {
		return user;
	}
	
	public Long getExpirationTime() {
		return this.expirationTime;
	}

}
