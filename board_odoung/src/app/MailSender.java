package app;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.trust", "smtp.gmail.com");

		//메일발송코드
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("inadang77@gmail.com", "bryjlfgkmlctelke");
			}
		});
		
		String receiver = "inadang77@gmail.com";
		String title = "제목";
		String content = "<h2 style='color:green'> 해위해위 </h2>";
		
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("inadang77@gmail.com", "관리자", "utf-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(title);
			message.setContent(content, "text/html;charset=utf-8");
			
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
