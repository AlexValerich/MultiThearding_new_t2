import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    private final int repeat;

    public MyCallable(int repeat) {

        this.repeat = repeat;
    }

    @Override
    public Integer call() throws Exception {
        Integer counter = 0;

            for (int i = 0; i<repeat;i++) {
        Thread.sleep(250);
        System.out.println("Всем привет! " + Thread.currentThread().getName());
        counter++;
    }
        return counter;
    }
}