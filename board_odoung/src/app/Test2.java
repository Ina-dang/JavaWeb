package app;

import java.util.ArrayList;
import java.util.List;

public class Test2 {
	public static void main(String[] args) {
		// 가, 나, 다, 라
		// 순서대로 조회
		
		List<String> list = new ArrayList<String>();
		list.add(new String("가"));
		list.add("나");
		list.add("다");
		list.add("라");
		
		System.out.println(list);
		
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.get(2));
		System.out.println(list.get(3));
//		System.out.println(list.get(4));
		
		for(int i = 0 ; i < 4 ; i++) { // 0, 1, 2, 3
			System.out.println(list.get(i)); // 가, 나, 다, 라
		}
		
		System.out.println("============================");
		list.remove(2);
		for(int i = 0 ; i < list.size() ; i++) { // 0, 1, 2, 3
			System.out.println(list.get(i));
		}
		
		System.out.println("============================");
		list.remove("가");
		
		for(String str : list) {
			System.out.println(str);
		}
	}
}
