package rocks.zipcode.atm;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

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

    private Parent createContent() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 600);

        TextArea areaInfo = new TextArea();

        Alert insufficientFunds = new Alert(Alert.AlertType.WARNING);
        insufficientFunds.setTitle("Insufficient Funds");
        insufficientFunds.setHeaderText("Insufficient Funds");

        field.setMaxWidth(200);



        //////////DROP-DOWN/////////////

        accountNums =FXCollections.observableArrayList();
        for(String s : cashMachine.getAccountNumbers()){
            accountNums.add(s);
        }
        //accountNums.add("3000");


        Button btnSubmit = new Button("Set Account ID");
        areaInfo.setStyle(" -fx-background-color: black;");
        btnSubmit.setStyle("-fx-background-color: #738aba");
        
        //btnSubmit.setStyle("fx-text-fill: red");
        Button btnExit = new Button("Exit");
        btnExit.setStyle("-fx-background-color: #738aba");

        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setStyle("-fx-background-color: #738aba");

        Button btnDeposit = new Button("Deposit");

        btnDeposit.setStyle("-fx-background-color: #738aba");

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
            field.setText("");

            //accountNums.add("6000");

            areaInfo.setText(cashMachine.toString());
        });

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
