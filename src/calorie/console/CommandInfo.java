/*
 * Consists info calorie.console commands.
 */

package calorie.console;

public class CommandInfo {

    /**
     * Prints greeting message and some help info.
     */
    public static void hello() {
        System.out.print("\nConnected.");
        System.out.print("\nEnter \"?\" for information.");
        System.out.print("\nEnter \"quit\" to exit.");
    }

    /**
     * Prints help info.
     */
    static void info() {
        final String info =
                "info: add items with 100g weight.\n\n" +
                        "commands:\n" +
                        "\tcreatetable [name_of_table] - creates a new table.\n" +
                        "\tdeletetable [name_of_table] - deletes a table.\n" +
                        "\tadd [name_of_item] [protein] [fat] [carbohydrates] - adds an item to a library.\n" +
                        "\tadd [name_of_item] [weight_of_item] [name_of_table] - add item to a table.\n" +
                        "\tremove [name_of_item] [name_of_table] - remove an item from a table.\n" +
                        "\tdelete [name_of_item] - deletes an item from a library\n" +
                        //TODO
                        "\tcleanlib - deletes all items from library\n" +
                        "\tshow - shows a library.\n" +
                        "\tshowtables - shows all tables.\n" +
                        "\tshowgroups - shows all groups\n" +
                        "\tshowtable [name_of_table] - shows a table.\n" +
                        //TODO
                        "\tshowgroup [name_of_group] - shows a table.\n" +
                        "\tcreategroup [name_of_group]  - creates a group.\n" +
                        "\tdeletegroup [name_of_group] - deletes a group\n" +
                        "\tremovefromgroup [name_of_table] [name_of_group] - removes a table from a group.\n" +
                        //TODO
                        "\taddtogroup [name_of_group] [name_of_table] - adds table to a group\n" +
                        "\tquit - to exit a program.";

        System.out.println(info);
    }

    /**
     * Prints message in case of wrong format of a command.
     */
    static void wrong() {
        System.out.print("\nWrong format of a command. Try again.");
        System.out.print("\nTry \"?\" for help.");
    }
}
