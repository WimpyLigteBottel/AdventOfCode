package nel.marco;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class Day1Test {


    @Test
    public void partOne() throws IOException {
        String answer = Day1.partOne();

        assertNotEquals("-1", answer);
        assertEquals("1316", answer);

    }

    @Test
    public void partTwo() throws IOException {

        String answer = Day1.partTwo();

        assertNotEquals("-1", answer);
        assertEquals("1344", answer);
    }
}