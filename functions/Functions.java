package functions;

import functions.meta.*;

public class Functions {
    /*
     * В пакете `functions` создайте класс `Functions`, содержащий вспомогательные
     * статические методы для работы с функциями. Сделайте так, чтобы в программе
     * вне этого класса нельзя было создать его объект. Класс должен содержать
     * следующие методы:
     * 
     * 
     * При написании методов следует воспользоваться созданными ранее классами из
     * пакета `functions.meta`.
     */
    // Приватный конструктор
    private Functions() {

    }

    private static final double EPSILON = 1e-10;

    /*
     * `public static Function shift(Function f, double shiftX, double shiftY)` –
     * возвращает объект функции, полученной из исходной сдвигом вдоль осей;
     */
    public static Function shift(Function f, double shiftX, double shiftY) {
        return new Shift(f, shiftX, shiftY);
    }

    /*
     * `public static Function scale(Function f, double scaleX, double scaleY)` –
     * возвращает объект функции, полученной из исходной масштабированием вдоль
     * осей;
     */
    public static Function scale(Function f, double scaleX, double scaleY) {
        return new Scale(f, scaleX, scaleY);
    }

    /*
     * `public static Function power(Function f, double power)` – возвращает объект
     * функции, являющейся заданной степенью исходной;
     */
    public static Function power(Function f, double power) {
        return new Power(f, power);
    }

    /*
     * `public static Function sum(Function f1, Function f2)` – возвращает объект
     * функции, являющейся суммой двух исходных;
     */
    public static Function sum(Function f1, Function f2) {
        return new Sum(f1, f2);
    }

    /*
     * `public static Function mult(Function f1, Function f2)` – возвращает объект
     * функции, являющейся произведением двух исходных;
     */
    public static Function mult(Function f1, Function f2) {
        return new Mult(f1, f2);
    }
    /*
     * `public static Function composition(Function f1, Function f2)` – возвращает
     * объект функции, являющейся композицией двух исходных.
     */

    public static Function composition(Function f1, Function f2) {
        return new Composition(f1, f2);
    }

    public static double integrate(Function function, double leftX, double rightX, double step) {
        // 1. Валидация шага
        if (step <= 0) {
            throw new IllegalArgumentException("Step must be positive");
        }

        // 2. Учет направления интегрирования
        if (leftX > rightX) {
            return -integrate(function, rightX, leftX, step);
        }

        if (Math.abs(rightX - leftX) < EPSILON) {
            return 0;
        }

        if (leftX < function.getLeftDomainBorder() || rightX > function.getRightDomainBorder()) {
            throw new IllegalArgumentException("Integration interval is out of function domain");
        }

        double result = 0;

        long n = (long) ((rightX - leftX) / step);

        double currentX = leftX;
        double currentY = function.getFunctionValue(leftX);

        for (int i = 0; i < n; i++) {
            double nextX = leftX + (i + 1) * step;
            double nextY = function.getFunctionValue(nextX);

            // Площадь трапеции
            result += (currentY + nextY) / 2 * step;

            currentY = nextY;
            currentX = nextX;
        }

        if (rightX - currentX > EPSILON) {
            double lastY = function.getFunctionValue(rightX);
            result += (currentY + lastY) / 2 * (rightX - currentX);
        }

        return result;
    }

}
