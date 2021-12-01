package nel.marco;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;

public class Day4 {


    public static List<String> readinput() throws IOException {
        String path = "D:\\coding repo\\AdventOfCode\\2020\\src\\main\\resources\\day4";

        return Files.readAllLines(Path.of(path));
    }

    public static List<String> foldIntoOneLine(List<String> list) {

        Set<String> newList = new HashSet<>();

        String line = "";
        for (int i = 0; i < list.size(); i++) {
            String inputLine = list.get(i);
            if (inputLine.isBlank()) {
                if (!line.isBlank()) {
                    newList.add(line);
                }

                line = "";
                continue;
            }

            line += inputLine + " ";
        }
        newList.add(line);


        return new ArrayList<>(newList);
    }


    public static String partOne() throws IOException {
        /*
        byr (Birth Year)
        iyr (Issue Year)
        eyr (Expiration Year)
        hgt (Height)
        hcl (Hair Color)
        ecl (Eye Color)
        pid (Passport ID)
        cid (Country ID) - Optional
         */

        List<String> input = foldIntoOneLine(readinput());

        int validPassports = 0;

        for (String line : input) {
            ID id = new ID(line);
            if (id.isValid()) {
                validPassports++;
            }
//            System.out.println(String.join("|", fields));
        }


        return "" + validPassports;
    }

    public static String partTwo() throws IOException {

        List<String> input = foldIntoOneLine(readinput());

        int validPassports = 0;

        for (String line : input) {
            ID id = new ID(line);
            if (id.isValidPart2()) {
                validPassports++;
            }
        }

        return "" + validPassports;
    }
}

class ID {

    boolean printError = false;

    String line = "";

    String byr = null;
    String iyr = null;
    String eyr = null;
    String hgt = null;
    String hcl = null;
    String ecl = null;
    String pid = null;
    String cid = null;

    public ID(String line) {
        var fields = line.split(" ");
        Map<String, String> map = new HashMap<>();
        Arrays.stream(fields).forEach(s -> {
            String[] keyAndValue = s.split(":");
            map.put(keyAndValue[0], keyAndValue[1]);
        });
        this.byr = map.get("byr");
        this.iyr = map.get("iyr");
        this.eyr = map.get("eyr");
        this.hgt = map.get("hgt");
        this.hcl = map.get("hcl");
        this.ecl = map.get("ecl");
        this.pid = map.get("pid");
        this.cid = map.get("cid");
        this.line = line;
    }

    public boolean isValid() {
        return
                byr != null &&
                        iyr != null &&
                        eyr != null &&
                        hgt != null &&
                        hcl != null &&
                        ecl != null &&
                        pid != null;
    }

    /**
     * byr (Birth Year) - four digits; at least 1920 and at most 2002.
     * iyr (Issue Year) - four digits; at least 2010 and at most 2020.
     * eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
     * hgt (Height) - a number followed by either cm or in:
     * If cm, the number must be at least 150 and at most 193.
     * If in, the number must be at least 59 and at most 76.
     * hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
     * ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
     * pid (Passport ID) - a nine-digit number, including leading zeroes.
     * cid (Country ID) - ignored, missing or not.
     *
     * @return
     */
    public boolean isValidPart2() {
        boolean isValid = isValid();

        if (!isValid) {
            return false;
        }

        if (is4DigitNumbersCorrect())
            return false;

        if (isRegexTestsCorrect())
            return false;

        if (!isHeightCorrect()) {
            printOutError("hgt", hgt);
            return false;
        }

        return true;
    }

    private boolean isRegexTestsCorrect() {
        boolean isValid;
        isValid = hcl.matches("#([0-9a-f]{6})");

        if (!isValid) {
            printOutError("hcl", hcl);
            return true;
        }

        isValid = ecl.matches("(amb|blu|brn|gry|grn|hzl|oth)");

        if (!isValid) {
            printOutError("ecl", ecl);
            return true;
        }

        isValid = pid.matches("[\\d]{9}");

        if (!isValid) {
            printOutError("pid", pid);
            return true;
        }
        return false;
    }

    private boolean is4DigitNumbersCorrect() {
        Function<String, Boolean> byrValidation = byr -> Integer.parseInt(byr) > 1919 && Integer.parseInt(byr) < 2003;
        Function<String, Boolean> iyrFunction = iyr -> Integer.parseInt(iyr) > 2009 && Integer.parseInt(iyr) < 2021;
        Function<String, Boolean> eyrFunction = eyr -> Integer.parseInt(eyr) > 2019 && Integer.parseInt(eyr) < 2031;

        boolean isValid;
        isValid = byrValidation.apply(byr);

        if (!isValid) {
            printOutError("byr", byr);
            return true;
        }

        isValid = iyrFunction.apply(iyr);

        if (!isValid) {
            printOutError("iyr", iyr);
            return true;
        }

        isValid = eyrFunction.apply(eyr);

        if (!isValid) {
            printOutError("eyr", eyr);
            return true;
        }
        return false;
    }

    private boolean isHeightCorrect() {
        boolean isValid;
        if (hgt.matches("[\\d]+cm")) {
            String digits = hgt.split("cm")[0];
            isValid = Integer.parseInt(digits) > 149 && Integer.parseInt(digits) < 194;
        } else if (hgt.matches("[\\d]+in")) {
            String digits = hgt.split("in")[0];
            isValid = Integer.parseInt(digits) > 58 && Integer.parseInt(digits) < 77;
        } else {
            isValid = false;
        }
        return isValid;
    }

    private void printOutError(String key, String value) {
        if (printError) {
            System.out.printf("%s:%s is not correct\n ", key, value);
            System.out.printf("line=%s\n", line);
        }
    }

    @Override
    public String toString() {
        return "ID{" +
                "byr='" + byr + '\'' +
                ", iyr='" + iyr + '\'' +
                ", eyr='" + eyr + '\'' +
                ", hgt='" + hgt + '\'' +
                ", hcl='" + hcl + '\'' +
                ", ecl='" + ecl + '\'' +
                ", pid='" + pid + '\'' +
                ", cid='" + cid + '\'' +
                '}';
    }
}
