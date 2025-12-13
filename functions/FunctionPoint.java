package functions;

import java.io.Serializable;

public class FunctionPoint implements Serializable {

    private static final long serialVersionUID = 1L;
    private double x, y;

    // getter'y & setter'y

    public FunctionPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // создаёт объект точки с теми же координатами, что у указанной точки;
    public FunctionPoint(FunctionPoint point) {
        this.x = point.x;
        this.y = point.y;
    }

    // – создаёт точку с координатами (0; 0).
    public FunctionPoint() {
        this.x = 0;
        this.y = 0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "; " + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof FunctionPoint))
            return false;
        FunctionPoint that = (FunctionPoint) o;
        return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0;
    }

    @Override
    public int hashCode() {
        long xBits = Double.doubleToLongBits(x);
        long yBits = Double.doubleToLongBits(y);

        int xHash = (int) (xBits & 0xFFFFFFFFL) ^ (int) (xBits >>> 32);
        int yHash = (int) (yBits & 0xFFFFFFFFL) ^ (int) (yBits >>> 32);

        return xHash ^ yHash;
    }

    @Override
    public Object clone() {
        return new FunctionPoint(this);
    }

}