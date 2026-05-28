package com.gamehub;

import java.util.ArrayList;

/**
 * Main Simulation class - orchestrates the Agentic World game.
 * Creates a world, populates it with agents, and runs the simulation.
 */
public class Simulation {
    private World world;
    private int turn;
    private int maxTurns;
    private static final int WORLD_ROWS = 20;
    private static final int WORLD_COLS = 40;
    private static final int MAX_TURNS = 100;
    private static final int NUM_HERBIVORES = 8;
    private static final int NUM_PREDATORS = 2;
    private static final int FOOD_RESPAWN_RATE = 3; // Respawn food every N turns
    
    /**
     * Constructor
     */
    public Simulation() {
        this.world = new World(WORLD_ROWS, WORLD_COLS);
        this.turn = 0;
        this.maxTurns = MAX_TURNS;
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
     * Display the current state
     */
    private void displayTurnInfo() {
        System.out.println("\n========== TURN " + turn + " ==========");
        world.display();
        world.displayStats();
    }
    
    /**
     * Run the complete simulation
     */
    public void run() {
        System.out.println("=== AGENTIC WORLD SIMULATION ===");
        System.out.println("World Size: " + WORLD_ROWS + " x " + WORLD_COLS);
        System.out.println("Max Turns: " + maxTurns);
        System.out.println();
        System.out.println("Legend:");
        System.out.println("  # = Wall");
        System.out.println("  F = Food");
        System.out.println("  H = Herbivore");
        System.out.println("  P = Predator");
        System.out.println("  . = Empty");
        System.out.println();
        
        displayTurnInfo();
        
        // Run simulation turns
        while (turn < maxTurns && world.getAgents().size() > 0) {
            runTurn();
            
            // Display every 5 turns for readability
            if (turn % 5 == 0 || world.getAgents().size() <= 1) {
                displayTurnInfo();
            }
            
            // Slow down display for better viewing
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        // Final display
        System.out.println("\n========== SIMULATION ENDED ==========");
        world.displayStats();
        
        if (world.getAgents().size() == 0) {
            System.out.println("All agents have died!");
        } else {
            System.out.println("Max turns reached!");
        }
    }
    
    /**
     * Main method - starts the simulation
     */
    public static void main(String[] args) {
        Simulation sim = new Simulation();
        sim.run();
    }
}
