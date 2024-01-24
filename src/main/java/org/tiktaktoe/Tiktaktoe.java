package org.tiktaktoe;

import java.util.Scanner;
import java.util.logging.Logger;

public class Tiktaktoe {

    Logger logger = Logger.getLogger("App");

    Scanner scan = new Scanner(System.in);
    byte input;
    byte random;
    byte winner = 0;
    char[] box = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    boolean boxEmpty = false;


    public void setBoxEmpty(boolean boxEmpty) {
        this.boxEmpty = boxEmpty;
    }


    public void startApplication() {

        System.out.println("Enter box number to select. Enjoy!\n");

        boolean isGameActive = true;
        while (isGameActive) {

            drawGrid();
            endGameMessage();
            getValidateXValue();
            whoWins();
            checkingForDrawResult();
            randomMoveForO();
            if (winner != 0) {
                endGameMessage();
                isGameActive = false;
                scan.close();
            }
        }
    }

    /**
     * This method draws grid with digits and then cleans up the battlefield by populating spaces ' '
     */
    public void drawGrid() {
        System.out.println("\n\n " + box[0] + " | " + box[1] + " | " + box[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[3] + " | " + box[4] + " | " + box[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[6] + " | " + box[7] + " | " + box[8] + " \n");
        if (!boxEmpty) {
            for (byte i = 0; i < 9; i++)
                box[i] = ' ';
            setBoxEmpty(true);
        }
    }

    /**
     * The method prints final end game message, depending on winner case
     */
    public void endGameMessage() {
        switch (winner) {
            case 1:
                System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            case 2:
                System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            case 3:
                System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            default:
                break;
        }
    }

    /**
     * The method gets in the valid value 'X' and sets it into the cell
     */

    public void getValidateXValue() {
        while (true) {
            input = scan.nextByte();
            if (input > 0 && input < 10) {
                if (box[input - 1] == 'X' || box[input - 1] == 'O')
                    logger.info("That one is already in use. Enter another.");
                else {
                    box[input - 1] = 'X';
                    break;
                }
            } else
                logger.info("Invalid input. Enter again.");
        }
    }

    /**
     * The method gets random empty cell for 'O'
     */
    public void randomMoveForO() {
        if (winner == 0) {
            while (true) {
                random = (byte) (Math.random() * (9 - 1 + 1) + 1);
                if (box[random - 1] != 'X' && box[random - 1] != 'O') {
                    box[random - 1] = 'O';
                    break;
                }
            }
        }
    }

    /**
     * The method checks game for draw result
     */

    public void checkingForDrawResult() {
        if (winner == 0) {
            boolean isDraw = true;
            for (int i = 0; i < 9; i++) {
                if (box[i] != 'X' && box[i] != 'O') {
                    isDraw = false;
                    break;
                }
            }

            if (isDraw) {
                winner = 3;
            }
        }
    }

    /**
     * The method used to determine who win the game X or O
     */

    public void whoWins() {

        int[][] lines = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };


        for (int[] line : lines) {
            if (box[line[0]] == 'X' && box[line[1]] == 'X' && box[line[2]] == 'X') {
                winner = 1;
                return;
            } else if (box[line[0]] == 'O' && box[line[1]] == 'O' && box[line[2]] == 'O') {
                winner = 2;
                return;
            }
        }
    }


}