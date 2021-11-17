package co.incubyte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionRepository {
  private final Clock clock;
  private List<Transaction> transactions=new ArrayList<>();

  public TransactionRepository(Clock clock){
this.clock=clock;
}
  public void addDeposite(int amount) {
  Transaction depositTransaction=new Transaction(clock.todayAsString(),amount);
  transactions.add(depositTransaction);
  }

  public void addWithdrawal(int amount) {
    throw new UnsupportedOperationException();
  }

  public List<Transaction> allTransactions() {
return Collections.unmodifiableList(transactions);
  }
}
