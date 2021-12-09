import nel.marco.Day8;
import nel.marco.Day9;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Profile {
    public static void profile(Runnable runnable, String part, long duration) {
        System.out.printf("Profiling %s (%,d ms)...%n", part, duration);
        DescriptiveStatistics statistics = new DescriptiveStatistics();

        long end = System.currentTimeMillis() + duration;
        while (System.currentTimeMillis() < end) {
            long start = System.nanoTime();
            runnable.run();
            statistics.addValue(System.nanoTime() - start);
        }

        System.out.printf("        N: %,d%n", statistics.getN());
        System.out.printf("        μ: %,.0f μs, σ: %,.0f μs%n", statistics.getMean() / 1000, statistics.getStandardDeviation() / 1000);
        System.out.printf("      min: %,.0f μs, max: %,.0f μs%n", statistics.getMin() / 1000, statistics.getMax() / 1000);
        System.out.printf("   median: %,.0f μs, skewness: %,.0f μs, kurtosis: %,.0f μs%n", statistics.getPercentile(.5) / 1000, statistics.getSkewness() / 1000, statistics.getKurtosis() / 1000);
    }

    public static void main(String[] args) throws IOException {

        List<String> fishes = readInput(false);

        Day9 day = new Day9();
        profile(() -> {
            day.part1(fishes);
        }, "(day 9.1)", 5000);

        System.out.println();

        profile(() -> {
            day.part2(fishes);
        }, "(day 9.2)", 5000);
    }

    public static List<String> readInput(boolean readExample) throws IOException {

        String path = "D:\\coding repo\\AdventOfCode\\2021\\src\\main\\resources\\day9";
        if (readExample) {
            path = "D:\\coding repo\\AdventOfCode\\2021\\src\\main\\resources\\day9Example";
        }

        List<String> stringList = Files.readAllLines(Path.of(path));

        return new ArrayList<>(stringList);

    }
}
