package com.bzdgs.dms;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * @ClassName: HS512Encode.java
 * @Description： TODO
 * @Author: yun
 * @Date: 2020/6/20 15:33
 * @Vsersion: 1.0.0
 **/
public class HS512Encode {
    private final static String algorithm = "HMACSHA512";



    public static void main(String[] args) {

        String result = hmacSHA512("3H921028CJ");



        System.out.println(result);

    }



    private static String hmacSHA512(String key) {

        String result = "";



        try {

            Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

            long unixTime = c.getTimeInMillis() / 1000;

            String data = String.valueOf(unixTime);



            final byte[] bytesData = data.getBytes("UTF-8");

            final byte[] bytesKey = key.getBytes();

            final SecretKey secretKey = new SecretKeySpec(bytesKey, algorithm);

            Mac mac = Mac.getInstance(algorithm);

            mac.init(secretKey);

            mac.update(bytesData);

            final byte[] macData = mac.doFinal();



            final long l = bytesToLong(macData);

            BigDecimal bDecimal = readUnsignedLong(l);

            BigDecimal pBigDecimal = new BigDecimal(Math.pow(10, 15));

            BigDecimal token = bDecimal.remainder(pBigDecimal);



            result = String.valueOf(token);



            return resultCheck(result);

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }



        return result;

    }



    private static String resultCheck(String token) {

        while (token.length() < 15) {

            token = "0" + token;

        }



        return token;

    }



    public static long bytesToLong(byte[] b) {

        long s = 0;

        long s0 = b[0] & 0xff;

        long s1 = b[1] & 0xff;

        long s2 = b[2] & 0xff;

        long s3 = b[3] & 0xff;

        long s4 = b[4] & 0xff;

        long s5 = b[5] & 0xff;

        long s6 = b[6] & 0xff;

        long s7 = b[7] & 0xff;

        s1 <<= 8;

        s2 <<= 16;

        s3 <<= 24;

        s4 <<= 8 * 4;

        s5 <<= 8 * 5;

        s6 <<= 8 * 6;

        s7 <<= 8 * 7;

        s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;

        return s;

    }



    public static final BigDecimal readUnsignedLong(long value) throws IOException {

        if (value >= 0) {
            return new BigDecimal(value);
        }
        long lowValue = value & 0x7fffffffffffffffL;

        return BigDecimal.valueOf(lowValue).add(BigDecimal.valueOf(Long.MAX_VALUE)).add(BigDecimal.valueOf(1));

    }
}
