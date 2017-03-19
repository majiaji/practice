package com.fantasy.practice.service.callback;

/**
 * Created by jiaji on 16/12/23.
 */
public class B {
    String remoteCall(A a) {
        System.out.println("this will cost 5 secodes...");
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.onComplete();
        synchronized (a) {
            a.notify();
        }
        return "remoteCall complete";
    }
}
