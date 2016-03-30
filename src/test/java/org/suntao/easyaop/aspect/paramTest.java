package org.suntao.easyaop.aspect;

public class paramTest {

	public static void main(String[] args) throws NoSuchFieldException,
			SecurityException {
		String h = "hello world";
		paramTest p = new paramTest();
		p.say(h);

		p.say(h);
	}

	public void say(String str) {
		System.out.println(str);
		str = "";
		return;
	}
}
