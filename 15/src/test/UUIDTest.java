package test;

import java.util.UUID;

public class UUIDTest {
	public static void main(String[] args) {
		
		//	Universal Unique ID
		UUID uuid = UUID.randomUUID(); //고유한 아이디
		System.out.println(uuid);
		System.out.println(uuid);
		
		uuid = UUID.randomUUID(); //고유한 아이디
		System.out.println(uuid);
	}
}
