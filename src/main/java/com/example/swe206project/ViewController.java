package com.example.swe206project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static javafx.application.Application.launch;

public class ViewController {
    private Stage stage; private Scene scene; private Parent root;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    void loginButtonTriggered(ActionEvent event) {
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
            // these comments would be used later ..
            // BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            // String inputLine;
            // StringBuffer response = new StringBuffer();
            
            // while ((inputLine = in.readLine()) != null) {
            //     response.append(inputLine);
            // }
            // in.close();
            // String info=response.toString();
            // // print result
            // System.out.println(response);
    
            // System.out.println(status);
            showMainScene(event);

            
    
            }
    
            catch(IOException e){
                System.out.println(e);
                
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
    public void showNewAccount(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewAccount.fxml"));
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

}