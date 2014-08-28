package designpattern.mediator;


//中介者
public interface AbsMediator {

	public abstract void action(AbsColleague colleague);
	
	public abstract void addColleague(AbsColleague colleague);
	
	public abstract void removeColleague(AbsColleague colleague);
	
	public abstract void clean();
	
}
