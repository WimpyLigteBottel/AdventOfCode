package nel.marco;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day7Test {

    Day7 day7 = new Day7();

    public List<Integer> readInput(boolean readExample) throws IOException {

        String path = "src\\main\\resources\\day7";
        if (readExample) {
            path = "src\\main\\resources\\day7Example";
        }

        List<String> stringList = Files.readAllLines(Path.of(path));

        return new ArrayList<>(Arrays.stream(stringList.get(0).split(",")).toList().stream().map(Integer::valueOf).toList());

    }

    @Test
    void part1() throws IOException {

        assertEquals(37, day7.part1(readInput(true)));
        assertEquals(355592, day7.part1(readInput(false)));

    }

    @Test
    void part2() throws IOException {

        assertEquals(168, day7.part2(readInput(true)));
        assertEquals(101618069, day7.part2(readInput(false)));
    }


}