import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

class SumTask extends RecursiveTask<Long> {
    private static final int THRESHOLD = 10;  // Set a threshold for splitting tasks
    private int[] numbers;
    private int start, end;

    // Constructor to initialize the task with array and boundaries
    public SumTask(int[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            // Compute the sum directly if the task is small enough
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += numbers[i];
            }
            return sum;
        } else {
            // Split the task into two subtasks

            int mid = (start + end) / 2;
            SumTask leftTask = new SumTask(numbers, start, mid);
            SumTask rightTask = new SumTask(numbers, mid, end);

            // Fork the left task and compute the right task
            leftTask.fork();
            long rightResult = rightTask.compute();

            // Join the result from the left task
            long leftResult = leftTask.join();

            return leftResult + rightResult;
        }
    }
}

public class ForkJoinDemo {
    public static void main(String[] args) {
        // Create an array of numbers
        int[] numbers = new int[100];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;  // Fill the array with numbers 1 to 20
        }

        // Create a ForkJoinPool
        ForkJoinPool pool = new ForkJoinPool();

        // Create the main task to calculate the sum of the array
        SumTask task = new SumTask(numbers, 0, numbers.length);

        // Start the task and get the result
        long result = pool.invoke(task);

        System.out.println("Sum of the array: " + result);
    }
}
