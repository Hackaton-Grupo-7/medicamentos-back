package hackatongrupo7.medicamentos_grupo7.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private static final String UTF8_ENCODING = "UTF-8";
    private static final String DASHBOARD_URL = "http://localhost:8080/swagger-ui/index.html#/";

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public void sendWelcomeEmail(String toEmail, String userName) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF8_ENCODING);

        Context context = new Context();
        context.setVariable("userName", userName);
        context.setVariable("dashboardUrl", DASHBOARD_URL);

        String htmlContent = templateEngine.process("WelcomeUser", context);

        helper.setTo(toEmail);
        helper.setSubject("Welcome to SleepUp!");
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }

}
