package OptionalTest;

import java.awt.font.OpenType;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Description
 *
 * @author tyw
 * @since 2022/3/25
 */
public class Test {
    public static void main(String[] args) {
//        EmptyTest();
        ElseTest();
    }

    public static void EmptyTest() {
        /**
         * 方法内部类（只能在外部类的方法之中使用）
         * 匿名内部类（略）
         */
//        Test test = new Test();
//        User user = test.new User(); //成员内部类只能通过外部类的方式构造
        User user = new User();//静态内部类可以直接构造
        User nullUser = null;
        user.setName("小张");
        Optional<User> optional1 = Optional.of(user);
        if (optional1.isPresent()) {
            System.out.println(optional1.get());
        }
        /**
         * Optional.of(null); 会报错
         *    Optional<User> optional3 = Optional.of(nullUser);
         *         if (optional3.isPresent()){
         *             System.out.println(optional3.get());
         *         }
         */


        Optional<User> optional2 = Optional.ofNullable(user);
        if (optional2.isPresent()) {
            System.out.println(optional2.get());
        }
        Optional<User> optional3 = Optional.ofNullable(nullUser);
        if (optional3.isPresent()) {
            System.out.println(optional3.get());
        }

        Optional<User> empty = Optional.empty();
        System.out.println(empty);


    }

    public static void ElseTest(){
        User user = null;
        User user2 = new User("正常情况");
        User orElse = Optional.ofNullable(user).orElse(new User("另外情况"));
        System.out.println(orElse.name);

        User user1 = Optional.ofNullable(user).orElseGet(new Supplier<User>() {
            @Override
            public User get() {
                return new User("张三");
            }
        });
        System.out.println(user1.getName());

//        Optional.ofNullable(user).orElseThrow()

    }

    public static class User {
        private String name;

        public User() {
        }

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
