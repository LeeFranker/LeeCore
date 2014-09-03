package designpattern.iterator;

public interface IfaceCollection {
    
    public IfaceIterator iterator();  
      
    public Object get(int i);  
      
    public int size();  
}
