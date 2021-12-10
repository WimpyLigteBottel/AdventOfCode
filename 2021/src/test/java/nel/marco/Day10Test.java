package nel.marco;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {

    Day10 day10 = new Day10();

    public List<String> readInput(boolean readExample) throws IOException {

        String path = "src\\main\\resources\\day10";
        if (readExample) {
            path = "src\\main\\resources\\day10Example";
        }

        List<String> stringList = Files.readAllLines(Path.of(path));

        return new ArrayList<>(stringList);

    }

    @Test
    void part1() throws IOException {

        assertEquals(26397, day10.part1(readInput(true)));
        assertEquals(339477, day10.part1(readInput(false)));
    }

    @Test
    void part2() throws IOException {
        assertEquals(288957, day10.part2(readInput(true)));
        assertEquals(3049320156L, day10.part2(readInput(false)));
    }
}