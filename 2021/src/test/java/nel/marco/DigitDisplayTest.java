package nel.marco;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

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
        digitDisplay.decode(new Pair(1,"be"),new Pair(7,"edb"));
    }

    @Test
    void decode_canDecode_6() {

        DigitDisplay digitDisplay = new DigitDisplay();
        digitDisplay.setDecoded(Map.of(1,"ab"));
        digitDisplay.decode(new Pair(8,"acedgfb"),new Pair(6,"cdfgeb"));
    }
}