package org.suntao.easyaop.aspect;

import java.util.Stack;

/**
 * 切面消息
 * <p>
 * 通常情况下,可以使用getMessage和sendMessage在各个Chip之间传递消息<br>
 * 内部是使用stack实现的
 * 
 * @author suntao
 *
 */
public class AspectMessage {
	private Stack<Object> messageStack;

	public AspectMessage() {
		messageStack = new Stack<Object>();
	}

	/**
	 * 获取并从消息栈中删除(最新的)消息
	 * 
	 * @return
	 */
	public Object getAndDeleteMessage() {
		Object result = null;
		if (!messageStack.empty())
			result = messageStack.pop();
		return result;
	}

	/**
	 * 获取(最近的)一条消息
	 * 
	 * @return
	 */
	public Object getMessage() {
		Object result = null;
		if (!messageStack.empty())
			result = messageStack.peek();
		return result;
	}

	/**
	 * 增加一条消息(到栈顶)
	 * 
	 * @param message
	 */
	public void sendMessage(Object message) {
		messageStack.push(message);
		return;

	}

}
