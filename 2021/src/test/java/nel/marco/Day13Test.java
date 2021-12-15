package nel.marco;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day13Test {

    Day13 day13 = new Day13();

    public List<String> readInput(boolean readExample) throws IOException {

        String path = "src\\main\\resources\\day13";
        if (readExample) {
            path = "src\\main\\resources\\day13Example";
        }

        List<String> stringList = Files.readAllLines(Path.of(path));

        return new ArrayList<>(stringList);

    }

    @Test
    void part1() throws IOException {
        assertEquals(-1, day13.part1(readInput(true)));
        assertEquals(-1, day13.part1(readInput(false)));
    }

    @Test
    void part2() throws IOException {
        assertEquals(-1, day13.part2(readInput(true)), "Part 2 Example message is wrong");//6231
        assertEquals(-1, day13.part2(readInput(false)),"Part 2 actual is wrong");
    }

}