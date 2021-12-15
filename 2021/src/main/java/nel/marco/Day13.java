package nel.marco;

import nel.marco.util.Point;

import java.util.List;
import java.util.stream.Collectors;


public class Day13 {


    public List<String> removeFoldInfo(List<String> input) {
        return input.stream().filter(s -> !s.isBlank() && !s.startsWith("fold")).collect(Collectors.toList());
    }

    public List<String> getFoldInfo(List<String> input) {
        return input.stream().filter(s -> !s.isBlank() && s.startsWith("fold")).collect(Collectors.toList());
    }


    public String[][] createMap(List<String> input, Point maxValues) {

        String[][] map = new String[maxValues.y()][maxValues.x()];

        for (int y = 0; y < maxValues.y(); y++) {
            for (int x = 0; x < maxValues.x(); x++) {
                map[y][x] = ".";
            }
        }

        List<String> strings = removeFoldInfo(input);

        for (String s : strings) {
            String[] split = s.split(",");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);

            map[y][x] = "#";
        }

        return map;
    }

    public long part1(List<String> input) {
        Point maxValues = new Point(11, 15);
        String[][] map = createMap(input, maxValues);

        List<String> foldInfo = getFoldInfo(input);
        System.out.println(printBoard(map));
        System.out.println("XXXXXXXXXXXXXXXXXXXXX");
        for (String s : foldInfo) {
            boolean x = s.contains("x");

            if (x) {
                map = foldAlongX(map, Integer.parseInt(s.split("=")[1]));
            } else {
                map = foldAlongY(map, Integer.parseInt(s.split("=")[1]));
            }

            System.out.println(printBoard(map));
            System.out.println();

        }


        return -1;
    }

    public String[][] foldAlongX(String[][] map, int amount) {
        int smallMapX = map.length;
        int smallMapY = amount;
        String[][] newMap = new String[smallMapX][smallMapY];

        //Create empty board
        for (int y = 0; y < smallMapY; y++) {
            for (int x = 0; x < smallMapX; x++) {
                newMap[x][y] = ".";
            }
        }

        //Copy the base board
        for (int y = 0; y < smallMapY; y++) {
            for (int x = 0; x < smallMapX; x++) {
                newMap[x][y] = map[x][y];
            }
        }
        return newMap;
    }

    public String[][] foldAlongY(String[][] map, int amount) {

        int smallMapX = amount;
        int smallMapY = map[0].length;
        String[][] newMap = new String[smallMapX][smallMapY];

        //Create empty board
        for (int y = 0; y < smallMapY; y++) {
            for (int x = 0; x < smallMapX; x++) {
                newMap[x][y] = ".";
            }
        }

        //Copy the base board
        for (int y = 0; y < smallMapY; y++) {
            for (int x = 0; x < smallMapX; x++) {
                newMap[x][y] = map[x][y];
            }
        }
        return newMap;

    }

    public String printBoard(String[][] board) {

        StringBuilder sb = new StringBuilder(1000);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }


        return sb.toString();
    }


    public long part2(List<String> input) {

        return -1;
    }

}
