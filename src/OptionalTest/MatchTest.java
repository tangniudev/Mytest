package OptionalTest;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/1
 */
public class MatchTest {
    public static void main(String[] args){
        int a =3 ;
        int b =4;
        System.out.println(a+b);

//        String temp = "user_group_fq4-子-002";
//        String name = "user_group_fq4-子-";
//        Pattern p = Pattern.compile( name );
//        Matcher m = p.matcher(temp);
//        if (m.find()){
//            System.out.println(true);
//        }
//        String count = "021";
//        int i = Integer.parseInt(count);
//        System.out.println(i);
//
//        System.out.println(String.format("%03d", i));

        String str = "defg不合格u_alt_son2_002";
//        String pattern = "[0-9a-zA-Z\\u4e00-\\u9fa5_]+_son\\d_";
//        String pattern2 = "_son\\d_";
//        Pattern r2 = Pattern.compile(pattern2);
//        Matcher m2 = r2.matcher(str);
//        System.out.println(m2.find());
//        String group1 = m2.group();
//        System.out.println(group1);
//
//        Pattern r = Pattern.compile(pattern);
//        Matcher m = r.matcher(str);
//        System.out.println(m.find());
//        System.out.println(m.group());
//        String group = m.group();
////        String replace = group.replace("_son", "").replace("_", "");
//        System.out.println(m.matches());
        String str2 = "分群3_son1_001";
        String s = checkName(str2);
        System.out.println(s);
    }

    public static String checkName(String name){
        String hasSon = "[0-9a-zA-Z\\u4e00-\\u9fa5_]+_son\\d_";
        Pattern r = Pattern.compile(hasSon);
        Matcher m = r.matcher(name);
        if (m.find()){//如果有子分群
//            System.out.println(m.group());
            String findSon = "_son\\d_";
            Pattern r2 = Pattern.compile(findSon);
            Matcher m2 = r2.matcher(name);
            if (m2.find()) {
                String sonNum = m2.group();
                int i = Integer.parseInt(sonNum.replace("_son", "").replace("_", ""))+1;
                String newStr = "_son" + i + "_";
                String replace = name.replace(sonNum, newStr);
//                System.out.println(replace);
                Matcher m3 = r.matcher(replace);
                if (m3.find()) {
                    return m3.group();
                }


            }

        }
        return name+"_";
    }



}
