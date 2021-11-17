package co.incubyte;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TransactionRepositoryShould {

  @Mock
  Clock clock;
 public static final String TODAY="12/05/2020";
  private TransactionRepository transactionRepository;

  @Before
  public void initialise() {
    transactionRepository = new TransactionRepository(clock);
  }

  @Test
  public void
  create_and_store_a_deposit_transaction() {
    given(clock.todayAsString()).willReturn(TODAY);
    transactionRepository.addDeposite(100);
    List<Transaction> transactions = transactionRepository.allTransactions();
    assertEquals(transactions.size(), 1);
    assertEquals(transactions.get(0), (transaction(TODAY, 100)));

  }

  private Transaction transaction(String date, int amount) {
    return new Transaction(date, amount);
  }
}
