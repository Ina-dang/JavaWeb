package utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import domain.Member;

public class Util {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//클래스는 생성자가 없음  (new 안됨)
		//클래스 리터럴 사용
		Class<Member> c = Member.class;
		System.out.println(c);
		
		//새 인스턴스를 만들거 c Class타입으로 만들거
		//>>기본생성자로 만들겠다는 뜻 >> InstantationException오류뜸 그래서 throws
		Member member= c.newInstance(); 
		Method[] methods = c.getMethods();

		for(Method method : methods) {
			String name = method.getName();
			if (name.startsWith("set")) {
				System.out.println(method.getName());
				System.out.println((char)(name.charAt(3) + 32));	
				System.out.println((char)(name.charAt(3) + 32) + name.substring(4)); // >> 나중에 리퀘스터 파라미터 처리하기한 용도
			
			//일단 세터 값 다 부여할거
//			method.invoke(member, "1234"); //만약 데이터타입 다른게 있으면 또 다른형태 처리해줘야함
			
			}
		}
		
		for(Method method : methods) {
			String name = method.getName();
			
				//메서드를 통한 인보케이션 처리 (간접적 호출)
			if (name.startsWith("get")) {

				System.out.println(name);
//				member.getAddrDetail() >> 이런건 직접적 호출
//				method.invoke(member, new Object[] {}); //>>아무것도없는 인자겟터 
				System.out.println(method.invoke(member, new Object[] {}));  
			}
			
//			System.out.println(method);
		}
	}
	
	//
	public static <T> T getParam (HttpServletRequest req, Class<T> c){
		T t = null;
		try {
			t = c.newInstance();
			Method[] methods = c.getMethods();
			for(Method method : methods) {
				String name = method.getName();
				//조건식 많이 생길검
				if (name.startsWith("set")) {
					//해당셋터메서드 파라미터타입 가져온 후에 그 인자에 맞춰서 INTERGER.PASERINT 같은 처리 해줘야함
					//지금은 STRING 밖에 안됨
					method.invoke(t, req.getParameter((char)(name.charAt(3) + 32) + name.substring(4)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return t; //>> 멤버로 가져와서 멤버로 처리 (보드로 가져오면 보드로 처리)
	}
}
