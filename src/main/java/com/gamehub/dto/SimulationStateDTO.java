package com.gamehub.dto;

/**
 * SimulationStateDTO - Data Transfer Object for simulation state
 */
public class SimulationStateDTO {
    public char[][] grid;
    public int turn;
    public int maxTurns;
    public int herbivoreCount;
    public int predatorCount;
    public int totalAgents;
    public boolean isPaused;
    public boolean isFinished;
    public int rows;
    public int cols;
    
    public SimulationStateDTO(char[][] grid, int turn, int maxTurns, 
                            int herbivoreCount, int predatorCount,
                            boolean isPaused, boolean isFinished,
                            int rows, int cols) {
        this.grid = grid;
        this.turn = turn;
        this.maxTurns = maxTurns;
        this.herbivoreCount = herbivoreCount;
        this.predatorCount = predatorCount;
        this.totalAgents = herbivoreCount + predatorCount;
        this.isPaused = isPaused;
        this.isFinished = isFinished;
        this.rows = rows;
        this.cols = cols;
    }
}
