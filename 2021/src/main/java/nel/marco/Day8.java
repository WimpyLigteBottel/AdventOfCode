package nel.marco;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day8 {


    public long part1(List<String> scrambledCodeInput) {
        long totalInstancesOfDigits = 0;

        for (int i = 0; i < scrambledCodeInput.size(); i++) {

            String line = scrambledCodeInput.get(i);
            String[] parts = line.split("\\|");
            String[] scrambled4DigitInput = parts[1].split(" "); // XXXXXX | THIS OUTPUT PART
            for (int x = 0; x < scrambled4DigitInput.length; x++) {
                String convertedString = convertStringToDigit(scrambled4DigitInput[x]);
                if (isDigit(convertedString)) {
                    totalInstancesOfDigits++;
                    scrambled4DigitInput[x] = convertedString;
                }
            }
        }

        return totalInstancesOfDigits;
    }

    private boolean isDigit(String convertedString) {

        try {
            Integer.parseInt(convertedString);
            return true;
        } catch (NumberFormatException e) {

        }

        return false;
    }

    private String convertStringToDigit(String scrambledInput) {
        DigitDisplay digitDisplay = new DigitDisplay();

        String s = switch (scrambledInput.length()) {
            case 2 -> {
                digitDisplay.modifyToLookAs(1);

                yield "1";
            }
            case 3 -> {
                digitDisplay.modifyToLookAs(7);
                yield "7";
            }
            case 4 -> {
                digitDisplay.modifyToLookAs(4);
                yield "4";
            }
            case 7 -> {
                digitDisplay.modifyToLookAs(8);
                yield "8";
            }
            default -> scrambledInput;
        };

        return s;
    }


    public long part2(List<String> scrambledCodeInput) {

        long total = 0;
        for (int i = 0; i < scrambledCodeInput.size(); i++) {
            Map<Integer, String> decoded = new HashMap<>();

            String line = scrambledCodeInput.get(i);
            String[] parts = line.split("\\|");
            String[] scrambled = parts[0].split(" "); // THIS INPUT | XXXXXXXXXX
            String[] scrambled4DigitInput = parts[1].split(" "); // XXXXXX | THIS OUTPUT PART
            updateDecodedWith1To4(decoded, scrambled4DigitInput);
            updateDecodedWith1To4(decoded, scrambled);

            DigitDisplay digitDisplay = new DigitDisplay();
            digitDisplay.setDecoded(decoded);

            updateDecodedMapToContainAllDigits(decoded, scrambled, digitDisplay);


            for (int j = 0; j < scrambled4DigitInput.length; j++) {
                if (scrambled4DigitInput[j].length() > 0)
                    scrambled4DigitInput[j] = digitDisplay.decode(scrambled4DigitInput[j]) + "";
            }
            int outputValue = Integer.parseInt(String.join("", scrambled4DigitInput));
            total += outputValue;

        }

        return total;

    }

    private void updateDecodedMapToContainAllDigits(Map<Integer, String> decoded, String[] scrambled, DigitDisplay digitDisplay) {

        digitDisplay.decode(new Pair(1, decoded.get(1)), new Pair(7, decoded.get(7))); // confirm top

        //confirm top-right
        for (int j = 0; j < scrambled.length; j++) {
            Pair a = new Pair(8, decoded.get(8));
            Pair b = new Pair(-1, scrambled[j]);

            if(decoded.containsValue(scrambled[j])){
                scrambled[j] = "";
                continue;
            }
            if (scrambled[j].length() == 6) {
                digitDisplay.decode(a, b);
            }
            decoded.putAll(digitDisplay.decoded);
        }

        scrambled = Arrays.stream(scrambled)
                .filter(s -> !s.isBlank())
                .toArray(String[]::new);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < scrambled.length; j++) {
                Pair a = new Pair(8, decoded.get(8));
                Pair b = new Pair(-1, scrambled[j]);
                if(decoded.containsValue(scrambled[j])){
                    scrambled[j] = "";
                    continue;
                }
                if (scrambled[j].length() == 5) {
                    digitDisplay.decode(a, b);
                }
                decoded.putAll(digitDisplay.decoded);
            }
            scrambled = Arrays.stream(scrambled)
                    .filter(s -> !s.isBlank())
                    .toArray(String[]::new);
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < scrambled.length; j++) {
                Pair a = new Pair(8, decoded.get(8));
                Pair b = new Pair(-1, scrambled[j]);
                if(decoded.containsValue(scrambled[j])){
                    scrambled[j] = "";
                    continue;
                }
                digitDisplay.decode(a, b);
                decoded.putAll(digitDisplay.decoded);
            }
            scrambled = Arrays.stream(scrambled)
                    .filter(s -> !s.isBlank())
                    .toArray(String[]::new);
        }

    }

    private void updateDecodedWith1To4(Map<Integer, String> decoded, String[] scrambled4DigitInput) {
        for (int x = 0; x < scrambled4DigitInput.length; x++) {
            String convertedString = convertStringToDigit(scrambled4DigitInput[x]);
            if (isDigit(convertedString)) {
                decoded.put(Integer.parseInt(convertedString), scrambled4DigitInput[x]);
            }
        }
    }
}

class DigitDisplay {

    Map<Integer, String> decoded = new HashMap<>();


    String template = """
            .1111.
            2    7
            2    7
            .3333.
            4    6
            4    6
            .5555.
            """;

    String modifyableText = """
            .1111.
            2    7
            2    7
            .3333.
            4    6
            4    6
            .5555.
            """;

    String topMiddle = "";
    String middleMiddle = "";
    String bottomMiddle = "";
    String topRight = "";
    String topLeft = "";
    String bottomRight = "";
    String bottomLeft = "";

    boolean isChanged = false;

    public void print() {
        System.out.println(modifyableText);
    }

    public void setDecoded(Map<Integer, String> decoded) {
        this.decoded = decoded;
    }

    public void modifyToLookAs(Integer input) {
        if (input == 1) {
            modifyableText = modifyableText.replaceAll("1", ".");
            modifyableText = modifyableText.replaceAll("2", ".");
            modifyableText = modifyableText.replaceAll("3", ".");
            modifyableText = modifyableText.replaceAll("4", ".");
            modifyableText = modifyableText.replaceAll("5", ".");
            isChanged = true;
        } else if (input == 4) {
            modifyableText = modifyableText.replaceAll("1", ".");
            modifyableText = modifyableText.replaceAll("4", ".");
            modifyableText = modifyableText.replaceAll("5", ".");
            isChanged = true;

        } else if (input == 7) {
            modifyableText = modifyableText.replaceAll("2", ".");
            modifyableText = modifyableText.replaceAll("3", ".");
            modifyableText = modifyableText.replaceAll("4", ".");
            modifyableText = modifyableText.replaceAll("5", ".");
            isChanged = true;

        }

    }

    public void decode(Pair a, Pair b) {
        if (a.key() == 1 && b.key() == 7) { //decode .1111.
            String topRow = b.value();
            topRow = topRow.replaceAll(a.value().charAt(0) + "", "");
            topRow = topRow.replaceAll(a.value().charAt(1) + "", "");
            modifyableText = modifyableText.replaceAll("1", topRow);
            topMiddle = topRow;
        }
        if (a.key() == 8 && b.value().length() == 6) { // 0 || 6 || 9
            String remainingLetter = a.value();
            for (int i = 0; i < b.value().length(); i++) {
                remainingLetter = remainingLetter.replaceAll(b.value().charAt(i) + "", "");
            }

            String one = decoded.get(1);// 2 letters
            if (one.charAt(0) == remainingLetter.toCharArray()[0] || one.charAt(1) == remainingLetter.toCharArray()[0]) {
                topRight = remainingLetter;
                modifyableText = modifyableText.replaceAll("7", topRight);
                decoded.put(6, b.value());
                bottomRight = one.replaceAll(remainingLetter, "");
                //Confirm the other sigment
                modifyableText = modifyableText.replaceAll("6", bottomRight);
            }

            //Figures out 0
            if (!topMiddle.isBlank() && !bottomMiddle.isBlank() && !modifyableText.contains(remainingLetter)) {
                remainingLetter = remainingLetter.replaceAll(topMiddle, "");
                remainingLetter = remainingLetter.replaceAll(bottomMiddle, "");
                if (remainingLetter.length() == 1) {
                    middleMiddle = remainingLetter;
                    modifyableText = modifyableText.replaceAll("3", middleMiddle);
                    if (!middleMiddle.isBlank() && !b.value().contains(middleMiddle)) {
                        decoded.put(0, b.value());
                    }
                }
            }
            if (decoded.get(0) != null && !decoded.get(0).equals(b.value())) {
                if (!b.value().contains(bottomLeft)) {
                    decoded.put(9, b.value());
                }
            }


        }

        if (a.key() == 8 && b.value().length() == 5) { // 2 || 5 || 3
            String remainingLetter = a.value();

            for (int i = 0; i < b.value().length(); i++) {
                remainingLetter = remainingLetter.replaceAll(b.value().charAt(i) + "", "");
            }

            String tempcode = b.value();

            tempcode = tempcode.replaceAll(topRight, "");
            tempcode = tempcode.replaceAll(bottomRight, "");

            if (b.value().length() - tempcode.length() == 2) {
                decoded.put(3, b.value());
                return;
            }

            if (decoded.get(3) != null) {
                var three = decoded.get(3).split("");

                for (int i = 0; i < three.length; i++) {
                    tempcode = tempcode.replaceAll(three[i], "");
                }
                boolean isFive = tempcode.length() == 1 && b.value().contains(bottomRight);
                if (isFive) {
                    decoded.put(5, b.value());
                    topLeft = tempcode;
                    modifyableText = modifyableText.replaceAll("2", topLeft);
                    return;
                } else {
                    decoded.put(2, b.value());
                    bottomLeft = tempcode;
                    modifyableText = modifyableText.replaceAll("4", bottomLeft);
                    return;
                }
            }
        }
        if (decoded.get(3) != null && decoded.get(4) != null) {
            String tempcode = decoded.get(3);
            var four = decoded.get(4).split("");
            for (int i = 0; i < four.length; i++) {
                tempcode = tempcode.replaceAll(four[i], "");
            }
            tempcode = tempcode.replaceAll(topMiddle, "");
            if (tempcode.length() == 1) {
                bottomMiddle = tempcode;
                modifyableText = modifyableText.replaceAll("5", bottomMiddle);
            }
        }

        if (decoded.size() == 9 && decoded.get(0) != null) {
            for (int i = 0; i < 9; i++) {
                boolean isInList = false;
                if (decoded.get(i).equals(b.value())) {
                    isInList = true;
                }
                if (!isInList && !b.value().contains(bottomLeft)) {
                    decoded.put(9, b.value());
                }
            }
        }
        if (decoded.size() == 9) {

            if (!decoded.containsValue(b.value())) {
                System.out.println(b.value());
            }

        }


    }

    public int decode(String a) {

        for (Map.Entry<Integer, String> entry : decoded.entrySet()) {
            Integer integer = entry.getKey();
            String s = entry.getValue();

            if (a.length() == s.length()) {//Only do check if they are the same length
                boolean isSame = true;
                for (int x = 0; x < s.length(); x++) {
                    if (!a.contains(s.charAt(x) + "")) {
                        isSame = false;
                    }
                }
                if (isSame) {
                    return integer;
                }
            }
        }
        try {
            return Integer.parseInt(a);
        } catch (Exception e) {
            throw new RuntimeException("I dont have this decoded");
        }
    }
}


record Pair(int key, String value) {
}
