package com.fantasy.practice.service.guava;


import org.junit.Test;

/**
 * Created by lingyao on 16/12/4.
 */
public class BloomFilterComponmentTest {
    @Test
    public void test() {
        BloomFilterComponment bloomFilterComponment = new BloomFilterComponment();
        bloomFilterComponment.init();

        System.out.println(bloomFilterComponment.put("haha"));
        System.out.println(bloomFilterComponment.put("haha"));
        bloomFilterComponment.put("hehe");


//        System.out.println(bloomFilterComponment.mightContain("haha"));
//        System.out.println(bloomFilterComponment.mightContain("hehe"));
//        System.out.println(bloomFilterComponment.mightContain("haha1"));
    }

}