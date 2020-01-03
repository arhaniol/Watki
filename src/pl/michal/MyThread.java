package pl.michal;

public class MyThread extends Thread {
    private static final int LOOPNO = 100;
    private Writer current, next;

    public MyThread(Writer current, Writer next) {
        this.current = current;
        this.next = next;
    }

    @Override
    public void run() {
        for (int i = 0; i < LOOPNO; i++) {
            current.waitUntil(i+1);
            current.printIt();
            next.increment();
        }
    }
}

class Writer {
    private String text;
    private int counter = 0;

    public Writer(String text) {
        this.text = text;
    }

    public synchronized void printIt() {
        System.out.print(text);
    }

    public synchronized void waitUntil(int i) {
        while (i > counter) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void increment() {
        counter++;
        notify();
    }
}
