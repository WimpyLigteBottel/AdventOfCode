package nel.marco;

import nel.marco.util.Point;

import java.util.ArrayList;
import java.util.List;

public class Day11 {

    public Octopus[][] setupBoard(List<String> input) {

        Octopus[][] board = new Octopus[input.size()][input.size()];


        for (int y = 0; y < input.size(); y++) {
            String[] line = input.get(y).split("");
            for (int x = 0; x < input.size(); x++) {
                String number = line[x];
                board[y][x] = new Octopus(false, Integer.parseInt(number));
            }
        }

        return board;
    }


    public long part1(List<String> input) {
        Octopus[][] board = setupBoard(input);
        Octopus[][] nextBoard = copyBoard(board);


        long total = 0L;
        for (int i = 0; i < 100; i++) {
            total += increaseAllOctopuseLevels(1, nextBoard);
        }

        return total;
    }

    public long increaseAllOctopuseLevels(int number, Octopus[][] board) {
        long total = 0L;
        long before = total;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                board[y][x] = new Octopus(false, board[y][x].energyLevel() + number);
            }
        }
        total += indicateFlashing(board);

        return total;
    }

    public long indicateFlashing(Octopus[][] board) {
        long counter = 0;

        List<Point<Integer,Integer>> needsToIncrease = new ArrayList<>();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                if (!board[y][x].hasFlashed() && board[y][x].energyLevel() > 8) {
                    board[y][x] = new Octopus(true, board[y][x].energyLevel());
                    needsToIncrease.addAll(increaseAdjacentOctopuses(1, x, y, board));
                    counter++;
                }
            }
        }

        return counter;
    }

    public List<Point> increaseAdjacentOctopuses(int number, int x, int y, Octopus[][] board) {

        List<Point> needsToIncrease = new ArrayList<>();

        //top row
        try {
            Octopus topLeft = board[y - 1][x - 1];
            Octopus octopus = new Octopus(topLeft.hasFlashed(), topLeft.energyLevel() + number);
            needsToIncrease.add(new Point(x,y));
        } catch (Exception e) {
        }
        try {

            Octopus topMiddle = board[y - 1][x];
            Octopus octopus = new Octopus(topMiddle.hasFlashed(), topMiddle.energyLevel() + number);
            needsToIncrease.add(new Point(x,y));
        } catch (Exception e) {
        }
        try {

            Octopus topRight = board[y - 1][x + 1];
            Octopus octopus = new Octopus(topRight.hasFlashed(), topRight.energyLevel() + number);
            needsToIncrease.add(octopus);
        } catch (Exception e) {
        }

        //middle row
        try {
            Octopus middleLeft = board[y][x - 1];
            Octopus octopus = new Octopus(middleLeft.hasFlashed(), middleLeft.energyLevel() + number);
            needsToIncrease.add(octopus);
        } catch (Exception e) {
        }
        try {
            Octopus middleRight = board[y][x + 1];
            Octopus octopus = new Octopus(middleRight.hasFlashed(), middleRight.energyLevel() + number);
            needsToIncrease.add(octopus);
        } catch (Exception e) {
        }


        //bottom row
        try {
            Octopus bottomLeft = board[y - 1][x - 1];
            Octopus octopus = new Octopus(bottomLeft.hasFlashed(), bottomLeft.energyLevel() + number);
            needsToIncrease.add(octopus);
        } catch (Exception e) {
        }
        try {
            Octopus bottomMiddle = board[y - 1][x];
            Octopus octopus = new Octopus(bottomMiddle.hasFlashed(), bottomMiddle.energyLevel() + number);
            needsToIncrease.add(octopus);
        } catch (Exception e) {
        }
        try {
            Octopus bottomRight = board[y - 1][x + 1];
            Octopus octopus = new Octopus(bottomRight.hasFlashed(), bottomRight.energyLevel() + number);
            needsToIncrease.add(octopus);
        } catch (Exception e) {
        }

        return needsToIncrease;
    }

    public Octopus[][] copyBoard(Octopus[][] currentBoard) {

        Octopus[][] board = new Octopus[currentBoard.length][currentBoard.length];

        for (int y = 0; y < currentBoard.length; y++) {
            for (int x = 0; x < currentBoard.length; x++) {
                board[y][x] = currentBoard[y][x];
            }
        }
        return board;
    }


    public long part2(List<String> input) {
        return -1;
    }
}

record Octopus(boolean hasFlashed, int energyLevel) {
};
