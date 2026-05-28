package com.gamehub;

import java.util.ArrayList;

/**
 * SimulationManager - manages the state and control of a simulation instance.
 * Allows starting, stepping, pausing, and getting the current state.
 */
public class SimulationManager {
    private World world;
    private int turn;
    private int maxTurns;
    private boolean isPaused;
    
    private static final int WORLD_ROWS = 20;
    private static final int WORLD_COLS = 40;
    private static final int MAX_TURNS = 100;
    private static final int NUM_HERBIVORES = 8;
    private static final int NUM_PREDATORS = 2;
    private static final int FOOD_RESPAWN_RATE = 3;
    
    /**
     * Constructor - initializes but does not start simulation
     */
    public SimulationManager() {
        this.world = new World(WORLD_ROWS, WORLD_COLS);
        this.turn = 0;
        this.maxTurns = MAX_TURNS;
        this.isPaused = true;
        initializeAgents();
    }
    
    /**
     * Initialize the world with agents
     */
    private void initializeAgents() {
        // Add herbivores at random positions
        for (int i = 0; i < NUM_HERBIVORES; i++) {
            Herbivore herb = new Herbivore(world);
            int row = 2 + (int)(Math.random() * (WORLD_ROWS - 4));
            int col = 2 + (int)(Math.random() * (WORLD_COLS - 4));
            world.addAgent(herb, row, col);
        }
        
        // Add predators at random positions
        for (int i = 0; i < NUM_PREDATORS; i++) {
            Predator pred = new Predator(world);
            int row = 2 + (int)(Math.random() * (WORLD_ROWS - 4));
            int col = 2 + (int)(Math.random() * (WORLD_COLS - 4));
            world.addAgent(pred, row, col);
        }
    }
    
    /**
     * Reset the simulation to initial state
     */
    public void reset() {
        this.world = new World(WORLD_ROWS, WORLD_COLS);
        this.turn = 0;
        this.isPaused = true;
        initializeAgents();
    }
    
    /**
     * Start/resume the simulation
     */
    public void start() {
        this.isPaused = false;
    }
    
    /**
     * Pause the simulation
     */
    public void pause() {
        this.isPaused = true;
    }
    
    /**
     * Run one turn of the simulation
     */
    public void step() {
        if (turn >= maxTurns || world.getAgents().size() == 0) {
            return;
        }
        
        runTurn();
    }
    
    /**
     * Run one complete turn of the simulation
     */
    private void runTurn() {
        turn++;
        
        // Make a copy of agents list since it might be modified during iteration
        ArrayList<Agent> agentsThisTurn = new ArrayList<>(world.getAgents());
        
        // All agents act
        for (Agent agent : agentsThisTurn) {
            if (agent.isAlive() && world.getAgents().contains(agent)) {
                agent.act();
            }
        }
        
        // Remove dead agents
        ArrayList<Agent> deadAgents = new ArrayList<>();
        for (Agent agent : world.getAgents()) {
            if (!agent.isAlive()) {
                deadAgents.add(agent);
            }
        }
        for (Agent agent : deadAgents) {
            world.removeAgent(agent);
        }
        
        // Respawn food occasionally
        if (turn % FOOD_RESPAWN_RATE == 0) {
            int r = 2 + (int)(Math.random() * (WORLD_ROWS - 4));
            int c = 2 + (int)(Math.random() * (WORLD_COLS - 4));
            world.addFoodAt(r, c);
        }
    }
    
    /**
     * Get the current grid state as a 2D array
     */
    public char[][] getGrid() {
        return world.getGridCopy();
    }
    
    /**
     * Get current turn number
     */
    public int getTurn() {
        return turn;
    }
    
    /**
     * Get max turns
     */
    public int getMaxTurns() {
        return maxTurns;
    }
    
    /**
     * Check if simulation is paused
     */
    public boolean isPaused() {
        return isPaused;
    }
    
    /**
     * Check if simulation is finished
     */
    public boolean isFinished() {
        return turn >= maxTurns || world.getAgents().size() == 0;
    }
    
    /**
     * Get world dimensions
     */
    public int getRows() {
        return world.getRows();
    }
    
    public int getCols() {
        return world.getCols();
    }
    
    /**
     * Get agent counts
     */
    public int getHerbivoreCount() {
        int count = 0;
        for (Agent agent : world.getAgents()) {
            if (agent instanceof Herbivore) count++;
        }
        return count;
    }
    
    public int getPredatorCount() {
        int count = 0;
        for (Agent agent : world.getAgents()) {
            if (agent instanceof Predator) count++;
        }
        return count;
    }
    
    /**
     * Get all agents
     */
    public ArrayList<Agent> getAgents() {
        return world.getAgents();
    }
}
