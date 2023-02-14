package burgers.sevice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import burgers.User;
import burgers.data.UserRepository;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateNotFoundException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserServices {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private Configuration configuration;
	
	@Autowired
    private JavaMailSender mailSender;
	
	final SimpleMailMessage simp = new SimpleMailMessage();
	
	public void register(User user) throws UnsupportedEncodingException, MessagingException,
	MalformedTemplateNameException, ParseException, IOException{
	    sendVerificationEmail(user);
	    repo.save(user);
	     
    }
     
    private void sendVerificationEmail(User user)
    		 throws MessagingException, TemplateNotFoundException, 
    		 MalformedTemplateNameException, ParseException, IOException{
    	
    	MimeMessage mimeMessage = mailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
    	helper.setSubject("Welcome To SpringHow.com");
        helper.setTo(user.getUserMail());
        helper.setFrom("egor.chervonikov@yandex.ru");
        mimeMessage.setHeader("header", "head");
        StringWriter writer = new StringWriter();
        
        configuration.getTemplate("mail.html").dump(writer);
        
        helper.setText(writer.getBuffer().toString(), true);
        mailSender.send(mimeMessage);
        
        user.setVerificationCode(randomString());
        user.setEmailIsVerified(false);
    }
    
    public static String randomString() {
        String[] startString = new String[64];

        return Arrays.stream(startString).map(x->Character.toString((int)(Math.random()*26+97)))
                .reduce("", (acc, i)->acc+=i);
    }
    
   
}
