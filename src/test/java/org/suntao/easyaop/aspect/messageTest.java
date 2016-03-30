package org.suntao.easyaop.aspect;

import org.junit.Before;
import org.junit.Test;

public class messageTest {
	public AspectMessage message;

	@Before
	public void setUp() throws Exception {
		message = new AspectMessage();
	}

	@Test
	public void testMessage() {
		message.sendMessage("hi");
		message.sendMessage("haha");
		message.sendMessage("nihao");
		System.out.println(message.getMessage());
		System.out.println(message.getMessage());
		System.out.println(message.getMessage());
		System.out.println(message.getAndDeleteMessage());
		System.out.println(message.getAndDeleteMessage());
		System.out.println(message.getAndDeleteMessage());
		System.out.println(message.getAndDeleteMessage());
	}

}
