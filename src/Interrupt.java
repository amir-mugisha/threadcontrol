 class Task implements Runnable {
    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Task in progress: " + i);
                Thread.sleep(1000);  // Simulates work by sleeping for 1 second
            }
        } catch (InterruptedException e) {
            System.out.println("Task was interrupted!");
        }
    }
}

 public class Interrupt {
     public static void main(String[] args) {

         Thread taskThread = new Thread(new Task());

         taskThread.start();

         try {
             Thread.sleep(3000);  // Main thread sleeps for 3 seconds
         } catch (InterruptedException e) {
             e.printStackTrace();
         }

         // Interrupting the task thread after 3 seconds
         taskThread.interrupt();
         System.out.println("Main thread sent interrupt signal.");
     }
 }