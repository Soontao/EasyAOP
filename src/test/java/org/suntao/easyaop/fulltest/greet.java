package org.suntao.easyaop.fulltest;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodProxy;

import org.suntao.easyaop.aspect.Aspect;
import org.suntao.easyaop.aspect.AspectMessage;

public class greet extends Aspect {

	@Override
	public void addChips() {

	}

	@Override
	public void before(Object obj, Method method, Object[] args,
			MethodProxy proxy, AspectMessage message) {
		super.before(obj, method, args, proxy, message);
		System.out.println("Before method " + method.getName());
	}

	@Override
	public void after(Object obj, Method method, Object[] args,
			MethodProxy proxy, AspectMessage message) {
		super.after(obj, method, args, proxy, message);
		System.out.println("After method " + method.getName());
	}

}
