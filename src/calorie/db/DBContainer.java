package calorie.db;

import calorie.dietcontainer.DietContainer;

import java.sql.*;
import java.util.ArrayList;

/**
 * Creates sqlite database connection. Consists methods to work with database fields.
 */
public class DBContainer {
    private Connection conn = null;

    /**
     * Opens a database connection.
     */
    public final void open() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:./src/db/user.db");
            System.out.println("Connected");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Closes a database connection.
     */
    public final void close() {
        try {
            conn.close();
            System.out.println("Disconnected");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates a library.
     */
    public final void createLib() {
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            String[] types = {"TABLE"};
            ResultSet rs = dbmd.getTables(null, null, "%", types);
            while (rs.next()) {
                if (rs.getString("TABLE_NAME").equals("library")) return;
            }

            String query = "CREATE TABLE library (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                    "\titem VARCHAR(15), \n" +
                    "\tprotein REAL, \n" +
                    "\tfat REAL, \n" +
                    "\tcarbohydrates REAL)";

            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            statement.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * Adds an item to a library (a table in sqlite database).
     * @param item - name of a item.
     * @param protein - protein content of an item.
     * @param fat - fat content of an item.
     * @param carbohydrates - carbohydrates content of an item.
     */
    public final void addToLib(String item, float protein, float fat, float carbohydrates) {
        try {
            String query = "INSERT INTO library (item, protein, fat, carbohydrates) " +
                    "VALUES ('" + item + "', '" + protein + "', '" + fat + "', '" + carbohydrates + "')";
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            statement.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates a DietContainer object and fills it with library's values.
     * @param container - a DietContainer object to collect values of library.
     */
    public final void showLib(DietContainer container) {
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT item, protein, fat, carbohydrates FROM library";
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                container.add(rs.getString("item"),
                        rs.getFloat("protein"), //TODO
                        rs.getFloat("protein"),
                        rs.getFloat("fat"),
                        rs.getFloat("carbohydrates"));
            }

            rs.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes an item from a library.
     * @param item - an item of a library to delete.
     */
    public final void deleteLibItem(String item) {
        try {
            Statement statement = conn.createStatement();
            String query = "DELETE FROM 'library' WHERE item = " + "'" + item + "'";
            statement.executeUpdate(query);
            statement.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates a table in an sqlite database.
     * @param table - name of a table.
     */
    public final void createTable(String table) {
        try {
            String query = "CREATE TABLE " + table + "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                    "\titem VARCHAR(15), \n" +
                    "\tweight REAL, \n" +
                    "\tprotein REAL, \n" +
                    "\tfat REAL, \n" +
                    "\tcarbohydrates REAL)";

            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            statement.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds an item to an sqlite database's table.
     * @param item - name of an item.
     * @param weight - weight of an item.
     * @param table - name of a table.
     */
    public final void addToTable(String item, float weight, String table) {
        float weightCoeff = weight / 100;
        float protein;
        float fat;
        float carbohydrates;

        try {
            Statement statement = conn.createStatement();
            String query = "SELECT item, protein, fat, carbohydrates FROM "
                    + "\'library\'" + " WHERE item = " + "'" + item + "'";
            ResultSet rs = statement.executeQuery(query);
            protein = rs.getFloat("protein");
            fat = rs.getFloat("fat");
            carbohydrates = rs.getFloat("carbohydrates");

            query = "INSERT INTO " + table + " (item, weight, protein, fat, carbohydrates) "
                    + "VALUES ('"
                    + item
                    + "', '"
                    + weight
                    + "', '"
                    + protein * weightCoeff
                    + "', '"
                    + fat * weightCoeff
                    + "', '"
                    + carbohydrates * weightCoeff
                    + "')";

            statement.executeUpdate(query);
            statement.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Fills a DietContainer object with an sqlite database table's values.
     * @param container - a DietContainer object to collect table's values.
     * @param table - a table with needed values.
     */
    public final void showTable(DietContainer container, String table) {
        container.setTableName(table);

        try {
            Statement statement = conn.createStatement();
            String query = "SELECT item, weight, protein, fat, carbohydrates FROM " + table;
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                container.add(rs.getString("item"),
                        rs.getFloat("weight"),
                        rs.getFloat("protein"),
                        rs.getFloat("fat"),
                        rs.getFloat("carbohydrates"));
            }

            rs.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets names of tables into the given list.
     * @param tableNames - list to collect table names.
     */
    public final void showTables(ArrayList<String> tableNames) {
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            String[] types = {"TABLE"};
            ResultSet rs = dbmd.getTables(null, null, "%", types);

            while (rs.next()) {
                tableNames.add(rs.getString("TABLE_NAME"));
            }

            for (int i = tableNames.size(); i > 0; i--) {
                if (tableNames.get(i - 1).charAt(0) == '_') {
                    tableNames.remove(i - 1);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes an item from a given table.
     * @param item - an item to delete.
     * @param table - table with needed item.
     */
    public final void removeFromTable(String item, String table) {
        try {
            Statement statement = conn.createStatement();
            String query = "DELETE FROM " + "'"+ table + "'" + " WHERE item = " + "'" + item + "'";
            statement.executeUpdate(query);
            statement.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes a table from an sqlite database.
     * @param table - a table to delete.
     */
    public final void deleteTable(String table) {
        try {
            String query = "DROP TABLE " + table;

            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            statement.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates a group of tables.
     * @param group - name of a group.
     */
    public final void createGroup(String group) {
        StringBuilder sb = new StringBuilder("_");
        sb.append(group);
        try {
            String query = "CREATE TABLE " + "'" + sb + "'" + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                    "\ttableName VARCHAR(15), \n" +
                    "\tweight REAL, \n" +
                    "\tprotein REAL, \n" +
                    "\tfat REAL, \n" +
                    "\tcarbohydrates REAL)";

            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            statement.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds a table to a group.
     * @param container - DietContainer object needed to buffer values of table.
     * @param table - name of a table to be added to a group.
     * @param group - name of a group to collect table.
     */
    public final void addToGroup(DietContainer container, String table, String group) {
        container.setTableName(group);

        try {
            Statement statement = conn.createStatement();
            String query = "SELECT item, weight, protein, fat, carbohydrates FROM " + table;
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                container.add(rs.getString("item"),
                        rs.getFloat("weight"),
                        rs.getFloat("protein"),
                        rs.getFloat("fat"),
                        rs.getFloat("carbohydrates"));
            }

            query = "INSERT INTO " + group + "(tableName, weight,  protein, fat, carbohydrates) " +
                    "VALUES ('"
                    + table
                    + "', '"
                    + container.getWeightTotal()
                    + "', '"
                    + container.getProteinAbsoluteTotal()
                    + "', '"
                    + container.getFatAbsoluteTotal()
                    + "', '"
                    + container.getCarbohydratesAbsoluteTotal() + "')";

            statement.executeUpdate(query);
            statement.close();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    /**
     * Gets names of groups into the given list.
     * @param tableNames - list to collect groups names.
     */
    public final void showGroups(ArrayList<String> tableNames) {
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            String[] types = {"TABLE"};
            ResultSet rs = dbmd.getTables(null, null, "%", types);

            while (rs.next()) {
                tableNames.add(rs.getString("TABLE_NAME"));
            }

            for (int i = tableNames.size(); i > 0; i--) {
                if (tableNames.get(i - 1).charAt(0) != '_') {
                    tableNames.remove(i - 1);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Print a calorie.console version of a given group.
     * @param group - group to print.
     */
    public final void showGroup(DietContainer container, String group) {
        container.setTableName(group);

        try {
            Statement statement = conn.createStatement();
            String query = "SELECT tableName, weight, protein, fat, carbohydrates FROM " + "'" + group + "'";
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                container.add(rs.getString("tableName"),
                        rs.getFloat("weight"),
                        rs.getFloat("protein"),
                        rs.getFloat("fat"),
                        rs.getFloat("carbohydrates"));
            }

            rs.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes an table from a given group.
     * @param tableName - a table name of a table to delete.
     * @param group - group with needed item.
     */
    public final void removeFromGroup(String tableName, String group) {
        try {
            Statement statement = conn.createStatement();
            String query = "DELETE FROM " + "'"+ group + "'" + " WHERE tableName = " + "'" + tableName + "'";
            statement.executeUpdate(query);
            statement.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
