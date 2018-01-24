package calorie.fx;

import calorie.CommandAction;
import calorie.db.DBContainer;
import calorie.dietcontainer.DietContainer;

import java.util.ArrayList;

class ActionFX {
     static ArrayList<String> getNameList(DietContainer lib) {
        DBContainer dbc = new DBContainer();
        dbc.open();
        dbc.showLib(lib);
        dbc.close();

        return lib.getNameList();
    }

    static void initLib(DietContainer lib) {
        CommandAction.fillLibContainer(lib);
    }
}
