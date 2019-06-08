package rocks.zipcode.atm;



import org.junit.Assert;
import org.junit.Test;
import rocks.zipcode.atm.bank.Bank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CashMachineTest {

    @Test
    public void getAccoutId() {
        CashMachine cm = new CashMachine(new Bank());
        cm.login(1000);

        int actual = cm.getAccoutId();
        int expected = 1000;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAccoutId2() {
        CashMachine cm = new CashMachine(new Bank());
        cm.login(2000);

        int actual = cm.getAccoutId();
        int expected = 2000;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAccoutName() {
        CashMachine cm = new CashMachine(new Bank());
        cm.login(1000);

        String actual = cm.getAccoutName();
        String expected = "Example 1";

        Assert.assertEquals(expected, actual);

    }
    @Test
    public void getAccoutName2() {
        CashMachine cm = new CashMachine(new Bank());
        cm.login(2000);

        String actual = cm.getAccoutName();
        String expected = "Example 2";

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getAccoutEmail() {
        CashMachine cm = new CashMachine(new Bank());
        cm.login(1000);

        String actual = cm.getAccoutEmail();
        String expected = "example1@gmail.com";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAccoutEmail2() {
        CashMachine cm = new CashMachine(new Bank());
        cm.login(2000);

        String actual = cm.getAccoutEmail();
        String expected = "example2@gmail.com";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAccoutBal() {
        CashMachine cm = new CashMachine(new Bank());
        cm.login(1000);

        int actual = cm.getAccoutBal();
        int expected = 500;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAccoutBal2() {
        CashMachine cm = new CashMachine(new Bank());
        cm.login(2000);

        int actual = cm.getAccoutBal();
        int expected = 200;

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void login() {
    }

    @Test
    public void deposit() {
        CashMachine cm = new CashMachine(new Bank());
        cm.login(2000);
        cm.deposit(100);

        int actual = cm.getAccoutBal();
        int expected = 300;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void deposit2(){
        CashMachine cm = new CashMachine(new Bank());
        cm.login(1000);
        cm.deposit(500);

        int actual = cm.getAccoutBal();
        int expected = 1000;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void withdraw() {
        CashMachine cm = new CashMachine(new Bank());
        cm.login(2000);
        cm.withdraw(200);

        int actual = cm.getAccoutBal();
        int expected = 0;

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void withdraw2(){
        CashMachine cm = new CashMachine(new Bank());
        cm.login(1000);
        cm.withdraw(300);

        int actual = cm.getAccoutBal();
        int expected = 200;

        Assert.assertEquals(expected, actual);



    }

    @Test
    public void addAccountTest1() {
        String[] args = {"1111", "Argo", "demo@eail.ext", "1000000"};
        List<String> input = new ArrayList<>(Arrays.asList(args));

        CashMachine cm = new CashMachine(new Bank());
        cm.addAccount(input);

        cm.login(1111);

        Integer expected = Integer.parseInt(args[0]);
        Integer actual = cm.getAccoutId();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void addAccountTest2() {
        String[] args = {"1111", "Argo", "demo@eail.ext", "1000000"};
        List<String> input = new ArrayList<>(Arrays.asList(args));

        CashMachine cm = new CashMachine(new Bank());
        cm.addAccount(input);

        cm.login(1111);

        String expected = args[1];
        String actual = cm.getAccoutName();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void addAccountTest3() {
        String[] args = {"1111", "Argo", "demo@eail.ext", "1000000"};
        List<String> input = new ArrayList<>(Arrays.asList(args));

        CashMachine cm = new CashMachine(new Bank());
        cm.addAccount(input);

        cm.login(1111);

        String expected = args[2];
        String actual = cm.getAccoutEmail();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void addAccountTest4() {
        String[] args = {"1111", "Argo", "demo@eail.ext", "1000000"};
        List<String> input = new ArrayList<>(Arrays.asList(args));

        CashMachine cm = new CashMachine(new Bank());
        cm.addAccount(input);

        cm.login(1111);

        Integer expected = Integer.parseInt(args[3]);
        Integer actual = cm.getAccoutBal();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addAccountTest5(){
        String[] args = {"", "Argo", "demo@email.ext", "100000"};
        List<String> input = new ArrayList<>(Arrays.asList(args));
        CashMachine cm = new CashMachine(new Bank());

        cm.addAccount(input);
        String expected = "Invalid account ID entered";
        String actual = cm.getGenericErrorMessage();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addAccountTest6(){
        String[] args = {"-1", "Argo", "demo@email.ext", "100000"};
        List<String> input = new ArrayList<>(Arrays.asList(args));
        CashMachine cm = new CashMachine(new Bank());

        cm.addAccount(input);
        String expected = "Negative account ID's are proscribed";
        String actual = cm.getGenericErrorMessage();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addAccountTest7(){
        String[] args = {"1000", "Argo", "demo@email.ext", "100000"};
        List<String> input = new ArrayList<>(Arrays.asList(args));
        CashMachine cm = new CashMachine(new Bank());

        cm.addAccount(input);
        String expected = "Account already exists";
        String actual = cm.getGenericErrorMessage();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addAccountTest8(){
        String[] args = {"1234", "", "demo@email.ext", "100000"};
        List<String> input = new ArrayList<>(Arrays.asList(args));
        CashMachine cm = new CashMachine(new Bank());

        cm.addAccount(input);
        String expected = "Name not entered";
        String actual = cm.getGenericErrorMessage();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addAccountTest9(){
        String[] args = {"1234", "Argo", "demoemail.ext", "100000"};
        List<String> input = new ArrayList<>(Arrays.asList(args));
        CashMachine cm = new CashMachine(new Bank());

        cm.addAccount(input);
        String expected = "Invalid email address";
        String actual = cm.getGenericErrorMessage();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addAccountTest10(){
        String[] args = {"1234", "Argo", "@email.ext", "100000"};
        List<String> input = new ArrayList<>(Arrays.asList(args));
        CashMachine cm = new CashMachine(new Bank());

        cm.addAccount(input);
        String expected = "Invalid email address";
        String actual = cm.getGenericErrorMessage();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addAccountTest11(){
        String[] args = {"1234", "Argo", "demo@.ext", "100000"};
        List<String> input = new ArrayList<>(Arrays.asList(args));
        CashMachine cm = new CashMachine(new Bank());

        cm.addAccount(input);
        String expected = "Invalid email address";
        String actual = cm.getGenericErrorMessage();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addAccountTest12(){
        String[] args = {"1234", "Argo", "demo@emailext", "100000"};
        List<String> input = new ArrayList<>(Arrays.asList(args));
        CashMachine cm = new CashMachine(new Bank());

        cm.addAccount(input);
        String expected = "Invalid email address";
        String actual = cm.getGenericErrorMessage();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addAccountTest13(){
        String[] args = {"1234", "Argo", "demo@email.", "100000"};
        List<String> input = new ArrayList<>(Arrays.asList(args));
        CashMachine cm = new CashMachine(new Bank());

        cm.addAccount(input);
        String expected = "Invalid email address";
        String actual = cm.getGenericErrorMessage();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addAccountTest14(){
        String[] args = {"1234", "Argo", "demo@email.ext", "-100000"};
        List<String> input = new ArrayList<>(Arrays.asList(args));
        CashMachine cm = new CashMachine(new Bank());

        cm.addAccount(input);
        String expected = "We don't want your negative balances 'round these parts";
        String actual = cm.getGenericErrorMessage();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addAccountTest15(){
        String[] args = {"1234", "Argo", "demo@email.ext", "0"};
        List<String> input = new ArrayList<>(Arrays.asList(args));
        CashMachine cm = new CashMachine(new Bank());

        cm.addAccount(input);
        cm.login(1234);
        Integer expected = 0;
        Integer actual = cm.getAccoutBal();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addAccountTest16(){
        String[] args = {"1234", "Argo", "demo@email.ext", "puppy"};
        List<String> input = new ArrayList<>(Arrays.asList(args));
        CashMachine cm = new CashMachine(new Bank());

        cm.addAccount(input);
        String expected = "Invalid balance entered";
        String actual = cm.getGenericErrorMessage();

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void exit() {
    }

    @Test
    public void toString1() {
    }

    @Test
    public void getAccountNumbers() {
    }

    @Test
    public void getWithdrawFailed() {
    }

    @Test
    public void getWithdrawFailedError() {
    }

    @Test
    public void getGenericError() {
    }

    @Test
    public void getGenericErrorMessage() {
    }
}