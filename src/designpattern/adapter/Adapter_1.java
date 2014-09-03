package designpattern.adapter;

public class Adapter_1 implements Job {

	private Person person;

	public Adapter_1(Person person) {
		this.person = person;
	}

	@Override
	public void speakEnglish() {
		person.speakEnglish();

	}

	@Override
	public void speakChinese() {
		person.speakChinese();
	}

	@Override
	public void speakFrench() {

	}

}
