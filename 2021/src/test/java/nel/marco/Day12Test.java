package nel.marco;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {

    Day12 day12 = new Day12();


    public List<String> readInput(boolean readExample) throws IOException {

        String path = "src\\main\\resources\\day12";
        if (readExample) {
            path = "src\\main\\resources\\day12Example";
        }

        List<String> stringList = Files.readAllLines(Path.of(path));

        return new ArrayList<>(stringList);

    }

    @Test
    void part1() throws IOException {
        assertEquals(226, day12.part1(readInput(true)));
        assertEquals(4378, day12.part1(readInput(false)));
    }

    @Test
    void part2() throws IOException {
        assertEquals(103, day12.part2(readInput(true)), "Part 2 Example message is wrong");//6231
        assertEquals(-1, day12.part2(readInput(false)),"Part 2 actual is wrong");
    }
}