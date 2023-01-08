package utils;

import java.lang.reflect.Proxy;

public class DynamicProxy {

	@SuppressWarnings("unchecked")
	public static <T> T withLogging(T target, Class<T> itf) {
		
		T proxy = (T) Proxy.newProxyInstance(itf.getClassLoader(), new Class<?>[] { itf }, new LoggingHandler(target));
		
		return proxy;
	
	}
	
}
