package rocks.zipcode.atm;

import rocks.zipcode.atm.bank.AccountData;
import rocks.zipcode.atm.bank.Bank;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ZipCodeWilmington
 */
public class CashMachine {

    private final Bank bank;
    private AccountData accountData = null;
    private Boolean overdraft = false;
    private Boolean withdrawFailed = false;
    private String withdrawFailedError = "";
    private Pattern withdrawFailedPattern =
            Pattern.compile("Withdraw failed: [\\d]+. Account has: -?[\\d]+");

    public CashMachine(Bank bank) {
        this.bank = bank;
        // "Withdraw failed: " + amount + ". Account has: " + account.getBalance()
        //Pattern p = Pattern.compile("Withdraw failed: " + "[\\d]+" + ". Account has: " + "[\\d]+");
    }

    /*
    private Consumer<AccountData> update = data -> {
        accountData = data;
    };*/
    public int getAccoutId() {
        return accountData.getId();
    }
    public String getAccoutName() {
        return accountData.getName();
    }
    public String getAccoutEmail() {
        return accountData.getEmail();
    }
    public int getAccoutBal() {
        return accountData.getBalance();
    }


    private Consumer<AccountData> update = data ->{
        overdraft = false;
        accountData = data;
        if(accountData.getBalance() < 0){
            overdraft = true;
        }
    };

    public void login(int id) {
        tryCall(
                () -> bank.getAccountById(id),
                update
        );
    }

    public void deposit(int amount) {
        if (accountData != null) {
            tryCall(
                    () -> bank.deposit(accountData, amount),
                    update
            );
        }
    }

    public void withdraw(int amount) {
        if (accountData != null) {
            tryCall(
                    () -> bank.withdraw(accountData, amount),
                    update

            );
        }
    }

    public void addAccount(List<String> info){
        tryCall(
                () -> bank.addAccount(info),
                update
        );
    }

    public void exit() {
        if (accountData != null) {
            accountData = null;
        }
    }

    @Override
    public String toString() {
        return accountData != null ? toWithdrawString() : "Try account 1000 or 2000 and click submit.";
    }

    private <T> void tryCall(Supplier<ActionResult<T> > action, Consumer<T> postAction) {
        withdrawFailed = false;
        withdrawFailedError = "";
        try {
            ActionResult<T> result = action.get();
            if (result.isSuccess()) {
                T data = result.getData();
                postAction.accept(data);
            } else {
                String errorMessage = result.getErrorMessage();
                Matcher m = withdrawFailedPattern.matcher(errorMessage);
                if(m.matches()){
                    withdrawFailed = true;
                    withdrawFailedError = errorMessage;
                }
                throw new RuntimeException(errorMessage);

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private String toWithdrawString(){
        StringBuilder sbuild = new StringBuilder();
        sbuild.append(accountData);
        if(overdraft) {
            sbuild.append("\nACCOUNT OVERDRAFTED");
        }
        /*if(withdrawFailed){
            sbuild.append("\n");
            sbuild.append(withdrawFailedError);
        }*/
        return sbuild.toString();
    }

    public List<String> getAccountNumbers(){
        return bank.getAccountNumbers();
    }

    public Boolean getWithdrawFailed(){
        return withdrawFailed;
    }

    public String getWithdrawFailedError(){
        return withdrawFailedError;
    }
}
