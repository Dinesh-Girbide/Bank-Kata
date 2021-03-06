package co.incubyte;

import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BankKataTest {

  @Mock
  Console console;
  private Account account;

  @Mock Clock clock;
  @Before
  public void initialise() {
    TransactionRepository transactionRepository = new TransactionRepository(clock);
    StatementPrinter statementPrinter = new StatementPrinter(console);
    account = new Account(transactionRepository, statementPrinter);
  }

  @Test
  public void
  print_statement_containing_all_transaction() {
    given(clock.todayAsString()).willReturn("01/04/2021","02/04/2021","10/04/2021");
    account.deposit(1000);
    account.withdraw(100);
    account.deposit(500);
    account.printStatement();

    InOrder inOrder = Mockito.inOrder(console);

    inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
    inOrder.verify(console).printLine("10/04/2021 | 500.00 | 1400.00");
    inOrder.verify(console).printLine("02/04/2021 | -100.00 | 900.00");
    inOrder.verify(console).printLine("01/04/2021 | 1000.00 | 1000.00");


  }
}
