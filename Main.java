import functions.*;
import functions.basic.Cos;
import functions.basic.Sin;

public class Main {
    public static void main(String[] args) {
        // Задание 1: Проверка итераторов
        System.out.println("Задание 1: Итераторы");
        testIterators();

        // Задание 2: Проверка фабрик
        System.out.println("\nЗадание 2: Фабрики");
        testFactories();

        // Задание 3: Проверка рефлексии
        System.out.println("\nЗадание 3: Рефлексия");
        testReflection();
    }

    public static void testIterators() {
        FunctionPoint[] points = {
            new FunctionPoint(1, 1),
            new FunctionPoint(2, 4),
            new FunctionPoint(3, 9)
        };
        
        // Проверка итератора для LinkedListTabulatedFunction
        TabulatedFunction listFunction = new LinkedListTabulatedFunction(points);
        System.out.println("Перебор LinkedListTabulatedFunction:");
        for (FunctionPoint p : listFunction) {
            System.out.println(p);
        }

        // Проверка итератора для ArrayTabulatedFunction
        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(points);
        System.out.println("\nПеребор ArrayTabulatedFunction:");
        for (FunctionPoint p : arrayFunction) {
            System.out.println(p);
        }
    }

    public static void testFactories() {
        Function f = new Cos();
        TabulatedFunction tf;
        
        // Используем фабрику по умолчанию (ArrayTabulatedFunction)
        tf = TabulatedFunctions.tabulate(f, 0, Math.PI, 11);
        System.out.println(tf.getClass());
        
        // Устанавливаем фабрику связного списка
        TabulatedFunctions.setTabulatedFunctionFactory(new LinkedListTabulatedFunction.LinkedListTabulatedFunctionFactory());
        tf = TabulatedFunctions.tabulate(f, 0, Math.PI, 11);
        System.out.println(tf.getClass());
        
        // Устанавливаем фабрику массива
        TabulatedFunctions.setTabulatedFunctionFactory(new ArrayTabulatedFunction.ArrayTabulatedFunctionFactory());
        tf = TabulatedFunctions.tabulate(f, 0, Math.PI, 11);
        System.out.println(tf.getClass());
    }

    public static void testReflection() {
        TabulatedFunction f;

        // Создание через рефлексию (класс, границы, кол-во точек)
        f = TabulatedFunctions.createTabulatedFunction(
            ArrayTabulatedFunction.class, 0, 10, 3);
        System.out.println(f.getClass());
        System.out.println(f);

        // Создание через рефлексию (класс, границы, массив значений)
        f = TabulatedFunctions.createTabulatedFunction(
            ArrayTabulatedFunction.class, 0, 10, new double[] {0, 10});
        System.out.println(f.getClass());
        System.out.println(f);

        // Создание через рефлексию (класс, массив точек)
        f = TabulatedFunctions.createTabulatedFunction(
            LinkedListTabulatedFunction.class, 
            new FunctionPoint[] {
                new FunctionPoint(0, 0),
                new FunctionPoint(10, 10)
            }
        );
        System.out.println(f.getClass());
        System.out.println(f);

        // Табулирование через рефлексию
        f = TabulatedFunctions.tabulate(
            LinkedListTabulatedFunction.class, new Sin(), 0, Math.PI, 11);
        System.out.println(f.getClass());
        System.out.println(f);
    }
}