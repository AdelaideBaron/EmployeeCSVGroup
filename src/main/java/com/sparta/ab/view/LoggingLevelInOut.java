package com.sparta.ab.view;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.logging.Level.*;

public class LoggingLevelInOut {
    //to print the logs, and all other relevant outputs, to user

    private static Logger logger = Logger.getLogger("my logger");

    public LoggingLevelInOut() {
    }

    public static void main(String[] args) {
        LoggingLevelInOut loggingLevelInOut = new LoggingLevelInOut();
        // printer.getlevelChoice();
     //   String levelChoice = printer.getlevelChoice();
    //    System.out.println(levelChoice);

    }

    public Level getlevelChoice() {
        logger.log(Level.INFO, "getting level");
        Level levelChoice = getUserInput();
        return levelChoice;

    }

    private Level getUserInput() {
        Scanner scan = new Scanner(System.in);
        Level levelChoice = ALL;
        System.out.println("Logging level choices: ");
        System.out.println("1. ALL \n 2. CONFIG \n 3. FINE \n 4. FINER \n 5. FINEST \n 6. INFO \n 7. OFF \n 8. SEVERE \n 9. WARNING");
        System.out.println("Enter the menu number of logging level you wish to see: ");
        int levelChoiceValue = scan.nextInt();
        levelChoice = loopLevelInput(levelChoiceValue);
        logger.log(Level.FINE, "level set");
        return levelChoice;
    }

    private Level loopLevelInput(int levelChoiceValue) {
        logger.log(Level.FINER, "searching for level");
        Level levelChoice = ALL;
        if (levelChoiceValue == 1) {
            levelChoice = ALL;
        } else if (levelChoiceValue == 2) {
            levelChoice = CONFIG;
        } else if (levelChoiceValue == 3) {
            levelChoice = FINE;
        } else if (levelChoiceValue == 4) {
            levelChoice = FINER;
        } else if (levelChoiceValue == 5) {
            levelChoice = FINEST;
        } else if (levelChoiceValue == 6) {
            levelChoice = INFO;
        } else if (levelChoiceValue == 7) {
            levelChoice = OFF;
        } else if (levelChoiceValue == 8) {
            levelChoice = SEVERE;
        } else if (levelChoiceValue == 9) {
            levelChoice = WARNING;
        }
        return levelChoice;
    }
}




