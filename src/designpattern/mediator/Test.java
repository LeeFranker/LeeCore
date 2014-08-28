package designpattern.mediator;

public class Test {

	public void test(){
		AbsMediator mediator=new AbsMediatorImpl();
		AbsColleague a=new ColleagueA(mediator);
		AbsColleague b=new ColleagueB(mediator);
		a.sendMsg(ColleagueA.FLAG);
		b.sendMsg(ColleagueB.FLAG);
	}
}
