package nel.marco;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class Day2Test {

    @org.junit.Test
    public void partOne() throws IOException {
        String answer = Day2.partOne()+"";

        Assert.assertNotEquals("-1", answer);
        System.out.println(answer);

    }

    @Test
    public void partTwo() throws IOException {

        String answer = Day2.partTwo()+"";

        Assert.assertNotEquals("-1", answer);
        System.out.println(answer);
    }
}