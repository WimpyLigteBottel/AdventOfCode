package nel.marco;

import org.junit.jupiter.api.Test;

import java.util.Map;

class DigitDisplayTest {

    @Test
    void print() {
    }

    @Test
    void modifyToLookAs() {
    }

    @Test
    void decode() {

        DigitDisplay digitDisplay = new DigitDisplay();
        digitDisplay.decode(new Point(1,"be"),new Point(7,"edb"));
    }

    @Test
    void decode_canDecode_6() {

        DigitDisplay digitDisplay = new DigitDisplay();
        digitDisplay.setDecoded(Map.of(1,"ab"));
        digitDisplay.decode(new Point(8,"acedgfb"),new Point(6,"cdfgeb"));
    }
}