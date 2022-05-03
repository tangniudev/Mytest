package Sync;

import java.util.concurrent.TimeUnit;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/20
 * 1.标准访问，先打印短信还是邮件
 * -----------sengSMS
 * -----------sendEmail
 * 2.停4秒在短信方法内，先打印短信还是邮件
 * -----------sengSMS
 * -----------sendEmail
 * 3.新增普通的hello方法，先打印短信还是hello
 * -----------getHello
 * -----------sengSMS
 * 4.现在有两部手机，先是短信还是邮件
 * -----------sendEmail
 * -----------sengSMS
 * 5.两个静态同步方法，一部手机，先打印短信还是邮件
 * -----------sengSMS
 * -----------sendEmail
 * 6.两个静态同步方法，两部手机，先打印短信还是邮件
 * -----------sengSMS
 * -----------sendEmail
 * 7.一个静态同步方法，一个普通方法，一部手机，先打印短信还是邮件
 * -----------sendEmail
 * -----------sengSMS
 * 8.一个静态同步方法，一个普通方法，一部手机，先打印短信还是邮件
 * -----------sendEmail
 * -----------sengSMS
 * 对于普通同步方法，锁的是当前实例对象
 * 对于静态方法，锁的是当前类的class对象
 * 对于同步方法块，锁的是Synchronized括号里配置的对象
 */
class Phone{
    public static synchronized void sendSMS() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("-----------sengSMS");
    }
    public  synchronized void sendEmail(){
        System.out.println("-----------sendEmail");
    }
    public void getHello(){
        System.out.println("-----------getHello");
    }
}
public class LockDemo01 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{
                try {
                    phone.sendSMS();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        },"AA").start();
        Thread.sleep(100);
        new Thread(()->{
                try {
                   phone.sendEmail();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        },"BB").start();
    }
}
