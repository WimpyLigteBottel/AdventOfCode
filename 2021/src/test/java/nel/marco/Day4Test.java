package nel.marco;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

class Day4Test {

    Day4 day4 = new Day4();


    @Test
    void getAllBingoBoards() throws IOException {
        Map<Integer, int[][]> allBingoBoards = day4.getAllBingoBoards();


        assertEquals(99, allBingoBoards.size());
    }


    @Test
    void getRandomNumbers() throws IOException {
        List<Integer> randomNumbers = day4.getRandomNumbers();


        assertEquals(100, randomNumbers.size());
    }

    @Test
    void part1() throws IOException {

        Map<Integer, int[][]> allBingoBoards = day4.getAllBingoBoards();
        List<Integer> randomNumbers = day4.getRandomNumbers();
        long answer = day4.part1(randomNumbers, allBingoBoards);
        assertEquals(51776,answer);
    }


    @Test
    void part2() throws IOException {

        Map<Integer, int[][]> allBingoBoards = day4.getAllBingoBoards();
        List<Integer> randomNumbers = day4.getRandomNumbers();
        long answer = day4.part2(randomNumbers, allBingoBoards);
        assertEquals(16830,answer);
    }
}