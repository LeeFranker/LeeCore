package designpattern.mediator;

import designpattern.mediator.AbsMediator;

//同事
public abstract class AbsColleague {

	// 中介者
	private AbsMediator mediator;

	public AbsMediator getMediator() {
		return mediator;
	}

	public AbsColleague(AbsMediator m) {
		mediator = m;
		mediator.addColleague(this);
	}

	// 消息
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// 发送消息
	public abstract void sendMsg();

	// 收到消息
	public abstract void getMsg(String msg);

	// 发送消息
	public void sendMsg(String msg) {
		this.message = msg;
		mediator.action(this);
	}

}
