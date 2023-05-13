import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HorseTest {
    @Test
    @Order(10)
    void constructorWithNullFirstParamShouldThrowIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 1.0, 1.0));
    }

    @Test
    @Order(20)
    void constructorWithNullFirstParamShouldThrowExceptionWithRelevantMessage() {
        String message = null;
        try {
            new Horse(null, 1.0, 1.0);
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
                () -> new Horse(str, 1.0, 1.0));
    }

    @ParameterizedTest
    @Order(40)
    @ValueSource(strings = { "", " ", "  ", "\t", "\t\t", " \t" })
    void constructorWithBlankFirstParamShouldThrowExceptionWithRelevantMessage(String str) {
        String message = null;
        try {
            new Horse(str, 1.0, 1.0);
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
                () -> new Horse("foo", -1.0, 1.0));
    }

    @Test
    @Order(60)
    void constructorWithNegativeSecondParamShouldThrowExceptionWithRelevantMessage() {
        String message = null;
        try {
            new Horse("foo", -1.0, 1.0);
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
                () -> new Horse("foo", 1.0, -1.0));
    }

    @Test
    @Order(80)
    void constructorWithNegativeThirdParamShouldThrowExceptionWithRelevantMessage() {
        String message = null;
        try {
            new Horse("foo", 1.0, -1.0);
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
        Horse horse = new Horse(expected, 1.0, 1.0);
        String actual = horse.getName();

        assertEquals(expected, actual);
    }

    @Test
    @Order(100)
    void getSpeedShouldReturnConstructorSecondParamValue() {
        double expected = 2.73;
        Horse horse = new Horse("foo", expected, 1.0);
        double actual = horse.getSpeed();

        assertEquals(expected, actual);
    }

    @Test
    @Order(110)
    void getDistanceShouldReturnConstructorThirdParamValue() {
        double expected = 2.73;
        Horse horse = new Horse("foo", 1.0, expected);
        double actual = horse.getDistance();

        assertEquals(expected, actual);
    }

    @Test
    @Order(120)
    void getDistanceShouldReturnZeroIfTwoParamConstructorUsed() {
        Horse horse = new Horse("foo", 1.0);
        double actual = horse.getDistance();

        assertEquals(0.0, actual);
    }

    // https://howtodoinjava.com/mockito/mock-static-methods/
    @Test
    @Order(130)
    void moveShouldCallGetRandomDoubleWithRelevantParams() {
        try (MockedStatic<Horse> mock = mockStatic(Horse.class)) {
            new Horse("foo", 1.0, 1.0).move();

            mock.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @Order(140)
    @ValueSource(doubles = { 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.89 })
    void moveShouldSetRelevantDistance(double randomValue) {
        try (MockedStatic<Horse> mock = mockStatic(Horse.class)) {
            mock.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);

            Horse horse = new Horse("foo", 2.2, 3.3);
            horse.move();
            double actual = horse.getDistance();
            double expected = 3.3 + 2.2 * randomValue;

            assertEquals(expected, actual);
        }
    }
}
