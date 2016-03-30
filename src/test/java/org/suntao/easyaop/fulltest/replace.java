package org.suntao.easyaop.fulltest;

import java.lang.reflect.Method;
import java.util.Date;

import net.sf.cglib.proxy.MethodProxy;

import org.suntao.easyaop.aspect.Aspect;
import org.suntao.easyaop.aspect.AspectMessage;

public class replace extends Aspect {

	@Override
	public void addChips() {

	}

	@Override
	public void before(Object obj, Method method, Object[] args,
			MethodProxy proxy, AspectMessage message) {
		message.sendMessage(new Date());
	}

	@Override
	public Object doSlicedMethod(Object obj, Method method, Object[] args,
			MethodProxy proxy, AspectMessage message) {
		for (Object o : args) {
			System.out.println(o);
		}
		return new Date();

	}

	@Override
	public void after(Object obj, Method method, Object[] args,
			MethodProxy proxy, AspectMessage message) {
		Date mDate = (Date) message.getMessage();
		System.out.println(mDate.getTime() - new Date().getTime());

	}
}
