package Sync;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/19
 * 第一步创建资源类，包含属性和操作
 * 第二部创建多线程操作资源类
 */
class Ticket {
    private int num = 30;
    public void sell(){
        if (num>0) {
            System.out.println(Thread.currentThread().getName() +"卖出了第"+ num--+"张票");
        }
    }
}
public class SellTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(()->{
            for (int i = 0;i<40;i++){
                ticket.sell();;
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 0;i<40;i++){
                ticket.sell();;
            }
        },"BB").start();
        new Thread(()->{
            for (int i = 0;i<40;i++){
                ticket.sell();;
            }
        },"CC").start();
    }


}
