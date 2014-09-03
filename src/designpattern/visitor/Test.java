package designpattern.visitor;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public void test(){
		List<Element> list=new ArrayList<Element>();
		
		list.add(new ElementA());
		list.add(new ElementB());
		
		for(int i=0;i<list.size();i++){
			list.get(i).accept(new Visitor());
		}
	}
}
