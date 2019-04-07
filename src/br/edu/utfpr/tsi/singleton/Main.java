package br.edu.utfpr.tsi.singleton;

public class Main {

    public static void main(String[] args) {
        var logger = Logger.getInstance();

        var thread = new Thread(logger::writeFile);
        var thread1 = new Thread(logger::writeFile);
        var thread2 = new Thread(logger::writeFile);
        var thread3 = new Thread(logger::writeFile);

        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();

        doSomething();
    }

    public static void doSomething() {
        Logger.getInstance().writeFile();
    }
}
