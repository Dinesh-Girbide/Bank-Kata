package co.incubyte;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountShould {
  @Mock
  TransactionRepository transactionRepository;
  private Account account;
  @Before
  public void initialise(){
    account=new Account(transactionRepository);
  }

  @Test public  void
  store_a_deposite_transaction(){
    account.deposit(100);
    verify(transactionRepository).addDeposite(100);
  }

}
