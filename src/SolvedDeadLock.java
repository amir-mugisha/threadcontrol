public class SolvedDeadLock {

    private static final Object resource1 = new Object();
    private static final Object resource2 = new Object();

    public static void main(String[] args) {

        // Thread 1 tries to lock resource1 first, then resource2
        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: Locked resource 1");

                // Simulate some work with resource1
                try { Thread.sleep(100); } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 1: Waiting to lock resource 2");
                synchronized (resource2) {
                    System.out.println("Thread 1: Locked resource 2");
                }
            }
        });

        // Thread 2 tries to lock resource2 first, then resource1
        Thread thread2 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 2: Locked resource 2");

                // Simulate some work with resource2
                try { Thread.sleep(100); } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 2: Waiting to lock resource 1");
                synchronized (resource2) {
                    System.out.println("Thread 2: Locked resource 1");
                }
            }
        });

        // Start both threads
        thread1.start();
        thread2.start();
    }
}
