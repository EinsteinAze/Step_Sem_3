import java.util.HashMap;
import java.util.Map;

abstract class Account {
    protected final String id;
    protected long balance;

    Account(String id, long balance) {
        this.id = id;
        this.balance = balance;
    }

    abstract long lowestAllowedBalance();

    abstract String rejectionReason();

    String withdraw(long amount) {
        if (balance - amount < lowestAllowedBalance()) {
            return id + " rejected: " + rejectionReason();
        }
        balance -= amount;
        return id + " balance " + balance;
    }
}

class SavingsAccount extends Account {
    private static final long MINIMUM_BALANCE = 1000;

    SavingsAccount(String id, long balance) {
        super(id, balance);
    }

    long lowestAllowedBalance() {
        return MINIMUM_BALANCE;
    }

    String rejectionReason() {
        return "minimum balance " + MINIMUM_BALANCE;
    }
}

class CurrentAccount extends Account {
    private static final long OVERDRAFT_LIMIT = 5000;

    CurrentAccount(String id, long balance) {
        super(id, balance);
    }

    long lowestAllowedBalance() {
        return -OVERDRAFT_LIMIT;
    }

    String rejectionReason() {
        return "overdraft limit " + OVERDRAFT_LIMIT;
    }
}

public class oopp1 {

    static void process(String[] operations) {
        Map<String, Account> accounts = new HashMap<>();
        for (String operation : operations) {
            String[] parts = operation.trim().split("\\s+");
            String command = parts[0];
            if (command.equals("Savings")) {
                accounts.put(parts[1], new SavingsAccount(parts[1], Long.parseLong(parts[2])));
            } else if (command.equals("Current")) {
                accounts.put(parts[1], new CurrentAccount(parts[1], Long.parseLong(parts[2])));
            } else if (command.equals("WITHDRAW")) {
                Account account = accounts.get(parts[1]);
                if (account == null) {
                    System.out.println("Account not found");
                } else {
                    System.out.println(account.withdraw(Long.parseLong(parts[2])));
                }
            }
        }
    }

    public static void main(String[] args) {
        process(new String[]{
                "Savings S1 5000",
                "WITHDRAW S1 4500",
                "Current C1 2000",
                "WITHDRAW C1 6000",
                "WITHDRAW S1 3000",
                "WITHDRAW C1 2000"
        });
        System.out.println();
        process(new String[]{
                "Savings S2 1000",
                "WITHDRAW S2 1",
                "WITHDRAW X9 100",
                "Current C2 100",
                "WITHDRAW C2 5100",
                "WITHDRAW C2 1"
        });
    }
}
