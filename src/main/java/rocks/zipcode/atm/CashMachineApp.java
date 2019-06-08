package rocks.zipcode.atm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;


import java.lang.Object;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Region;
import javafx.scene.layout.Pane;



/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {



    private TextField field = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());
    ObservableList<String> accountNums;

    private Parent createContent() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 600);

       // TextArea areaInfo = new TextArea();

        TextArea accountId = new TextArea();
        TextArea name = new TextArea();
        TextArea email = new TextArea();
        TextArea balance = new TextArea();


        Alert insufficientFunds = new Alert(Alert.AlertType.WARNING);
        insufficientFunds.setTitle("Insufficient Funds");
        insufficientFunds.setHeaderText("Insufficient Funds");

        //field.setMaxSize(70, 20);



  

        //areaInfo.setStyle(" -fx-background-color: black;");


        //////////DROP-DOWN/////////////

        accountNums =FXCollections.observableArrayList();
        for(String s : cashMachine.getAccountNumbers()){
            accountNums.add(s);
        }
        //accountNums.add("3000");

        Button btnSubmit = new Button("Set Account ID");

        //btnSubmit.setStyle("-fx-background-color: black");
        //btnSubmit.setStyle("fx-text-fill: red");

        Button btnExit = new Button("Exit");
        btnExit.setStyle("-fx-background-color: black");

        Button btnWithdraw = new Button("Withdraw");

        Button btnDeposit = new Button("Deposit");

        Button btnAddAccount = new Button("Add Account");

        ComboBox accountMenu = new ComboBox(accountNums);
        accountMenu.setPromptText("Choose an Account");



        //////////SUBMIT/////////////

        btnSubmit.setOnAction(e -> {
            System.out.println(accountMenu.getValue().toString());
            int id = Integer.parseInt(accountMenu.getValue().toString());
            cashMachine.login(id);
            btnDeposit.setDisable(false);
            btnExit.setDisable(false);
            btnWithdraw.setDisable(false);
            btnSubmit.setDisable(true);

            accountId.setText(Integer.toString(cashMachine.getAccoutId()));
            balance.setText(Integer.toString(cashMachine.getAccoutBal()));
            name.setText(cashMachine.getAccoutName());
            email.setText(cashMachine.getAccoutEmail());

            //areaInfo.setText(cashMachine.toString());
        });


        //accountNums.add("4000");

        //////////DEPOSIT/////////////

        btnDeposit.setDisable(true);
        btnDeposit.setOnAction(e -> {
            int amount = Integer.parseInt(field.getText());
            cashMachine.deposit(amount);
            field.setText("");
            //areaInfo.setText(cashMachine.toString());
            balance.setText(Integer.toString(cashMachine.getAccoutBal()));
        });

        //////////WITHDRAW/////////////

        btnWithdraw.setDisable(true);
        btnWithdraw.setOnAction(e -> {
            int amount = Integer.parseInt(field.getText());
            cashMachine.withdraw(amount);
            field.setText("");

           // areaInfo.setText(cashMachine.toString());

            balance.setText(Integer.toString(cashMachine.getAccoutBal()));

            if(cashMachine.getWithdrawFailed()){
                insufficientFunds.setContentText(cashMachine.getWithdrawFailedError());
                insufficientFunds.showAndWait();


            }
        });
        //////////EXIT/////////////

        btnExit.setDisable(true);
        btnExit.setOnAction(e -> {
            cashMachine.exit();
            btnDeposit.setDisable(true);
            btnExit.setDisable(true);
            btnWithdraw.setDisable(true);
            btnSubmit.setDisable(false);
            field.setText("");
            accountId.setText("Try account 1000 or 2000 and click submit.");
            name.setText("");
            email.setText("");
            balance.setText("");

            //accountNums.add("6000");

            //areaInfo.setText(cashMachine.toString());
        });

        btnAddAccount.setOnAction(e ->{
            TextInputDialog newAccountDialog = new TextInputDialog("something");
            newAccountDialog.setContentText("This is something");
            newAccountDialog.showAndWait();

            //Dialog
        });


        FlowPane flowpane = new FlowPane();

        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(accountMenu);
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);
        flowpane.getChildren().add(btnAddAccount);
        vbox.getChildren().addAll(field, flowpane, accountId, name, email, balance);
        //accountNums.add("5000");
        return vbox;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
