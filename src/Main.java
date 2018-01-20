/**
 * Main class of the program
 */
package calorie;


import calorie.console.CommandConstructor;
import calorie.console.CommandInfo;

public class Main {
    public static void main(String[] args) {
        CommandAction.createLib();
        CommandInfo.hello();
        CommandConstructor.constructor();
    }
}
