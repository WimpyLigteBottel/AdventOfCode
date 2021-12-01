package nel.marco;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day3 {


    public static List<String> readinput() throws IOException {

        String path = "G:\\Code repositery\\AdventOfCode\\src\\main\\resources\\day3";

        return Files.readAllLines(Path.of(path));

    }


    public static List<String> duplicate(List<String> map, int times) {


        for (int x = 0; x < times; x++) {
            for (int i = 0; i < map.size(); i++) {
                String newRow = map.get(i) + map.get(i);
                map.set(i, newRow);
            }
        }

        return map;
    }

    public static long slope(List<String> map, int x, int y) {

        int tempX = x;
        int tempY = y;

        int counter = 0;
        while (y < map.size()) {

            if (map.get(y).charAt(x) == '#') {
                counter++;
            }


            y = y + tempY;
            x = x + tempX;
        }


        return counter;

    }


    public static long partOne() throws IOException {

        List<String> duplicate = duplicate(readinput(), 8);




        return slope(duplicate,3,1);
    }

    public static long partTwo() throws IOException {

        List<String> duplicate = duplicate(readinput(), 8);


        return slope(duplicate,3,1) * slope(duplicate,1,1) * slope(duplicate,5,1) * slope(duplicate,7,1) * slope(duplicate,1,2);
    }
}
