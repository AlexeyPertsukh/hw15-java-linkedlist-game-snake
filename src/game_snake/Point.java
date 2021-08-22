package game_snake;

//узел однонаправленного списка
public class Point {

        private final int x;
        private final int y;
        private Point nextPoint;

    public Point(int x, int y, Point nextPoint) {
        this.x = x;
        this.y = y;
        this.nextPoint = nextPoint;
    }

    public Point(int x, int y) {
        this(x, y, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setNextPoint(Point nextPoint) {
        this.nextPoint = nextPoint;
    }

    public Point getNextPoint() {
        return nextPoint;
    }

}
