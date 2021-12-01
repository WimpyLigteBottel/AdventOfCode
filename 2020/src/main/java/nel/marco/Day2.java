package nel.marco;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day2 {


    public static List<String> readinput() throws IOException {

        String path = "G:\\Code repositery\\AdventOfCode\\src\\main\\resources\\day2";

        return Files.readAllLines(Path.of(path));

    }


    public static int partOne() throws IOException {

        int counter[] = {0};
        readinput().forEach(s -> {


            String[] parts = s.split(": ");

            //2-7 p: pbhhzpmppb

            String password = parts[1];
            char letter = parts[0].charAt(parts[0].length() - 1);

            String range = parts[0].substring(0, parts[0].indexOf(" "));


            int start = Integer.parseInt(range.split("-")[0]);
            int end = Integer.parseInt(range.split("-")[1]);

            long amountOftimesLetterAppears = Arrays.stream(password.split("")).filter(s1 -> s1.equalsIgnoreCase(letter + "")).count();


            boolean containsLetter = password.contains(letter + "");
            boolean isBetween = amountOftimesLetterAppears >= start && amountOftimesLetterAppears <= end;
            if (isBetween && containsLetter) {
                counter[0]++;
            }


        });


        return counter[0];
    }

    public static int partTwo() throws IOException {
        int counter[] = {0};
        readinput().forEach(s -> {


            String[] parts = s.split(":");

            //2-7 p: pbhhzpmppb

            String password = parts[1];
            char letter = parts[0].charAt(parts[0].length() - 1);

            String range = parts[0].substring(0, parts[0].indexOf(" "));


            int start = Integer.parseInt(range.split("-")[0]);
            int end = Integer.parseInt(range.split("-")[1]);

            boolean isValidLength = start < password.length() && end < password.length();
            if (isValidLength) {
                boolean containsLetter = password.charAt(start) == letter || password.charAt(end) == letter;
                if (containsLetter && password.charAt(start) != password.charAt(end)) {
                    counter[0]++;
                }
            }


        });


        return counter[0];
    }
}
