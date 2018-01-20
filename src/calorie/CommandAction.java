/*
 * API to work with DBContainer object.
 */

package calorie;

import calorie.console.TableCreator;
import calorie.db.DBContainer;
import calorie.dietcontainer.DietContainer;

import java.util.ArrayList;

/**
 * Consists methods to manipulate with sqlite database.
 */
public class CommandAction {

    /**
     * Creates a library.
     */
    static void createLib() {
        DBContainer dbc = new DBContainer();
        dbc.open();
        dbc.createLib();
        dbc.close();
    }

    /**
     * Adds an item to a library (a table in sqlite database).
     * @param item - name of a item.
     * @param protein - protein content of an item.
     * @param fat - fat content of an item.
     * @param carbohydrates - carbohydrates content of an item.
     */
    public static void addToLib(String item, float protein, float fat, float carbohydrates) {
        DBContainer dbc = new DBContainer();
        dbc.open();
        dbc.addToLib(item, protein, fat, carbohydrates);
        dbc.close();
    }

    /**
     * Prints a library.
     */
    public static void showLib() {
        DietContainer lib = new DietContainer();
        DBContainer dbc = new DBContainer();
        dbc.open();
        dbc.showLib(lib);
        dbc.close();
        TableCreator.createLib(lib);
    }

    /**
     * Deletes an item from a library.
     * @param item - an item of a library to delete.
     */
    public static void deleteLibItem(String item) {
        DBContainer dbc = new DBContainer();
        dbc.open();
        dbc.deleteLibItem(item);
        dbc.close();
    }

    /**
     * Creates a table in an sqlite database.
     * @param table - name of a table.
     */
    public static void createTable(String table) {
        DBContainer dbc = new DBContainer();
        dbc.open();
        dbc.createTable(table);
        dbc.close();
    }

    /**
     * Adds an item to an sqlite database's table.
     * @param item - name of an item.
     * @param table - name of a table.
     */
    public static void addToTable(String item, float weight, String table) {
        DBContainer dbc = new DBContainer();
        dbc.open();
        dbc.addToTable(item, weight, table);
        dbc.close();
    }

    /**
     * Print a calorie.console version of a given table.
     * @param table - table to print.
     */
    public static void showTable(String table) {
        DietContainer container = new DietContainer();
        DBContainer dbc = new DBContainer();
        dbc.open();
        dbc.showTable(container, table);
        dbc.close();
        TableCreator.createTable(container);
    }

    /**
     * Shows a list of all tables.
     */
    public static void showTables() {
        ArrayList<String> tables = new ArrayList<>();
        DBContainer dbc = new DBContainer();
        dbc.open();
        dbc.showTables(tables);
        dbc.close();
        System.out.println(tables);
    }

    /**
     * Deletes an item from a given table.
     * @param item - an item to delete.
     * @param table - table with needed item.
     */
    public static void removeFromTable(String item, String table) {
        DBContainer dbc = new DBContainer();
        dbc.open();
        dbc.removeFromTable(item, table);
        dbc.close();
    }

    /**
     * Deletes a table from an sqlite database.
     * @param table - a table to delete.
     */
    public static void deleteTable(String table) {
        DBContainer dbc = new DBContainer();
        dbc.open();
        dbc.deleteTable(table);
        dbc.close();
    }

    /**
     * Creates a group of tables.
     * @param group - name of a group.
     */
    public static void createGroup(String group) {
        DBContainer dbc = new DBContainer();
        dbc.open();
        dbc.createGroup(group);
        dbc.close();
    }

    /**
     * Adds a table to a group.
     * @param table - name of a table to be added to a group.
     * @param group - name of a group to collect table.
     */
    public static void addToGroup(String table, String group) {
        DietContainer container = new DietContainer();
        DBContainer dbc = new DBContainer();
        dbc.open();
        dbc.addToGroup(container, table, group);
        dbc.close();
    }

    /**
     * Print a calorie.console version of a given group.
     * @param group - group to print.
     */
    public static void showGroup(String group) {
        DietContainer dc = new DietContainer();
        DBContainer dbc = new DBContainer();
        dbc.open();
        dbc.showGroup(dc, group);
        dbc.close();
        TableCreator.createTable(dc);
    }

    /**
     * Shows a list of all tables.
     */
    public static void showGroups() {
        ArrayList<String> groups = new ArrayList<>();
        DBContainer dbc = new DBContainer();
        dbc.open();
        dbc.showGroups(groups);
        dbc.close();
        System.out.println(groups);
    }

    /**
     * Deletes an table from a given group.
     * @param tableName - a table name of a table to delete.
     * @param group - group with needed item.
     */
    public static void removeFromGroup(String tableName, String group) {
        DBContainer dbc = new DBContainer();
        dbc.open();
        dbc.removeFromGroup(tableName, group);
        dbc.close();
    }

    /**
     *
     * !!!Redundant method!!!
     *
     * Deletes a group from an sqlite database.
     * @param group - name of a group.
     */
    public static void deleteGroup(String group) {
        deleteTable(group);
    }

    /**
     * Quits the program.
     */
    public static void quit() {
        System.exit(0);
    }
}
