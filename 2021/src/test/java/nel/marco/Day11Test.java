package nel.marco;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Test {

    public List<String> readInput(boolean readExample) throws IOException {

        String path = "src\\main\\resources\\day11";
        if (readExample) {
            path = "src\\main\\resources\\day11Example";
        }

        List<String> stringList = Files.readAllLines(Path.of(path));

        return new ArrayList<>(stringList);

    }

    Day11 day11 = new Day11();

    @Test
    void part1() throws IOException {
        assertEquals(1656, day11.part1(readInput(true)));
        assertEquals(-1, day11.part1(readInput(false)));
    }

    @Test
    void part2() {
    }
}