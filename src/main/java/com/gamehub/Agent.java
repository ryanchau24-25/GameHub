package com.gamehub;

/**
 * Abstract Agent class that defines behavior for all agents in the simulation.
 * Subclasses must implement the act() method to define specific agent behavior.
 */
public abstract class Agent {
    protected int row;
    protected int col;
    protected char symbol;
    protected int energy;
    protected int maxEnergy;
    protected World world;
    
    /**
     * Constructor for an agent
     */
    public Agent(char symbol, World world, int maxEnergy) {
        this.symbol = symbol;
        this.world = world;
        this.maxEnergy = maxEnergy;
        this.energy = maxEnergy;
        this.row = -1;
        this.col = -1;
    }
    
    /**
     * Set the agent's position in the world
     */
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    /**
     * Abstract method that defines what the agent does each turn
     * Subclasses must implement this
     */
    public abstract void act();
    
    /**
     * Decrease energy each turn (cost of living)
     */
    public void decreaseEnergy(int amount) {
        this.energy -= amount;
    }
    
    /**
     * Increase energy (from eating)
     */
    public void increaseEnergy(int amount) {
        this.energy = Math.min(energy + amount, maxEnergy);
    }
    
    /**
     * Check if agent is still alive
     */
    public boolean isAlive() {
        return energy > 0;
    }
    
    /**
     * Try to move the agent to a new position
     */
    public boolean moveTowards(int newRow, int newCol) {
        if (world.isValidPosition(newRow, newCol)) {
            // Check if position is empty or has food
            Agent agentThere = world.getAgentAt(newRow, newCol);
            if (agentThere == null) {
                this.row = newRow;
                this.col = newCol;
                decreaseEnergy(1); // Movement costs energy
                return true;
            }
        }
        return false;
    }
    
    // Getters
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public char getSymbol() {
        return symbol;
    }
    
    public int getEnergy() {
        return energy;
    }
    
    public int getMaxEnergy() {
        return maxEnergy;
    }
    
    /**
     * Find the distance to another agent
     */
    protected int distanceTo(Agent other) {
        int dr = Math.abs(row - other.getRow());
        int dc = Math.abs(col - other.getCol());
        return dr + dc; // Manhattan distance
    }
    
    /**
     * Get nearby agents (within a certain distance)
     */
    protected Agent getNearbyAgent(int range) {
        for (Agent agent : world.getAgents()) {
            if (agent != this && distanceTo(agent) <= range && distanceTo(agent) > 0) {
                return agent;
            }
        }
        return null;
    }
}
