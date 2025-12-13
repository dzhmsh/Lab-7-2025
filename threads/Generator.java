package threads;

import functions.basic.Log;
import java.util.Random;

public class Generator extends Thread {
    private final Task task;
    private final Semaphore semaphore;

    public Generator(Task task, Semaphore semaphore) {
        this.task = task;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < task.tasksCount; i++) {
            if (isInterrupted()) {
                System.out.println("Generator interrupted");
                break;
            }
            try {
                semaphore.beginWrite();

                double base = 1.1 + random.nextDouble() * 8.9;
                task.function = new Log(base);
                task.leftX = random.nextDouble() * 100;
                task.rightX = 100 + random.nextDouble() * 100;
                task.step = 0.001 + random.nextDouble() * 0.999;

                System.out.printf("Source %.4f %.4f %.4f%n", task.leftX, task.rightX, task.step);

                semaphore.endWrite();

            } catch (InterruptedException e) {
                System.out.println("Generator interrupted during wait");
                interrupt(); // Восстанавливаем
                break;
            }
        }
    }

}