package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        /*Group root = new Group();
        VBox vbox = new VBox();
        GridPane grid = new GridPane();

        Text txt = new Text("Sup");
        Button button = new Button("Say Sup");
        TextField tf = new TextField();
        Label label = new Label("Sup");

        txt.setFont(new Font("Papyrus",80));
        tf.setText("Say Sup");
        txt.setY(50);
        button.setOnAction(evt -> System.out.println("Sup " + tf.getText()));


        grid.add(label,0,0);
        grid.add(tf,1,0);
        grid.add(button,1,1);
        grid.setHgap(20);

        vbox.getChildren().addAll(txt, grid);
        root.getChildren().add(vbox);
*/

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
