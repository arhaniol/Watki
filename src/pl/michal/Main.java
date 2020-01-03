package pl.michal;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Task 1:");
        task1();
        System.out.println("Task 2:");
        task2();
        System.out.println("Task 3:");
        task3();
    }

    private static void task3() {
        Writer hello = new Writer("Hello ");
        Writer world = new Writer("world");
        Writer exclamation = new Writer("!\n");

        MyThread t1 = new MyThread(hello, world);
        MyThread t2 = new MyThread(world, exclamation);
        MyThread t3 = new MyThread(exclamation, hello);

        t1.start();
        t2.start();
        t3.start();

        hello.increment();
    }

    private static void task2() {
        int threadNumber, i;
        ThreadProc[] threads;
        threadNumber = insertNumber();
        threads = new ThreadProc[threadNumber];
        threads[0] = new ThreadProc(0, null);
        for (i = 1; i < threads.length; i++) {
            threads[i] = new ThreadProc(i, threads[i - 1]);
        }
        threads[threads.length - 1].start();
        for (i = threads.length - 1; i >= 0; i--) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void task1() {
        int threadNumber, i;
        ThreadProc[] threads;
        threadNumber = insertNumber();
        threads = new ThreadProc[threadNumber];
        threads[threadNumber - 1] = new ThreadProc(threadNumber - 1, null);
        for (i = threads.length - 2; i >= 0; i--) {
            threads[i] = new ThreadProc(i, threads[i + 1]);
        }
        threads[0].start();
        for (i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static int insertNumber() {
        Scanner input = new Scanner(System.in);
        System.out.print("Input integer number:");
        return input.nextInt();
    }

    static class ThreadProc extends Thread {
        int id;
        ThreadProc threadProc;

        ThreadProc(int id, ThreadProc threadProc) {
            this.id = id;
            this.threadProc = threadProc;
        }

        @Override
        public void run() {
            if (threadProc != null) {
                threadProc.start();
            }
            System.out.println("Thread-" + id);
        }
    }
}
