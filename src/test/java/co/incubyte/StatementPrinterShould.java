package co.incubyte;

import static org.mockito.Mockito.verify;

import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StatementPrinterShould {

  private static final List<Transaction> NO_TRANSACTIONS = Collections.EMPTY_LIST;
  @Mock
  Console console;

  @Test
  public void
  always_print_the_header() {
    StatementPrinter statementPrinter = new StatementPrinter(console);
    statementPrinter.print(NO_TRANSACTIONS);
    verify(console).printLine("DATE | AMOUNT  | BALANCE");

  }

}
