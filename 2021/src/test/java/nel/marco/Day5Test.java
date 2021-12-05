package nel.marco;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day5Test {

    Day5 day5 = new Day5();

    public List<String> readInput(boolean readExample) throws IOException {

        String path = "D:\\coding repo\\AdventOfCode\\2021\\src\\main\\resources\\day5";
        if(readExample){
            path = "D:\\coding repo\\AdventOfCode\\2021\\src\\main\\resources\\day5Example";
        }

        return new ArrayList<>(Files.readAllLines(Path.of(path)));

    }

    @Test
    public void expandPoints_yChange() {
        List<Point> a = day5.expandPoints("1,1 -> 1,3");
        assertEquals(3, a.size());

        List<Point> b = day5.expandPoints("1,3 -> 1,1");
        assertEquals(3, b.size());
    }

    @Test
    public void expandPoints_xChange() {
        List<Point> a = day5.expandPoints("9,7 -> 7,7");
        assertEquals(3, a.size());

        List<Point> b = day5.expandPoints("7,7 -> 9,7");
        assertEquals(3, b.size());
    }


    @Test
    public void expandPoints2_diagonalChange() {
        List<Point> a = day5.expandPointsPart2("9,7 -> 7,9");
        assertEquals(3, a.size());

        List<Point> b = day5.expandPointsPart2("7,9 -> 9,7");
        assertEquals(3, b.size());

    }

    @Test
    public void expandPoints2_xChange() {
        List<Point> a = day5.expandPointsPart2("1,1 -> 3,3");
        assertEquals(3, a.size());

        List<Point> b = day5.expandPointsPart2("3,3 -> 1,1");
        assertEquals(3, b.size());

    }

    @Test
    public void expandPoints2_edgeCase() {
        List<Point> a = day5.expandPointsPart2("6,4 -> 2,0");
        assertEquals(5, a.size());
    }

    @Test
    void setupBoard_b() {
        String[][] board = day5.setupBoard(10, 10);
        for (int i = 0; i < board.length; i++) {
            board[i][i] = "1";
        }

        String expected = """
                1.........
                .1........
                ..1.......
                ...1......
                ....1.....
                .....1....
                ......1...
                .......1..
                ........1.
                .........1
                """;

        assertEquals(expected, day5.printBoard(board));
    }

    @Test
    void setupBoard_a() {
        String[][] board = day5.setupBoard(10, 10);
        for (int i = 0; i < board.length; i++) {
            board[board[0].length - 1 - i][i] = "1";
        }

        String expected = """
                .........1
                ........1.
                .......1..
                ......1...
                .....1....
                ....1.....
                ...1......
                ..1.......
                .1........
                1.........
                """;

        assertEquals(expected, day5.printBoard(board));
    }


    @Test
    void part1() throws IOException {
        assertEquals(5, day5.part1(readInput(true)));
        assertEquals(6687, day5.part1(readInput(false)));
    }

    @Test
    void part2() throws IOException {
        assertEquals(12, day5.part2(readInput(true)));
        assertEquals(19851, day5.part2(readInput(false)));
    }
}