package designpattern.memento;

public class Test {
	public void test(){
		Source source=new Source();
		source.setName("A");
		Memento to=source.createMemento();
		
		Storage storate=new Storage();
		storate.setMemento(to);
		
		System.out.println("-->"+source.getName());
		
		source.setName("B");
		
		System.out.println("-->"+source.getName());
		
		source.setMemento(storate.getMemento());
		
		
		System.out.println("-->"+source.getName());
	}
}
