package co.incubyte;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountShould {

  @Mock
  TransactionRepository transactionRepository;
  @Mock
  private StatementPrinter statementPrinter;
  private Account account;

  @Before
  public void initialise() {
    account = new Account(transactionRepository, statementPrinter);
  }

  @Test
  public void
  store_a_deposite_transaction() {
    account.deposit(100);
    verify(transactionRepository).addDeposite(100);
  }

  @Test
  public void
  store_a_withdrawal_transaction() {
    account.withdraw(100);
    verify(transactionRepository).addWithdrawal(100);
  }

  @Test
  public void
  print_a_statement() {
    List<Transaction> transactions = Arrays.asList((new Transaction("12/05/2020",100)));
    given(transactionRepository.allTransactions()).willReturn(transactions);
    account.printStatement();
    verify(statementPrinter).print(transactions);
  }
}
