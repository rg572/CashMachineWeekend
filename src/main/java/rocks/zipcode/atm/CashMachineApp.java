package rocks.zipcode.atm;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;

import javafx.scene.paint.Color;


import javafx.scene.text.Font;

import javafx.scene.layout.GridPane;

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


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {



    private TextField field = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());
    ObservableList<String> accountNums;
    private TextField passWord = new TextField();
    private TextField userName = new TextField();


    private Parent login() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(300, 500);
        Button login = new Button("Login");


        FlowPane flowpane = new FlowPane();

        flowpane.getChildren().add(userName);
        flowpane.getChildren().add(passWord);
        flowpane.getChildren().add(login);
        vbox.getChildren().addAll(userName,passWord,login);

        return vbox;
    }

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
        btnSubmit.setStyle("-fx-background-color: #86a5d6");
        btnSubmit.setFont(Font.font("sans-serif"));



        //btnSubmit.setStyle("fx-text-fill: red");

        Button btnExit = new Button("Exit");

        btnExit.setStyle("-fx-background-color: black");


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
            btnAddAccount.setDisable(true);

            field.setText("");
            accountId.setText("Try account 1000 or 2000 and click submit.");
            name.setText("");
            email.setText("");
            balance.setText("");

            //accountNums.add("6000");

            //areaInfo.setText(cashMachine.toString());
        });
        btnAddAccount.setDisable(true);
        btnAddAccount.setOnAction(e ->{
            //TextInputDialog newAccountDialog = new TextInputDialog("something");
            //newAccountDialog.setContentText("This is something");
            //newAccountDialog.showAndWait();

            Dialog<List<String>> dialog = new Dialog<>();
            dialog.setTitle("It's a dialog");
            dialog.setHeaderText("IN SPAAAAAACE!!!!");

            // dialog.setGraphic(new ImageView(this.getClass().getResource("fileName.extension").toString())) //add an icon

            ButtonType addButtonType = new ButtonType("Add Account", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20,150,10,10));

            TextField accountID = new TextField();
            accountID.setPromptText("Account ID");
            TextField accountName = new TextField();
            accountName.setPromptText("Name");
            TextField accountEmail = new TextField();
            accountEmail.setPromptText("Email Address");
            TextField accountBalance = new TextField();
            accountBalance.setPromptText("Starting Balance");

            grid.add(new Label("Account ID"),0,0);
            grid.add(accountID, 1,0);
            grid.add(new Label("Name"), 0, 1);
            grid.add(accountName,1,1);
            grid.add(new Label("Email Address"),0,2);
            grid.add(accountEmail,1,2);
            grid.add(new Label("Starting Balance"),0,3);
            grid.add(accountBalance,1,3);

            //Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
            //addButton.setDisable(true);


            dialog.getDialogPane().setContent(grid);

            dialog.setResultConverter(dialogButton -> {
                if( dialogButton == addButtonType){
                    List<String> inputList = new ArrayList<>();
                    inputList.add(accountID.getText());
                    inputList.add(accountName.getText());
                    inputList.add(accountEmail.getText());
                    inputList.add(accountBalance.getText());
                    return inputList;
                }
                return null;
            });

            Optional<List<String>> result = dialog.showAndWait();

            result.ifPresent(newAccountInfo -> {
                System.out.println("Account ID:     "+ newAccountInfo.get(0));
                System.out.println("Name:           " + newAccountInfo.get(1));
                System.out.println("Email Address:: " + newAccountInfo.get(2));
                System.out.println("Balance:        " + newAccountInfo.get(3));
            });

        });


        FlowPane flowpane = new FlowPane();

        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(accountMenu);
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);
        flowpane.getChildren().add(btnAddAccount);

        vbox.getChildren().addAll(field, flowpane, accountId, name, email, balance);
        flowpane.setHgap(5);


        //accountNums.add("5000");
        return vbox;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(login()));
        stage.setScene(new Scene(createContent()));

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
