package rocks.zipcode.atm.bank;

import rocks.zipcode.atm.ActionResult;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ZipCodeWilmington
 */
public class Bank {

    private Map<Integer, Account> accounts = new HashMap<>();

    public Bank() {
        accounts.put(1000, new BasicAccount(new AccountData(
                1000, "Example 1", "example1@gmail.com", 500.0
        )));

        accounts.put(2000, new PremiumAccount(new AccountData(
                2000, "Example 2", "example2@gmail.com", 200.0
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

    public ActionResult<AccountData> deposit(AccountData accountData, double amount) {
        Account account = accounts.get(accountData.getId());
        account.deposit(amount);

        return ActionResult.success(account.getAccountData());
    }

    public ActionResult<AccountData> withdraw(AccountData accountData, double amount) {
        Account account = accounts.get(accountData.getId());
        boolean ok = account.withdraw(amount);

        if (ok) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("Withdraw failed: " + amount + ". Account has: " + account.getBalance());
        }
    }

    public ActionResult<AccountData> addAccount(List<String> info){
        Integer id;
        String name;
        String email;
        double balance;

        try{
            id = Integer.parseInt(info.get(0));
            if(id < 0) return ActionResult.fail("Negative account ID's are proscribed");
            if(accounts.get(id) != null) return ActionResult.fail("Account already exists");

        } catch(NumberFormatException e){
            return ActionResult.fail("Invalid account ID entered");
        }

        name = info.get(1);
        if(name.equals("") || name == null){
            return ActionResult.fail("Name not entered");
        }

        Pattern p = Pattern.compile(".+@.+\\..+");
        Matcher m = p.matcher(info.get(2));
        if(m.matches()){
            email = info.get(2);
        } else {
            return ActionResult.fail("Invalid email address");
        }

        try{
            balance = Integer.parseInt(info.get(3));
            if(balance < 0) return ActionResult.fail("We don't want your negative balances 'round these parts");
        } catch(NumberFormatException e){
            return ActionResult.fail("Invalid balance entered");
        }

        AccountData freshAccountData = new AccountData(id,name,email,balance);
        accounts.put(id, new BasicAccount(freshAccountData));
        return ActionResult.success(freshAccountData);
        //return ActionResult.success(new AccountData(id,name,email,balance));
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
