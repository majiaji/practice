package com.fantasy.practice.service.guava;


import org.junit.Test;

/**
 * Created by jiaji on 16/12/4.
 */
public class BloomFilterComponmentTest {
    @Test
    public void test() {
//        BloomFilterComponment bloomFilterComponment = new BloomFilterComponment();
//
//        System.out.println(bloomFilterComponment.put("haha"));
//        System.out.println(bloomFilterComponment.put("haha"));
//        bloomFilterComponment.put("hehe");
//
//
//        System.out.println(bloomFilterComponment.mightContain("haha"));
//        System.out.println(bloomFilterComponment.mightContain("hehe"));
//        System.out.println(bloomFilterComponment.mightContain("haha1"));

        String a = "{\"url\":\"https://g.alicdn.com/qn/log/0.1.1/log.min.js\",\"md5\":\"9defee0cc9e80c993b4263b125578b79\"}";
        System.out.println(a.hashCode()%10000);
    }

}