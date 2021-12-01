package nel.marco;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {


    public static List<Integer> readDayOneInput() throws IOException {

        String path = "G:\\Code repositery\\AdventOfCode\\src\\main\\resources\\day1";

        return Files.readAllLines(Path.of(path))
                .stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

    }


    public static String partOne() throws IOException {

        List<Integer> input = readDayOneInput();

        for (int i = 0; i < input.size(); i++) {
            for (int j = 1; j < input.size(); j++) {

                Integer first = input.get(i);
                Integer second = input.get(j);
                if (first + second == 2020) {
                    return String.valueOf(first * second);
                }

            }
        }
        return "-1";
    }

    public static String partTwo() throws IOException {

        List<Integer> input = readDayOneInput();

        for (int i = 0; i < input.size(); i++) {
            for (int j = 1; j < input.size(); j++) {
                for (int x = 2; x < input.size(); x++) {

                    Integer first = input.get(i);
                    Integer second = input.get(j);
                    Integer third = input.get(x);

                    if (first + second + third == 2020) {
                        return String.valueOf(first * second * third);
                    }

                }


            }
        }
        return "-1";
    }
}
