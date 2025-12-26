package dev.ethang;

import com.ibm.icu.text.MessageFormat;
import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.text.RelativeDateTimeFormatter;
import com.ibm.icu.text.RuleBasedNumberFormat;
import com.ibm.icu.text.Transliterator;
import com.ibm.icu.util.Currency;
import com.ibm.icu.util.ULocale;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Main {
     static void main() {
        long number = 1234567890L;
        Locale mexico = Locale.forLanguageTag("es-MX");
        String result = formatNumber(number, mexico);

        System.out.println(result);
    }

    public static String formatNumber(long number, Locale locale) {
        RuleBasedNumberFormat formatter = new RuleBasedNumberFormat(
                locale,
                RuleBasedNumberFormat.SPELLOUT
        );

        return formatter.format(number);
    }

    public static String formatOrdinal(long number, Locale locale) {
        RuleBasedNumberFormat formatter = new RuleBasedNumberFormat(
                locale,
                RuleBasedNumberFormat.ORDINAL
        );

        return formatter.format(number);
    }

    public static String formatSpelloutOrdinal(long number, Locale locale) {
        RuleBasedNumberFormat formatter = new RuleBasedNumberFormat(
                locale,
                RuleBasedNumberFormat.SPELLOUT
        );

        return formatter.format(number, "%spellout-ordinal");
    }

    public static String formatPluralMessage(int count, Locale locale) {
        String pattern = "{count, plural, " +
                "=0 {no apples}" +
                "one {one apple}" +
                "other {# apples}}";
        MessageFormat msgFmt = new MessageFormat(pattern, locale);
        Map<String, Object> args = new HashMap<>();
        args.put("count", count);
        return msgFmt.format(args);
    }

    public static String formatRelativeTime(double offset, ULocale locale, RelativeDateTimeFormatter.RelativeUnit unit) {
        RelativeDateTimeFormatter fmt = RelativeDateTimeFormatter.getInstance(locale);
        return fmt.format(offset, RelativeDateTimeFormatter.Direction.NEXT, unit);
    }

    public static String formatCurrency(double amount, String currencyCode, Locale locale) {
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        fmt.setCurrency(Currency.getInstance(currencyCode));
        return fmt.format(amount);
    }

    public static String transliterate(String text, String id) {
        Transliterator transliterator = Transliterator.getInstance(id);
        return transliterator.transliterate(text);
    }
}
