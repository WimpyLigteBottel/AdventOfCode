package nel.marco;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day4 {


    public static List<String> readinput() throws IOException {


        String path = "D:\\coding repo\\AdventOfCode\\2020\\src\\main\\resources\\day4";

        return Files.readAllLines(Path.of(path));


//        return Arrays.stream("""
//                pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
//                hcl:#623a2f
//
//                eyr:2029 ecl:blu cid:129 byr:1989
//                iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm
//
//                hcl:#888785
//                hgt:164cm byr:2001 iyr:2015 cid:88
//                pid:545766238 ecl:hzl
//                eyr:2022
//
//                iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719
//
//                """.split("\n")).toList();

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
            var fields = line.split(" ");
            Map<String, String> map = new HashMap<>();
            Arrays.stream(fields).forEach(s -> {
                String[] keyAndValue = s.split(":");
                map.put(keyAndValue[0], keyAndValue[1]);
            });

            ID id = new ID(map);
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

    public ID(Map<String, String> map) {
        byr = map.get("byr");
        iyr = map.get("iyr");
        eyr = map.get("eyr");
        hgt = map.get("hgt");
        hcl = map.get("hcl");
        ecl = map.get("ecl");
        pid = map.get("pid");
        cid = map.get("cid");
    }

    public ID(String line) {
        var fields = line.split(" ");
        Map<String, String> map = new HashMap<>();
        Arrays.stream(fields).forEach(s -> {
            String[] keyAndValue = s.split(":");
            map.put(keyAndValue[0], keyAndValue[1]);
        });
        byr = map.get("byr");
        iyr = map.get("iyr");
        eyr = map.get("eyr");
        hgt = map.get("hgt");
        hcl = map.get("hcl");
        ecl = map.get("ecl");
        pid = map.get("pid");
        cid = map.get("cid");
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

        isValid = Integer.parseInt(byr) > 1919 && Integer.parseInt(byr) < 2003;

        if (!isValid) {
            printOutError("byr", byr);
            return false;
        }

        isValid = Integer.parseInt(iyr) > 2009 && Integer.parseInt(iyr) < 2021;

        if (!isValid) {
            printOutError("iyr", iyr);
            return false;
        }

        isValid = Integer.parseInt(eyr) > 2019 && Integer.parseInt(eyr) < 2031;

        if (!isValid) {
            printOutError("eyr", eyr);
            return false;
        }

        isValid = isHeightCorrect();

        if (!isValid) {
            printOutError("hgt", hgt);
            return false;
        }

        isValid = hcl.matches("#([0-9a-f]{6})");

        if (!isValid) {
            printOutError("hcl", hcl);
            return false;
        }

        isValid = Arrays.stream("amb blu brn gry grn hzl oth".split(" ")).toList().contains(ecl);

        if (!isValid) {
            printOutError("ecl", ecl);
            return false;
        }

        isValid = pid.matches("[\\d]{9}");

        if (!isValid) {
            printOutError("pid", pid);
            return false;
        }

        return true;
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
        if(printError){
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