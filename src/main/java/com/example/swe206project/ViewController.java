package com.example.swe206project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;

public class ViewController implements Initializable {
    private Stage stage; private Scene scene; private Parent root;
    private User user;
    @FXML

    private TextField usernameTextField;
    @FXML
    private ListView listOfParticipants = new ListView<>();
    
    @FXML
    private Label labelLoad;

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
    private ListView<Label> listOfShownSports;

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
    private TextField singleStudentTextField;

   

    @FXML
    private ToggleGroup tourType;
    private boolean loaded=false;
    private boolean loadedT=false;
    private String selectedSport;
    private DatePicker startDate;
    private DatePicker closingDate;
    @FXML
    private Label BackButton;

    @FXML
    private TextField tournamnetName;
    private Tournamnet selectedTournamnet;
    @FXML
    private TextField DurationBetweenMatches;
    @FXML
    private ListView<HBox> listOfShownTournamnet;
    @FXML
    private Label tournamnetVisibleName;
    boolean loadedTName=false;
    @FXML
    private ListView<TextField> RegistrationList;
    private boolean loadedSScene =false;
    @FXML
    private TextField sizeOfTeam_textField;
    @FXML
    private ListView<TextField> ListOfEnteredScores;

    @FXML
    private ListView<HBox> ListOfShownMatches;
    private List<Match> matches=new ArrayList();
    private List<TextField> scores=new ArrayList<>();
    @FXML
    private BorderPane tablePane;

    @FXML 
    void loadTable(MouseEvent event){
        
        if (getSelectedTournamnet().isEleminationType()){

            HBox mainRow=new HBox();
            VBox mainColumn=new VBox();
            HBox subRow=new HBox();
            VBox column=new VBox();
            int pos=1;
            int value=0;
            Label text1=new Label();
            Label text2=new Label();
            int count=0;
            Tournamnet _t=getSelectedTournamnet();
            Match[] _list=_t.getStageMatches(pos);
            updateTournamentInfo(_t);

            boolean valid=true;
            for (int i=1;i!=_t.getNumOfParticipants();i=i*2){
                value++;
            }
            for (int i=0;i<value;i++){ //num of mainrows

            try{
                
                 _list=_t.getStageMatches(pos);
                 System.out.println(_list[0]);
                valid=true;


        }   
        
            catch (Exception e){
                valid=false;
 
            }

        
        
        
   
    
        finally{
                if (valid){
                 _list=_t.getStageMatches(pos);
            }
                if (i==0){
                    for (int j=0;j<_t.getNumOfParticipants()/(Math.pow(2, pos));j++){
                    subRow=new HBox();
                    column=new VBox();
                    if (valid){
                        Object t1=_list[j].get1();
                        if (t1 instanceof Student){
                        text1=new Label(((Student)_list[j].get1()).getFormatedSoloParticipant());
                    
                        text2=new Label(((Student)_list[j].get2()).getFormatedSoloParticipant());
                        }else {
                            text1=new Label(_list[j].getContentender1Name());
                    
                            text2=new Label(_list[j].getContentender2Name());
                        }
                    }else{
                        text1.setText("");
                        text2.setText("");

                    }

                    column.getChildren().add(new Text(text1.getText()+"____"));

                    column.getChildren().add(new Text(text2.getText()+"____"));
                    subRow.getChildren().add(column);
                    VBox test=new VBox();
                    Label t=new Label("|");
                    t.setScaleY(2.8*(i+1));;
                    test.getChildren().add(new Text("     "));
                    if (j==getSelectedTournamnet().getNumOfParticipants()/(Math.pow(2, pos)-2)){
                        t.setScaleY(1.5*(i+1));
                    }
                    test.getChildren().add(t);
                    subRow.getChildren().add(test);
                    mainColumn.getChildren().add( subRow);

                    }
                    mainColumn.getChildren().add(new Text(" "));

                    mainRow.getChildren().add(mainColumn);
                    count++;
                }

                else {
                    mainColumn=new VBox( );
                    for (int j=0;j<getSelectedTournamnet().getNumOfParticipants()/(Math.pow(2, pos));j++){

                        subRow=new HBox();
                        column=new VBox();
                        for (int k=0;k<count;k++){
                            column.getChildren().add(new Text(" "));

                        }
                        if (valid){
                            Object t1=_list[j].get1();
                            if (t1 instanceof Student){
                            text1=new Label(((Student)_list[j].get1()).getFormatedSoloParticipant());
                        
                            text2=new Label(((Student)_list[j].get2()).getFormatedSoloParticipant());
                            }else {
                                text1=new Label(_list[j].getContentender1Name());
                        
                                text2=new Label(_list[j].getContentender2Name());
                            }
                       
                           }else{
                               text1.setText("");
                               text2.setText("");
       
                           }
                        column.getChildren().add(new Text("____"+text1.getText()));
                        for (int k=0;k<count;k++){
                            column.getChildren().add(new Text(" "));}
                        column.getChildren().add(new Text("____"+text2.getText()));
                        subRow.getChildren().add(column);
                        VBox test=new VBox();
                        Label t=new Label("|");
                        t.setScaleY(3.5/(i-0.3
                        ));;
                        
        
                        test.getChildren().add(new Text(""));
                        if (j==getSelectedTournamnet().getNumOfParticipants()/(Math.pow(2, pos)-2)){
                            t.setScaleY(4/(i-0.3));
                        }
                        for (int k=0;k<count;k++){
                            Text space=new Text(" ");
                            space.setScaleY(1.1);
                        test.getChildren().add(space);

                        }
                        test.getChildren().add(t);
                        subRow.getChildren().add(test);
                        mainColumn.getChildren().add( subRow);
                        }

                        mainColumn.setScaleX(1.5);
                        mainColumn.setScaleY(1.05);
                        mainColumn.setLayoutX(150);
                        mainRow.getChildren().add(mainColumn);
                        mainRow.getChildren().add(new Text("         "));
                        count++;   
                }   
            }

            mainRow.setScaleX(1.4);
            mainRow.setScaleY(1);
    
            tablePane.setCenter(mainRow);
            mainRow.setAlignment(Pos.CENTER);
            pos++;
        }
        if (_t.isFinishedScoring()){
            if (getSelectedTournamnet().isteamBased()){
             text1=new Label(getSelectedTournamnet().getWinner().toString());}
             else {
                text1=new Label(((Student)getSelectedTournamnet().getWinner()).getFormatedSoloParticipant());
             }
  
            subRow=new HBox();
            column=new VBox();
            
            column.getChildren().add(new Text(" "));
            column.getChildren().add(new Text(" "));

            column.getChildren().add(new Text("_____"+ text1.getText()));
            column.setScaleX(1.5);
            subRow.getChildren().add(column);
            mainRow.getChildren().add(subRow);
            tablePane.setCenter(mainRow);
        }
            
            
        

        }
        else {
            HBox mainRU =new HBox();
            for (int i=0;i<2;i++){
                VBox inColumn =new VBox();
                Label team;
              
                if (getSelectedTournamnet().isteamBased()){
                 team=new Label("TEAM");}
                else {
                    team=new Label("Player");
                }
                if (i==1){
                    team=new Label("points");
                }
                inColumn.getChildren().add(team);
                for (int j=0;j<getSelectedTournamnet().getNumOfParticipants();j++){
                    Tournamnet t=getSelectedTournamnet();
                    List<Object> list=t.getParticipants();
                    t.RoundRobinRoundsGenerator();
                    updateTournamentInfo(t);

                    if (i==1){
                        int point =t.point(list.get(j).toString());
                        inColumn.getChildren().add(new Label(String.valueOf("     " +point)));
                        
                    }
                    else {
                    if (t.isteamBased()){
                        Label name=new Label(list.get(j).toString());
                        inColumn.getChildren().add(name);
                    }
                    else {
                        Label name=new Label(((Student)list.get(j)).getFormatedSoloParticipant());
                        inColumn.getChildren().add(name);

                    }
                }
                }
                mainRU.getChildren().add(inColumn);
                mainRU.setScaleX(1.5);
                mainRU.setAlignment(Pos.CENTER);
           
                
            }
            
            tablePane.setCenter(mainRU);
            
            HBox dRow=new HBox();
            for (int i=1;i<getSelectedTournamnet().getNumOfParticipants();i++){
                Tournamnet t=getSelectedTournamnet();
                t.RoundRobinRoundsGenerator();
                VBox iColumn=new VBox();
                Label round=new Label(" Round"+(i));
                iColumn.getChildren().add(round);
                Match[] matches=t.getStageMatches(i);
                for (int j=0;j<matches.length;j++){
                    if (t.isteamBased()){
                        iColumn.getChildren().add(new Label(((Team)(matches[j].get1())).getShort()+" vs "+((Team)(matches[j].get2())).getShort()));


                    }
                    else 
                         { iColumn.getChildren().add(new Label(((Student)(matches[j].get1())).getShort()+" vs "+((Student)(matches[j].get2())).getShort()));
                        }


                }
                dRow.getChildren().add(iColumn);


            }
            dRow.setScaleX(1.4);
            dRow.setAlignment(Pos.BOTTOM_CENTER);

            tablePane.setBottom(dRow);

        }
    }








   


      
    
    @FXML
    void generateTables(ActionEvent event) throws IOException {
        if (getSelectedTournamnet().getNumOfParticipants()!=getSelectedTournamnet().getNumOfRegistredParticipants()){
            ErrorScene("Error :  The registration is not closed");
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tables.fxml"));
        root=loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
  


    }
    @FXML
    void ConfirmScoreTriggered(MouseEvent event) {
        Tournamnet t=getSelectedTournamnet();

        for (int i=0;i<matches.size();i++){
            try{
                int score1=Integer.valueOf(scores.get(i).getText().substring(0, 1));
                int score2=Integer.valueOf(scores.get(i).getText().substring(2, 3));

                matches.get(i).editMatchScore(score1, score2);
                t.addPoints(matches.get(i));


            }catch(NumberFormatException e){
                ErrorScene("Error : wrong input");
            }
        }
        updateTournamentInfo(t);
        Match[] _list=new Match[matches.size()];
        Object[] winners=new Object[matches.size()];

   
        if (t.isEleminationType()){
            for (int i=0;i<_list.length;i++){
                _list[i]=matches.get(i);
                winners[i]=matches.get(i).showWinner();
            }
        t.confirmMatches(t.getCurrentStage(), _list);
        t.nextStage();
        t.addNewStageMatches(t.getCurrentStage(), winners);
        updateTournamentInfo(t);
        ErrorScene("Entering the scores for stage number " +(t.getCurrentStage()-1)+" has been done");}
        else {
            List<Object> l=t.getParticipants();
            for (int i=0;i<l.size();i++){
                System.out.println(l.get(i).toString()+"points = "+t.point(l.get(i).toString()));
            }
            t.nextStage();
            updateTournamentInfo(t);

            ErrorScene("Entering the scores for stage number " +(t.getCurrentStage()-1)+" has been done");

        }


        
    }

    @FXML
    void loadScores(MouseEvent event) {
        if (loadedSScene==false){
        if (this.getSelectedTournamnet().isEleminationType()){
            Tournamnet t=getSelectedTournamnet();
            Match[] listOfMatches=t.getStageMatches(t.getCurrentStage());
            for (int i=0;i<listOfMatches.length;i++){
                HBox match=new HBox();
                Label matchLabel=new Label(listOfMatches[i].toString());
                TextField score=new TextField();
                matches.add(listOfMatches[i]);
                scores.add(score);
                match.getChildren().add(matchLabel);
                match.getChildren().add(score);
                ListOfShownMatches.getItems().add(match);;
            }
            loadedSScene=true;

        }
        else{
            Tournamnet t=getSelectedTournamnet();
            t.RoundRobinRoundsGenerator();
            Match[] listOfMatches=t.getStageMatches(t.getCurrentStage());
            for (int i=0;i<listOfMatches.length;i++){
                HBox match=new HBox();
                Label matchLabel=new Label(listOfMatches[i].toString());
                TextField score=new TextField();
                matches.add(listOfMatches[i]);
                scores.add(score);
                match.getChildren().add(matchLabel);
                match.getChildren().add(score);
                ListOfShownMatches.getItems().add(match);;
            }
            loadedSScene=true;



        }


    }
    }

    @FXML
    void loadTournamnets(MouseEvent event) {
        if (loadedT==false){
        DataBase d=new DataBase();
        HBox row=new HBox();
        try{
        for (int i=0;i<d.getTournamnets().size();i++){
            Tournamnet tournamnet=d.getTournamnets().get(i);
            row=new HBox();
            row.setSpacing(50);
            VBox innerColumn=new VBox();
            Label tournamnetName=new Label(tournamnet.getName().toUpperCase());
            tournamnetName.setScaleX(1.2);
            innerColumn.getChildren().add(tournamnetName);
            String tType;
            if (tournamnet.isEleminationType()){
                tType="Elemination";
            }
            else {
                tType="Round Robin";
            }
            String participating;
            if (tournamnet.isteamBased()){
                participating="teamBased";

            }
            else {
                participating= "Indivisual";
            }
            HBox innerRow= new HBox();
            String status;
            if (tournamnet.getPeriodStatus()){
                status="open";
            }
            else {
                status="closed";
            }
            Label innerLabel=new Label("Sport : "+tournamnet.getSport()+","+" Participating : "+ participating+","+" Type : "+tType+","+"  status : "+status);
            innerLabel.setScaleX(1);

            innerRow.getChildren().add(innerLabel);
          
            innerColumn.getChildren().add(innerRow);
            Label startDate=new Label("Registration starts from : "+tournamnet.getStartingDate().toString());
            Label endingDate=new Label("Registration ends on : "+tournamnet.getEndingDate().toString());
            HBox miRow =new HBox(2.5);
            miRow.getChildren().add(startDate);
            miRow.getChildren().add(endingDate);
            innerColumn.getChildren().add(miRow);

            row.getChildren().add(innerColumn);
            row.setAlignment(Pos.CENTER);
            row.setOnMouseClicked(e->{
                
                try {

                    ObjectOutputStream w=new ObjectOutputStream(new FileOutputStream(new File("tourney.io")));
                    w.writeObject(tournamnet);
                    showSelectedTournament(event);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
    
                
            });
            listOfShownTournamnet.getItems().add(row);

    
        }
        }catch(NullPointerException e){
            d.resetTournamnets();

        }
       }loadedT=true;

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
    @FXML
    void loadTName(MouseEvent event) {
        tournamnetVisibleName.setText(getSelectedTournamnet().getName());
        tournamnetVisibleName.setMinSize(30,35);

    }
    @FXML
    void loadListOfRegistration(ActionEvent event) {
        
        if (sizeOfTeam_textField.getText().isEmpty()){
            ErrorScene("Error : sizeOfTeam is not entered");
            return;
        }
        try{
            int size=Integer.valueOf(sizeOfTeam_textField.getText());
            labelLoad.setText("Enter usernames of the students");
            for (int i=0;i<size;i++){
                TextField in=new TextField();
                RegistrationList.getItems().add(in);
                        }

        }catch(NumberFormatException e){
            ErrorScene("Error : not a valid input");
        }

    }
    @FXML
    void RegisterSingleStudent(ActionEvent event) throws Exception {
       {
  
            try{
                String _url="https://us-central1-swe206-221.cloudfunctions.net/app/User?username="+ singleStudentTextField.getText();
                URL url=new URL(_url);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                
                connection.setReadTimeout(5000);
                int status =connection.getResponseCode();
                if (status!=200){
                    ErrorScene("Error : Username not found");
                    return;
                }

                Student _user;

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                
                while ((inputLine = in.readLine()) != null) {
    
                    response.append(inputLine);
                }
                in.close();
                String info =response.toString();
                info =info.substring(1, info.length()-1);
                String[] listOfInfo=info.split(":");
                String name;
                String email;
                if (listOfInfo.length==2){
                    ErrorScene("Error : admins can not participate");
                    return;
                }
                else {
                 name=listOfInfo[1].substring(1, listOfInfo[1].lastIndexOf(",")-1);
                 email=listOfInfo[3].substring(1,listOfInfo[3].length()-1);
                }

                _user=new Student(name, email, singleStudentTextField.getText());
                _user.setFormatedSoloParticipant("player "+(getSelectedTournamnet().getNumOfRegistredParticipants()+1));
                _user.setShort(getSelectedTournamnet().getNumOfRegistredParticipants()+1);
                Tournamnet _t=getSelectedTournamnet();
                _t.addParticipant(_user);
                updateTournamentInfo(_t);
                ErrorScene("the student is registered");
                
                }
                catch(IOException e){
                    System.out.println(e);
                }
        }

    }


    @FXML
    void registrationConfirmTriggered(MouseEvent event) throws Exception {
        List<TextField> listOfUserNames=RegistrationList.getItems();
        ArrayList<Student> registredStudents=new ArrayList<>();
        for (int i=0;i<listOfUserNames.size();i++){
            try{
                String _url="https://us-central1-swe206-221.cloudfunctions.net/app/User?username="+ listOfUserNames.get(i).getText();
                URL url=new URL(_url);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                
                connection.setReadTimeout(5000);
                int status =connection.getResponseCode();
                if (status!=200){
                    ErrorScene("Error : Username of student number "+(i+1)+" not found");
                    return;
                }

                User _user;

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                
                while ((inputLine = in.readLine()) != null) {
    
                    response.append(inputLine);
                }
                in.close();
                String info =response.toString();
                info =info.substring(1, info.length()-1);
                String[] listOfInfo=info.split(":");
                String name;
                String email;
                if (listOfInfo.length==2){
                    ErrorScene("Error : admins can not participate");
                    return;
                }
                else {
                 name=listOfInfo[1].substring(1, listOfInfo[1].lastIndexOf(",")-1);
                 email=listOfInfo[3].substring(1,listOfInfo[3].length()-1);
                }

                _user=new Student(name, email, listOfUserNames.get(i).getText());
                registredStudents.add((Student)_user);

              
                }
                catch(IOException e){
                    System.out.println(e);
                }
        }
        Team team=new Team("TEAM "+(getSelectedTournamnet().getNumOfRegistredParticipants()+1), registredStudents);
        team.setShort(getSelectedTournamnet().getNumOfRegistredParticipants()+1);
        Tournamnet _t=getSelectedTournamnet();
        _t.addParticipant(team);
        updateTournamentInfo(_t);
        ErrorScene("the team is registered");
        
        
    }
    void updateTournamentInfo(Tournamnet tournamnet){
        try {

            ObjectOutputStream w=new ObjectOutputStream(new FileOutputStream(new File("tourney.io")));
            w.writeObject(tournamnet);
            w.close();
            DataBase d=new DataBase();
            d.updateTournament(tournamnet);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
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
    public void showRegister(ActionEvent event) throws Exception {
        
        if (getSelectedTournamnet().isFinished()){
            ErrorScene("Error : the tournamnet is finished");
            return;
        }
        if (getSelectedTournamnet().getPeriodStatus()==false){
            ErrorScene("Error the registration is closed");
            return;
        }
        if (getSelectedTournamnet().isteamBased()){
        

        FXMLLoader loader = new FXMLLoader(getClass().getResource("registationScene_forTeams.fxml"));
        root=loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
        if (getUser() instanceof Admin && !getSelectedTournamnet().isteamBased()){
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registrationScene_forSolo.fxml"));
        root=loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


        }
        if (getUser() instanceof Student && !getSelectedTournamnet().isteamBased() &&!getSelectedTournamnet().isFinished()){
                Student _user=(Student) getUser();
                _user.setFormatedSoloParticipant("Participant "+(getSelectedTournamnet().getNumOfRegistredParticipants()+1));
                _user.setShort(getSelectedTournamnet().getNumOfRegistredParticipants()+1);
                Tournamnet _t=getSelectedTournamnet();
                _t.addParticipant(getUser());
                updateTournamentInfo(_t);
                ErrorScene("the student is registereed");
                
            
        }
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
    public void showSelectedTournament(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectedTournament.fxml"));
        root=loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    void selectSport(String selectedSport){
        this.selectedSport=selectedSport;
    }
    @FXML
    void loadSports(MouseEvent event) {
        DataBase d=new DataBase();
        int i=0;

        if (loaded==false){
            for ( i=0;i<d.getSports().size();i++){
                Label sportLabel=new Label(d.getSports().get(i));
                sportLabel.setMinWidth(Double.POSITIVE_INFINITY);
                sportLabel.setOnMousePressed(e->{
                    selectSport(sportLabel.getText())  ;
                });
            
            listOfShownSports.getItems().add(sportLabel);
        }
        this.loaded=true;
}

    }

    public void showAddEditScores(ActionEvent event) throws IOException {



        if (getSelectedTournamnet().isFinishedScoring()){
            ErrorScene("Error scoring period is closed");
            return;

        }
        else if (getUser() instanceof Student){
            ErrorScene("ERROR : user is not authorized");
            return;
        }
        

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
    
    public Tournamnet getSelectedTournamnet(){
        try{
            ObjectInputStream reading=new ObjectInputStream(new FileInputStream(new File("tourney.io")));
            return (Tournamnet)reading.readObject();
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
        Label sportLabel=new Label(newSportName.getText());
        listOfShownSports.getItems().add(sportLabel);
        sportLabel.setOnMouseClicked(e->{
            sportLabel.setMinWidth(Double.POSITIVE_INFINITY);

            selectSport(sportLabel.getText())     ;
        });


    }

    @FXML
    void selectDateTriggered(ActionEvent event) {
        scheduleDate();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (getSelectedTournamnet()!=null){
            ArrayList<Label> participants = new ArrayList<>();
            if (!getSelectedTournamnet().isteamBased()){
                for ( int i=0;i<getSelectedTournamnet().getParticipants().size();i++) {
                    Label participant = new Label(((Student) getSelectedTournamnet().getParticipants().get(i)).getName()+ " : "+(((Student) getSelectedTournamnet().getParticipants().get(i))).getFormatedSoloParticipant());
                    participant.setMinWidth(Double.POSITIVE_INFINITY);


                    participants.add(participant);
                }
            }
            else {
                for (int i=0;i<getSelectedTournamnet().getParticipants().size();i++){
                    Label participant = new Label(((Team) getSelectedTournamnet().getParticipants().get(i)).getName());
                    participant.setMinWidth(Double.POSITIVE_INFINITY);
                    participants.add(participant);
                    System.out.println(((Team) getSelectedTournamnet().getParticipants().get(i)).getMembersList());
                    for (int j=0 ;j<((Team) getSelectedTournamnet().getParticipants().get(i)).getNumberOfMembers();j++){
                        participant = new Label(((Team) getSelectedTournamnet().getParticipants().get(i)).getMembersList().get(j).getName());
                        participant.setMinWidth(Double.POSITIVE_INFINITY);
                        participants.add(participant);
                    }
                }
            }
            this.listOfParticipants.getItems().addAll(participants);
        }
    }
}

