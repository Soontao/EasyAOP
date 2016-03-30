package org.suntao.easyaop.aspect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.sf.cglib.proxy.MethodProxy;

/**
 * 切面
 * 
 * @author suntao
 *
 */
public abstract class Aspect implements AspectInterface {
	/**
	 * before 方法 的序号
	 */
	public static int BEFOREORDER = -10;
	/**
	 * after 方法 的序号
	 */
	public static int AFTERORDER = 10;
	/**
	 * 被代理 方法 的序号
	 */
	public static int ORIGINALORDER = 0;
	/**
	 * 切面的Chip
	 */
	private List<AspectChip> chips;

	public Aspect() {
		chips = new ArrayList<AspectChip>();
		generateDefaultMethodChip();
		addChips();
		sortChipList();

	}

	private Boolean checkOrderExist(AspectChip chip) throws Exception {
		Boolean result = true;
		for (AspectChip c : chips) {
			if (c.order == chip.order) {
				result = false;
				break;
			}
		}
		return result;
	}

	@Override
	public Boolean addChip(AspectChip chip) {

		Boolean result = false;
		try {
			checkOrderExist(chip);
			chips.add(chip);
			sortChipList();
			result = true;
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * The method used to load your own chips into this aspect <br>
	 * Although can override before or after method to use basic function<br>
	 * 这个方法用于加载用户自己编写的AspectChip<br>
	 * 也可以通过覆盖before和after方法,实现一些简单的功能
	 * <p>
	 * The most important thing you have to know is:<br>
	 * The order numer of sliced method(original method) is ZERO<br>
	 * before() is -10 <br>
	 * after() is +10 <br>
	 * Chips will be called depend on 'order number of chips',from small to
	 * large <br>
	 * <p>
	 * Aspect中包含着多个AspectChip<br>
	 * 它们将会按照AspectChip的Order从小到大的顺序执行<br>
	 * before() doSlicedMethod() 和after()的order分别是-10 0 10
	 */
	public abstract void addChips();

	/**
	 * Will be called after the sliced method
	 * 
	 * @param obj
	 * @param method
	 * @param args
	 * @param proxy
	 * @param message
	 */
	public void after(Object obj, Method method, Object[] args,
			MethodProxy proxy, AspectMessage message) {

	}

	@Override
	public Object aspectRun(Object obj, Method method, Object[] args,
			MethodProxy proxy, AspectMessage message) {
		Object result = null;
		if (message == null)
			message = new AspectMessage();
		for (AspectChip chip : chips) {
			try {
				if (chip.order == 0) {
					result = chip.run(obj, method, args, proxy, message);
				} else {
					chip.run(obj, method, args, proxy, message);
				}
			} catch (Exception exception) {
				exceptionProcess(obj, method, args, proxy, message, exception);
			}
		}
		return result;
	}

	/**
	 * Will be called before the sliced method
	 * 
	 * @param obj
	 * @param method
	 * @param args
	 * @param proxy
	 * @param message
	 */
	public void before(Object obj, Method method, Object[] args,
			MethodProxy proxy, AspectMessage message) {

	}

	@Override
	public Object doSlicedMethod(Object obj, Method method, Object[] args,
			MethodProxy proxy, AspectMessage message) {
		Object result = null;
		try {
			result = proxy.invokeSuper(obj, args);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void exceptionProcess(Object obj, Method method, Object[] args,
			MethodProxy proxy, AspectMessage message, Exception exception) {

		exception.printStackTrace();

	}

	/**
	 * Use to generate <br>
	 * before() doSlicedMethod() after() Three chips<br>
	 * Will be giving order number is -10 0 10
	 * <p>
	 * 将默认的三个方法转化为AspectChip<br>
	 * before doslicedmethod 和 after方法<br>
	 * 将分别给与 -10 0 10的执行顺序
	 * 
	 * @return
	 */
	private void generateDefaultMethodChip() {
		addChip(new AspectChip(BEFOREORDER) {
			@Override
			Object run(Object obj, Method method, Object[] args,
					MethodProxy proxy, AspectMessage message) {
				before(obj, method, args, proxy, message);
				return null;
			}
		});
		addChip(new AspectChip(ORIGINALORDER) {

			@Override
			Object run(Object obj, Method method, Object[] args,
					MethodProxy proxy, AspectMessage message) {
				return doSlicedMethod(obj, method, args, proxy, message);
			}
		});
		addChip(new AspectChip(AFTERORDER) {

			@Override
			Object run(Object obj, Method method, Object[] args,
					MethodProxy proxy, AspectMessage message) {
				after(obj, method, args, proxy, message);
				return null;
			}
		});

		return;

	}

	private void sortChipList() {
		Comparator<AspectChip> comparator = new AspectChipComparator();
		Collections.sort(chips, comparator);
	}

	public List<AspectChip> getChips() {
		return chips;
	}

	public void setChips(List<AspectChip> chips) {
		this.chips = chips;
	}

}
