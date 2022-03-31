package app;

import java.net.URLDecoder;
import java.util.Random;

import org.mindrot.jbcrypt.BCrypt;

public class SaltTest {
	public static void main(String[] args) {
		String pw1 = BCrypt.hashpw("12345", BCrypt.gensalt(8));
		System.out.println(pw1);
		String pw2 = BCrypt.hashpw("가", BCrypt.gensalt());
		System.out.println(pw2);
		
		System.out.println(BCrypt.checkpw("12345", pw1));
		System.out.println(BCrypt.checkpw("12345", pw2));
		
		Random random = new Random();
		int result = random.nextInt(100000000);
		System.out.println(result);
		

	}
}
