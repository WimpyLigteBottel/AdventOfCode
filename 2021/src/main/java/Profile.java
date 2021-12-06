import nel.marco.Day6;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Profile {
    public static void profile(Runnable runnable, long duration) {
        System.out.printf("Profiling (%,d ms)...%n", duration);
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

        List<Integer> fishes = readInput(false);

        profile(()-> {
            new Day6().part1(fishes,80);
        },5000);

        System.out.println();

        profile(()-> {
            new Day6().part2(fishes,256);
        },5000);
    }

    public static List<Integer> readInput(boolean readExample) throws IOException {

        String path = "D:\\coding repo\\AdventOfCode\\2021\\src\\main\\resources\\day6";
        if(readExample){
            path = "D:\\coding repo\\AdventOfCode\\2021\\src\\main\\resources\\day6Example";
        }

        List<String> stringList = Files.readAllLines(Path.of(path));

        return new ArrayList<>(Arrays.stream(stringList.get(0).split(",")).toList().stream().map(Integer::valueOf).toList());

    }
}
