package rocks.zipcode.atm.bank;

import rocks.zipcode.atm.ActionResult;

import java.util.*;

/**
 * @author ZipCodeWilmington
 */
public class Bank {

    private Map<Integer, Account> accounts = new HashMap<>();

    public Bank() {
        accounts.put(1000, new BasicAccount(new AccountData(
                1000, "Example 1", "example1@gmail.com", 500
        )));

        accounts.put(2000, new PremiumAccount(new AccountData(
                2000, "Example 2", "example2@gmail.com", 200
        )));
    }

    public ActionResult<AccountData> getAccountById(int id) {
        Account account = accounts.get(id);

        if (account != null) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("No account with id: " + id + "\nTry account 1000 or 2000");
        }
    }

    public ActionResult<AccountData> deposit(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        account.deposit(amount);

        return ActionResult.success(account.getAccountData());
    }

    public ActionResult<AccountData> withdraw(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        boolean ok = account.withdraw(amount);

        if (ok) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("Withdraw failed: " + amount + ". Account has: " + account.getBalance());
        }
    }

    public List<String> getAccountNumbers(){
        List<String> accountNumbers = new ArrayList<>();
        for(Integer accountNumber : accounts.keySet()){
            accountNumbers.add(accountNumber.toString());
        }
        Collections.sort(accountNumbers, new NumericStringSort());
        return accountNumbers;
    }

    private class NumericStringSort implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            if(Integer.parseInt(o1) < Integer.parseInt(o2)){
                return -1;
            }
            else if(Integer.parseInt(o1) > Integer.parseInt(o2)){
                return 1;
            }
            else {
                return 0;
            }
        }
    }
}
