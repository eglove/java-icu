package dev.ethang;

import com.ibm.icu.text.RelativeDateTimeFormatter;
import com.ibm.icu.util.ULocale;
import org.junit.jupiter.api.Test;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {

    @Test
    public void testFormatNumberUs() {
        assertEquals("one billion two hundred thirty-four million five hundred sixty-seven thousand eight hundred ninety", 
                     Main.formatNumber(1234567890L, Locale.US));
    }

    @Test
    public void testFormatNumberSmall() {
        assertEquals("five", Main.formatNumber(5L, Locale.US));
    }

    @Test
    public void testFormatNumberZero() {
        assertEquals("zero", Main.formatNumber(0L, Locale.US));
    }

    @Test
    public void testFormatNumberNegative() {
        assertEquals("minus five", Main.formatNumber(-5L, Locale.US));
    }

    @Test
    public void testFormatNumberFrance() {
        // Checking for French spelling
        String result = Main.formatNumber(123L, Locale.FRANCE);
        assertEquals("cent vingt-trois", result.toLowerCase());
    }

    @Test
    public void testFormatPluralMessage() {
        assertEquals("no apples", Main.formatPluralMessage(0, Locale.US));
        assertEquals("one apple", Main.formatPluralMessage(1, Locale.US));
        assertEquals("5 apples", Main.formatPluralMessage(5, Locale.US));
    }

    @Test
    public void testFormatRelativeTime() {
        assertEquals("in 1 day", Main.formatRelativeTime(1, ULocale.US, RelativeDateTimeFormatter.RelativeUnit.DAYS));
        assertEquals("in 2 weeks", Main.formatRelativeTime(2, ULocale.US, RelativeDateTimeFormatter.RelativeUnit.WEEKS));
    }

    @Test
    public void testFormatCurrency() {
        String result = Main.formatCurrency(1234.56, "USD", Locale.US);
        // ICU might use non-breaking space or different symbols depending on version/data
        // We check if it contains common parts
        assertTrue(result.contains("1,234.56"));
        assertTrue(result.contains("$"));
    }

    @Test
    public void testTransliterate() {
        String cyrillic = "Привет";
        String latin = Main.transliterate(cyrillic, "Any-Latin");
        assertEquals("Privet", latin);
    }

    @Test
    public void testFormatOrdinal() {
        assertEquals("1st", Main.formatOrdinal(1, Locale.US));
        assertEquals("2nd", Main.formatOrdinal(2, Locale.US));
        assertEquals("3rd", Main.formatOrdinal(3, Locale.US));
        assertEquals("4th", Main.formatOrdinal(4, Locale.US));
        assertEquals("11th", Main.formatOrdinal(11, Locale.US));
        assertEquals("21st", Main.formatOrdinal(21, Locale.US));
    }

    @Test
    public void testFormatSpelloutOrdinal() {
        assertEquals("first", Main.formatSpelloutOrdinal(1, Locale.US));
        assertEquals("second", Main.formatSpelloutOrdinal(2, Locale.US));
        assertEquals("third", Main.formatSpelloutOrdinal(3, Locale.US));
        assertEquals("one hundred twenty-third", Main.formatSpelloutOrdinal(123, Locale.US));
    }
}
