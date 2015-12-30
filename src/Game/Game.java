/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Entities.Plant;
import Entities.PlantField;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angel
 */
public class Game {

    public static final int ROWS = 10, COLUMNS = 10;

    private PlantField field;

    private int placeToTake;

    private Scanner sc;
    private String cmd;

    private boolean exit;

    private float initTime;
    private float currentTime;

    private int numberOfPlants;

    public Game() {
        initialize();
        while (!exit) {
            update();
        }
    }

    public void initialize() {
        initTime = System.nanoTime();
        placeToTake = -1;
        sc = new Scanner(System.in);
        field = new PlantField(ROWS, COLUMNS, getNanoTimeInSeconds());
    }

    public void update() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }

        field.draw();
        currentTime = getNanoTimeInSeconds();
        //Tiempo
        System.out.println("Current time: " + currentTime);
        //Numero de plantas que tenemos recogidas
        System.out.println("Number of plants: " + numberOfPlants);
        //Dibujar plantas
        //Actualizar plantas
        field.update(currentTime, placeToTake);
        //Reiniciar el sitio de uso
        if (placeToTake != -1) {
            placeToTake = -1;
        }
        //Check cmd
        try {
            System.out.println("What do you want to do? ");
            cmd = sc.next();
            cmd = cmd + "\n";
            checkCmd();
        } catch (Exception e) {
            cmd = "";
        }

        //Limpiar console
        clearConsole();
    }

    private void checkCmd() throws Exception {
        if (cmd.equals("quit" + "\n")) {
            exit = true;
        } else if (cmd.equals("plant" + "\n")) {
            System.out.println("ROW: ");
            int f = sc.nextInt();
            System.out.println("COLUMN: ");
            int c = sc.nextInt();
            if (field.getPlants()[f][c] == null) {
                field.getPlants()[f][c] = new Plant(getNanoTimeInSeconds());
                return;
            } else {
                System.out.println("You can't plant a plant there.");
            }
        } else if (cmd.equals("take" + "\n")) {
            System.out.println("ROW: ");
            int f = sc.nextInt();
            System.out.println("COLUMN: ");
            int c = sc.nextInt();
            if (field.getPlants()[f][c] != null) {
                field.getPlants()[f][c] = null;
                numberOfPlants++;
                return;
            } else {
                System.out.println("You can't take a plant there.");
            }
        } else if (cmd.equals("plants" + "\n")) {
            for (int i = 0; i < field.getPlants().length; i++) {
                System.out.println();
                for (int j = 0; j < field.getPlants()[i].length; j++) {
                    if (field.getPlants()[i][j] != null) {
                        System.out.print(i + "," + j + "  ");
                    } else {
                        System.out.print(" " + "," + " " + "  ");
                    }
                }
                 System.out.println();
            }
        } else if (cmd.equals("empties" + "\n")) {
            for (int i = 0; i < field.getPlants().length; i++) {
                System.out.println();
                for (int j = 0; j < field.getPlants()[i].length; j++) {
                    if (field.getPlants()[i][j] == null) {
                        System.out.print(i + "," + j + "  ");
                    } else {
                        System.out.print(" " + "," + " " + "  ");
                    }
                }
                System.out.println();
            }
        } else if (cmd.equals("help" + "\n")) {
            System.out.println("-plant: plant a plant\n-take: recollect a grown plant\n-plants: list plant spaces\n-empties: list empty places\n-quit: quit the game");
        } else {
            System.out.println("Error, type help to get the cmds list");
        }
        System.in.read();
    }

    private void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            //  Handle any exceptions.
        }
    }

    public float getNanoTimeInSeconds() {
        return (float) ((System.nanoTime() - initTime) / 1000000000.0);
    }

}
