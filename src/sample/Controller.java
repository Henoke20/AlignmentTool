package sample;

import Alignment.GlobalAlign;
import Alignment.LocalAlign;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Controller {

    @FXML private TextField SequenceA, SequenceB, Gap, Match, Mismatch;
    @FXML private ToggleGroup myToggleGroup;
    @FXML private RadioButton Local,Global;

    public void handleSaySup(ActionEvent actionEvent) {



        try {
            // calls local aligner or Global aligner depending on which Raido Button Selected
            if (myToggleGroup.getSelectedToggle().equals(Local)) {

                LocalAlign lAligner = LocalAlign.GetLocalAlignInstance();
                lAligner.FillTable(Integer.parseInt(Gap.getText()), Integer.parseInt(Match.getText())
                        , Integer.parseInt(Mismatch.getText()), SequenceA.getText(), SequenceB.getText());
            } else if (myToggleGroup.getSelectedToggle().equals(Global)) {

                GlobalAlign gAligner = GlobalAlign.GetGlobalAlignInstance();
                gAligner.FillTable(Integer.parseInt(Gap.getText()), Integer.parseInt(Match.getText())
                        , Integer.parseInt(Mismatch.getText()), SequenceA.getText(), SequenceB.getText());
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Warning");
            alert.setContentText("Double check your parameters !!!");

            alert.showAndWait();
        }


    }
}
