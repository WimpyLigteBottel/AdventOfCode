package nel.marco;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day4Test {

    @Test
    void readinput() throws IOException {
        System.out.println(Day4.readinput());

        assertEquals(957, Day4.readinput().size());
    }

    @Test
    void partOne() throws IOException {

        var answer1 = Day4.partOne();
        System.out.println("answer1 = " + answer1);

        assertEquals("190", answer1);
    }

    @Test
    void partTwo() throws IOException {

        var answer2 = Day4.partTwo();
        System.out.println("answer2 = " + answer2);
        assertEquals("121", answer2);


    }

    @Test
    public void testValidation_byrTests() {
        // four digits; at least 1920 and at most 2002.
        assertEquals(false, new ID("byr:1919 pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 hcl:#623a2f").isValidPart2());
        assertEquals(true, new ID("byr:1920 pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 hcl:#623a2f").isValidPart2());
        assertEquals(true, new ID("byr:1925 pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 hcl:#623a2f").isValidPart2());
        assertEquals(true, new ID("byr:2002 pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 hcl:#623a2f").isValidPart2());
        assertEquals(false, new ID("byr:2003 pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 hcl:#623a2f").isValidPart2());
    }

    @Test
    public void testValidation_iyrTests() {
        // four digits; at least 2010 and at most 2020.
        assertEquals(false, new ID("iyr:2009 byr:2002 pid:087499704 hgt:74in ecl:grn eyr:2030 hcl:#623a2f").isValidPart2());
        assertEquals(true, new ID("iyr:2010 byr:2002 pid:087499704 hgt:74in ecl:grn eyr:2030 hcl:#623a2f").isValidPart2());
        assertEquals(true, new ID("iyr:2012 byr:2002 pid:087499704 hgt:74in ecl:grn eyr:2030 hcl:#623a2f").isValidPart2());
        assertEquals(true, new ID("iyr:2020 byr:2002 pid:087499704 hgt:74in ecl:grn eyr:2030 hcl:#623a2f").isValidPart2());
        assertEquals(false, new ID("iyr:2021 byr:2002 pid:087499704 hgt:74in ecl:grn eyr:2030 hcl:#623a2f").isValidPart2());
    }


    @Test
    public void testValidation_eyrTests() {
        // four digits; at least 2020 and at most 2030.
        assertEquals(false, new ID("eyr:2019 iyr:2010 byr:2002 pid:087499704 hgt:74in ecl:grn hcl:#623a2f").isValidPart2());
        assertEquals(true, new ID("eyr:2020 iyr:2010 byr:2002 pid:087499704 hgt:74in ecl:grn hcl:#623a2f").isValidPart2());
        assertEquals(true, new ID("eyr:2025 iyr:2012 byr:2002 pid:087499704 hgt:74in ecl:grn hcl:#623a2f").isValidPart2());
        assertEquals(true, new ID("eyr:2030 iyr:2020 byr:2002 pid:087499704 hgt:74in ecl:grn hcl:#623a2f").isValidPart2());
        assertEquals(false, new ID("eyr:2031 iyr:2010 byr:2002 pid:087499704 hgt:74in ecl:grn hcl:#623a2f").isValidPart2());
    }

    @Test
    public void testValidation_hgtTests() {
        /*
         * hgt (Height) - a number followed by either cm or in:
         * If cm, the number must be at least 150 and at most 193.
         * If in, the number must be at least 59 and at most 76.
         */
        assertEquals(false, new ID("hgt:58in eyr:2025 iyr:2010 byr:2002 pid:087499704 ecl:grn hcl:#623a2f").isValidPart2());
        assertEquals(true, new ID("hgt:59in eyr:2025 iyr:2010 byr:2002 pid:087499704 ecl:grn hcl:#623a2f").isValidPart2());
        assertEquals(true, new ID("hgt:74in eyr:2025 iyr:2012 byr:2002 pid:087499704 ecl:grn hcl:#623a2f").isValidPart2());
        assertEquals(true, new ID("hgt:76in eyr:2025 iyr:2020 byr:2002 pid:087499704 ecl:grn hcl:#623a2f").isValidPart2());
        assertEquals(false, new ID("hgt:77in eyr:2025 iyr:2010 byr:2002 pid:087499704 ecl:grn hcl:#623a2f").isValidPart2());


        assertEquals(false, new ID("hgt:149cm eyr:2025 iyr:2010 byr:2002 pid:087499704 ecl:grn hcl:#623a2f").isValidPart2());
        assertEquals(true, new ID("hgt:150cm eyr:2025 iyr:2010 byr:2002 pid:087499704 ecl:grn hcl:#623a2f").isValidPart2());
        assertEquals(true, new ID("hgt:150cm eyr:2025 iyr:2012 byr:2002 pid:087499704 ecl:grn hcl:#623a2f").isValidPart2());
        assertEquals(true, new ID("hgt:193cm eyr:2025 iyr:2020 byr:2002 pid:087499704 ecl:grn hcl:#623a2f").isValidPart2());
        assertEquals(false, new ID("hgt:194cm eyr:2025 iyr:2010 byr:2002 pid:087499704 ecl:grn hcl:#623a2f").isValidPart2());
    }

    @Test
    public void testValidation_hclTests() {
        /*
         *  a # followed by exactly six characters 0-9 or a-f.
         */
        assertEquals(false, new ID("hcl:#623a2 hgt:74in eyr:2025 iyr:2010 byr:2002 pid:087499704 ecl:grn").isValidPart2());
        assertEquals(false, new ID("hcl:623a24 hgt:74in eyr:2025 iyr:2010 byr:2002 pid:087499704 ecl:grn").isValidPart2());
        assertEquals(true, new ID("hcl:#6aaa2f hgt:74in eyr:2025 iyr:2010 byr:2002 pid:087499704 ecl:grn").isValidPart2());
        assertEquals(true, new ID("hcl:#623f2f hgt:74in eyr:2025 iyr:2012 byr:2002 pid:087499704 ecl:grn").isValidPart2());
        assertEquals(true, new ID("hcl:#6a3f2f hgt:74in eyr:2025 iyr:2020 byr:2002 pid:087499704 ecl:grn").isValidPart2());
        assertEquals(false, new ID("hcl:#623a2fX hgt:74in eyr:2025 iyr:2010 byr:2002 pid:087499704 ecl:grn").isValidPart2());

    }

    @Test
    public void testValidation_eclTests() {
        /*
         *  exactly one of: amb blu brn gry grn hzl oth.
         */
        assertEquals(true, new ID("ecl:amb hcl:#6aaa2f hgt:74in eyr:2025 iyr:2010 byr:2002 pid:087499704").isValidPart2());
        assertEquals(true, new ID("ecl:blu hcl:#6aaa2f hgt:74in eyr:2025 iyr:2010 byr:2002 pid:087499704").isValidPart2());
        assertEquals(true, new ID("ecl:brn hcl:#6aaa2f hgt:74in eyr:2025 iyr:2012 byr:2002 pid:087499704").isValidPart2());
        assertEquals(true, new ID("ecl:gry hcl:#6aaa2f hgt:74in eyr:2025 iyr:2020 byr:2002 pid:087499704").isValidPart2());
        assertEquals(true, new ID("ecl:hzl hcl:#6aaa2f hgt:74in eyr:2025 iyr:2010 byr:2002 pid:087499704").isValidPart2());
        assertEquals(true, new ID("ecl:oth hcl:#6aaa2f hgt:74in eyr:2025 iyr:2010 byr:2002 pid:087499704").isValidPart2());
        assertEquals(false, new ID("ecl:aaa hcl:#6aaa2f hgt:74in eyr:2025 iyr:2010 byr:2002 pid:087499704").isValidPart2());
        assertEquals(false, new ID("ecl:AMB hcl:#6aaa2f hgt:74in eyr:2025 iyr:2010 byr:2002 pid:087499704").isValidPart2());

    }

    @Test
    public void testValidation_pidTests() {
        /*
         *  pid (Passport ID) - a nine-digit number, including leading zeroes.
         */
        assertEquals(true, new ID("pid:087499702 ecl:amb hcl:#6aaa2f hgt:74in eyr:2025 iyr:2010 byr:2002").isValidPart2());
        assertEquals(true, new ID("pid:000000004 ecl:amb hcl:#6aaa2f hgt:74in eyr:2025 iyr:2010 byr:2002").isValidPart2());
        assertEquals(true, new ID("pid:000000000 ecl:amb hcl:#6aaa2f hgt:74in eyr:2025 iyr:2012 byr:2002").isValidPart2());
        assertEquals(false, new ID("pid:12345678 ecl:amb hcl:#6aaa2f hgt:74in eyr:2025 iyr:2012 byr:2002").isValidPart2());
        assertEquals(false, new ID("pid:2199562577 ecl:amb hcl:#6aaa2f hgt:74in eyr:2025 iyr:2012 byr:2002").isValidPart2());
        assertEquals(false, new ID("pid:1655089174 ecl:amb hcl:#6aaa2f hgt:74in eyr:2025 iyr:2012 byr:2002").isValidPart2());

    }
}