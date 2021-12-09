package nel.marco;

import nel.marco.util.Point;

import java.util.*;

public class Day5 {


    public List<Point> expandPoints(String text) {
        String[] points = text.split(" -> ");

        String[] fromPoint = points[0].split(",");
        String[] toPoint = points[1].split(",");

        Point from = new Point(Integer.parseInt(fromPoint[0]), Integer.parseInt(fromPoint[1]));
        Point to = new Point(Integer.parseInt(toPoint[0]), Integer.parseInt(toPoint[1]));

        Set<Point> positions = new HashSet<>();


        if (from.x() == to.x() || from.y() == to.y()) {
            positions.add(from);
            positions.add(to);

            while (from.x() < to.x()) {
                from = new Point(from.x() + 1, from.y());
                positions.add(from);
            }

            while (from.x() > to.x()) {
                from = new Point(from.x() - 1, from.y());
                positions.add(from);
            }

            while (from.y() < to.y()) {
                from = new Point(from.x(), from.y() + 1);
                positions.add(from);
            }

            while (from.y() > to.y()) {
                from = new Point(from.x(), from.y() - 1);
                positions.add(from);
            }
        }


        return new ArrayList<>(positions);
    }

    public List<Point> expandPointsPart2(String text) {
        String[] points = text.split(" -> ");

        String[] fromPoint = points[0].split(",");
        String[] toPoint = points[1].split(",");

        Point from = new Point(Integer.parseInt(fromPoint[0]), Integer.parseInt(fromPoint[1]));
        Point to = new Point(Integer.parseInt(toPoint[0]), Integer.parseInt(toPoint[1]));

        Set<Point> positions = new HashSet<>(expandPoints(text));

        boolean isDiagonal = positions.size() == 0;
        if (isDiagonal) {
            positions.add(from);
            positions.add(to);

            boolean isExact = from.x() == from.y() && to.x() == to.y();
            if (isExact || from.x() < to.x() && from.y() < to.y() || from.x() > to.x() && from.y() > to.y()) {
                while (from.x() < to.x()) {
                    from = new Point(from.x() + 1, from.y() + 1);
                    positions.add(from);
                }

                while (from.x() > to.x()) {
                    from = new Point(from.x() - 1, from.y() - 1);
                    positions.add(from);
                }
            }  else {
                while (from.x() < to.x()) {
                    from = new Point(from.x() + 1, from.y() - 1);
                    positions.add(from);
                }

                while (from.x() > to.x()) {
                    from = new Point(from.x() - 1, from.y() + 1);
                    positions.add(from);
                }

            }
        }


        if (from.y() < 0 || from.x() < 0)
           throw new RuntimeException("Error, cant have negative values");


        return new ArrayList<>(positions);
    }

    public String[][] setupBoard(int xSize, int ySize) {
        String[][] board = new String[xSize][ySize];

        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                board[x][y] = ".";
            }
        }
        return board;
    }

    public String printBoard(String[][] board) {

        StringBuilder sb = new StringBuilder(1000);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }


        return sb.toString();
    }


    public long part1(List<String> lines) {

        String[][] board = setupBoard(1000, 1000);

        lines.stream()
                .map(this::expandPoints)
                .flatMap(Collection::stream)
                .forEach(point -> {
                    String mark = markingDigit(board[point.y()][point.x()]);
                    board[point.y()][point.x()] = mark;
                });

        long count = Arrays.stream(printBoard(board).split(""))
                .filter(s -> !s.equals(".") && !s.equals("1") && !s.equals("\n"))
                .count();


        return count;
    }


    public String markingDigit(String value) {

        return switch (value) {
            case "." -> "1";
            case "1" -> "2";
            case "2" -> "3";
            default -> "X";
        };

    }


    public long part2(List<String> lines) {
        String[][] board = setupBoard(1000, 1000);

        lines.stream()
                .map(this::expandPointsPart2)
                .flatMap(Collection::stream)
                .forEach(point -> {
                    String mark = markingDigit(board[point.y()][point.x()]);
                    board[point.y()][point.x()] = mark;
                });

        long count = Arrays.stream(printBoard(board).split(""))
                .filter(s -> !s.equals(".") && !s.equals("1") && !s.equals("\n"))
                .count();


        return count;
    }

}


