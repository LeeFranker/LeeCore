package designpattern.memento;

public class Source {
	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Memento createMemento() {
		return new Memento(name);
	}

	public void setMemento(Memento memento) {
		this.name=memento.getName();
	}
	

	
}
