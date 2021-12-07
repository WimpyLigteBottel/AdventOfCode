package nel.marco;

import java.util.List;

public class Day7 {

    public int part1(List<Integer> crabSubmarines) {

        long sum = crabSubmarines.stream().mapToLong(Integer::longValue).sum();
        long highestNumber = crabSubmarines.stream().max(Integer::compareTo).orElse(-1);

        int lowestFuelCost = Integer.MAX_VALUE;
        long previousFuelCost = Integer.MAX_VALUE;
        for (int i = 0; i < highestNumber; i++) {
            long moveToPosition = (sum / crabSubmarines.size()) - i;
            long totalFuelCost = calculateFuelCost(crabSubmarines, moveToPosition, false);
            lowestFuelCost = (int) Math.min(lowestFuelCost, totalFuelCost);
            if (totalFuelCost > previousFuelCost) {
                break;
            }
            previousFuelCost = totalFuelCost;

        }

        previousFuelCost = Integer.MAX_VALUE;
        for (int i = 0; i < highestNumber; i++) {
            long moveToPosition = (sum / crabSubmarines.size()) + i;
            long totalFuelCost = calculateFuelCost(crabSubmarines, moveToPosition, false);
            lowestFuelCost = (int) Math.min(lowestFuelCost, totalFuelCost);
            if (totalFuelCost > previousFuelCost) {
                break;
            }
            previousFuelCost = totalFuelCost;
        }

        return lowestFuelCost;

    }


    private long calculateFuelCost(List<Integer> crabSubmarines, long avg, boolean withIncrementalMovements) {
        long totalFuelCost = 0;
        for (Integer fuel : crabSubmarines) {

            if (fuel < avg) {
                long tempFuel = avg - fuel;

                if (withIncrementalMovements)
                    tempFuel = factorial(tempFuel);

                totalFuelCost += tempFuel;
            } else if (fuel > avg) {
                long tempFuel = fuel - avg;

                if (withIncrementalMovements)
                    tempFuel = factorial(tempFuel);

                totalFuelCost += tempFuel;
            }
        }
        return totalFuelCost;
    }

    public long factorial(long n) {
        if (n == 1)
            return 1L;

        return n + factorial(n - 1);
    }

    public long part2(List<Integer> crabSubmarines) {

        long sum = crabSubmarines.stream().mapToLong(Integer::longValue).sum();
        long highestNumber = crabSubmarines.stream().max(Integer::compareTo).orElse(-1);

        long lowestFuelCost = Integer.MAX_VALUE;
        long previousFuelCost = Integer.MAX_VALUE;
        for (int i = 0; i < highestNumber; i++) {
            long moveToPosition = (sum / crabSubmarines.size()) - i;
            long totalFuelCost = calculateFuelCost(crabSubmarines, moveToPosition, true);
            lowestFuelCost = (int) Math.min(lowestFuelCost, totalFuelCost);
            if (totalFuelCost > previousFuelCost) {
                break;
            }
            previousFuelCost = totalFuelCost;

        }

        previousFuelCost = Integer.MAX_VALUE;
        for (int i = 0; i < highestNumber; i++) {
            long moveToPosition = (sum / crabSubmarines.size()) + i;
            long totalFuelCost = calculateFuelCost(crabSubmarines, moveToPosition, true);
            lowestFuelCost = (int) Math.min(lowestFuelCost, totalFuelCost);
            if (totalFuelCost > previousFuelCost) {
                break;
            }
            previousFuelCost = totalFuelCost;
        }

        return lowestFuelCost;

    }
}
