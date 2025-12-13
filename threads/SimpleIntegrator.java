package threads;

import functions.Functions;

public class SimpleIntegrator implements Runnable {
    private final Task task;

    public SimpleIntegrator(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        for (int i = 0; i < task.tasksCount; i++) {
            synchronized (task) {
                if (task.function == null) {
                    System.out.println("Result: Function is null, skipping...");
                    continue;
                }

                double result = Functions.integrate(task.function, task.leftX, task.rightX, task.step);
                System.out.printf("Result %.4f %.4f %.4f %.6f%n", task.leftX, task.rightX, task.step, result);
            }
        }
    }
}