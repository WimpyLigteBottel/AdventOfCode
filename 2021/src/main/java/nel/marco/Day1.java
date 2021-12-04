package nel.marco;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {


    public static List<Integer> readDayOneInput() throws IOException {

        String path = "D:\\coding repo\\AdventOfCode\\2021\\src\\main\\resources\\day1";

        return Files.readAllLines(Path.of(path))
                .stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

    }


    public static String partOne() throws IOException {

        int increase = 0;

        List<Integer> list = readDayOneInput();
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) < list.get(i)) {
                increase++;
            }
        }

        return increase + "";
    }

    public static String partTwo() throws IOException {

        int increase = 0;

        List<Integer> list = readDayOneInput();
        for (int i = 1; i < list.size() - 2; i++) {

            int previousSum = list.get(i - 1) + list.get(i) + list.get(i + 1);
            int sum = list.get(i) + list.get(i + 1) + list.get(i + 2);


            if (previousSum < sum) {
                increase++;
            }
        }

        return increase + "";
    }
}
