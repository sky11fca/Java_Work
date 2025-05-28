package com.hexGameServer;

import java.util.*;

public class HexGame {
    private Player[][] board;
    private int size;
    private DisjointSet disjointSet;

    public HexGame(int size) {
        this.size = size;
        this.board = new Player[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                board[i][j] = Player.NONE;
            }
        }

        this.disjointSet = new DisjointSet(size*size+4);
    }

    public boolean makeMove(int x, int y, Player player) {
        if(x<0 || y<0 || x>=size || y>=size) {
            throw new IllegalArgumentException("Illegal move");
        }

        if(board[x][y] != Player.NONE) {
            return false;
        }

        board[x][y] = player;
        int cell = x*size+y;
        if(player == Player.PLAYER1){
            if(x==0) disjointSet.union(cell, size*size);
            if(x==size-1) disjointSet.union(cell, size*size+1);

            connectWithNeighbors(x, y, Player.PLAYER1, cell);
        }
        else if(player == Player.PLAYER2){
            if(y==0) disjointSet.union(cell, size*size+2);
            if(y==size-1) disjointSet.union(cell, size*size+3);

            connectWithNeighbors(x, y, Player.PLAYER2, cell);
        }
        return true;
    }

    private void connectWithNeighbors(int x, int y, Player player, int cell) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, 1}, {1, -1}};

        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            if (nx >= 0 && nx < size && ny >= 0 && ny < size && board[nx][ny] == player) {
                disjointSet.union(cell, nx * size + ny);
            }
        }
    }

    public boolean hasPlayerWon(Player player)
    {
        if(player == Player.PLAYER1) {
            return disjointSet.find(size*size) == disjointSet.find(size*size+1);
        }
        else{
            return disjointSet.find(size*size+2) == disjointSet.find(size*size+3);
        }
    }

    @Override
    public String toString() {
        return "HexGame{" +
                "board=" + Arrays.toString(board) +
                ", size=" + size +
                ", disjointSet=" + disjointSet +
                '}';
    }
}
