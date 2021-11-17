package co.incubyte;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)
public class BankKataTest {

  @Mock
  Console console;
  private Account account;

  @Before
  public void initialise() {
    account = new Account();
  }

  @Test
  public void
  print_statement_containing_all_transaction() {
    account.deposit(1000);
    account.withdraw(100);
    account.deposit(500);
    account.printStatement();
  }
}
