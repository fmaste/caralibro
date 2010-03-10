package caralibro.integrator;

public class FBInitData extends InitData {
	
	public static final int FAN_PAGE = 0;
	public static final int GROUP = 1;
	
	// app info
	private Long id;
	private String key;
	private String secrect;
	private boolean isDesktop;	
	// session info
	private String sessionKey;
	private String sessionSecret;	
	// User info
	private Long userId;	
	// More..
	private Long expirationTime;
	private String sourceName;
	private int sourceType;
	
	public FBInitData(Long id, String key, String secret, boolean isDesktop, String sessionKey, 
			String sessionSecret, Long userId, String sourceName, int sourceType) {
		this.id = id;
		this.key = key;
		this.secrect = secret;
		this.isDesktop = isDesktop;
		
		this.sessionKey = sessionKey;
		this.sessionSecret = sessionSecret;
		
		this.userId = userId;
		
		this.expirationTime = 0L;
		this.sourceName = sourceName;
		this.sourceType = sourceType;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getSecrect() {
		return secrect;
	}
	public void setSecrect(String secrect) {
		this.secrect = secrect;
	}
	public boolean isDesktop() {
		return isDesktop;
	}
	public void setDesktop(boolean isDesktop) {
		this.isDesktop = isDesktop;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getSessionSecret() {
		return sessionSecret;
	}

	public void setSessionSecret(String sessionSecret) {
		this.sessionSecret = sessionSecret;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Long expirationTime) {
		this.expirationTime = expirationTime;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public int getSourceType() {
		return sourceType;
	}

	public void setSourceType(int sourceType) {
		this.sourceType = sourceType;
	}
	
}
