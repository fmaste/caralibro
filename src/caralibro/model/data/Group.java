package caralibro.model.data;


/*
 * A Facebook group is a stream source but not an author. The group can't post, only member users that can be admins or not.
 * 
 * @author		Federico Pascual Mastellone (fmaste@gmail.com)
 * @author		Simon Aberg Cobo (sima.cobo@gmail.com)
 */
public class Group implements Source {
	private Long id = null;
	private String name = null;
	
	public Group() {
	}
	
	@Override
	public Long getId() {
		return id;
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
