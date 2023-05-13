import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HippodromeTest {

    @Test
    @Order(10)
    void constructorWithNullParamShouldThrowIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null));
    }

    @Test
    @Order(20)
    void constructorWithNullParamShouldThrowExceptionWithRelevantMessage() {
        String message = null;
        try {
            new Hippodrome(null);
        }
        catch (Exception e) {
            message = e.getMessage();
        }

        assertEquals("Horses cannot be null.", message);
    }

    @Test
    @Order(30)
    void getHorsesShouldReturnTheListPassedToConstructor() {
        List<Horse> expectedHorses = new ArrayList<>();
        for (int i = 0; i < 30; ++i) {
            expectedHorses.add(new Horse("Horse #" + i, 1.0 + 0.1 * i));
        }

        List<Horse> actualHorses = new Hippodrome(expectedHorses).getHorses();

        assertEquals(expectedHorses, actualHorses);
    }

    @Test
    @Order(40)
    void moveShouldMoveAllHorses() {
        List<Horse> horses = new ArrayList<>();
        Horse horseMock = Mockito.mock(Horse.class);
        int expectedNumber = 50;

        for (int i = 0; i < expectedNumber; ++i) {
            horses.add(horseMock);
        }

        new Hippodrome(horses).move();

        Mockito.verify(horseMock, Mockito.times(expectedNumber)).move();
    }

    @Test
    @Order(50)
    void getWinnerShouldReturnTheHorseWithTheLongestDistance() {
        List<Horse> horses = Arrays.asList(
                new Horse("foo", 1.0, 2.0),
                new Horse("bar", 1.0, 4.0),
                new Horse("baz", 1.0, 3.0)
        );

        Horse expected = Collections.max(horses, Comparator.comparing(Horse::getDistance));
        Horse actual = new Hippodrome(horses).getWinner();

        assertEquals(expected, actual);
    }
}
