package Controller_member;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import domain.Member;
import service.MemberService;
//이메일 발송 (난수인증코드)
@WebServlet("/member/memberAuth")
public class MemberAuth extends HttpServlet{
	private MemberService memberService = MemberService.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String id = req.getParameter("id");
		
		//JBCrypt
		Member member = memberService.idFind(id);
		member.setAuthToken(String.format("%08d", new Random().nextInt(100_000_000)));
		
		//TOKEN PROCESS
		memberService.updateAuthToken(member);
		String host = "http://localhost:8080" + req.getContextPath() + "/member/procAuth";
		String queryString = "id=" + member.getId() + "&authToken=" + 
				URLEncoder.encode(BCrypt.hashpw(member.getAuthToken(), BCrypt.gensalt()), "utf-8") ;
		
		String content = String.format("    <table width='600' style='margin:0 auto'>\r\n" + 
				"        <tr>\r\n" + 
				"        <td align=\"center\"><h1 s>이 이메일은 본인 확인을 위한 이메일 입니다.</h1></td>\r\n" + 
				"        </tr>\r\n" + 
				"        <tr>\r\n" + 
				"        <td><p style='color: #555555';>이 메일의 소유자가 %s님 본인일 경우 아래의 링크를 클릭하세요.</p></td>\r\n" + 
				"        </tr>\r\n" + 
				"        <tr>\r\n" + 
				"            <td align=\"center\">\r\n" + 
				"                <div style='display: inline-block; padding: 8px; background-color: #222222;'>\r\n" + 
				"                <a href='%s' style='text-decoration: none; color: #ddd;'>인증하기</a>\r\n" + 
				"            </td>\r\n" + 
				"        </div>\r\n" + 
				"        </tr>\r\n" + 
				"    </table>    ", member.getId(), host + "?" + queryString);
		
		
		//java.util.UnknownFormatConversionException: Conversion = ';' 오류뜸
		
//		String content = String.format("<body class=\"clean-body u_body\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #e7e7e7;color: #000000\">\r\n" + 
//				"  <!--[if IE]><div class=\"ie-container\"><![endif]-->\r\n" + 
//				"  <!--[if mso]><div class=\"mso-container\"><![endif]-->\r\n" + 
//				"  <table style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #e7e7e7;width:100%\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
//				"  <tbody>\r\n" + 
//				"  <tr style=\"vertical-align: top\">\r\n" + 
//				"    <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\r\n" + 
//				"    <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #e7e7e7;\"><![endif]-->\r\n" + 
//				"    \r\n" + 
//				"\r\n" + 
//				"<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\r\n" + 
//				"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\r\n" + 
//				"    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\r\n" + 
//				"      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\r\n" + 
//				"      \r\n" + 
//				"<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\r\n" + 
//				"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\r\n" + 
//				"  <div style=\"width: 100% !important;\">\r\n" + 
//				"  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\r\n" + 
//				"  \r\n" + 
//				"<table id=\"u_content_image_1\" style=\"font-family:'Open Sans',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
//				"  <tbody>\r\n" + 
//				"    <tr>\r\n" + 
//				"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:25px 10px 15px;font-family:'Open Sans',sans-serif;\" align=\"left\">\r\n" + 
//				"        \r\n" + 
//				"<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + 
//				"  <tr>\r\n" + 
//				"    <td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\r\n" + 
//				"      \r\n" + 
//				"      <img align=\"center\" border=\"0\" src=\"images/image-1.png\" alt=\"Logo\" title=\"Logo\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 100%;max-width: 200px;\" width=\"200\" class=\"v-src-width v-src-max-width\"/>\r\n" + 
//				"      \r\n" + 
//				"    </td>\r\n" + 
//				"  </tr>\r\n" + 
//				"</table>\r\n" + 
//				"\r\n" + 
//				"      </td>\r\n" + 
//				"    </tr>\r\n" + 
//				"  </tbody>\r\n" + 
//				"</table>\r\n" + 
//				"\r\n" + 
//				"<table style=\"font-family:'Open Sans',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
//				"  <tbody>\r\n" + 
//				"    <tr>\r\n" + 
//				"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Open Sans',sans-serif;\" align=\"left\">\r\n" + 
//				"        \r\n" + 
//				"  <table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 1px solid #236fa1;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\r\n" + 
//				"    <tbody>\r\n" + 
//				"      <tr style=\"vertical-align: top\">\r\n" + 
//				"        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\r\n" + 
//				"          <span>&#160;</span>\r\n" + 
//				"        </td>\r\n" + 
//				"      </tr>\r\n" + 
//				"    </tbody>\r\n" + 
//				"  </table>\r\n" + 
//				"\r\n" + 
//				"      </td>\r\n" + 
//				"    </tr>\r\n" + 
//				"  </tbody>\r\n" + 
//				"</table>\r\n" + 
//				"\r\n" + 
//				"<table style=\"font-family:'Open Sans',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
//				"  <tbody>\r\n" + 
//				"    <tr>\r\n" + 
//				"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Open Sans',sans-serif;\" align=\"left\">\r\n" + 
//				"        \r\n" + 
//				"  <h1 style=\"margin: 0px; color: #000000; line-height: 140%; text-align: center; word-wrap: break-word; font-weight: normal; font-family: 'Raleway',sans-serif; font-size: 31px;\">\r\n" + 
//				"    <strong>메일인증 안내입니다.</strong>\r\n" + 
//				"  </h1>\r\n" + 
//				"\r\n" + 
//				"      </td>\r\n" + 
//				"    </tr>\r\n" + 
//				"  </tbody>\r\n" + 
//				"</table>\r\n" + 
//				"\r\n" + 
//				"  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\r\n" + 
//				"  </div>\r\n" + 
//				"</div>\r\n" + 
//				"<!--[if (mso)|(IE)]></td><![endif]-->\r\n" + 
//				"      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\r\n" + 
//				"    </div>\r\n" + 
//				"  </div>\r\n" + 
//				"</div>\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\r\n" + 
//				"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\r\n" + 
//				"    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\r\n" + 
//				"      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\r\n" + 
//				"      \r\n" + 
//				"<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\r\n" + 
//				"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\r\n" + 
//				"  <div style=\"width: 100% !important;\">\r\n" + 
//				"  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\r\n" + 
//				"  \r\n" + 
//				"<table style=\"font-family:'Open Sans',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
//				"  <tbody>\r\n" + 
//				"    <tr>\r\n" + 
//				"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:30px 10px 30px 20px;font-family:'Open Sans',sans-serif;\" align=\"left\">\r\n" + 
//				"        \r\n" + 
//				"  <div style=\"color: #333333; line-height: 130%; text-align: left; word-wrap: break-word;\">\r\n" + 
//				"    <p style=\"line-height: 130%; font-size: 14px;\"><span style=\"font-size: 16px; line-height: 20.8px;\"><strong>안녕하세요.</strong></span></p>\r\n" + 
//				"<p style=\"line-height: 130%; font-size: 14px;\"><span style=\"font-size: 16px; line-height: 20.8px;\"><strong>메이플 스토리를 이용해 주셔서 진심으로 감사드립니다.</strong></span></p>\r\n" + 
//				"<p style=\"line-height: 130%; font-size: 14px;\"><span style=\"font-size: 16px; line-height: 20.8px;\"><strong>아래 '메일 인증' 버튼을 클릭하여 회원인증을 완료해 주세요.</strong></span></p>\r\n" + 
//				"<p style=\"line-height: 130%; font-size: 14px;\"><span style=\"font-size: 16px; line-height: 20.8px;\"><strong>감사합니다</strong></span></p>\r\n" + 
//				"  </div>\r\n" + 
//				"\r\n" + 
//				"      </td>\r\n" + 
//				"    </tr>\r\n" + 
//				"  </tbody>\r\n" + 
//				"</table>\r\n" + 
//				"\r\n" + 
//				"  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\r\n" + 
//				"  </div>\r\n" + 
//				"</div>\r\n" + 
//				"<!--[if (mso)|(IE)]></td><![endif]-->\r\n" + 
//				"      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\r\n" + 
//				"    </div>\r\n" + 
//				"  </div>\r\n" + 
//				"</div>\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\r\n" + 
//				"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #f7f6f4;\">\r\n" + 
//				"    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\r\n" + 
//				"      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #f7f6f4;\"><![endif]-->\r\n" + 
//				"      \r\n" + 
//				"<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\r\n" + 
//				"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\r\n" + 
//				"  <div style=\"width: 100% !important;\">\r\n" + 
//				"  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\r\n" + 
//				"  \r\n" + 
//				"<table style=\"font-family:'Open Sans',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
//				"  <tbody>\r\n" + 
//				"    <tr>\r\n" + 
//				"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:5px 10px 10px 20px;font-family:'Open Sans',sans-serif;\" align=\"left\">\r\n" + 
//				"        \r\n" + 
//				"  <table height=\"0px\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"22%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 3px solid #3598db;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\r\n" + 
//				"    <tbody>\r\n" + 
//				"      <tr style=\"vertical-align: top\">\r\n" + 
//				"        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\r\n" + 
//				"          <span>&#160;</span>\r\n" + 
//				"        </td>\r\n" + 
//				"      </tr>\r\n" + 
//				"    </tbody>\r\n" + 
//				"  </table>\r\n" + 
//				"\r\n" + 
//				"      </td>\r\n" + 
//				"    </tr>\r\n" + 
//				"  </tbody>\r\n" + 
//				"</table>\r\n" + 
//				"\r\n" + 
//				"<table style=\"font-family:'Open Sans',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
//				"  <tbody>\r\n" + 
//				"    <tr>\r\n" + 
//				"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 10px 20px;font-family:'Open Sans',sans-serif;\" align=\"left\">\r\n" + 
//				"        \r\n" + 
//				"  <div style=\"color: #333333; line-height: 140%; text-align: left; word-wrap: break-word;\">\r\n" + 
//				"    <p style=\"font-size: 14px; line-height: 140%;\"><strong>가입 아이디 : </strong> <span style=\"color: #828080; font-size: 14px; line-height: 19.6px;\">%s</span></p>\r\n" + 
//				"  </div>\r\n" + 
//				"\r\n" + 
//				"      </td>\r\n" + 
//				"    </tr>\r\n" + 
//				"  </tbody>\r\n" + 
//				"</table>\r\n" + 
//				"\r\n" + 
//				"<table style=\"font-family:'Open Sans',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
//				"  <tbody>\r\n" + 
//				"    <tr>\r\n" + 
//				"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:5px 10px 10px 20px;font-family:'Open Sans',sans-serif;\" align=\"left\">\r\n" + 
//				"        \r\n" + 
//				"  <div style=\"color: #333333; line-height: 140%; text-align: left; word-wrap: break-word;\">\r\n" + 
//				"    <p style=\"font-size: 14px; line-height: 140%;\"><strong>가입 일자 : </strong>&nbsp;<span style=\"color: #828080; font-size: 14px; line-height: 19.6px;\">%s</span></p>\r\n" + 
//				"  </div>\r\n" + 
//				"\r\n" + 
//				"      </td>\r\n" + 
//				"    </tr>\r\n" + 
//				"  </tbody>\r\n" + 
//				"</table>\r\n" + 
//				"\r\n" + 
//				"  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\r\n" + 
//				"  </div>\r\n" + 
//				"</div>\r\n" + 
//				"<!--[if (mso)|(IE)]></td><![endif]-->\r\n" + 
//				"      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\r\n" + 
//				"    </div>\r\n" + 
//				"  </div>\r\n" + 
//				"</div>\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\r\n" + 
//				"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\r\n" + 
//				"    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\r\n" + 
//				"      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\r\n" + 
//				"      \r\n" + 
//				"<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\r\n" + 
//				"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\r\n" + 
//				"  <div style=\"width: 100% !important;\">\r\n" + 
//				"  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\r\n" + 
//				"  \r\n" + 
//				"<table id=\"u_content_text_15\" style=\"font-family:'Open Sans',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
//				"  <tbody>\r\n" + 
//				"    <tr>\r\n" + 
//				"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:20px 23px 15px 20px;font-family:'Open Sans',sans-serif;\" align=\"left\">\r\n" + 
//				"        \r\n" + 
//				"  <div style=\"color: #333333; line-height: 150%; text-align: left; word-wrap: break-word;\">\r\n" + 
//				"    <p style=\"font-size: 14px; line-height: 150%;\">위 링크는 10분 동안 인증할 수 있습니다. </p>\r\n" + 
//				"<p style=\"font-size: 14px; line-height: 150%;\">10분이 지난 후에는 회원 수정 화면에서 다시 인증해 주세요.</p>\r\n" + 
//				"  </div>\r\n" + 
//				"\r\n" + 
//				"      </td>\r\n" + 
//				"    </tr>\r\n" + 
//				"  </tbody>\r\n" + 
//				"</table>\r\n" + 
//				"\r\n" + 
//				"<table style=\"font-family:'Open Sans',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
//				"  <tbody>\r\n" + 
//				"    <tr>\r\n" + 
//				"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 30px;font-family:'Open Sans',sans-serif;\" align=\"left\">\r\n" + 
//				"        \r\n" + 
//				"<div align=\"center\">\r\n" + 
//				"  <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing: 0; border-collapse: collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;font-family:'Open Sans',sans-serif;\"><tr><td style=\"font-family:'Open Sans',sans-serif;\" align=\"center\"><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"\" style=\"height:43px; v-text-anchor:middle; width:138px;\" arcsize=\"4.5%\" stroke=\"f\" fillcolor=\"#27dd3e\"><w:anchorlock/><center style=\"color:#FFFFFF;font-family:'Open Sans',sans-serif;\"><![endif]-->\r\n" + 
//				"    <a href='%s' target=\"_blank\" style=\"box-sizing: border-box;display: inline-block;font-family:'Open Sans',sans-serif;text-decoration: none;-webkit-text-size-adjust: none;text-align: center;color: #FFFFFF; background-color: #27dd3e; border-radius: 2px;-webkit-border-radius: 2px; -moz-border-radius: 2px; width:auto; max-width:100%; overflow-wrap: break-word; word-break: break-word; word-wrap:break-word; mso-border-alt: none;\">\r\n" + 
//				"      <span style=\"display:block;padding:12px 35px;line-height:120%;\"><span style=\"font-family: Cabin, sans-serif; font-size: 14px; line-height: 16.8px;\"><strong><span style=\"font-size: 16px; line-height: 19.2px;\">메일 인증</span></strong></span></span>\r\n" + 
//				"    </a>\r\n" + 
//				"  <!--[if mso]></center></v:roundrect></td></tr></table><![endif]-->\r\n" + 
//				"</div>\r\n" + 
//				"\r\n" + 
//				"      </td>\r\n" + 
//				"    </tr>\r\n" + 
//				"  </tbody>\r\n" + 
//				"</table>\r\n" + 
//				"\r\n" + 
//				"<table style=\"font-family:'Open Sans',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
//				"  <tbody>\r\n" + 
//				"    <tr>\r\n" + 
//				"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Open Sans',sans-serif;\" align=\"left\">\r\n" + 
//				"        \r\n" + 
//				"  <table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 2px solid #939391;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\r\n" + 
//				"    <tbody>\r\n" + 
//				"      <tr style=\"vertical-align: top\">\r\n" + 
//				"        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\r\n" + 
//				"          <span>&#160;</span>\r\n" + 
//				"        </td>\r\n" + 
//				"      </tr>\r\n" + 
//				"    </tbody>\r\n" + 
//				"  </table>\r\n" + 
//				"\r\n" + 
//				"      </td>\r\n" + 
//				"    </tr>\r\n" + 
//				"  </tbody>\r\n" + 
//				"</table>\r\n" + 
//				"\r\n" + 
//				"<table style=\"font-family:'Open Sans',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
//				"  <tbody>\r\n" + 
//				"    <tr>\r\n" + 
//				"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Open Sans',sans-serif;\" align=\"left\">\r\n" + 
//				"        \r\n" + 
//				"  <div style=\"color: #828080; line-height: 160%; text-align: center; word-wrap: break-word;\">\r\n" + 
//				"    <p style=\"font-size: 14px; line-height: 160%;\">㈜넥슨코리아 대표이사 이정헌 경기도 성남시 분당구 판교로 256번길 7</p>\r\n" + 
//				"<p style=\"font-size: 14px; line-height: 160%;\">전화 : 1588-7701 팩스 : 0502-258-8322<br />E-mail : <a rel=\"noopener\" href=\"mailto:contact-us@nexon.co.kr\" target=\"_blank\">contactus@nexon.co.kr </a></p>\r\n" + 
//				"<p style=\"font-size: 14px; line-height: 160%;\">사업자등록번호 : 220-87-17483호</p>\r\n" + 
//				"<p style=\"font-size: 14px; line-height: 160%;\">통신판매업 신고번호 : 제2013-경기성남-1659호 사업자정보확인</p>\r\n" + 
//				"  </div>\r\n" + 
//				"\r\n" + 
//				"      </td>\r\n" + 
//				"    </tr>\r\n" + 
//				"  </tbody>\r\n" + 
//				"</table>\r\n" + 
//				"\r\n" + 
//				"<table style=\"font-family:'Open Sans',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
//				"  <tbody>\r\n" + 
//				"    <tr>\r\n" + 
//				"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Open Sans',sans-serif;\" align=\"left\">\r\n" + 
//				"        \r\n" + 
//				"  <table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"64%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 1px solid #BBBBBB;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\r\n" + 
//				"    <tbody>\r\n" + 
//				"      <tr style=\"vertical-align: top\">\r\n" + 
//				"        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\r\n" + 
//				"          <span>&#160;</span>\r\n" + 
//				"        </td>\r\n" + 
//				"      </tr>\r\n" + 
//				"    </tbody>\r\n" + 
//				"  </table>\r\n" + 
//				"\r\n" + 
//				"      </td>\r\n" + 
//				"    </tr>\r\n" + 
//				"  </tbody>\r\n" + 
//				"</table>\r\n" + 
//				"\r\n" + 
//				"<table style=\"font-family:'Open Sans',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
//				"  <tbody>\r\n" + 
//				"    <tr>\r\n" + 
//				"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:0px 10px 20px;font-family:'Open Sans',sans-serif;\" align=\"left\">\r\n" + 
//				"        \r\n" + 
//				"  <div style=\"color: #828080; line-height: 140%; text-align: center; word-wrap: break-word;\">\r\n" + 
//				"    <p style=\"font-size: 14px; line-height: 140%;\">&copy; NEXON Korea Corporation All Rights Reserved.</p>\r\n" + 
//				"  </div>\r\n" + 
//				"\r\n" + 
//				"      </td>\r\n" + 
//				"    </tr>\r\n" + 
//				"  </tbody>\r\n" + 
//				"</table>\r\n" + 
//				"\r\n" + 
//				"  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\r\n" + 
//				"  </div>\r\n" + 
//				"</div>\r\n" + 
//				"<!--[if (mso)|(IE)]></td><![endif]-->\r\n" + 
//				"      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\r\n" + 
//				"    </div>\r\n" + 
//				"  </div>\r\n" + 
//				"</div>\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"    <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n" + 
//				"    </td>\r\n" + 
//				"  </tr>\r\n" + 
//				"  </tbody>\r\n" + 
//				"  </table>\r\n" + 
//				"  <!--[if mso]></div><![endif]-->\r\n" + 
//				"  <!--[if IE]></div><![endif]-->\r\n" + 
//				"</body>", member.getId(), member.getJoinDate(), host + "?" + queryString);
//		
		
		//메일발송
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
		
		String receiver = email;
		String title = "[nexon] 이메일 인증 메일";
		
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("inadang77@gmail.com", "관리자", "utf-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(title);
			message.setContent(content, "text/html;charset=utf-8");
			
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		};
		System.out.println("done");
		resp.setContentType("text/plain");
		resp.getWriter().print("success");
	}
}
