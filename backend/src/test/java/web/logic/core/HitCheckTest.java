package web.logic.core;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;

public class HitCheckTest {
    private static final float r = 4.0f;

    private boolean check(float x, float y) {
        return new HitCheck(x, y, r).wasThereHit();
    }

    @ParameterizedTest
    @CsvSource({
            "0.0, 2.0",
            "2.0, 0.0",
            "1.0, 1.0",
            "0.5, 1.1",
            "0.0, 4.0",
            "-2.0, 0.0",
            "-2.0, 4.0",
            "-1.0, 1.1",
            "0.0, -2.0",
            "1.4, -1.4",
            "1.1, -1.3"
    })
    void hits(float x, float y) {
        assertTrue(check(x, y));
    }

    @ParameterizedTest
    @CsvSource({
            "2.1, 0.0",
            "1.1, 1.1",
            "-1.2, 4.1",
            "-3.0, 2.1",
            "-1.0, -1.0",
            "1.8, -1.8"
    })
    void misses(float x, float y) {
        assertFalse(check(x, y));
    }
}
