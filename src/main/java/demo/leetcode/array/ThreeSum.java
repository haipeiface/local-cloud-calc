package demo.leetcode.array;

import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
//import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class ThreeSum {

    public static void main(String args[]) {
        List integers = Arrays.asList(1, 2, 3);


        System.out.println(System.currentTimeMillis());
//        "10226733"
//        String str = "utf-8";
//
//        try {// 生成一个MD5加密计算摘要
//            MessageDigest md = MessageDigest.getInstance("MD5");
//
//            // 计算md5函数
////            md.update(str.getBytes());
//            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
//            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
//            // new BigInteger(1, md.digest()).toString(16);
//            byte[] digest = md.digest(str.getBytes());
//            String hexStr = ByteUtils.toHexString(digest);
//            System.out.println(hexStr);
//
//        }catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//    }

//        String md5 = "";
//
//        String str = "Marydon";
//        try {
//            md5 = DigestUtils.md5DigestAsHex(str.getBytes("utf-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(md5);



//        System.out.println(DigestUtils.md5Hex("Marydon"));

        String nickname1 = "生立*97b";
        try {
            String nickname2 = Base64.getEncoder().encodeToString(nickname1.getBytes("UTF-8"));
            System.out.println(nickname2);
            String nickname3 = URLEncoder.encode(nickname2,"UTF-8");
            System.out.println(nickname3);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
