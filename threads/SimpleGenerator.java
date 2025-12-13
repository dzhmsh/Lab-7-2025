package threads;

import functions.basic.Log;
import java.util.Random;

public class SimpleGenerator implements Runnable {
    private final Task task;

    public SimpleGenerator(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < task.tasksCount; i++) {
            synchronized (task) {
                double base = 1.1 + random.nextDouble() * 8.9;
                task.function = new Log(base);

                task.leftX = random.nextDouble() * 100;
                task.rightX = 100 + random.nextDouble() * 100;

                task.step = 0.001 + random.nextDouble() * 0.999;

                System.out.printf("Source %.4f %.4f %.4f%n", task.leftX, task.rightX, task.step);
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

}