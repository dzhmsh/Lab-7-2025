package threads;

import functions.Functions;

public class Integrator extends Thread {
    private final Task task;
    private final Semaphore semaphore;

    public Integrator(Task task, Semaphore semaphore) {
        this.task = task;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        for (int i = 0; i < task.tasksCount; i++) {
            if (isInterrupted()) {
                System.out.println("Integrator interrupted");
                break;
            }
            try {
                semaphore.beginRead();

                double result = Functions.integrate(task.function, task.leftX, task.rightX, task.step);
                System.out.printf("Result %.4f %.4f %.4f %.6f%n", task.leftX, task.rightX, task.step, result);

                semaphore.endRead();

            } catch (InterruptedException e) {
                System.out.println("Integrator interrupted during wait");
                interrupt();
                break;
            }
        }
    }

}