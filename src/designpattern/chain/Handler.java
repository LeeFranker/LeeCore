package designpattern.chain;

public class Handler extends AbsHandler implements IfaceHandler{

	
	private String name;
	
	public Handler(String name){
		this.name=name;
	}
	
	@Override
	public void operator() {
	
		System.out.println("name="+name);
		
		if(getHandler()!=null)
		getHandler().operator();
	}

}
