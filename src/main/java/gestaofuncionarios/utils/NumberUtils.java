package gestaofuncionarios.utils;

import java.text.NumberFormat;

public class NumberUtils {
  public static String formatDecimalLocale(double number) {
    return NumberFormat.getNumberInstance().format(number);
  }
}
