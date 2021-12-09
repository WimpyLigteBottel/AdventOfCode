package nel.marco.util;

public record Point(int x, int y) {

    public Point flipXandY(){
        return new Point(y,x);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}