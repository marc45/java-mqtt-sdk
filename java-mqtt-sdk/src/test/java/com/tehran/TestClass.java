package com.tehran;

import java.util.concurrent.CompletableFuture;

public class TestClass {

    public static void main(String[] args) {
        create()
                .thenAccept(data -> System.out.println("Hello"))
                .thenRun(new Daemon()).thenRun(() -> System.out.println("AAA"));


                /*.thenRun(() -> {
                    Thread t = new Thread(() -> {
                        while (true) {
                            System.out.println("Hello");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    t.start();
                }).thenRun(() -> System.out.println("END")) ;*/
    }

    public static CompletableFuture<Integer> create() {
        return CompletableFuture.supplyAsync(() -> 2) ;
    }
}
