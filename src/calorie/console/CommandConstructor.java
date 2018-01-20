package calorie.console;

import calorie.CommandAction;

import java.util.Scanner;

/**
 * Creates a command from a query of words taken from calorie.console.
 * Executes an appropriate command from the CommandAction.java.
 */
public class CommandConstructor extends CommandInfo {
    public static void constructor() {
        try {
            while (true) {
                System.out.print("\nEnter a command: ");
                Scanner scanner = new Scanner(System.in);
                String[] command = scanner.nextLine().trim().split(" ");

                if (command.length > 5) {
                    wrong();
                }

                if (command.length == 1) {
                    switch (command[0]) {
                        case "show": CommandAction.showLib();
                            break;
                        case "showtables": CommandAction.showTables();
                            break;
                        case "showgroups": CommandAction.showGroups();
                            break;
                        case "quit": CommandAction.quit();
                            break;
                        case "?": info();
                            break;
                        case "help": info();
                            break;
                        default: wrong();
                    }
                }

                if (command.length == 2) {
                    switch (command[0]){
                        case "createtable": CommandAction.createTable(command[1]);
                            break;
                        case "creategroup": CommandAction.createGroup(command[1]);
                            break;
                        case "deletetable": CommandAction.deleteTable(command[1]);
                            break;
                        case "deletegroup": CommandAction.deleteGroup(command[1]);
                            break;
                        case "showtable": CommandAction.showTable(command[1]);
                            break;
                        case "showgroup": CommandAction.showGroup(command[1]);
                            break;
                        case "delete": CommandAction.deleteLibItem(command[1]);
                            break;
                        default: wrong();
                    }
                }

                if (command.length == 3) {
                    switch (command[0]) {
                        case "addtogroup": CommandAction.addToGroup(command[1], command[2]);
                            break;
                        case "removefromgroup": CommandAction.removeFromGroup(command[1], command[2]);
                            break;
                        case "remove": CommandAction.removeFromTable(command[1], command[2]);
                            break;
                        default: wrong();
                    }
                }

                if (command.length == 4) {
                    switch (command[0]) {
                        case "add":
                            CommandAction.addToTable(command[1], Float.parseFloat(command[2]), command[3]);
                            break;
                    }
                }

                if (command.length == 5) {
                    switch (command[0]) {
                        case "add": CommandAction.addToLib(command[1],
                                Float.parseFloat(command[2]),
                                Float.parseFloat(command[3]),
                                Float.parseFloat(command[4]));
                            break;
                        default: wrong();
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
