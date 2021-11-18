package co.incubyte;

import com.sun.tools.jconsole.JConsoleContext;
import java.util.List;

public class StatementPrinter {
private Console console;
  private static final String STATEMENT_HEADER="DATE | AMOUNT  | BALANCE";

  public StatementPrinter(Console console) {
 this.console=console;
  }

  public void print(List<Transaction> transactions) {
console.printLine(STATEMENT_HEADER);
  }
}
