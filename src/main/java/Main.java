import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int sum = 0;
        int numberOfTask = 5;
        Integer resultOfTask;

        MyCallable myCallable = new MyCallable(2);
        List<Future<Integer>> list = new ArrayList<>();
        final ExecutorService threadPool = Executors.newFixedThreadPool(4);


        for (int i = 0; i < numberOfTask; i++) {
            final Future<Integer> task = threadPool.submit(myCallable);
            list.add(task);
        }
        for (Future<Integer> fut : list) {
            try {
                resultOfTask = fut.get();
                sum = sum + resultOfTask;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("количество повторов в задаче"+sum);

        System.out.println("запуск  anyTask");

        List<MyCallable> anyList = new ArrayList<>();
        for (int i = 0; i < numberOfTask; i++) {
            anyList.add(myCallable);
        }

        System.out.println("Первая задача, количество повторов " + threadPool.invokeAny(anyList));

        threadPool.shutdown();
    }
}
