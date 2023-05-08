package com.example.swe206project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.stage.Stage;

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
    private ListView<String> listOfShownSports;

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

    @FXML
    private TextField tournamnetName;

    @FXML
    private TextField DurationBetweenMatches;
 
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
    @FXML
    void loadSports(MouseEvent event) {
        DataBase d=new DataBase();
        if (loaded==false){
            for (int i=0;i<d.getSports().size();i++){
            listOfShownSports.getItems().add(d.getSports().get(i));
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
    
  
    @FXML
    void confirmNewTournemantTriggered(ActionEvent event) {
        System.out.println("round robin = " + rType.isSelected());
        System.out.println("name = "+ tournamnetName.getText());
        System.out.println("duration = "+ DurationBetweenMatches.getText());

        System.out.println("solo = "+ soloType.isSelected());

    }

    @FXML
    void newSportTriggered(ActionEvent event) {
        DataBase d=new DataBase();
        if (d.sportExist(newSportName.getText()))
            return;
        d.addSport(newSportName.getText());
        listOfShownSports.getItems().add(newSportName.getText());


    }

    @FXML
    void selectDateTriggered(ActionEvent event) {
        System.out.println("wadaw");

    }






}
