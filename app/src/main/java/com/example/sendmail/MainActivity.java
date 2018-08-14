package com.example.sendmail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.sendmail.uitl.CrashHandler;
import com.example.sendmail.uitl.FileUtil;
import com.example.sendmail.uitl.mail.Mail;
import com.example.sendmail.uitl.mail.MailSender;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Mail mail = createMail(new String[]{"收件人"}, null, null);
        final MailSender sender = new MailSender();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<File> fileList = FileUtil.getFileList(CrashHandler.getGlobalpath());

                sender.sendFileMail(mail, fileList);
            }
        }).start();


    }

    // Private Methods

    private static final String HOST = "smtp.126.com";
    private static final String PORT = "25"; //
    private static final String FROM_ADD = "你的邮箱地址";
    private static final String FROM_PSW = "你的授权码";

    private static Mail createMail(String[] toAdds, String[] ccAdds, String bccAdds[]) {
        final Mail mail = new Mail();
        mail.setDebug(true);
        mail.setMailServerHost(HOST);
        mail.setMailServerPort(PORT);
        mail.setValidate(true);
        String[] split = FROM_ADD.split("@");

        mail.setUserName(split[0]); // 你的邮箱地址
        mail.setPassword(FROM_PSW);// 您的邮箱密码
        mail.setFromAddress(FROM_ADD); // 发送的邮箱
        mail.setToAddress(toAdds); // 发到哪个邮件去
        mail.setCcAddress(ccAdds);// 抄送邮件
        mail.setBccAddress(bccAdds);// 秘密抄送邮件
        mail.setSubject("崩溃日志"); // 邮件主题
        mail.setContent("崩溃日志，详见附件"); // 邮件文本
        return mail;
    }

    // Private Methods  __END__


}
