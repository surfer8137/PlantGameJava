/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Angel
 */
public class PlantField {

    private Plant[][] plants;

    public PlantField(int sizeX, int sizeY, float time) {
        this.plants = new Plant[sizeX][sizeY];
    }

    public void update(float time, int resetPos) {
        for (int i = 0; i < plants.length; i++) {
            for (int j = 0; j < plants[i].length; j++) {
                if (plants[i][j] != null) {
                    if (resetPos == i) {
                        plants[i][j].update(time, true);
                    } else {
                        plants[i][j].update(time, false);
                    }
                }
            }
        }
    }

    public void draw() {
        System.out.println();  
        System.out.println();
        for (int x = 0; x < plants.length; x++) {
            for (int y = 0; y < plants[x].length; y++) {
                if (plants[x][y] != null) {
                    System.out.print(plants[x][y].getSymbol());
                }
                else{
                    System.out.print(' ');
                }
            }
            System.out.println("\n");
        } 
        for (int x = 0; x < plants.length; x++) {
            System.out.print('-');
        }
        System.out.println();
    }

    public Plant[][] getPlants() {
        return plants;
    }
}
