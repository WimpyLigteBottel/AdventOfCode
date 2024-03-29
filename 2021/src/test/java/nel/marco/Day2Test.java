package nel.marco;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class Day2Test {

    @Test
    public void partOne() throws IOException {
        String answer = Day2.partOne() + "";

        assertNotEquals("-1", answer);
        assertEquals("1484118", answer);

    }

    @Test
    public void partTwo() throws IOException {

        String answer = Day2.partTwo() + "";

        assertNotEquals("-1", answer);
        assertEquals("1463827010", answer);
    }
}