/*
 * Container that consists lists and methods to calculate values of library items.
 */
package calorie.fx;

import calorie.CommandAction;
import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class DietContainerFX {
    private String tableName;
    private ObservableList<String> nameList = FXCollections.observableArrayList();
    private ArrayList<Float> proteinAbsoluteList = new ArrayList<>();
    private ArrayList<Float> fatAbsoluteList = new ArrayList<>();
    private ArrayList<Float> carbohydratesAbsoluteList = new ArrayList<>();
    private ArrayList<Float> proteinRelativeList = new ArrayList<>();
    private ArrayList<Float> fatRelativeList = new ArrayList<>();
    private ArrayList<Float> carbohydratesRelativeList = new ArrayList<>();
    private ArrayList<Float> calorieAbsoluteList = new ArrayList<>();
    private ArrayList<Float> calorieRelativeList = new ArrayList<>();
    private ArrayList<Float> weightList = new ArrayList<>();

    private float proteinAbsoluteTotal = 0;
    private float fatAbsoluteTotal = 0;
    private float carbohydratesAbsoluteTotal = 0;
    private float proteinRelativeTotal = 0;
    private float fatRelativeTotal = 0;
    private float carbohydratesRelativeTotal = 0;
    private float calorieTotal = 0;
    private float weightTotal = 0;

    /**
     * Creates a group of tables. Actually creates a DietContainer that consists total elements of each given table.
     * @param containers - tables needed to by united into a group.
     */
    public DietContainerFX(DietContainerFX... containers) {
        for (DietContainerFX container : containers) {
            this.nameList.addAll(container.nameList);
            this.proteinAbsoluteList.addAll(container.proteinAbsoluteList);
            this.fatAbsoluteList.addAll(container.fatAbsoluteList);
            this.carbohydratesAbsoluteList.addAll(container.carbohydratesAbsoluteList);
            this.proteinRelativeList.addAll(container.proteinRelativeList);
            this.fatRelativeList.addAll(container.fatRelativeList);
            this.carbohydratesRelativeList.addAll(container.carbohydratesRelativeList);
            this.calorieAbsoluteList.addAll(container.calorieAbsoluteList);
            this.calorieRelativeList.addAll(container.calorieRelativeList);
            this.weightList.addAll(container.weightList);

            this.proteinAbsoluteTotal += container.proteinAbsoluteTotal;
            this.fatAbsoluteTotal += container.fatAbsoluteTotal;
            this.carbohydratesAbsoluteTotal += container.carbohydratesAbsoluteTotal;
            this.calorieTotal += container.calorieTotal;
            this.weightTotal += container.weightTotal;

            this.calculateTotal();
        }
    }

    public final void add(String itemOfNameStr, float weight, float proteinStr, float fatStr, float carbohydratesStr) {
        nameList.add(itemOfNameStr);
        proteinAbsoluteList.add(proteinStr);
        fatAbsoluteList.add(fatStr);
        carbohydratesAbsoluteList.add(carbohydratesStr);
        weightList.add(weight);

        calorieAbsoluteList.add((proteinStr * 4) +
                (fatStr * 9) +
                (carbohydratesStr * 4));

        proteinRelativeList.add(((proteinStr * 4) * 100) /
                ((fatStr * 9) +
                        (carbohydratesStr * 4) +
                        (proteinStr * 4)));

        fatRelativeList.add(((fatStr * 9) * 100) /
                ((proteinStr * 4) +
                        (carbohydratesStr * 4) +
                        (fatStr * 9)));

        carbohydratesRelativeList.add(((carbohydratesStr * 4) * 100) /
                ((fatStr * 9) +
                        (proteinStr * 4) +
                        (carbohydratesStr * 4)));

        calorieRelativeList.add(-1f);

        calculateTotal();
    }

    private void calculateAbsoluteTotalProtein() {
        Float result = 0f;

        for (Float f : proteinAbsoluteList) {
            result += f;
        }

        proteinAbsoluteTotal = result;
    }

    private void calculateRelativeTotalProtein() {
        proteinRelativeTotal = ((proteinAbsoluteTotal * 4) * 100) / ((proteinAbsoluteTotal * 4) +
                (fatAbsoluteTotal * 9) +
                (carbohydratesAbsoluteTotal * 4));
    }

    private void calculateAbsoluteTotalFat() {
        Float result = 0f;

        for (Float f : fatAbsoluteList) {
            result += f;
        }

        fatAbsoluteTotal = result;
    }

    private void calculateRelativeTotalFat() {
        fatRelativeTotal = ((fatAbsoluteTotal * 9) * 100) / ((proteinAbsoluteTotal * 4) +
                (fatAbsoluteTotal * 9) +
                (carbohydratesAbsoluteTotal * 4));
    }

    private void calculateAbsoluteTotalCarbohydrates() {
        Float result = 0f;

        for (Float f : carbohydratesAbsoluteList) {
            result += f;
        }

        carbohydratesAbsoluteTotal = result;
    }

    private void calculateRelativeTotalCarbohydrates() {
        carbohydratesRelativeTotal = ((carbohydratesAbsoluteTotal * 4) * 100) / ((proteinAbsoluteTotal * 4) +
                (fatAbsoluteTotal * 9) +
                (carbohydratesAbsoluteTotal * 4));
    }

    private void calculateAbsoluteTotalCalorie() {
        Float result = 0f;

        for (String s : nameList) {
            result += ((proteinAbsoluteList.get(nameList.indexOf(s)) * 4) +
                    (fatAbsoluteList.get(nameList.indexOf(s)) * 9) +
                    (carbohydratesAbsoluteList.get(nameList.indexOf(s)) * 4));
        }

        calorieTotal = result;
    }

    private void calculateRelativeTotalCalorie() {
        for (String item : nameList) {
            calorieRelativeList.set(nameList.indexOf(item), calculateRelativeCalorieByName(item));
        }
    }

    private void calculateTotalWeight() {
        Float result = 0f;

        for (Float f : weightList) {
            result += f;
        }

        weightTotal = result;
    }

    private float calculateRelativeCalorieByName(String s) {
        float a = 0;
        float b = 0;

        for (String item : nameList) {
            if (!item.equals(s)) {
                b += calorieAbsoluteList.get(nameList.indexOf(item));
            }

            a = calorieAbsoluteList.get(nameList.indexOf(item));
        }
        b += a;

        return a * 100 / b;
    }

    private void calculateTotal() {
        calculateAbsoluteTotalProtein();
        calculateAbsoluteTotalFat();
        calculateAbsoluteTotalCarbohydrates();
        calculateRelativeTotalProtein();
        calculateRelativeTotalFat();
        calculateRelativeTotalCarbohydrates();
        calculateAbsoluteTotalCalorie();
        calculateRelativeTotalCalorie();
        calculateTotalWeight();
    }

    public final void deleteItem(String item) {
        proteinAbsoluteList.remove(nameList.indexOf(item));
        fatAbsoluteList.remove(nameList.indexOf(item));
        carbohydratesAbsoluteList.remove(nameList.indexOf(item));
        proteinRelativeList.remove(nameList.indexOf(item));
        fatRelativeList.remove(nameList.indexOf(item));
        carbohydratesRelativeList.remove(nameList.indexOf(item));
        calorieAbsoluteList.remove(nameList.indexOf(item));
        calorieRelativeList.remove(nameList.indexOf(item));
        weightList.remove(nameList.indexOf(item));
        nameList.remove(nameList.indexOf(item));
        calculateTotal();
    }


    public final ArrayList<String> getNameList() { return new ArrayList<>(nameList); }

    public final String getTableName() { return tableName; }

    public void setTableName(String tableName) { this.tableName = tableName; }

    public final String getNameListItem(String item) { return nameList.get(nameList.indexOf(item)); }

    public final String getNameListItem(int item) { return nameList.get(item); }

    public final float getProteinAbsoluteListItem(String item) { return proteinAbsoluteList.get(nameList.indexOf(item)); }

    public final float getFatAbsoluteListItem(String item) { return fatAbsoluteList.get(nameList.indexOf(item)); }

    public final float getCarbohydratesAbsoluteListItem(String item) { return carbohydratesAbsoluteList.get(nameList.indexOf(item)); }

    public final float getProteinRelativeListItem(String item) { return proteinRelativeList.get(nameList.indexOf(item)); }

    public final float getFatRelativeListItem(String item) { return fatRelativeList.get(nameList.indexOf(item)); }

    public final float getCarbohydratesRelativeListItem(String item) { return carbohydratesRelativeList.get(nameList.indexOf(item)); }

    public final float getCalorieAbsoluteListItem(String item) { return calorieAbsoluteList.get(nameList.indexOf(item)); }

    public final float getCalorieRelativeListItem(String item) { return calorieRelativeList.get(nameList.indexOf(item)); }

    public final float getWeightListItem(String item) { return weightList.get(nameList.indexOf(item)); }

    public final float getProteinAbsoluteTotal() { return proteinAbsoluteTotal; }

    public final float getFatAbsoluteTotal() { return fatAbsoluteTotal; }

    public final float getCarbohydratesAbsoluteTotal() { return carbohydratesAbsoluteTotal; }

    public final float getProteinRelativeTotal() { return proteinRelativeTotal; }

    public final float getFatRelativeTotal() { return fatRelativeTotal; }

    public final float getCarbohydratesRelativeTotal() { return carbohydratesRelativeTotal; }

    public final float getCalorieTotal() { return calorieTotal; }

    public final float getWeightTotal() { return weightTotal; }

    public final int getNameListLength() { return nameList.size(); }
}
