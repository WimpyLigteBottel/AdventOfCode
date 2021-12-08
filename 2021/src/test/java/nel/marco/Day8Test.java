package nel.marco;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class Day8Test {

    Day8 day8 = new Day8();

    public List<String> readInput(boolean readExample) throws IOException {

        String path = "src\\main\\resources\\day8";
        if (readExample) {
            path = "src\\main\\resources\\day8Example";
        }

        List<String> stringList = Files.readAllLines(Path.of(path));

        return new ArrayList<>(stringList);

    }


    @Test
    void part1() throws IOException {

        assertEquals(26, day8.part1(readInput(true)));
        assertEquals(26, day8.part1(readInput(false)));
    }

    @Test
    void part2() throws IOException {

        assertEquals(61229, day8.part2(readInput(true)));
        assertEquals(1031553, day8.part2(readInput(false)));
    }
}