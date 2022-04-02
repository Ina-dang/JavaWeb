package utils;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

public class Reflection {
	public static <T> T getParam (HttpServletRequest req, Class<T> c) {
		T t = null;
		try {
			t = c.newInstance();
			Method[] methods = c.getMethods();
			
			//메소드 이름 가져오기
			for(Method method : methods) {
				String name = method.getName();
				
				//변수 이름 가져오기
				if (name.startsWith("set")) {
					method.invoke(t, req.getParameter((char)(name.charAt(3) + 32) + name.substring(4)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
}
