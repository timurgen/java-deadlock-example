package no.ohuen.deadlockexample;

/**
 * Simple deadlock example
 * @author 100tsa
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        
        Thread threadA = new Thread(new DeadLockRunner(a, b));
        Thread threadB = new Thread(new DeadLockRunner(b, a));
        
        threadA.start();
        threadB.start();

    }

    static class DeadLockRunner implements Runnable {

        private final Object a;
        private final Object b;

        public DeadLockRunner(Object a, Object b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (this.a) {
                System.out.println(Thread.currentThread().getName() + " A is here " + a);
                synchronized (this.b) {
                    System.out.println(Thread.currentThread().getName() + " B is here " + b);
                }
            }
        }
    }
}
