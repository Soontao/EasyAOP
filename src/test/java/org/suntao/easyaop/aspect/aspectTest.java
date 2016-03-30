package org.suntao.easyaop.aspect;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.MethodProxy;

import org.junit.Before;
import org.junit.Test;

public class aspectTest {
	Aspect aspect;

	@Before
	public void setUp() throws Exception {
		aspect = new Aspect() {
			@Override
			public Object doSlicedMethod(Object obj, Method method,
					Object[] args, MethodProxy proxy, AspectMessage message) {
				return null;
			}

			@Override
			public void before(Object obj, Method method, Object[] args,
					MethodProxy proxy, AspectMessage message) {
			}

			@Override
			public void after(Object obj, Method method, Object[] args,
					MethodProxy proxy, AspectMessage message) {
			}

			@Override
			public void addChips() {
				addChip(new AspectChip(-15) {

					@Override
					Object run(Object obj, Method method, Object[] args,
							MethodProxy proxy, AspectMessage message) {
						message.sendMessage("hi");
						return null;
					}
				});
				addChip(new AspectChip(13) {

					@Override
					Object run(Object obj, Method method, Object[] args,
							MethodProxy proxy, AspectMessage message) {
						System.out.println(message.getMessage());
						return null;
					}
				});
				addChip(new AspectChip(-3) {

					@Override
					Object run(Object obj, Method method, Object[] args,
							MethodProxy proxy, AspectMessage message) {
						return null;
					}
				});

			}
		};
	}

	@Test
	public void sequenceTest() {

		for (AspectChip c : aspect.getChips()) {
			System.out.println(c.order);
		}

	}

	@Test
	public void messageTest() {
		aspect.aspectRun(null, null, null, null, null);

	}
}
