package com.fantasy.practice.service.callback;

import org.junit.Test;

import java.util.function.Function;

/**
 * Created by jiaji on 16/12/23.
 */
public class BTest {
    @Test
    public void testSync() {
        A a = new A();
        a.doBizSync();
    }

    @Test
    public void testAsync() {
        A a = new A();
        a.doBizAsync();
    }

    @Test
    public void testPredicate() {
//        Predicate<String> predicate = s -> s.equals("huazhao");
//        System.out.println(predicate.test("huazhao"));

        Function<String, String> function = s -> {
            System.out.println(s);
            return s;
        };
        function.andThen(function).apply("haha");
    }
}