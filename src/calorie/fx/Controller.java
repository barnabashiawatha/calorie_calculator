package calorie.fx;

import calorie.CommandAction;
import calorie.dietcontainer.DietContainer;
import javafx.beans.Observable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.Arrays;

public class Controller {
    @FXML
    ListView listView;
    @FXML
    TextField nameItem;
    @FXML
    TextField protein;
    @FXML
    TextField fat;
    @FXML
    TextField carb;
    @FXML
    Button addBtn;

    private DietContainer lib = new DietContainer();
    ObservableList<String> items = FXCollections.observableArrayList(ActionFX.getNameList(lib));

    @FXML
    public void initialize() {
        listView.setItems(items);
        ActionFX.initLib(lib);
    }

    @FXML
    public void add() {
    CommandAction.updateLib(lib, nameItem.getText(),
            Integer.parseInt(protein.getText()),
            Integer.parseInt(fat.getText()),
            Integer.parseInt(carb.getText()));
    }
}
