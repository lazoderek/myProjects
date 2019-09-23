package controller;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dereklazo
 */

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StartController {

    @FXML
    private TextField answerBox;
    
    @FXML
    private AnchorPane pane;
    
    @FXML
    public void initialize(){
        answerBox.setText("");
        pane.setOnKeyPressed(key -> {
            if(key.getCode().equals(KeyCode.ESCAPE)){
                Platform.exit();
            } else if(key.getCode().equals(KeyCode.ENTER)){
                try {
                    changeScene();
                } catch (Exception ex) {
                    System.out.println("Error in initialize method of start controller " + ex.getMessage());
                }
            }
        });
    }
    
    @FXML
    public void mouseEnter(MouseEvent event) throws Exception {
           changeScene();
    }
    
    public void changeScene() throws Exception{
        
        do{
            if(answerBox.getText().isEmpty()){
                TextInputDialog dialog = new TextInputDialog();
                dialog.setContentText("Renter your answer: ");
                Optional<String> result = dialog.showAndWait();
                
                result.ifPresent(text -> answerBox.setText(text));
                
            }
        } while(answerBox.getText().isEmpty());
        
        HangmanController.setText(answerBox.getText());
        Parent root = FXMLLoader.load(getClass().getResource("/view/hangman.fxml"));
        Scene scene = new Scene(root);
        
        
        Stage stage = ((Stage)pane.getScene().getWindow());
        stage.setX(650);
        stage.setTitle("Game");
        stage.setScene(scene);
        stage.show();
    }
    
    public TextField getAnswer(){
        return answerBox;
    }
    
    @FXML
    public void close(MouseEvent event) {
       Platform.exit();
    }
}
