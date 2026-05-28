package com.gamehub.dto;

/**
 * SimulationStateDTO - Data Transfer Object for simulation state
 */
public class SimulationStateDTO {
    public String[] grid;
    public int turn;
    public int maxTurns;
    public int herbivoreCount;
    public int predatorCount;
    public int totalAgents;
    public int playerEnergy;
    public boolean playerAlive;
    public boolean isPaused;
    public boolean isFinished;
    public int rows;
    public int cols;
    
    public SimulationStateDTO(char[][] grid, int turn, int maxTurns, 
                            int herbivoreCount, int predatorCount,
                            boolean playerAlive, int playerEnergy,
                            boolean isPaused, boolean isFinished,
                            int rows, int cols) {
        this.grid = new String[grid.length];
        for (int r = 0; r < grid.length; r++) {
            this.grid[r] = new String(grid[r]);
        }
        this.turn = turn;
        this.maxTurns = maxTurns;
        this.herbivoreCount = herbivoreCount;
        this.predatorCount = predatorCount;
        this.totalAgents = herbivoreCount + predatorCount + (playerAlive ? 1 : 0);
        this.playerEnergy = playerEnergy;
        this.playerAlive = playerAlive;
        this.isPaused = isPaused;
        this.isFinished = isFinished;
        this.rows = rows;
        this.cols = cols;
    }
}
