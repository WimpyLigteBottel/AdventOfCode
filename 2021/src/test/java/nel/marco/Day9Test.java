package nel.marco;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Test {

    Day9 day9 = new Day9();

    public List<String> readInput(boolean readExample) throws IOException {

        String path = "src\\main\\resources\\day9";
        if (readExample) {
            path = "src\\main\\resources\\day9Example";
        }

        List<String> stringList = Files.readAllLines(Path.of(path));

        return new ArrayList<>(stringList);

    }


    @Test
    void part1() throws IOException {

        assertEquals(15, day9.part1(readInput(true)));
        assertEquals(458, day9.part1(readInput(false)));
    }

    @Test
    void part2() throws IOException {

        assertEquals(1134, day9.part2(readInput(true)));
        assertEquals(1391940, day9.part2(readInput(false)));
    }
}