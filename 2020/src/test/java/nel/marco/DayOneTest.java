package nel.marco;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DayOneTest {


    @Test
    public void partOne() throws IOException {
        String answer = Day1.partOne();

        Assert.assertNotEquals("-1", answer);
        System.out.println(answer);

    }

    @Test
    public void partTwo() throws IOException {

        String answer = Day1.partTwo();

        Assert.assertNotEquals("-1", answer);
        System.out.println(answer);
    }
}