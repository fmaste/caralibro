package caralibro.model;

// Models a Facebook Application.
// To create one you need to have a Facebook account. 
// Log in and go to Facebook's Developer Application, www.facebook.com/developers
// Click "Allow" to let the Developer Application access your profile.
// Click "Set Up New Application" to create yours.
// To use your Application with Caralibro you need the "API Key", "Application Secret" and "Application ID".
// Now go to "Edit Settings" on your Application page.
// On the "Advanced" tab, Set your application as being of type "Desktop" and disable the "Sandbox Mode".
public class Application {
	private Long id = null;
	private String key = null;
	private String secret = null;
	private Boolean desktop = null;

	public Application() {
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

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	public Boolean isDesktop() {
		return desktop;
	}
	
	public void setDesktop(Boolean desktop) {
		this.desktop = desktop;
	}
	
}
