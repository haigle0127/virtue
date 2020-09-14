package cn.haigle.around.common.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮件工具类
 * @author haigle
 * @date 2019/3/6 13:05
 */
public class MailUtils {

    /**
     * 发送邮件
     * @param to 给谁发
     * @param text 发送内容
     * @author haigle
     * @date 2019/3/6 13:06
     */
    public static void sendMail(String to,String text) throws MessagingException {

        /*
         * 创建连接对象 连接到邮件服务器
         */
        Properties properties = new Properties();

        /*
         * 设置发送邮件的基本参数
         * 发送邮件服务器(注意，此处根据你的服务器来决定，如果使用的是QQ服务器，请填写smtp.qq.com)
         */
        properties.put("mail.smtp.host", "smtp.qq.com");

        /*
         * 发送端口（根据实际情况填写，一般均为25）
         */
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.auth", "true");

        /*
         * 设置发送邮件的账号和密码
         */
        Session session = Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                /*
                 * 两个参数分别是发送邮件的账户和密码
                 * (注意，如果配置后不生效，请检测是否开启了 POP3/SMTP 服务，
                 * QQ邮箱对应设置位置在: [设置-账户-POP3/IMAP/SMTP/Exchange/CardDAV/CalDAV服务])
                 */
                return new PasswordAuthentication("991382548@qq.com","");
            }
        });

        /*
         * 创建邮件对象
         */
        Message message = new MimeMessage(session);

        /*
         * 设置发件人
         */
        message.setFrom(new InternetAddress("991382548@qq.com"));

        /*
         * 设置收件人
         */
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));

        /*
         * 设置主题
         */
        message.setSubject("这是一份测试邮件");

        /*
         * 设置邮件正文  第二个参数是邮件发送的类型
         */
        message.setContent(text,"text/html;charset=UTF-8");

        /*
         * 发送一封邮件
         */
        Transport.send(message);
    }


    public static void main(String[] args) {
        try {
            sendMail("haigle@outlook.com", String.valueOf(Math.random() * 999));
            System.out.println("邮件发送成功!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

//    @Autowired
//    JavaMailSenderImpl mailSender;
//
//    public  void test(String test) {
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        // 邮箱设置
//        message.setSubject(test);
//        message.setText("正在进行程序测试");
//
//        message.setTo("haigle@outlook.com");
//        message.setFrom("991382548@qq.com");
//
//        mailSender.send(message);
//    }

}
