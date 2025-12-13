import functions.Functions;
import functions.basic.Exp;
import functions.basic.Log;
import threads.*;

import java.util.Random;

public class Main {
        public static void main(String[] args) {
                // Задание 1: Проверка метода интегрирования
                System.out.println("Задание 1: Интеграл экспоненты ");
                testIntegration();

                // Задание 2: Последовательное выполнение
                System.out.println("\nЗадание 2: nonThread ");
                nonThread();

                // Задание 3: Простые потоки (synchronized blocks)
                System.out.println("\nЗадание 3: simpleThreads ");
                simpleThreads();

                // Задание 4: Сложные потоки (Semaphore + interrupt)
                System.out.println("\nЗадание 4: complicatedThreads ");
                complicatedThreads();
        }

        private static void testIntegration() {
                Exp exp = new Exp();
                double left = 0;
                double right = 1;
                double theoreticalValue = Math.E - 1;
                double step = 0.1;

                // Подбор шага
                while (true) {
                        double calculated = Functions.integrate(exp, left, right, step);
                        if (Math.abs(calculated - theoreticalValue) < 1e-7) {
                                System.out.printf("Точность 1e-7 достигнута при шаге: %.8f%n", step);
                                System.out.printf("Calculated: %.9f, Theoretical: %.9f%n", calculated,
                                                theoreticalValue);
                                break;
                        }
                        step /= 2; // Уменьшаем шаг
                }
        }

        public static void nonThread() {
                Task task = new Task(10);
                Random random = new Random();

                for (int i = 0; i < task.tasksCount; i++) {
                        double base = 1.1 + random.nextDouble() * 8.9;
                        task.function = new Log(base);
                        task.leftX = random.nextDouble() * 100;
                        task.rightX = 100 + random.nextDouble() * 100;
                        task.step = 0.001 + random.nextDouble() * 0.999;

                        System.out.printf("Source %.4f %.4f %.4f%n", task.leftX, task.rightX, task.step);

                        double res = Functions.integrate(task.function, task.leftX, task.rightX, task.step);
                        System.out.printf("Result %.4f %.4f %.4f %.6f%n", task.leftX, task.rightX, task.step, res);
                }
        }

        public static void simpleThreads() {
                Task task = new Task(10);
                Thread generator = new Thread(new SimpleGenerator(task));
                Thread integrator = new Thread(new SimpleIntegrator(task));

                // Изменение приоритетов (опционально по заданию)
                generator.setPriority(Thread.NORM_PRIORITY);
                integrator.setPriority(Thread.MAX_PRIORITY);

                generator.start();
                integrator.start();

                try {
                        generator.join();
                        integrator.join();
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
        }

        public static void complicatedThreads() {
                Task task = new Task(10); // Количество заданий
                Semaphore semaphore = new Semaphore();

                Generator generator = new Generator(task, semaphore);
                Integrator integrator = new Integrator(task, semaphore);

                generator.start();
                integrator.start();

                // Прерывание через 50 мс
                try {
                        Thread.sleep(50);
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }

                System.out.println("> Main thread interrupting child threads...");
                generator.interrupt();
                integrator.interrupt();
        }

}