package Control;

import Object.Explain;
import Object.WordAdvanced;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class FixControl {
    @FXML
    TextField word;
    @FXML
    TextField kind;
    @FXML
    TextField pronounce;
    @FXML
    TextArea fix;
    public static WordAdvanced wordFix;

    @FXML
    AnchorPane scenePane;

    Stage stage;

    public void Start() {
        StringBuilder temp = new StringBuilder();
        word.setText(wordFix.getWord());
        pronounce.setText(wordFix.getPronounce());
        kind.setText(wordFix.explains.get(0).getKind());
        for (int i = 0; i < wordFix.explains.size(); i++) {
            if (wordFix.explains.get(i).getExplain() != "") {
                temp.append("*").append(" " + wordFix.explains.get(i).getExplain() + "\n");
            }
        }
        fix.setText(temp.toString());
        fix.setWrapText(true);
    }

    public void ConfirmFix() {
        wordFix.explains.removeAll(wordFix.explains);
        String text = fix.getText();
        text = text.replace("* ", "");
        String split[] = text.split("\n");
        for (int i = 0; i < split.length; i++) {
            wordFix.explains.add(new Explain("", split[i], ""));
        }
        stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }
}
