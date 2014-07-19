package database.bean;

/**
 * 临时
 * 
 * @author LeeFranker
 * 
 */
public class Bean implements Entity {

	private String id;
	private String name;

	@Override
	public String getUniqueID() {
		return id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
