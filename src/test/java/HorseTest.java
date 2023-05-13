import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    @Test
    void constructorWithNullFirstParamShouldThrowIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 0.0, 0.0));
    }

    @Test
    void constructorWithNullFirstParamShouldThrowExceptionWithRelevantMessage() {
        String message = null;
        try {
            new Horse(null, 0.0, 0.0);
        }
        catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals("Name cannot be null.", message);
    }

    @ParameterizedTest
    @ValueSource(strings = { "", " ", "  ", "\t", "\t\t", " \t" })
    void constructorWithBlankFirstParamShouldThrowIllegalArgumentException(String str) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(str, 0.0, 0.0));
    }

    @ParameterizedTest
    @ValueSource(strings = { "", " ", "  ", "\t", "\t\t", " \t" })
    void constructorWithBlankFirstParamShouldThrowExceptionWithRelevantMessage(String str) {
        String message = null;
        try {
            new Horse(str, 0.0, 0.0);
        }
        catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals("Name cannot be blank.", message);
    }

    @Test
    void constructorWithNegativeSecondParamShouldThrowIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("foo", -1.0, 0.0));
    }

    @Test
    void constructorWithNegativeSecondParamShouldThrowExceptionWithRelevantMessage() {
        String message = null;
        try {
            new Horse("foo", -1.0, 0.0);
        }
        catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals("Speed cannot be negative.", message);
    }

    @Test
    void constructorWithNegativeThirdParamShouldThrowIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("foo", 0.0, -1.0));
    }

    @Test
    void constructorWithNegativeThirdParamShouldThrowExceptionWithRelevantMessage() {
        String message = null;
        try {
            new Horse("foo", 0.0, -1.0);
        }
        catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals("Distance cannot be negative.", message);
    }
}
