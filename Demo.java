/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author dereklazo
 */
public class Demo extends Application {
    
    private AnchorPane root;
    
    
    @Override
    public void start(Stage stage) throws IOException {
        root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/start.fxml"));
        Scene scene = new Scene(root);
        
        scene.getStylesheets().add("/view/colors.css");
       
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Main");
        stage.show();
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
