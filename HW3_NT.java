/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3_nt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;


/**
 *
 * @author Nathen
 */
public class HW3_NT extends Application {
    
    BorderPane layout;
    Stage stage;
    Scene scene;
    MenuBar menu;
    Menu app;
    MenuItem itmReset, itmExit;
    Label lbl;
    TextArea textBox;
    String str;
    int x, y, n;
    
    @Override
    public void start(Stage stage) throws Exception {

        //initializes click counter
        n=1;
        
        //Creates Menu Bar
        menu = new MenuBar();
        
        //Creates Menu
        app = new Menu("Main");
        
        //Creates Menu Items
        itmReset = new MenuItem("Reset, Ctrl+X");
        itmExit = new MenuItem("Exit, Esc");
        
        //Adds Items to menu and adds menu to menu bar
        app.getItems().addAll(itmReset, itmExit);
        menu.getMenus().add(app);
        
        layout = new BorderPane();
       
        //Creates readonly textbox
        textBox = new TextArea("");
        textBox.setPrefWidth(150);
        textBox.setEditable(false);
        
        //Creates resizable image
        ImageView image = new ImageView();
        image.fitWidthProperty().bind(stage.widthProperty().subtract(150));
        image.fitHeightProperty().bind(stage.heightProperty().subtract(100));
        Image img = new Image(getClass().getResourceAsStream("/hw3_nt/hat.jpg"));
        image.setImage(img);
        
        //Organize all controls into layout
        layout.setTop(menu);     
        layout.setLeft(textBox);
        layout.setCenter(image);
        lbl = new Label("Coords X: Y:");
        layout.setBottom(lbl);
        
        //Sets Window
        stage.setTitle("Mouse traker");
        scene = new Scene(layout, 550, 450);
        stage.setScene(scene);
        stage.show();
        scene.getRoot().requestFocus();
      
        //Menu item resets pannel when clicked   
        itmReset.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                textBox.setText("");
                n = 1;
            }
        });
        
        //Menu item closes window when clicked
        itmExit.setOnAction(new EventHandler(){
            public void handle(Event event){
                Platform.exit();
            }
        });        
        
        //Coordinates added to textbox when mouse is clicked
        image.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                str = textBox.getText();
                str = str + n +": X:" + x + " Y:" + y + "\n";
                textBox.setText(str);
                n++;
            }
        });
        
        //Mouse gets location on image
        image.setOnMouseMoved(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                    scene.setCursor(Cursor.CROSSHAIR);
                    x = (int) me.getX();
                    y = (int) me.getY();
                    lbl.setText("Coords X:" + x + " Y:" + y);
            }
        });
        
        //Mouse returns to default when leaving image
        image.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                scene.setCursor(Cursor.DEFAULT);
                lbl.setText("Coords X: Y:");
            }
        });
        
        //Reads key commands
        //Closes window if escape is pressed
        //Resets textbox when key is matched with input
        KeyCodeCombination key = new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            public void handle(KeyEvent e){
                if(e.getCode() == KeyCode.ESCAPE){
                    Platform.exit();
                }else if(key.match(e)){
                    textBox.setText("");
                    n = 1;
                }else{
                    
                }
            }
        });   
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}