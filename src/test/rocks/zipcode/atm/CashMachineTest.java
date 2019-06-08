package rocks.zipcode.atm;

import org.junit.Assert;
import org.junit.Test;
import rocks.zipcode.atm.bank.Bank;
import sun.util.resources.cldr.zh.CalendarData_zh_Hans_SG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CashMachineTest {

    @Test
    public void getAccoutId() {
    }

    @Test
    public void getAccoutName() {
    }

    @Test
    public void getAccoutEmail() {
    }

    @Test
    public void getAccoutBal() {
    }

    @Test
    public void login() {
    }

    @Test
    public void deposit() {
    }

    @Test
    public void withdraw() {
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