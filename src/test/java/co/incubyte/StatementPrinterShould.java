package co.incubyte;

import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StatementPrinterShould {

  private StatementPrinter statementPrinter;
  private static final List<Transaction> NO_TRANSACTIONS = Collections.EMPTY_LIST;
  @Mock
  Console console;

  @Before
  public void initialise() {
    statementPrinter = new StatementPrinter(console);
  }

  @Test
  public void
  always_print_the_header() {
    statementPrinter.print(NO_TRANSACTIONS);
    verify(console).printLine("DATE | AMOUNT | BALANCE");

  }

  @Test
  public void
  print_transaction_in_reverse_chronological_order() {
    List<Transaction> transactions = transactionsContaining(
        deposite("01/04/2021", 1000),
        withdrawal("02/04/2021", 100),
        deposite("10/04/2021", 500)
    );
    statementPrinter.print(transactions);
    InOrder inOrder = Mockito.inOrder(console);

    inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
    inOrder.verify(console).printLine("10/04/2021 | 500.00 | 1400.00");
    inOrder.verify(console).printLine("02/04/2021 | -100.00 | 900.00");
    inOrder.verify(console).printLine("01/04/2021 | 1000.00 | 1000.00");


  }

  private List<Transaction> transactionsContaining(Transaction... transactions) {
    return Arrays.asList(transactions);
  }

  private Transaction withdrawal(String date, int amount) {
    return new Transaction(date, -amount);
  }

  private Transaction deposite(String date, int amount) {
    return new Transaction(date, amount);
  }

}
