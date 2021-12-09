package nel.marco;

import nel.marco.util.Point;

import java.util.*;
import java.util.concurrent.*;

public class Day9 {

    public int[][] setup(List<String> readInput) {
        int[][] array = new int[readInput.size()][readInput.get(0).length()];

        for (int i = 0; i < readInput.size(); i++) {
            String[] line = readInput.get(i).split("");
            for (int j = 0; j < line.length; j++) {
                array[i][j] = Integer.parseInt(line[j]);
            }
        }

        return array;
    }

    public long part1(List<String> readInput) {
        Part1 part1 = new Part1(setup(readInput));

        return part1.solve();
    }


    public long part2(List<String> readInput) {
        Part2 part2 = new Part2(setup(readInput));
        return part2.solve();
    }
}

class Part1 {

    int[][] board;

    public Part1(int[][] setup) {
        this.board = setup;
    }

    public long solve() {

        long total = findAllLowpointsInCenter();
        total += findAllLowpointsAroundBoard();
        return total;
    }

    private long findAllLowpointsAroundBoard() {

        long total = 0;
        //sides (not corners)
        total = calculateTheSides(board, total);
        total = caluclateTheCorners(board, total);

        return total;
    }

    private long findAllLowpointsInCenter() {
        long total = 0l;

        for (int y = 1; y < board.length - 1; y++) {
            for (int x = 1; x < board[0].length - 1; x++) {
                int pointer = board[y][x];

                List<Boolean> sides = new ArrayList<>();
                boolean isRight = pointer < board[y][x + 1];
                boolean isLeft = pointer < board[y][x - 1];
                boolean isTop = pointer < board[y - 1][x];
                boolean isBottom = pointer < board[y + 1][x];

                sides.add(isRight);
                sides.add(isLeft);
                sides.add(isTop);
                sides.add(isBottom);

                if (countTrueInList(sides) == 4) {
                    total += pointer + 1;
                }

            }
        }
        return total;
    }

    private long caluclateTheCorners(int[][] board, long total) {
        total += topLeftCorner(board);
        total += topRightCorner(board);
        total += bottomLeftCorner(board);
        total += bottomRightCorner(board);
        return total;
    }

    private long calculateTheSides(int[][] board, long total) {
        for (int x = 1; x < board[0].length - 1; x++) {
            int y = 0;
            int pointer = board[y][x];
            boolean isRight = pointer < board[y][x + 1];
            boolean isBottom = pointer < board[y + 1][x];
            boolean isLeft = pointer < board[y][x - 1];

            List<Boolean> sides = new ArrayList<>();
            sides.add(isRight);
            sides.add(isLeft);
            sides.add(isBottom);

            if (countTrueInList(sides) == 3) {
                total += pointer + 1;
            }
        }


        //bottom row (not corners)
        for (int x = 1; x < board[0].length - 1; x++) {
            int y = board.length - 1;
            int pointer = board[y][x];
            boolean isRight = pointer < board[y][x + 1];
            boolean isLeft = pointer < board[y][x - 1];
            boolean isTop = pointer < board[y - 1][x];

            List<Boolean> sides = new ArrayList<>();
            sides.add(isRight);
            sides.add(isLeft);
            sides.add(isTop);

            if (countTrueInList(sides) == 3) {
                total += pointer + 1;
            }
        }

        //left row (not corners)
        for (int y = 1; y < board.length - 2; y++) {
            int x = 0;
            int pointer = board[y][x];
            boolean isRight = pointer < board[y][x + 1];
            boolean isBottom = pointer < board[y + 1][x];
            boolean isTop = pointer < board[y - 1][x];

            List<Boolean> sides = new ArrayList<>();
            sides.add(isRight);
            sides.add(isBottom);
            sides.add(isTop);

            if (countTrueInList(sides) == 3) {
                total += pointer + 1;
            }
        }

        //right row (not corners)
        for (int y = 1; y < board.length - 2; y++) {
            int x = board.length - 1;
            int pointer = board[y][x];
            boolean isBottom = pointer < board[y + 1][x];
            boolean isLeft = pointer < board[y][x - 1];
            boolean isTop = pointer < board[y - 1][x];

            List<Boolean> sides = new ArrayList<>();
            sides.add(isBottom);
            sides.add(isLeft);
            sides.add(isTop);

            if (countTrueInList(sides) == 3) {
                total += pointer + 1;
            }
        }
        return total;
    }

    private long bottomRightCorner(int[][] board) {
        int y = board.length - 1, x = board.length - 1;
        int pointer = board[y][x];
        boolean isLeft = pointer < board[y][x - 1];
        boolean isTop = pointer < board[y - 1][x];
        List<Boolean> sides = new ArrayList<>();
        sides.add(isTop);
        sides.add(isLeft);
        if (countTrueInList(sides) == 2) {
            return pointer + 1;
        }
        return 0;
    }

    private long bottomLeftCorner(int[][] board) {
        int y = board.length - 1, x = 0;
        int pointer = board[y][x];
        boolean isRight = pointer < board[y][x + 1];
        boolean isTop = pointer < board[y - 1][x];
        List<Boolean> sides = new ArrayList<>();
        sides.add(isRight);
        sides.add(isTop);
        if (countTrueInList(sides) == 2) {
            return pointer + 1;
        }
        return 0L;
    }

    private long topLeftCorner(int[][] board) {
        int y = 0, x = 0;
        int pointer = board[y][x];
        boolean isRight = pointer < board[y][x + 1];
        boolean isBottom = pointer < board[y + 1][x];
        List<Boolean> sides = new ArrayList<>();
        sides.add(isRight);
        sides.add(isBottom);
        if (countTrueInList(sides) == 2) {
            return pointer + 1;
        }
        return 0L;
    }

    private long topRightCorner(int[][] board) {
        long total = 0L;
        int y = 0, x = board[0].length - 1;
        int pointer = board[y][x];
        boolean isLeft = pointer < board[y][x - 1];
        boolean isBottom = pointer < board[y + 1][x];
        List<Boolean> sides = new ArrayList<>();
        sides.add(isLeft);
        sides.add(isBottom);
        if (countTrueInList(sides) == 2) {
            total += pointer + 1;
        }
        return total;
    }


    private long countTrueInList(List<Boolean> list) {
        return list.stream().filter(aBoolean -> aBoolean == true).count();
    }


}

class Part2 {

    int[][] board;

    public Part2(int[][] setup) {
        this.board = setup;
    }

    public long solve() {
        List<Point> centerOfAllThePoints = new ArrayList<>();

        centerOfAllThePoints.addAll(findAllLowpointsInCenter());
        centerOfAllThePoints.addAll(findAllLowpointsAroundBoard());

        List<Long> areas = new ArrayList<>();

        List<Future<Long>> futures = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(12);
        for (Point point : centerOfAllThePoints) {
            Future<Long> submit = executorService.submit(() -> findArea(point));
            futures.add(submit);
        }

        while(futures.size() >0){
            Long area = null;
            try {
                area = futures.remove(0).get();
                areas.add(area);

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();

        areas.sort(Long::compareTo);


        return areas.get(areas.size()-1) * areas.get(areas.size()-2) * areas.get(areas.size()-3);
    }

    private long findArea(Point point) {

        Set<Point> area = new HashSet<>();
        List<Point> listOfPointsToLookAt = new ArrayList<>();
        listOfPointsToLookAt.add(point);
        area.add(point);


        while (listOfPointsToLookAt.size() > 0) {
            Point tempPoint = listOfPointsToLookAt.get(0);
//            tempPoint = tempPoint.flipXandY();
            area.add(tempPoint);
            int pointNumber = board[tempPoint.y()][tempPoint.x()];


            try {
                Point above = new Point(tempPoint.x(), tempPoint.y() - 1);
                int numberAbove = board[above.y()][above.x()];
                if (numberAbove < 9 && !area.contains(above)) {
                    listOfPointsToLookAt.add(above);
                }
            } catch (Exception e) {
//                System.out.printf("failed [x:%d;y:%d]\n", tempPoint.x(), tempPoint.y());
            }

            try {
                Point below = new Point(tempPoint.x(), tempPoint.y() + 1);
                int numberBelow = board[below.y()][below.x()];
                if (numberBelow < 9 && !area.contains(below)) {
                    listOfPointsToLookAt.add(below);
                }
            } catch (Exception e) {
//                System.out.printf("failed [x:%d;y:%d]\n", tempPoint.x(), tempPoint.y());
            }

            try {
                Point left = new Point(tempPoint.x() - 1, tempPoint.y());
                int numberLeft = board[left.y()][left.x()];
                if (numberLeft < 9 && !area.contains(left)) {
                    listOfPointsToLookAt.add(left);
                }
            } catch (Exception e) {
//                System.out.printf("failed [x:%d;y:%d]\n", tempPoint.x(), tempPoint.y());
            }

            try {
                Point right = new Point(tempPoint.x() + 1, tempPoint.y());
                int numberRight = board[right.y()][right.x()];
                if (numberRight < 9 && !area.contains(right)) {
                    listOfPointsToLookAt.add(right);
                }
            } catch (Exception e) {
//                System.out.printf("failed [x:%d;y:%d]\n", tempPoint.x(), tempPoint.y());
            }

            listOfPointsToLookAt.remove(0);
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for (Point p : area) {
            int pint = board[p.y()][p.x()];
            map.putIfAbsent(pint, 0);
            map.put(pint, map.get(pint) + 1);
        }

        return area.size();
    }

    public List<Point> findAllLowpointsAroundBoard() {

        List<Point> total = new ArrayList<>();
        //sides (not corners)
        total.addAll(calculateTheSides(board));
        total.addAll(caluclateTheCorners(board));

        return total;
    }

    public List<Point> findAllLowpointsInCenter() {
        List<Point> list = new ArrayList<>();

        for (int y = 1; y < board.length - 1; y++) {
            for (int x = 1; x < board[0].length - 1; x++) {
                int pointer = board[y][x];

                List<Boolean> sides = new ArrayList<>();
                boolean isRight = pointer < board[y][x + 1];
                boolean isLeft = pointer < board[y][x - 1];
                boolean isTop = pointer < board[y - 1][x];
                boolean isBottom = pointer < board[y + 1][x];

                sides.add(isRight);
                sides.add(isLeft);
                sides.add(isTop);
                sides.add(isBottom);

                if (countTrueInList(sides) == 4) {
                    list.add(new Point(x, y));
                }

            }
        }
        return list;
    }

    private List<Point> caluclateTheCorners(int[][] board) {
        List<Point> list = new ArrayList<>();

        Point topLeftCorner = topLeftCorner(board);
        if (topLeftCorner != null)
            list.add(topLeftCorner);

        Point topRightCorner = topRightCorner(board);
        if (topRightCorner != null)
            list.add(topRightCorner);

        Point bottomLeftCorner = bottomLeftCorner(board);
        if (bottomLeftCorner != null)
            list.add(bottomLeftCorner);

        Point bottomRightCorner = bottomRightCorner(board);
        if (bottomRightCorner != null)
            list.add(bottomRightCorner);

        return list;
    }

    private List<Point> calculateTheSides(int[][] board) {
        List<Point> points = new ArrayList<>();
        for (int x = 1; x < board[0].length - 1; x++) {
            int y = 0;
            int pointer = board[y][x];
            boolean isRight = pointer < board[y][x + 1];
            boolean isBottom = pointer < board[y + 1][x];
            boolean isLeft = pointer < board[y][x - 1];

            List<Boolean> sides = new ArrayList<>();
            sides.add(isRight);
            sides.add(isLeft);
            sides.add(isBottom);

            if (countTrueInList(sides) == 3) {
                points.add(new Point(x, y));
            }
        }


        //bottom row (not corners)
        for (int x = 1; x < board[0].length - 1; x++) {
            int y = board.length - 1;
            int pointer = board[y][x];
            boolean isRight = pointer < board[y][x + 1];
            boolean isLeft = pointer < board[y][x - 1];
            boolean isTop = pointer < board[y - 1][x];

            List<Boolean> sides = new ArrayList<>();
            sides.add(isRight);
            sides.add(isLeft);
            sides.add(isTop);

            if (countTrueInList(sides) == 3) {
                points.add(new Point(x, y));
            }
        }

        //left row (not corners)
        for (int y = 1; y < board.length - 2; y++) {
            int x = 0;
            int pointer = board[y][x];
            boolean isRight = pointer < board[y][x + 1];
            boolean isBottom = pointer < board[y + 1][x];
            boolean isTop = pointer < board[y - 1][x];

            List<Boolean> sides = new ArrayList<>();
            sides.add(isRight);
            sides.add(isBottom);
            sides.add(isTop);

            if (countTrueInList(sides) == 3) {
                points.add(new Point(x, y));
            }
        }

        //right row (not corners)
        for (int y = 1; y < board.length - 2; y++) {
            int x = board.length - 1;
            int pointer = board[y][x];
            boolean isBottom = pointer < board[y + 1][x];
            boolean isLeft = pointer < board[y][x - 1];
            boolean isTop = pointer < board[y - 1][x];

            List<Boolean> sides = new ArrayList<>();
            sides.add(isBottom);
            sides.add(isLeft);
            sides.add(isTop);

            if (countTrueInList(sides) == 3) {
                points.add(new Point(x, y));
            }
        }
        return points;
    }

    private Point bottomRightCorner(int[][] board) {
        int y = board.length - 1, x = board.length - 1;
        int pointer = board[y][x];
        boolean isLeft = pointer < board[y][x - 1];
        boolean isTop = pointer < board[y - 1][x];
        List<Boolean> sides = new ArrayList<>();
        sides.add(isTop);
        sides.add(isLeft);
        if (countTrueInList(sides) == 2) {
            return new Point(x, y);
        }
        return null;
    }

    private Point bottomLeftCorner(int[][] board) {
        int y = board.length - 1, x = 0;
        int pointer = board[y][x];
        boolean isRight = pointer < board[y][x + 1];
        boolean isTop = pointer < board[y - 1][x];
        List<Boolean> sides = new ArrayList<>();
        sides.add(isRight);
        sides.add(isTop);
        if (countTrueInList(sides) == 2) {
            return new Point(x, y);
        }
        return null;
    }

    private Point topLeftCorner(int[][] board) {
        int y = 0, x = 0;
        int pointer = board[y][x];
        boolean isRight = pointer < board[y][x + 1];
        boolean isBottom = pointer < board[y + 1][x];
        List<Boolean> sides = new ArrayList<>();
        sides.add(isRight);
        sides.add(isBottom);
        if (countTrueInList(sides) == 2) {
            return new Point(x, y);
        }
        return null;
    }

    private Point topRightCorner(int[][] board) {
        int y = 0, x = board[0].length - 1;
        int pointer = board[y][x];
        boolean isLeft = pointer < board[y][x - 1];
        boolean isBottom = pointer < board[y + 1][x];
        List<Boolean> sides = new ArrayList<>();
        sides.add(isLeft);
        sides.add(isBottom);
        if (countTrueInList(sides) == 2) {
            return new Point(x, y);
        }

        return null;
    }


    private long countTrueInList(List<Boolean> list) {
        return list.stream().filter(aBoolean -> aBoolean == true).count();
    }


}
