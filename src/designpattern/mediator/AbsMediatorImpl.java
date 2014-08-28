package designpattern.mediator;

import java.util.Vector;

public class AbsMediatorImpl implements AbsMediator {

	// 线程安全
	protected Vector<AbsColleague> vector = new Vector<AbsColleague>();

	@Override
	public void action(AbsColleague colleague) {
		String msg = colleague.getMessage();

		for (int i = 0; i < vector.size(); i++) {
			if (vector.get(i).equals(colleague)) {
				colleague.sendMsg();
				break;
			}
		}
		for (int i = 0; i < vector.size(); i++) {
			if (vector.get(i).equals(colleague))
				continue;
			vector.get(i).getMsg(msg);
		}
	}

	@Override
	public void addColleague(AbsColleague colleague) {
		if (colleague != null)
			vector.add(colleague);
	}

	@Override
	public void clean() {
		vector.clear();
	}

	@Override
	public void removeColleague(AbsColleague colleague) {
		if (colleague != null)
			vector.remove(colleague);

	}

}
