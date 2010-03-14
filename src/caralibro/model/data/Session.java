package caralibro.model.data;

public class Session {
	private String key = null;
	private String secret = null;
	private User user = null;
	private Long expirationTime = null; // Unix time in seconds, not milliseconds
	
	public Session() {
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/*
	 * @return Unix time in seconds, not milliseconds
	 */
	public Long getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Long expirationTime) {
		this.expirationTime = expirationTime;
	}
	
}
