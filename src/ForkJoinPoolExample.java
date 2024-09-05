import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolExample extends RecursiveTask<Integer> {
    private final int workLoad;

    public ForkJoinPoolExample(int workLoad) {
        this.workLoad = workLoad;
    }

    @Override
    protected Integer compute() {
        if (workLoad <= 5) {
            return workLoad * 3;
        } else {
            int workLoad1 = workLoad / 2;
            int workLoad2 = workLoad / 2;

            ForkJoinPoolExample left = new ForkJoinPoolExample(workLoad1);
            ForkJoinPoolExample right = new ForkJoinPoolExample(workLoad2);

            left.fork();
            right.fork();

            return left.compute() + right.join();
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        ForkJoinPoolExample forkJoinPoolExample = new ForkJoinPoolExample(10);

        int result = forkJoinPool.invoke(forkJoinPoolExample);

        System.out.println("Result: " + result);
    }
}
