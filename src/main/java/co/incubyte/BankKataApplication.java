package co.incubyte;

public class BankKataApplication {

  public static void main(String[] args) {
    Clock clock=new Clock();
    Console console=new Console();
    StatementPrinter statementPrinter=new StatementPrinter(console);
    TransactionRepository transactionRepository=new TransactionRepository(clock);
    Account account=new Account(transactionRepository,statementPrinter);
    account.deposit(1000);
    account.withdraw(400);
    account.deposit(100);
    account.printStatement();
  }
}
