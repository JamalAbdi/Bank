package coe528.project;


import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class guiImplament extends Application  {

    @Override
    public void start(Stage primaryStage) {
TextField textField = new TextField ();
    
    HBox hb = new HBox();
    
    Button btn = new Button();
    btn.setText("Please exit this box ");
    btn.setOnAction(new EventHandler<ActionEvent>() {
    Label label1 = new Label("Name:");
    TextField textField = new TextField ();
    HBox hb = new HBox();

    
            @Override
            public void handle(ActionEvent event) {
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Welcome to the bank!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    public void takeinput(){
               } 
    public static void main(String[] args) throws IOException  {
        launch(args);
        Users Jamal,acc1;
        Jamal = new Users("admin","admin","manager",100000,"Silver");
        
        Jamal.startup();
        Jamal.Login();
    }
}
    

