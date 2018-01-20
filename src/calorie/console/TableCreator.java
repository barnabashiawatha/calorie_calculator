
package calorie.console;

import calorie.dietcontainer.DietContainer;

/**
 * Consists methods to create tables for calorie.console print.
 */
public class TableCreator {
    /**
     * Creates and prints upper part of a table.
     * @param container - DietContainer object that consists values to create table.
     */
    public static void createTable(DietContainer container) {
        String table = container.getTableName();
        String buf;

        StringBuilder tableSb = new StringBuilder("");

        for (int i = 0; i < 20 - table.length(); i++ ) {
            tableSb.append(" ");
        }

        table = table + tableSb.toString();

        String mainRow = table + "| " +
                "Protein(100g)      | " +
                "Fat(100g)          | " +
                "Carbohydrates(100g)| " +
                "Total calorie(100g)    | ";


        System.out.println(mainRow);
        System.out.println("---------------------------------------------" +
                "----------------------------------------------------------------");

        for (int i = 0; i < container.getNameListLength(); i++) {
            String item = container.getNameListItem(i);
            StringBuilder sb = new StringBuilder("");

            sb.append(container.getNameListItem(item));

            for (int j = 0; j < 10 - item.length(); j++ ) {
                sb.append(" ");
            }

            buf = container.getWeightListItem(item) + "(g)";
            sb.append(buf);

            for (int j = 0; j < 10 - buf.length(); j++ ) {
                sb.append(" ");
            }

            sb.append("| ");

            buf = container.getProteinAbsoluteListItem(item) + "(g) ("
                    + String.format("%.5s", container.getProteinRelativeListItem(item)) + "%)";
            sb.append(buf);

            for (int j = 0; j < 19 - buf.length(); j++ ) {
                sb.append(" ");
            }
            sb.append("| ");

            buf = container.getFatAbsoluteListItem(item) + "(g) ("
                    + String.format("%.5s", container.getFatRelativeListItem(item)) + "%)";
            sb.append(buf);

            for (int j = 0; j < 19 - buf.length(); j++ ) {
                sb.append(" ");
            }
            sb.append("| ");

            buf = container.getCarbohydratesAbsoluteListItem(item) + "(g) ("
                    + String.format("%.5s", container.getCarbohydratesRelativeListItem(item)) + "%)";
            sb.append(buf);

            for (int j = 0; j < 19 - buf.length(); j++ ) {
                sb.append(" ");
            }
            sb.append("| ");

            buf = container.getCalorieAbsoluteListItem(item) + "(ccal)";
            sb.append(buf);

            for (int j = 0; j < 23 - buf.length(); j++ ) {
                sb.append(" ");
            }
            sb.append("| ");

            System.out.println(sb);
        }

        System.out.print("\n");
        createTotalTable(container);
    }

    /**
     * Creates and prints lower part of a table.
     * @param container - DietContainer object that consists values to create table.
     */
    private static void createTotalTable(DietContainer container) {
        String protein = "Protein total: " +
                container.getProteinAbsoluteTotal() + "(g) (" +
                String.format("%.5s", container.getProteinRelativeTotal()) + "%) " +
                String.format("%.5s", (container.getProteinAbsoluteTotal() * 4)) + "(ccal)";
        System.out.println(protein);

        String fat = "Fat total:     " +
                container.getFatAbsoluteTotal() + "(g) (" +
                String.format("%.5s", container.getFatRelativeTotal()) + "%) " +
                String.format("%.5s", (container.getFatAbsoluteTotal() * 9)) + "(ccal)";
        System.out.println(fat);

        String carbohydrates = "Carb total:    " +
                container.getCarbohydratesAbsoluteTotal() + "(g) (" +
                String.format("%.5s", container.getCarbohydratesRelativeTotal()) + "%) " +
                String.format("%.5s", (container.getCarbohydratesAbsoluteTotal() * 4)) + "(ccal)";
        System.out.println(carbohydrates);

        String calorieTotal = "Calorie total: " +
                String.format("%.5s", container.getCalorieTotal()) + "(ccal)";
        System.out.println(calorieTotal);
    }

    /**
     * Creates and prints library.
     * @param container - DietContainer object that consists values to create library.
     */
    public static void createLib(DietContainer container) {
        String buf;
        String mainRow = "Library  | " +
                "Protein(100g) | " +
                "Fat(100g)     | " +
                "Carb(100g)    | ";

        System.out.println(mainRow);
        System.out.println("----------------------------------------------------------");

        for (int i = 0; i < container.getNameListLength(); i++) {
            String item = container.getNameListItem(i);
            StringBuilder sb = new StringBuilder("");

            sb.append(container.getNameListItem(item));

            for (int j = 0; j < 9 - container.getNameListItem(item).length(); j++ ) {
                sb.append(" ");
            }
            sb.append("| ");

            buf = container.getProteinAbsoluteListItem(item) + "(g)";
            sb.append(buf);

            for (int j = 0; j < 14 - buf.length(); j++ ) {
                sb.append(" ");
            }
            sb.append("| ");

            buf = container.getFatAbsoluteListItem(item) + "(g)";
            sb.append(buf);

            for (int j = 0; j < 14 - buf.length(); j++ ) {
                sb.append(" ");
            }
            sb.append("| ");

            buf = container.getCarbohydratesAbsoluteListItem(item) + "(g)";
            sb.append(buf);

            for (int j = 0; j < 14 - buf.length(); j++ ) {
                sb.append(" ");
            }
            sb.append("| ");

            System.out.println(sb);
        }
    }
}
