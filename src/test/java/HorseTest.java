import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HorseTest {
    @Test
    @Order(10)
    void constructorWithNullFirstParamShouldThrowIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 0.0, 0.0));
    }

    @Test
    @Order(20)
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
    @Order(30)
    @ValueSource(strings = { "", " ", "  ", "\t", "\t\t", " \t" })
    void constructorWithBlankFirstParamShouldThrowIllegalArgumentException(String str) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(str, 0.0, 0.0));
    }

    @ParameterizedTest
    @Order(40)
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
    @Order(50)
    void constructorWithNegativeSecondParamShouldThrowIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("foo", -1.0, 0.0));
    }

    @Test
    @Order(60)
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
    @Order(70)
    void constructorWithNegativeThirdParamShouldThrowIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("foo", 0.0, -1.0));
    }

    @Test
    @Order(80)
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

    @Test
    @Order(90)
    void getNameShouldReturnConstructorFirstParamValue() {
        String expected = "foo";
        Horse horse = new Horse(expected, 0.0, 0.0);
        String actual = horse.getName();

        assertEquals(expected, actual);
    }

    @Test
    @Order(100)
    void getSpeedShouldReturnConstructorSecondParamValue() {
        double expected = 2.73;
        Horse horse = new Horse("foo", expected, 0.0);
        double actual = horse.getSpeed();

        assertEquals(expected, actual);
    }

    @Test
    @Order(110)
    void getDistanceShouldReturnConstructorThirdParamValue() {
        double expected = 2.73;
        Horse horse = new Horse("foo", 0.0, expected);
        double actual = horse.getDistance();

        assertEquals(expected, actual);
    }

    @Test
    @Order(120)
    void getDistanceShouldReturnZeroIfTwoParamConstructorUsed() {
        Horse horse = new Horse("foo", 0.0);
        double actual = horse.getDistance();

        assertEquals(0.0, actual);
    }

}
