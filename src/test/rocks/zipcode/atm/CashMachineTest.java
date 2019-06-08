package rocks.zipcode.atm;


import org.junit.Assert;
import org.junit.Test;
import rocks.zipcode.atm.bank.Bank;

import static org.junit.Assert.*;

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
    public void addAccount() {
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