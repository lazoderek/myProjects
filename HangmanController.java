package controller;



import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class HangmanController {

    private static String answer; //Answer of hangman screen
    private int imgIndex = 0; //Keeps track of current hangman image
    private boolean charFound; //Checks if letter is found
    private char[] slots; //The users input
    private Button[] buttons; //The alphabet of buttons
    private ImageView[] images; //The batman images
    
    @FXML
    private Label ansBox; //The label being used with slots
    
    @FXML
    private Label categoryDisplay; //Displays the category of choice
    
    @FXML
    private ImageView head1, torso2, leftArm3, rightArm5, leftLeg6, body7, cape8; 
    
    @FXML
    private Button a, b, c, d, e, f ,g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z;
    
    @FXML
    private AnchorPane pane;
    
    @FXML //Method runs at startup [Note: Similar to constructor, but no object needed]
    public void initialize(){        
        //Storing images, slots, and buttons in arrays
        
        slots = new char[answer.length()];
        buttons = new Button[]{a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z};
        images = new ImageView[]{head1, torso2, leftArm3, rightArm5, leftLeg6, body7, cape8};
        
        //Adds spacing to slot
        for(int i =0; i < answer.length(); i++){      
            slots[i] = '_';
            
            if(answer.charAt(i) == ' '){
                 slots[i] = ' ';
            }
        }
        
        ansBox.setText(String.valueOf(slots));
        

    }
    
    
    @FXML //Shows image if wrong selection is picked
    public void showImage(MouseEvent event) {

        checkAnswer((Button)event.getSource());
        
    } //end showImage   
    
    @FXML //Changes the cursor when over button
    public void onHover(MouseEvent event){
       ((Button)event.getSource()).getScene().setCursor(Cursor.HAND);
    }
    
    @FXML //CHanges cursor to default
    public void offHover(MouseEvent event){
        ((Button)event.getSource()).getScene().setCursor(Cursor.DEFAULT);
    }
    
    @FXML //Se6nds the key to check answer
    public void setKey(KeyEvent event){
        
        for (Button button : buttons) {
            if(event.getCode().toString().equals(button.getText().toUpperCase())){
                button.setVisible(false);
                checkAnswer(button);
            }
        }
        
    }
    
    // Checks if button matches answer
    public void checkAnswer(Button button){
        
        
        charFound=false;
        
        //Cycles through hangman images
        if(imgIndex < images.length){
          for(int i=0; i<answer.length(); i++){
              //Checks if button pressed or typed matches any given button
              
              
              if(Character.toLowerCase(answer.charAt(i)) == button.getId().charAt(0)){
                  
                  
                  //Puts letters of answer and response in camel case
                  if(i == 0 || answer.charAt(i-1) == ' '){
                      char[] upperCase = answer.toCharArray();
                      upperCase[i] = Character.toUpperCase(upperCase[i]);
                      answer = String.valueOf(upperCase);
                  }
                  
                 
          
                  
                  slots[i] = answer.charAt(i);
                  charFound=true;
              }
              
          } //end for
          
          //Checks if letter is part of answer
          if(!charFound){
              images[imgIndex].setVisible(!charFound);
              
              if(imgIndex == images.length-1){
                  endGame();
              }
              
              imgIndex++;
          } else{
              ansBox.setText(String.valueOf(slots));
          }
          
          //Checks if slots match answer
          if(answer.equals(String.copyValueOf(slots))){
            
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Winner");
            alert.setHeaderText("You Win !");
            alert.setContentText("You got lucky this time ... ");
            alert.getButtonTypes().add(ButtonType.OK);
            alert.showAndWait();
            Platform.exit();
          }
          
        }
    } //end checkAnswer
    
    //Sets the value of the answer
    public static void setText(String answer){
        HangmanController.answer = answer;
    }
    
    //Ends the game with a lose
    public void endGame(){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Loser");
        alert.setHeaderText("Game Over");
        alert.setContentText("You have run out of tries");
        
        
        alert.getButtonTypes().add(ButtonType.OK);
        alert.showAndWait();
        Platform.exit();
    } //end endGame
    
    @FXML
    public void end(KeyEvent event){
        if(event.getCode().equals(KeyCode.ESCAPE)){
            Platform.exit();
        }
    }
}


