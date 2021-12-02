package nel.marco;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {


    public static List<String> readDayOneInput() throws IOException {

        String path = "D:\\coding repo\\AdventOfCode\\2021\\src\\main\\resources\\day2";

        return Files.readAllLines(Path.of(path))
                .stream()
                .collect(Collectors.toList());

    }


    public static int partOne() throws IOException {

        int x = 0;
        int y = 0;

        List<String> list = readDayOneInput();

        for (String line : list) {

            String[] direction = line.split(" ");
            int distance = Integer.parseInt(direction[1]);

            switch (direction[0]) {
                case "forward" -> {
                    x += distance;
                }
                case "down" -> {
                    y += distance;

                }
                case "up" -> {
                    y -= distance;
                }
            }

        }


        return x * y;
    }

    public static int partTwo() throws IOException {


        int x = 0;
        int y = 0;
        int aim = 0;

        List<String> list = readDayOneInput();

        for (String line : list) {

            String[] direction = line.split(" ");
            int distance = Integer.parseInt(direction[1]);

            switch (direction[0]) {
                case "forward" -> {
                    x += distance;
                    y += distance * aim;
                }
                case "down" -> {
                    aim += distance;

                }
                case "up" -> {
                    aim -= distance;
                }
            }

        }
        return x * y;
    }
}
