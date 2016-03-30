package org.suntao.easyaop.aspect;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodProxy;

public interface AspectInterface {

	/**
	 * All aspectChips will be called<br>
	 * order by its 'order number'
	 * <p>
	 * 按照从小到大的order顺序<br>
	 * 运行所有Chips
	 * 
	 * @param obj
	 *            对象
	 * @param method
	 *            原方法
	 * @param args
	 *            参数
	 * @param proxy
	 *            代理
	 * @param message
	 *            消息
	 * @return 原方法的返回值
	 */
	Object aspectRun(Object obj, Method method, Object[] args,
			MethodProxy proxy, AspectMessage message);

	/**
	 * This is the method be sliced
	 * <p>
	 * 这就是被切面处理的原方法<br>
	 * 如果覆盖此方法<br>
	 * 原方法将可能不会被执行<br>
	 * 当然你也可以调用super方法执行
	 * 
	 * @param obj
	 * @param method
	 * @param args
	 * @param proxy
	 * @param message
	 * @return
	 */
	Object doSlicedMethod(Object obj, Method method, Object[] args,
			MethodProxy proxy, AspectMessage message);

	/**
	 * Exception Process
	 * <p>
	 * 异常处理<br>
	 * 如果AspectChips之中发生Exception<br>
	 * 将首先被这里捕获<br>
	 * 默认情况下,应该是调用printstacktrace,并不抛出
	 * 
	 * @param obj
	 * @param method
	 * @param args
	 * @param proxy
	 * @param message
	 * @param exception
	 */
	void exceptionProcess(Object obj, Method method, Object[] args,
			MethodProxy proxy, AspectMessage message, Exception exception);

	/**
	 * 添加一个AspectChip
	 * 
	 * @param chip
	 * @return
	 */
	Boolean addChip(AspectChip chip);

}
