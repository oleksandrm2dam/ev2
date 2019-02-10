package com.oleksandrm2dam.minesweeper;

public class Tile {

    private boolean hasMine;
    private boolean isChecked;
    private boolean isFlagged;
    private boolean checkedAround;
    private int numberOfMinesAround;

    public Tile() {
        hasMine = false;
        isChecked = false;
        isFlagged = false;
        checkedAround = false;
        numberOfMinesAround = 0;
    }

    public boolean hasMine() {
        return hasMine;
    }

    public void setHasMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public int getNumberOfMinesAround() {
        return numberOfMinesAround;
    }

    public void setNumberOfMinesAround(int numberOfMinesAround) {
        this.numberOfMinesAround = numberOfMinesAround;
    }

    public boolean isHasMine() {
        return hasMine;
    }

    public boolean isCheckedAround() {
        return checkedAround;
    }

    public void setCheckedAround(boolean checkedAround) {
        this.checkedAround = checkedAround;
    }
}
