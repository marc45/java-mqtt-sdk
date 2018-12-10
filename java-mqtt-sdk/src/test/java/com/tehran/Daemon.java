package com.tehran;

public class Daemon implements Runnable{
    public void execute() {
        while (true) {
            System.out.println("I am run");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        execute();
    }
}
