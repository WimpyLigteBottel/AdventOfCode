package nel.marco;

import java.util.ArrayList;
import java.util.List;

public class Day3 {
    public  int part1(List<String> lines) {
        final int gr = toDecimal(calculatEpsilonRateOrGamaRate(lines, true));
        final int er = toDecimal(calculatEpsilonRateOrGamaRate(lines, false));

        return gr * er;
    }


    public int part2(List<String> lines) {

        final int oxygenRate = toDecimal(calculateOxyGenOrCo2(lines, 0, false));
        final int Co2Scraper = toDecimal(calculateOxyGenOrCo2(lines, 0, true));

        return oxygenRate * Co2Scraper;
    }

    private String calculatEpsilonRateOrGamaRate(List<String> lines, boolean isGamaRate) {
        String buildUpGamaRate = "";

        for (int x = 0; x < lines.get(0).length(); x++) {
            int commonDigit = findMostCommonDigit(lines, x, isGamaRate);
            if (commonDigit == 0) {
                buildUpGamaRate += "1";
            } else {
                buildUpGamaRate += "0";
            }
        }
        return buildUpGamaRate;
    }

    private int findMostCommonDigit(List<String> lines, int position, boolean reverse) {
        int count0 = 0;
        int count1 = 0;

        for (int i = 0; i < lines.size(); i++) {
            final String digit = lines.get(i).charAt(position) + "";
            if (digit.equals("1")) {
                count1++;
            } else {
                count0++;
            }
        }

        int answer = -1;

        if (count1 > count0) {
            answer = 1;
        } else if (count1 < count0) {
            answer = 0;
        } else {
            answer = 1;
        }

        if (reverse) {
            if (answer == 1)
                answer = 0;
            else
                answer = 1;
        }

        return answer;
    }

    public String calculateOxyGenOrCo2(List<String> lines, int x, boolean isCo2) {

        if (lines.size() == 1) {
            return lines.get(0);
        }

        List<String> keepOnlyTheseNumbers = new ArrayList<>();
        int digit = findMostCommonDigit(lines, x, isCo2);

        for (int i = 0; i < lines.size(); i++) {
            String c = lines.get(i).charAt(x) + "";

            if (c.intern().equals(digit + ""))
                keepOnlyTheseNumbers.add(lines.get(i));
        }

        return calculateOxyGenOrCo2(keepOnlyTheseNumbers, x + 1, isCo2);

    }

    private int toDecimal(String text) {
        return Integer.parseInt(text, 2);
    }
}
