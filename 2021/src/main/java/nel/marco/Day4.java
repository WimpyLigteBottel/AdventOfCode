package nel.marco;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Day4 {

    Path puzzlePath = Path.of("src/main/resources/day4");


    /**
     * Gets the randomnumbers that going to be drawn in order
     *
     * @return
     * @throws IOException
     */
    public List<Integer> getRandomNumbers() throws IOException {
        List<String> input = Files.readAllLines(puzzlePath);

        return Arrays.stream(input.get(0).split(",")).map(Integer::valueOf).collect(Collectors.toList());
    }

    /**
     * Gets all the bingo boards and index them accordingly on how i found them
     *
     * @return
     * @throws IOException
     */
    public Map<Integer, int[][]> getAllBingoBoards() throws IOException {

        //clean board data
        List<String> input = Files.readAllLines(puzzlePath)
                .stream()
                .map(String::trim)
                .map(s -> s.replaceAll("  ", " "))
                .collect(Collectors.toList());

        //Remove the first 2 lines (random numbers)
        input.remove(0);
        input.remove(0);

        int globalMapIndex = 0;

        Map<Integer, int[][]> bingoMap = new HashMap<>();

        int[][] board = new int[5][5];
        int y = 0;
        while (input.size() > 0) {
            String line = input.remove(0);
            if (line.isBlank()) {
                bingoMap.put(globalMapIndex++, board);
                board = new int[5][5];
                y = 0;
                continue;
            }
            String[] s = line.split(" ");
            for (int x = 0; x < s.length; x++) {
                board[y][x] = Integer.parseInt(s[x]);
            }
            y++;
        }
        //Adding the last board
        bingoMap.put(globalMapIndex, board);


        return bingoMap;
    }

    /**
     * Get the first winning bingoBoard and returns the score of the winning board by counting all the "unmarked/drawn"
     * numbers and then multiply the last drawn number with the sum score
     *
     * @param randomNumbers
     * @param bingoMap
     * @return
     */
    public long part1(List<Integer> randomNumbers, Map<Integer, int[][]> bingoMap) {
        BingoBoardWithPulledNumbers board = getFirstWinningBingoBoard(randomNumbers, bingoMap);

        return calculateSumOfNoneChosenNumbers(board);
    }

    public long part2(List<Integer> randomNumbers, Map<Integer, int[][]> bingoMap) {

        // Gets the last remaining winning board
        BingoBoardWithPulledNumbers lastWinningBoard = null;
        while (bingoMap.size() > 0) {
            lastWinningBoard = getFirstWinningBingoBoard(randomNumbers, bingoMap);
            bingoMap.remove(lastWinningBoard.index());
        }

        return calculateSumOfNoneChosenNumbers(lastWinningBoard);
    }

    private List<Integer> getAllDigitsOnTheBoard(BingoBoardWithPulledNumbers first) {
        List<Integer> sumOfDigits = new ArrayList<>();

        for (int x = 0; x < first.board()[0].length; x++) {
            for (int y = 0; y < first.board()[0].length; y++) {
                sumOfDigits.add(first.board()[x][y]);
            }
        }
        return sumOfDigits;
    }


    /**
     * Start by finding the sum of all unmarked numbers on that board.
     * Then, multiply that sum by the number that was just called when the board won.
     *
     * @param winningBoard
     * @return
     */
    private long calculateSumOfNoneChosenNumbers(BingoBoardWithPulledNumbers winningBoard) {
        List<Integer> sumOfDigits = getAllDigitsOnTheBoard(winningBoard);

        //Remove drawnNumbers
        winningBoard.drawnNumbers().forEach(sumOfDigits::remove);

        //Add up remainingDigits
        long total = sumOfDigits.stream().mapToLong(Integer::longValue).sum();

        Integer lastDrawnDigit = getLastDrawnDigit(winningBoard);

        return total * lastDrawnDigit;
    }

    /**
     * Gets the last digit in list
     *
     * @param lastWinningBoard
     * @return
     */
    private Integer getLastDrawnDigit(BingoBoardWithPulledNumbers lastWinningBoard) {
        return new LinkedList<>(lastWinningBoard.drawnNumbers()).getLast(); // << easier to read but performance is worse
    }

    private BingoBoardWithPulledNumbers getFirstWinningBingoBoard(List<Integer> randomNumbers, Map<Integer, int[][]> bingoMap) {

        List<Integer> temp = new ArrayList<>(randomNumbers); // I don't want to modify parameter being passed in
        List<Integer> numbersDrawn = new ArrayList<>();
        while (!temp.isEmpty()) {
            numbersDrawn.add(temp.remove(0)); // Draws a numbers and then checks if a board has won
            for (Map.Entry<Integer, int[][]> entry : bingoMap.entrySet()) {
                Optional<int[][]> row = winningBoard(bingoMap, numbersDrawn, entry, false);
                Optional<int[][]> column = winningBoard(bingoMap, numbersDrawn, entry, true);

                if (row.isPresent()) {
                    return new BingoBoardWithPulledNumbers(numbersDrawn, entry.getKey(), row.get());
                }

                if (column.isPresent()) {
                    return new BingoBoardWithPulledNumbers(numbersDrawn, entry.getKey(), column.get());
                }
            }
        }

        throw new RuntimeException("No winning board");
    }

    /**
     * Checks if the board is a bingo
     * @param bingoMap
     * @param numbersDrawn
     * @param entry
     * @param reverse
     * @return
     */
    private Optional<int[][]> winningBoard(Map<Integer, int[][]> bingoMap, List<Integer> numbersDrawn, Map.Entry<Integer, int[][]> entry, boolean reverse) {
        boolean isCorrectSize = numbersDrawn.size() > 4;

        for (int i = 0; i < entry.getValue().length; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < entry.getValue().length; j++) {
                if (!reverse)
                    row.add(entry.getValue()[i][j]);
                else
                    row.add(entry.getValue()[j][i]);
            }

            //Check if there is a bingo
            if (numbersDrawn.containsAll(row) && isCorrectSize) {
                return Optional.of(bingoMap.get(entry.getKey()));
            }
        }

        // Found no winning board
        return Optional.empty();
    }
}

record BingoBoardWithPulledNumbers(List<Integer> drawnNumbers, int index, int[][] board) {

    public Object key() {
        return drawnNumbers;
    }

    public Object value() {
        return board;
    }
}
