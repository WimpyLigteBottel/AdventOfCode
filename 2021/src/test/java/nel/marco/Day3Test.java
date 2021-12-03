package nel.marco;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class Day3Test {

    public static List<String> readDayOneInput() throws IOException {

        String path = "D:\\coding repo\\AdventOfCode\\2021\\src\\main\\resources\\day3";

        return Files.readAllLines(Path.of(path))
                .stream()
                .collect(Collectors.toList());

    }
    @Test
    public void partOne() throws IOException {
        String answer = new Day3().part1(readDayOneInput())+"";

        Assert.assertNotEquals("-1", answer);
        System.out.println("part 1:"+answer);

    }

    @Test
    public void partTwo() throws IOException {

        String answer = new Day3().part2(readDayOneInput())+"";

        Assert.assertNotEquals("-1", answer);
        System.out.println("part 2:"+answer);
    }
}