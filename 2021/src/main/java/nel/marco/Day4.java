package nel.marco;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Day4 {

    Path puzzlePath = Path.of("src/main/resources/day4");


    public List<Integer> getRandomNumbers() throws IOException {
        List<String> input = Files.readAllLines(puzzlePath);

        return Arrays.stream(input.get(0).split(",")).map(Integer::valueOf).collect(Collectors.toList());
    }


    public Map<Integer, int[][]> getAllBingoBoards() throws IOException {
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
        while (input.size() != 0) {
            String line = input.get(0);

            int y = 0;
            while (!line.isBlank()) {
                String[] s = line.split(" ");
                for (int x = 0; x < s.length; x++) {
                    board[y][x] = Integer.parseInt(s[x]);
                }
                y++;
                input.remove(0);
                if (input.size() >= 1) {
                    line = input.get(0).trim().replaceAll("  ", " ");
                }
                if (input.size() == 0) {
                    bingoMap.put(globalMapIndex++, board);
                    break;
                }
                if (line.isBlank()) {
                    input.remove(0);
                    bingoMap.put(globalMapIndex++, board);
                    board = new int[5][5];
                }

            }

        }


        return bingoMap;
    }

    public long part1(List<Integer> randomNumbers, Map<Integer, int[][]> bingoMap) {


        Pair first = getFirstBingo(randomNumbers, bingoMap);


        List<Integer> sumOfDigits = new ArrayList<>();

        for (int x = 0; x < first.board()[0].length; x++) {
            for (int y = 0; y < first.board()[0].length; y++) {
                sumOfDigits.add(first.board()[x][y]);
            }
        }

        first.drawnNumbers().forEach(sumOfDigits::remove);

        long total = sumOfDigits.stream().mapToLong(Integer::longValue).sum();
        Integer lastDrawnDigit = first.drawnNumbers().get(first.drawnNumbers().size() - 1);
        return total * lastDrawnDigit;
    }

    public long part2(List<Integer> randomNumbers, Map<Integer, int[][]> bingoMap) {

        Pair lastWinningBoard = null;
        while (bingoMap.size() > 1) {
            lastWinningBoard = getFirstBingo(randomNumbers, bingoMap);

            bingoMap.remove(lastWinningBoard.index());
        }
        lastWinningBoard = getFirstBingo(randomNumbers, bingoMap);

        List<Integer> sumOfDigits = new ArrayList<>();

        for (int x = 0; x < lastWinningBoard.board()[0].length; x++) {
            for (int y = 0; y < lastWinningBoard.board()[0].length; y++) {
                sumOfDigits.add(lastWinningBoard.board()[x][y]);
            }
        }

        lastWinningBoard.drawnNumbers().forEach(sumOfDigits::remove);

        long total = sumOfDigits.stream().mapToLong(Integer::longValue).sum();
        Integer lastDrawnDigit = lastWinningBoard.drawnNumbers().get(lastWinningBoard.drawnNumbers().size() - 1);
        return total * lastDrawnDigit;
    }

    private Pair getFirstBingo(List<Integer> temp, Map<Integer, int[][]> bingoMap) {

        List<Integer> randomNumbers = new ArrayList<>(temp);
        List<Integer> numbersDrawn = new ArrayList<>();
        while (!randomNumbers.isEmpty()) {
            numbersDrawn.add(randomNumbers.remove(0));
            for (Map.Entry<Integer, int[][]> entry : bingoMap.entrySet()) {
                int[][] row = matchingBoard(bingoMap, numbersDrawn, entry, false);

                if (row != null) {
                    return new Pair(numbersDrawn, entry.getKey(), row);
                }

                int[][] column = matchingBoard(bingoMap, numbersDrawn, entry, true);

                if (column != null) {
                    return new Pair(numbersDrawn, entry.getKey(), column);
                }
            }
        }

        return null;
    }

    private int[][] matchingBoard(Map<Integer, int[][]> bingoMap, List<Integer> numbersDrawn, Map.Entry<Integer, int[][]> entry, boolean reverse) {

        boolean isCorrectSize = numbersDrawn.size() > 4;

        List<List<Integer>> rows = new ArrayList<>();
        //across
        for (int i = 0; i < entry.getValue().length; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < entry.getValue().length; j++) {
                if (!reverse)
                    row.add(entry.getValue()[i][j]);
                else
                    row.add(entry.getValue()[j][i]);
            }
            if (numbersDrawn.containsAll(row) && isCorrectSize) {
                return bingoMap.get(entry.getKey());
            }

            rows.add(row);
        }

        return null;
    }
}

record Pair(List<Integer> drawnNumbers, int index, int[][] board) {

    public Object key() {
        return drawnNumbers;
    }

    public Object value() {
        return board;
    }
}
