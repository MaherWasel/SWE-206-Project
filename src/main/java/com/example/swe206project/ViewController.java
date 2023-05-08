package com.example.swe206project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.time.LocalDate;

public class ViewController {
    private Stage stage; private Scene scene; private Parent root;
    private User user;
    @FXML

    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;
    @FXML
    private Button newTButton;
    @FXML
    private Button confirmButton;

    @FXML
    private Button dateButton;

    @FXML
    private RadioButton eType;

    @FXML
    private ListView<Button> listOfShownSports;

    @FXML
    private ToggleGroup memKind;

    @FXML
    private TextField newSportName;

    @FXML
    private TextField numOfParticipants;

    @FXML
    private RadioButton rType;

    @FXML
    private RadioButton soloType;

    @FXML
    private Button submitingNewSport;

    @FXML
    private RadioButton teamType;

    @FXML
    private ToggleGroup tourType;
    private boolean loaded=false;
    private boolean loadedT=false;
    private String selectedSport;
    private DatePicker startDate;
    private DatePicker closingDate;
    @FXML
    private TextField tournamnetName;

    @FXML
    private TextField DurationBetweenMatches;
    @FXML
    private ListView<HBox> listOfShownTournamnet;

    @FXML
    void loadTournamnets(MouseEvent event) {
        if (loadedT==false){
        DataBase d=new DataBase();
        HBox row=new HBox();

        for (int i=0;i<d.getTournamnets().size();i++){
            row=new HBox();
            row.setSpacing(50);
            VBox innerColumn=new VBox();
            Label tournamnetName=new Label(d.getTournamnets().get(i).getName());
            tournamnetName.setScaleX(1.4);
            innerColumn.getChildren().add(tournamnetName);
            HBox innerRow= new HBox();
            Label sportLabel=new Label("Sport : "+d.getTournamnets().get(i).getSport());
            sportLabel.setScaleX(1);

            innerRow.getChildren().add(sportLabel);
            Label statusLabel=new Label("status : closed");
            statusLabel.setScaleX(1);
            innerRow.setSpacing(20);
            innerRow.getChildren().add(statusLabel);
            innerColumn.getChildren().add(innerRow);
            RadioButton choseTButton=new RadioButton();
            choseTButton.setAlignment(Pos.CENTER_RIGHT);
            row.getChildren().add(innerColumn);
            row.getChildren().add(choseTButton);
            row.setAlignment(Pos.CENTER);
            listOfShownTournamnet.getItems().add(row);
    
        }
    }   loadedT=true;

    }
 
// instead of having it as a rigid method, it should be used in a branch of  if-else statement
    @FXML
    void validateNewTButton(ActionEvent event) throws IOException {
        if (getUser() instanceof Student){
            ErrorScene("Error : The user is not authorized");
            return;
        }
        else{
            showNewTournament(event);
        }

    }
    


 
    void ErrorScene(String error){

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Message");
        alert.setHeaderText(error);
        alert.show();

    }
 

     public void showLogin(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        root=loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void showMainScene(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
        root=loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
  

        stage.show();
    }

    public void showNewTournament(ActionEvent event) throws IOException {


        if (getUser() instanceof Student){
            ErrorScene("Error : The user is not authorized");
            return;
        }
        else{
            ;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewTournament.fxml"));
        root=loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
    }
    public void showSelectedTournament(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectedTournament.fxml"));
        root=loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    void selectSport(String selectedSport){
        this.selectedSport=selectedSport;
        System.out.println(selectedSport);
    }
    @FXML
    void loadSports(MouseEvent event) {
        DataBase d=new DataBase();
        int i=0;

        if (loaded==false){
            for ( i=0;i<d.getSports().size();i++){
                Button sportButton=new Button(d.getSports().get(i));
                sportButton.setOnAction(e->{
                    selectSport(sportButton.getText())  ;   
                });
            
            listOfShownSports.getItems().add(sportButton);
        }
        this.loaded=true;
}

    }

    public void showAddEditScores(ActionEvent event) throws IOException {
        if (getUser() instanceof Student){
            ErrorScene("Error : The user is not authorized");
            return;
        }
        else{
            showNewTournament(event);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEditScores.fxml"));
        root=loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
    }
    public void showViewMembers(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewMembers.fxml"));
        root=loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public  void loginButtonTriggered(ActionEvent event) {
        try{
            String _url="https://us-central1-swe206-221.cloudfunctions.net/app/UserSignIn?username="+ usernameTextField.getText()+"&password="+passwordTextField.getText();
            URL url=new URL(_url);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int status =connection.getResponseCode();
            if (status!=200){
                ErrorScene("Error : Username or Password is wrong");
                return;
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            
            while ((inputLine = in.readLine()) != null) {

                response.append(inputLine);
            }
            System.out.println(response);
            in.close();
            String info =response.toString();
            info =info.substring(1, info.length()-1);
            String[] listOfInfo=info.split(":");
            String name;
            String type;
            String email;
            if (listOfInfo.length==2){
                name= usernameTextField.getText();
                type="admin";
                email="";
            }
            else {
             name=listOfInfo[1].substring(1, listOfInfo[1].lastIndexOf(",")-1);
             type =listOfInfo[2].substring(1, listOfInfo[2].lastIndexOf(",")-1);
             email=listOfInfo[3].substring(1,listOfInfo[3].length()-1);
            }

            if (type.equals("student")){
                user=new Student(name, email, usernameTextField.getText());
            }
            else {
                user=new Admin(name, email, usernameTextField.getText());
            }
            ObjectOutputStream writing =new ObjectOutputStream(new FileOutputStream(new File(("user.io"))));
            writing.writeObject(user);
          
            showMainScene(event);
            }
            catch(IOException e){
                System.out.println(e);
            }
    }

    public User getUser(){
        try{
            ObjectInputStream reading=new ObjectInputStream(new FileInputStream(new File("user.io")));
            return (User)reading.readObject();
        }
        catch(IOException e){
            System.out.println(e);

            return null;
            
        }
        catch(ClassNotFoundException e){
            System.out.println(e);
            return null;

        }
    }
    private void scheduleDate() {
        VBox vbox = new VBox(20);
        vbox.setStyle("-fx-padding: 10;");
        Scene scene = new Scene(vbox, 400, 200);
        Stage dateStage=new Stage();
        dateStage.setScene(scene);
        dateStage.show();
        startDate = new DatePicker();
        closingDate = new DatePicker();
        startDate.setValue(LocalDate.now());
        final Callback<DatePicker, DateCell> dayCellFactory = 
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                           
                            if (item.isBefore(
                                startDate.getValue().plusDays(1))
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                            }   
                    }
                };
            }
        };
        closingDate.setDayCellFactory(dayCellFactory);
        closingDate.setValue(startDate.getValue().plusDays(1));
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        Label checkInlabel = new Label("Registration Starting Date:");
        gridPane.add(checkInlabel, 0, 0);
        GridPane.setHalignment(checkInlabel, HPos.LEFT);
        gridPane.add(startDate, 0, 1);
        Label checkOutlabel = new Label("Closing Starting Date:");
        gridPane.add(checkOutlabel, 0, 2);
        GridPane.setHalignment(checkOutlabel, HPos.LEFT);
        gridPane.add(closingDate, 0, 3);
        vbox.getChildren().add(gridPane);
    }
    
    Boolean validateNewTInfo(String name, String sport , String  numOfParticipants,
    String DurnationBetweenMatches ){
        if (name.isEmpty())
            return false;
        if (selectedSport==null||selectedSport.isEmpty()){
            return false;
        }
        return true;


    }
    @FXML
    void confirmNewTournemantTriggered(ActionEvent event) throws IOException {
        String name=tournamnetName.getText();
        String sport=selectedSport;
        Boolean valid=validateNewTInfo(name, sport, numOfParticipants.getText(), DurationBetweenMatches.getText());
        if (valid==false){
            ErrorScene("not valid input");
            return;
        }
        
        boolean teamBased=teamType.isSelected();
        boolean isElemination=eType.isSelected();
        int  numOfParticipant=Integer.valueOf(numOfParticipants.getText());
        int DurnationBetweenMatches=Integer.valueOf(DurationBetweenMatches.getText());
        Tournamnet tournamnet=new Tournamnet(name, sport, teamBased, isElemination, numOfParticipant, DurnationBetweenMatches, startDate.getValue(), closingDate.getValue());

        DataBase d=new DataBase();
        d.addTournamnet(tournamnet);
        loadedT=false;
        showMainScene(event);

    }


    @FXML
    void newSportTriggered(ActionEvent event) {
        DataBase d=new DataBase();
        if (d.sportExist(newSportName.getText()))
            return;
        d.addSport(newSportName.getText());
        Button sportButton=new Button(newSportName.getText());
        listOfShownSports.getItems().add(sportButton);
        sportButton.setOnAction(e->{
            selectSport(sportButton.getText())     ;
        });


    }

    @FXML
    void selectDateTriggered(ActionEvent event) {
        scheduleDate();
    }






}
