package rocks.zipcode.atm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {

    private TextField field = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());
    ObservableList<String> accountNums;

    private Scene login() {

        return
    }

    private Parent createContent() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 600);

        TextArea areaInfo = new TextArea();

        Alert insufficientFunds = new Alert(Alert.AlertType.WARNING);
        insufficientFunds.setTitle("Insufficient Funds");
        insufficientFunds.setHeaderText("Insufficient Funds");

        //////////DROP-DOWN/////////////

        accountNums =FXCollections.observableArrayList();
        for(String s : cashMachine.getAccountNumbers()){
            accountNums.add(s);
        }
        //accountNums.add("3000");
        areaInfo.setStyle(" -fx-background-color: black;");

        Button btnSubmit = new Button("Set Account ID");
        btnSubmit.setStyle("-fx-background-color: #86a5d6");
        btnSubmit.setFont(Font.font("sans-serif"));



        //btnSubmit.setStyle("fx-text-fill: red");
        Button btnExit = new Button("Exit");
        btnExit.setStyle("-fx-background-color: #86a5d6; -fx-radius: 20" );
        btnExit.setFont(Font.font("sans-serif"));

        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setStyle("-fx-background-color: #86a5d6; -fx-radius: 20");
        btnWithdraw.setFont(Font.font("sans-serif"));


        Button btnDeposit = new Button("Deposit");
        btnDeposit.setStyle("-fx-background-color: #86a5d6;  -fx-radius: 50");
        btnDeposit.setFont(Font.font("sans-serif"));


        Button btnAddAccount = new Button("Add Account");
        btnAddAccount.setStyle("-fx-background-color: #86a5d6;  -fx-radius: 20");
        btnAddAccount.setFont(Font.font("sans-serif"));



        ComboBox accountMenu = new ComboBox(accountNums);
        accountMenu.setPromptText("Choose an Account");
        accountMenu.setStyle("-fx-background-color: #86a5d6;  -fx-radius: 20");

        vbox.setStyle("-fx-background-color: #4b6184; -fx-border-color: black; -fx-border-width: 2");



        //////////SUBMIT/////////////

        btnSubmit.setOnAction(e -> {
            System.out.println(accountMenu.getValue().toString());
            int id = Integer.parseInt(accountMenu.getValue().toString());
            cashMachine.login(id);
            btnDeposit.setDisable(false);
            btnExit.setDisable(false);
            btnWithdraw.setDisable(false);
            btnAddAccount.setDisable(false);


            btnSubmit.setDisable(true);

            areaInfo.setText(cashMachine.toString());
        });


        //accountNums.add("4000");

        //////////DEPOSIT/////////////

        btnDeposit.setDisable(true);
        btnDeposit.setOnAction(e -> {
            int amount = Integer.parseInt(field.getText());
            cashMachine.deposit(amount);

            areaInfo.setText(cashMachine.toString());
        });

        //////////WITHDRAW/////////////

        btnWithdraw.setDisable(true);
        btnWithdraw.setOnAction(e -> {
            int amount = Integer.parseInt(field.getText());
            cashMachine.withdraw(amount);

            areaInfo.setText(cashMachine.toString());

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
            btnAddAccount.setDisable(true);

            field.setText("");

            //accountNums.add("6000");

            areaInfo.setText(cashMachine.toString());
        });
        btnAddAccount.setDisable(true);
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
        flowpane.setHgap(5);
        vbox.getChildren().addAll(field, flowpane, areaInfo);
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
