package nel.marco;

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


        long total = 0L;
        for (int i = 0; i < 100; i++) {
            increaseAllOctopuseLevels(1, board);
            total += indicateFlashing(board);
        }

        return total;
    }

    public void printBoard(Octopus[][] board) {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                System.out.print(board[y][x].energyLevel());
            }
            System.out.println();
        }
    }

    public void increaseAllOctopuseLevels(int number, Octopus[][] board) {

        boolean hasTenEnergy = false;

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                board[y][x] = new Octopus(false, board[y][x].energyLevel() + number);
                if(board[y][x].energyLevel() >= 10)
                    hasTenEnergy = true;
            }
        }


        while(hasTenEnergy){
            hasTenEnergy = false;
            for (int y = 0; y < board.length; y++) {
                for (int x = 0; x < board.length; x++) {
                    if (board[y][x].energyLevel() >= 10) {
                        board[y][x] = new Octopus(true, 0);
                        increaseAdjacentOctopuses(1, x, y, board);
                        hasTenEnergy = true;
                    }
                }
            }
        }

    }

    public void updateFlashing(Octopus octopus) {
        if (octopus.energyLevel() >= 10){
            octopus = new Octopus(true, 0);
        }
    }

    public long indicateFlashing(Octopus[][] board) {
        long counter = 0;

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                if (!board[y][x].hasFlashed() && board[y][x].energyLevel() > 9) {
                    board[y][x] = new Octopus(true, 0);
                }
            }
        }

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                if (board[y][x].hasFlashed()) {
                    counter++;
                }
            }
        }


        return counter;
    }

    private boolean hasNotFlashed(Octopus octopus) {

        return !octopus.hasFlashed();
    }

    public void increaseAdjacentOctopuses(int number, int x, int y, Octopus[][] board) {

        //top row
        try {
            Octopus topLeft = board[y - 1][x - 1];
            Octopus octopus = new Octopus(topLeft.hasFlashed(), topLeft.energyLevel() + number);
            if (hasNotFlashed(octopus))
                board[y - 1][x - 1] = octopus;
            updateFlashing(octopus);
        } catch (Exception ignore) {
        }
        try {

            Octopus topMiddle = board[y - 1][x];
            Octopus octopus = new Octopus(topMiddle.hasFlashed(), topMiddle.energyLevel() + number);
            if (hasNotFlashed(octopus))
                board[y - 1][x] = octopus;
            updateFlashing(octopus);
        } catch (Exception ignore) {
        }
        try {

            Octopus topRight = board[y - 1][x + 1];
            Octopus octopus = new Octopus(topRight.hasFlashed(), topRight.energyLevel() + number);
            if (hasNotFlashed(octopus))
                board[y - 1][x + 1] = octopus;
            updateFlashing(octopus);
        } catch (Exception ignore) {
        }

        //middle row
        try {
            Octopus middleLeft = board[y][x - 1];
            Octopus octopus = new Octopus(middleLeft.hasFlashed(), middleLeft.energyLevel() + number);
            if (hasNotFlashed(octopus))
                board[y][x - 1] = octopus;
            updateFlashing(octopus);
        } catch (Exception ignore) {
        }
        try {
            Octopus middleRight = board[y][x + 1];
            Octopus octopus = new Octopus(middleRight.hasFlashed(), middleRight.energyLevel() + number);
            if (hasNotFlashed(octopus))
                board[y][x + 1] = octopus;
            updateFlashing(octopus);
        } catch (Exception ignore) {
        }


        //bottom row
        try {
            Octopus bottomLeft = board[y + 1][x - 1];
            Octopus octopus = new Octopus(bottomLeft.hasFlashed(), bottomLeft.energyLevel() + number);
            if (hasNotFlashed(octopus))
                board[y + 1][x - 1] = octopus;
            updateFlashing(octopus);
        } catch (Exception ignore) {
        }
        try {
            Octopus bottomMiddle = board[y + 1][x];
            Octopus octopus = new Octopus(bottomMiddle.hasFlashed(), bottomMiddle.energyLevel() + number);
            if (hasNotFlashed(octopus))
                board[y + 1][x] = octopus;
            updateFlashing(octopus);
        } catch (Exception ignore) {
        }
        try {
            Octopus bottomRight = board[y + 1][x + 1];
            Octopus octopus = new Octopus(bottomRight.hasFlashed(), bottomRight.energyLevel() + number);
            if (hasNotFlashed(octopus))
                board[y + 1][x + 1] = octopus;
            updateFlashing(octopus);
        } catch (Exception ignore) {
        }

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
        Octopus[][] board = setupBoard(input);

        for (int i = 1; i < 1000; i++) {
            increaseAllOctopuseLevels(1, board);
            if(indicateFlashing(board) == 100){
                return i;
            }
        }

        return -1;
    }
}

record Octopus(boolean hasFlashed, int energyLevel) {
};
