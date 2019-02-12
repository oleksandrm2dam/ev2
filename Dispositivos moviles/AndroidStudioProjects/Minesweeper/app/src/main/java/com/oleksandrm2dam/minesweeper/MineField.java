package com.oleksandrm2dam.minesweeper;

import java.util.Random;

public class MineField {

    private int width, height;
    private Tile[][] tiles;
    private Random random;
    private int numberOfMines;
    private int numCheckedTiles;
    private int totalNumTiles;

    public MineField(int width, int height, int numberOfMines) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        random = new Random();
        this.numberOfMines = numberOfMines;
        numCheckedTiles = 0;
        totalNumTiles = width * height;
        initTiles();
    }

    private void initTiles() {
        for(int i = 0; i < width; ++i) {
            for(int j = 0; j < height; ++j) {
                tiles[i][j] = new Tile();
            }
        }
        int currentNumMines = 0;
        while(currentNumMines < numberOfMines) {
            int newMine = random.nextInt(totalNumTiles);
            int i = newMine % width;
            int j = newMine / width;
            if(!tiles[i][j].hasMine()) {
                tiles[i][j].setHasMine(true);
                ++currentNumMines;
            }
        }
        for(int i = 0; i < width; ++i) {
            for(int j = 0; j < height; ++j) {
                countMinesAroundTile(i, j);
            }
        }
    }

    private void countMinesAroundTile(int tileI, int tileJ) {
        int count = 0;
        for(int i = tileI - 1; i <= tileI + 1; ++i) {
            for(int j = tileJ - 1; j <= tileJ + 1; ++j) {
                if(i >= 0 && i < width && j >= 0 && j < height) {
                    if(i != tileI || j != tileJ) {
                        if(tiles[i][j].hasMine()) {
                            ++count;
                        }
                    }
                }
            }
        }
        tiles[tileI][tileJ].setNumberOfMinesAround(count);
    }

    public void checkEmptyTile(int tileI, int tileJ) {
        for(int i = tileI - 1; i <= tileI + 1; ++i) {
            for(int j = tileJ - 1; j <= tileJ + 1; ++j) {
                if(i >= 0 && i < width && j >= 0 && j < height) {
                    if(!tiles[i][j].isChecked()) {
                        tiles[i][j].setChecked(true);
                        ++numCheckedTiles;
                        if(tiles[i][j].getNumberOfMinesAround() == 0
                                && !tiles[i][j].isCheckedAround()) {
                            tiles[i][j].setCheckedAround(true);
                            checkEmptyTile(i, j);
                        }
                    }
                }
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public int getNumberOfMines() {
        return numberOfMines;
    }

    public void setNumberOfMines(int numberOfMines) {
        this.numberOfMines = numberOfMines;
    }

    public int getNumCheckedTiles() {
        return numCheckedTiles;
    }

    public void setNumCheckedTiles(int numCheckedTiles) {
        this.numCheckedTiles = numCheckedTiles;
    }

    public int getTotalNumTiles() {
        return totalNumTiles;
    }
}
