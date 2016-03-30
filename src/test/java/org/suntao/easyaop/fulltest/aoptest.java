package org.suntao.easyaop.fulltest;

import java.util.Date;

import org.suntao.easyaop.EasyAOP;
import org.suntao.easyaop.annotation.SliceTheMethod;

/**
 * AOP测试
 * 
 * @author suntao
 *
 */
public class aoptest {
	public static void main(String[] args) {
		HelloKitty h = EasyAOP.getProxy(HelloKitty.class);
		h.sayHello("it can get param");
		System.out.println(h.getTime());
		HelloKitty h2 = EasyAOP.getProxy(HelloKitty.class);
		h2.sayHello("it can create new instance");
		System.out.println(h2.getTime());
		HelloMockey m = EasyAOP.getProxy(HelloMockey.class);
		Date t = m.sayHelloAndGetTime("hello");
		System.out.println(t);

	}
}

class HelloKitty {

	int number = 0;

	@SliceTheMethod(targetAspectClass = greet.class)
	public void sayHello(String str) {
		System.out.println(str);
		System.out.println(number);
		number++;
		return;
	}

	@SliceTheMethod(targetAspectClass = greet.class)
	public String getTime() {
		System.out.println("no thing happend");
		String date = new Date().toGMTString() + "\t it can return obejct";
		System.out.println(number);
		return date;
	}
}

interface HelloMockey {
	@SliceTheMethod(targetAspectClass = replace.class)
	public Date sayHelloAndGetTime(String str);

}