package com.oleksandrm2dam.minesweeper;

import java.util.Random;

public class MineField {

    private int width, height;
    private Tile[][] tiles;
    private Random random;
    private double mineChance;
    private int numberOfMines;

    public MineField(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        random = new Random();
        mineChance = 0.3;
        numberOfMines = 0;
        initTiles();
    }

    public MineField(int width, int height, double mineChance) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        random = new Random();
        this.mineChance = mineChance;
        numberOfMines = 0;
        initTiles();
    }

    private void initTiles() {
        for(int i = 0; i < width; ++i) {
            for(int j = 0; j < height; ++j) {
                tiles[i][j] = new Tile();
                if(random.nextDouble() <= mineChance) {
                    tiles[i][j].setHasMine(true);
                    ++numberOfMines;
                }
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

    public double getMineChance() {
        return mineChance;
    }

    public void setMineChance(double mineChance) {
        this.mineChance = mineChance;
    }

    public int getNumberOfMines() {
        return numberOfMines;
    }

    public void setNumberOfMines(int numberOfMines) {
        this.numberOfMines = numberOfMines;
    }
}
