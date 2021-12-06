package nel.marco;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day6Test {

    Day6 day6 = new Day6();

    public List<Integer> readInput(boolean readExample) throws IOException {

        String path = "D:\\coding repo\\AdventOfCode\\2021\\src\\main\\resources\\day6";
        if(readExample){
            path = "D:\\coding repo\\AdventOfCode\\2021\\src\\main\\resources\\day6Example";
        }

        List<String> stringList = Files.readAllLines(Path.of(path));

        return new ArrayList<>(Arrays.stream(stringList.get(0).split(",")).toList().stream().map(Integer::valueOf).toList());

    }

    @Test
    public void part1() throws IOException {

        assertEquals(26,day6.part1(readInput(true), 18));
        assertEquals(5934,day6.part1(readInput(true), 80));
        assertEquals(396210,day6.part1(readInput(false), 80));

    }



    @Test
    public void part2() throws IOException {

        assertEquals(BigInteger.valueOf(5934),day6.part2(readInput(true), 80));
        assertEquals(BigInteger.valueOf(26984457539L),day6.part2(readInput(true), 256));
        assertEquals(BigInteger.valueOf(396210),day6.part2(readInput(false), 80));
        assertEquals(BigInteger.valueOf(1770823541496L),day6.part2(readInput(false), 256));
    }

}