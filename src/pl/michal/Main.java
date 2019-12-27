package pl.michal;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int nThread;
//        System.out.println("How much threads would you like create? :");
//        nThread = input.nextInt();
//        System.out.println("Task1:");
//        task1(nThread);
//        System.out.println("Task2:");
//        task2(nThread);
        System.out.println("Task3:");
        task3(5);
    }

    private static void task3(int limit) {
        PrintThread printHelloT = new PrintThread("Hello "),
                printWorldT = new PrintThread("world"),
                printExclamationT = new PrintThread("!\n");

        printHelloT.setLIMIT(limit);
        printHelloT.start();
        printWorldT.start();
        printExclamationT.start();
    }

    private static void task1(int nThread) {
        NamedThread[] thread = new NamedThread[nThread];
        int i;
        for (i = 0; i < nThread; i++) {
            thread[i] = new NamedThread(i);
//            thread[i].run();
        }

    }

    private static void task2(int nThread) {
        NamedThread[] thread = new NamedThread[nThread];
        int i;
        for (i = nThread - 1; i >= 0; i--) {
            thread[i] = new NamedThread(i);
            new Thread(thread[i]).start();
        }
    }
}

class NamedThread implements Runnable {

    NamedThread(int no) {
        System.out.println("Thread-" + no);
    }

    @Override
    public void run() {
    }
}

class PrintThread extends Thread {
    private String text;
    private Integer counter = 0;
    private int LIMIT;

    PrintThread(String text) {
        this.text = text;
    }

    @Override
    public void run() {
        int i = 0;
        System.out.print(text);
        while (counter < LIMIT) {
            if (i != counter) {
                System.out.print(text);
                increment();
            } else {

            }
        }
    }

    private synchronized void increment() {
        counter++;
        counter.notify();
    }

    public void setLIMIT(int LIMIT) {
        this.LIMIT = LIMIT;
    }
}
