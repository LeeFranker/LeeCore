package designpattern.adapter;

public class Adapter extends Person implements Job {

	@Override
	public void speakFrench() {

		System.out.println("speakfrench");

	}

}
