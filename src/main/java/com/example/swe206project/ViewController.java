package com.example.swe206project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ViewController {
    private Stage stage; private Scene scene; private Parent root;
    private User user;
    @FXML

    private TextField idTextField;

    @FXML
    private TextField passwordTextField;
    @FXML
    private Button newTButton;

 

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


        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewTournament.fxml"));
        root=loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
 
        stage.setScene(scene);
        stage.show();
    }
    public void showSelectedTournament(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectedTournament.fxml"));
        root=loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showAddEditScores(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEditScores.fxml"));
        root=loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
            String _url="https://us-central1-swe206-221.cloudfunctions.net/app/UserSignIn?username="+idTextField.getText()+"&password="+passwordTextField.getText();
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
                name=idTextField.getText();
                type="admin";
                email="";
            }
            else {
             name=listOfInfo[1].substring(1, listOfInfo[1].lastIndexOf(",")-1);
             type =listOfInfo[2].substring(1, listOfInfo[2].lastIndexOf(",")-1);
             email=listOfInfo[3].substring(1,listOfInfo[3].length()-1);
            }

            if (type.equals("student")){
                user=new Student(name, email,idTextField.getText());
            }
            else {
                user=new Admin(name, email, idTextField.getText());
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


}