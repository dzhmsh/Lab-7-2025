package functions;

import java.io.Serializable;

public interface TabulatedFunction extends Function, Serializable, Cloneable, Iterable<FunctionPoint> {
    /*
     * • public double getLeftDomainBorder() – возвращает значение левой границы
     * области определения функции;
     * 
     * • public double getRightDomainBorder() – возвращает значение правой границы
     * области определения функции;
     * 
     * • public double getFunctionValue(double x) – возвращает значение функции в
     * заданной точке.
     * 
     * Исключите соответствующие методы из интерфейса TabulatedFunction и сделайте
     * так, чтобы он расширял интерфейс Function. Теперь табулированные функции буду
     * частным случаем функций одной переменной.
     */

    int getPointsCount();

    FunctionPoint getPoint(int index);

    void setPoint(int index, FunctionPoint point) throws InappropriateFunctionPointException;

    double getPointX(int index);

    void setPointX(int index, double x) throws InappropriateFunctionPointException;

    double getPointY(int index);

    void setPointY(int index, double y);

    void deletePoint(int index);

    void addPoint(FunctionPoint point) throws InappropriateFunctionPointException;

}