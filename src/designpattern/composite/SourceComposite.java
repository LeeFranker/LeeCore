package designpattern.composite;

import java.util.ArrayList;
import java.util.List;

public class SourceComposite {

	private String sourceName;
	private List<Source> list = new ArrayList<Source>();

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}


	public List<Source> getList() {
		return list;
	}

	public void setList(List<Source> list) {
		this.list = list;
	}

	public void add(Source source){
		list.add(source);
	}
	
	public void remove(Source source){
		list.remove(source);
	}

}
