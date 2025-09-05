package utils;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Multipart;

import base.initBase;

/**
 * Tapan Gandhi 12/12/2021
 */
public class Email {

	static boolean SendEmail() {
		String sStatus = "Passed";
		if (initBase.totalFail > 0) {
			sStatus = "Failed";
		} else if (initBase.totalPass == 0 && initBase.totalFail == 0) {
			sStatus = "NotRun";
		}
		String sRepFldNam = "RunID-";
		String repTime = (GlobalSetup.getReportTime());
		if (initBase.reportName.contains("Mobile")) {
			initBase.browser = "Mobile";
		}
		String repNam = initBase.reportName + "-" + initBase.browser + " - " + initBase.jenEnv.toUpperCase()
				+ " ENV, Test suite has " + sStatus + " - " + repTime;
		String to = initBase.strEmailTo;
		String cc = initBase.strEmailCC;
		String subject = repNam;
		String content = "";
		content = "Hi,\r\n\n" + " Below is the test plan result.\r\n" + " \r\n"
				+ " \r---------------------------------------------------------------\n";
		content = content + "\rTotal Pass : " + initBase.totalPass + " Total Fail : " + initBase.totalFail
				+ " Total TC : " + (initBase.totalPass + initBase.totalFail);
		content = content + "\r---------------------------------------------------------------\n";
		String strRepPort = "8000/";
		if (initBase.reportFld.contains("Megh")) {
			strRepPort = "8002/";
		}
		content = content + "\rRefer Report links: http://192.168.6.201:" + strRepPort + sRepFldNam + repTime
				+ " , for further details.\n";
		String signature = "\nRegards,\nQC Team";
//		final String senderEmail = "automationnewuser2020@gmail.com";
//		final String appPassword = "qgzx yjax whjh rxml";

		final String senderEmail = "tapan.gandhi@mantratec.com";
		final String appPassword = "ry1H0qn9ehBp";
		// Receiver's email
		String emailTo = to;//
		String emailCC = cc;
		// SMTP Server Properties
		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.zoho.com");
		properties.put("mail.smtp.port", "587");

		// Create a new Session with Authentication
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, appPassword);
			}
		});

		try {
			// Create a new email message
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(emailCC));

			message.setSubject(subject);
			// message.setText(content + "\n\n" + signature);

			//
			// Attachment file path
			// Create the text part
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setText(content + "\n\n" + signature);

			// Create the attachment part
			String filePath = "data/addmaster.properties";
			File file = new File(filePath);
			// Combine parts
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(textPart);
			if (file.exists()) {
				MimeBodyPart attachmentPart = new MimeBodyPart();
				attachmentPart.attachFile(filePath); 
				multipart.addBodyPart(attachmentPart);
			}
			// Set to message
			message.setContent(multipart);

			// Send the email
			Transport.send(message);

			System.out.println("✅ Email sent successfully!");
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			return false;
		}
		return true;
	}

}
/// EOCLASS