package format;

import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author alex.fang
 * @date 2023/2/28
 * https://www.apiref.com/java11-zh/java.base/java/text/DecimalFormat.html
 */
class DecimalFormatTest {
    double pi = 3.141592653;
    long c = 299792458;

    @Test
    void testFormat_withZero() {
        assertEquals("3", new DecimalFormat("0").format(pi));
        assertEquals("3.14", new DecimalFormat("0.00").format(pi));
        assertEquals("03.142", new DecimalFormat("00.000").format(pi));
    }

    @Test
    void testFormat_withWell() {
        assertEquals("3", new DecimalFormat("#").format(pi));
        assertEquals("3.14", new DecimalFormat("#.##").format(pi));
        assertEquals("3.142", new DecimalFormat("##.###").format(pi));
    }

    @Test
    void testFormat_withPercent() {
        assertEquals("314%", new DecimalFormat("0%").format(pi));
        assertEquals("314.16%", new DecimalFormat("0.00%").format(pi));
        assertEquals("314.159%", new DecimalFormat("00.000%").format(pi));

        assertEquals("314%", new DecimalFormat("#%").format(pi));
        assertEquals("314.16%", new DecimalFormat("#.##%").format(pi));
        assertEquals("314.159%", new DecimalFormat("##.###%").format(pi));
    }

    @Test
    void testFormat_withThousandth() {
        assertEquals("3142‰", new DecimalFormat("0‰").format(pi));
        assertEquals("3141.59‰", new DecimalFormat("0.00‰").format(pi));
        assertEquals("3141.593‰", new DecimalFormat("00.000‰").format(pi));

        assertEquals("3142‰", new DecimalFormat("#‰").format(pi));
        assertEquals("3141.59‰", new DecimalFormat("#.##‰").format(pi));
        assertEquals("3141.593‰", new DecimalFormat("##.###‰").format(pi));
    }

    @Test
    void testFormat_whitsScientificNotation() {
        assertEquals("3E8", new DecimalFormat("0E0").format(c));
        assertEquals("3.00E8", new DecimalFormat("0.00E0").format(c));
        assertEquals("29.979E7", new DecimalFormat("00.000E0").format(c));

        assertEquals(".3E9", new DecimalFormat("#E0").format(c));
        assertEquals("3E8", new DecimalFormat("#.##E0").format(c));
        assertEquals("2.9979E8", new DecimalFormat("##.###E0").format(c));
    }

    @Test
    void testFormat_whitComma() {
        assertEquals("299,792,458.00", new DecimalFormat(",000.00").format(c));
    }
}
