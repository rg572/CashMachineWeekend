package rocks.zipcode.atm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
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
    private TextField passWord = new TextField();
    private TextField userName = new TextField();

/////////LOGIN MODAL//////////
    private Parent login() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(250, 400);
        Button login = new Button("Login");
        Label head = new Label("DOGE BANK");

        login.setOnAction(e -> { });

        head.setStyle("-fx-text-fill: white");

        vbox.setStyle("-fx-background-color: #30415d ");
        vbox.setAlignment(Pos.CENTER);

        login.setStyle("-fx-text-fill: #30415d;");


        FlowPane flowpane = new FlowPane();
        flowpane.getChildren().add(head);
        flowpane.getChildren().add(userName);
        flowpane.getChildren().add(passWord);
        flowpane.getChildren().add(login);
        vbox.getChildren().addAll(head,userName,passWord,login);

        return vbox;
    }


///////////MAIN SCENE////////////
    private Parent createContent() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 500);

        Label title = new Label("ABBD Bank");
        title.setStyle("-fx-text-fill: white; -fx-font: 40 Tahoma; ");

        Label idLabel = new Label("Account ID:");
        idLabel.setStyle("-fx-text-fill: white");
        TextArea accountId = new TextArea();
        accountId.setMaxHeight(10);
        accountId.setDisable(true);
        accountId.setStyle("-fx-opacity: 1;");

        Label nameLabel = new Label("Account Holder Name:");
        nameLabel.setStyle("-fx-text-fill: white");
        TextArea name = new TextArea();
        name.setMaxHeight(10);
        name.setDisable(true);
        name.setStyle("-fx-opacity: 1;");

        Label emailLabel = new Label("Account Email:");
        emailLabel.setStyle("-fx-text-fill: white");
        TextArea email = new TextArea();
        email.setMaxHeight(10);
        email.setDisable(true);
        email.setStyle("-fx-opacity: 1;");

        Label balanceLabel = new Label("Account Balance:");
        balanceLabel.setStyle("-fx-text-fill: white");
        TextArea balance = new TextArea();
        balance.setMaxHeight(10);
        balance.setDisable(true);
        balance.setStyle("-fx-opacity: 1;");

        Alert insufficientFunds = new Alert(Alert.AlertType.WARNING);
        insufficientFunds.setTitle("Insufficient Funds");
        insufficientFunds.setHeaderText("Insufficient Funds");

        Alert helpAlert = new Alert(Alert.AlertType.INFORMATION);
        //Dialog<List<String>> helpAlert= new Dialog<>();

//        helpAlert.setTitle("\"Help\"");
//        helpAlert.setHeaderText("Google it");
//        helpAlert.getDialogPane().setStyle("-fx-font-size: 20pt");
//        ImageView kris = new ImageView("Kris.png");
//        kris.setFitHeight(150);
//        kris.setFitWidth(150);
//        helpAlert.setGraphic(kris);

//////////DROP-DOWN/////////////

        accountNums =FXCollections.observableArrayList();
        for(String s : cashMachine.getAccountNumbers()){
            accountNums.add(s);
        }

////////BUTTON STYLES//////////
        Button btnSubmit = new Button("Set Account ID");

        btnSubmit.setStyle("-fx-background-color: #86a5d6");
        btnSubmit.setFont(Font.font("sans-serif"));

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

        Button btnHelp = new Button("Help");
        btnHelp.setStyle("-fx-background-color: #86a5d6; -fx-radius: 20");
        btnHelp.setFont(Font.font("sans-serif"));


        vbox.setStyle("-fx-background-color: #4b6184; -fx-border-color: black; -fx-border-width: 2");


//////////SUBMIT/////////////

        btnSubmit.setOnAction(e -> {
            System.out.println(accountMenu.getValue().toString());
            int id = Integer.parseInt(accountMenu.getValue().toString());
            cashMachine.login(id);
            btnDeposit.setDisable(false);
            btnExit.setDisable(false);
            btnWithdraw.setDisable(false);
            btnAddAccount.setDisable(true);
            btnSubmit.setDisable(true);

            accountId.setText(Integer.toString(cashMachine.getAccoutId()));
            balance.setText(Double.toString(cashMachine.getAccoutBal()));
            name.setText(cashMachine.getAccoutName());
            email.setText(cashMachine.getAccoutEmail());
        });

//////////DEPOSIT/////////////

        btnDeposit.setDisable(true);
        btnDeposit.setOnAction(e -> {
            double amount = Integer.parseInt(field.getText());
            cashMachine.deposit(amount);
            field.setText("");
            balance.setText(Double.toString(cashMachine.getAccoutBal()));
        });

//////////WITHDRAW/////////////

        btnWithdraw.setDisable(true);
        btnWithdraw.setOnAction(e -> {
            double amount = Integer.parseInt(field.getText());
            cashMachine.withdraw(amount);
            field.setText("");

            balance.setText(Double.toString(cashMachine.getAccoutBal()));

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
            btnAddAccount.setDisable(false);

            field.setText("");
            accountId.setText("Try account 1000 or 2000 and click submit.");
            name.setText("");
            email.setText("");
            balance.setText("");

            //accountNums.add("6000");
        });

/////////////DIALOG BOX?/////////////////

        btnAddAccount.setDisable(false);
        btnAddAccount.setOnAction(e ->{

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

                cashMachine.addAccount(result.get());
                accountNums.clear();
                for(String s : cashMachine.getAccountNumbers()){
                    accountNums.add(s);
                }

                if(cashMachine.getGenericError()){
                    //System.out.println("yeller Error");
                    Alert yeller = new Alert(Alert.AlertType.ERROR);
                    yeller.setTitle("Danger, Will Robinson!");
                    //yeller.setHeaderText("Something, somewhere went terribly wrong");
                    yeller.setHeaderText(cashMachine.getGenericErrorMessage());
                    yeller.showAndWait();
                }


                System.out.println("Account ID:     "+ newAccountInfo.get(0));
                System.out.println("Name:           " + newAccountInfo.get(1));
                System.out.println("Email Address:: " + newAccountInfo.get(2));
                System.out.println("Balance:        " + newAccountInfo.get(3));
            });

        });

/////////////////HELP BUTTON////////////////

        btnHelp.setOnAction(e ->{
            helpAlert.showAndWait();

        });


        FlowPane flowpane = new FlowPane();

        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(accountMenu);
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);
        flowpane.getChildren().add(btnAddAccount);
        flowpane.getChildren().add(btnHelp);

        vbox.getChildren().addAll(title ,field, flowpane, idLabel, accountId, nameLabel, name, emailLabel, email, balanceLabel, balance);
        flowpane.setHgap(5);

        return vbox;
    }


    @Override
    public void start(Stage stage) throws Exception {
        //stage.setScene(new Scene(login()));
        stage.setScene(new Scene(createContent()));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
