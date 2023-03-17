package format;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author alex.fang
 * @date 2023/3/17
 */
class BigDecimalTest {
    @Test
    void testRoundingModel() {
        BigDecimal decimal =BigDecimal.valueOf(1.23456);
        assertEquals("1.235", decimal.setScale(3, RoundingMode.HALF_UP).toPlainString());
        assertEquals("1.235", decimal.setScale(3, RoundingMode.HALF_DOWN).toPlainString());
        assertEquals("1.235", decimal.setScale(3, RoundingMode.HALF_EVEN).toPlainString());
        assertEquals("1.235", decimal.setScale(3, RoundingMode.CEILING).toPlainString());
        assertEquals("1.234", decimal.setScale(3, RoundingMode.DOWN).toPlainString());
        assertEquals("1.235", decimal.setScale(3, RoundingMode.UP).toPlainString());
        assertEquals("1.234", decimal.setScale(3, RoundingMode.FLOOR).toPlainString());
    }
}
