package com.fantasy.practice.service.callback;

/**
 * Created by jiaji on 16/12/23.
 */
public class A implements ICallBack {
    B b = new B();

    void doBizSync() {
        b.remoteCall(this);
        System.out.println("doBizSync return");
    }

    void doBizAsync() {
        A temp = this;
        new Thread(() -> {
            b.remoteCall(temp);
        }).start();
        System.out.println("doBizAsync return");
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onComplete() {
        System.out.println("call back completed");
    }
}
