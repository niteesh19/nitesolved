package design;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Design a cash register, CashRegister program
 * Your register currently has the following bills/coins within it:
 * 'PENNY': .01,
 * 'NICKEL': .05,
 * 'DIME': .10,
 * 'QUARTER': .25,
 * 'HALF DOLLAR': .50,
 * 'ONE': 1.00, ...
 * Each line contains two numbers which are separated by a semicolon.
 * The first is the Purchase price (PP) and the second is the cash(CH).
 * For each set of input produce a single line of output, which is the cashBack to be returned to the customer.
 */

public class CashRegistry {

  enum Denomination {
    ONE_HUNDRED(100.00f),
    FIFTY( 50.00f),
    TWENTY( 20.00f),
    TEN( 10.00f),
    FIVE(  5.00f),
    TWO(  2.00f),
    ONE(  1.00f),
    HALF_DOLLAR(  0.50f),
    QUARTER(  0.25f),
    DIME(  0.10f),
    NICKEL(  0.05f),
    PENNY(  0.01f);

    private final float value;
    private final String description;

    Denomination(float value) {
      this.value = value;
      this.description = this.name().replace("_", " ");
    }

    public float getValue() {
      return this.value;
    }

    @Override
    public String toString() {
      return this.description;
    }
  }

  private static void printCashChange(String line) {
    String[] fields = line.split(";", 2);
    float pp = Float.parseFloat(fields[0]);
    float ch = Float.parseFloat(fields[1]);

    System.out.println(getCashChange(pp, ch));
  }

  private static String getCashChange(float price, float cash) {
    if (cash < price) { return "ERROR"; }
    if (cash == price) { return "ZERO"; }
    float cashBack = cash - price;

    StringBuilder change = new StringBuilder();

    for (Denomination d : Denomination.values()) {
      while (cashBack >= d.getValue()) {
        cashBack -= d.getValue();
        change.append(d).append(',');
      }
    }
    change.setLength(change.length() - 1);
    return change.toString();
  }

  public static void main(String[] args) throws FileNotFoundException {
    Scanner input = (args.length > 0) ? new Scanner(new File(args[0]))
        : new Scanner(System.in);

    while (input.hasNextLine()) {
      printCashChange(input.nextLine());
    }
  }
}
