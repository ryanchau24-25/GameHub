package com.gamehub;

/**
 * Predator agent - hunts herbivores for food.
 * Searches for herbivores and moves towards them.
 * Dies if energy reaches 0.
 */
public class Predator extends Agent {
    private static final int HUNT_RANGE = 8;
    private static final int PREY_VALUE = 30;
    
    /**
     * Constructor for Predator
     */
    public Predator(World world) {
        super('P', world, 40);
    }
    
    /**
     * Each turn, the predator searches for prey or moves randomly
     */
    @Override
    public void act() {
        // Lose energy just for living
        decreaseEnergy(1);
        
        // Check if there's a herbivore adjacent to this agent
        if (eatAdjacentPrey()) {
            return;
        }
        
        // Search for nearby prey
        Agent prey = findNearbyPrey();
        
        if (prey != null) {
            // Move towards the prey
            moveTowardsPrey(prey);
        } else {
            // Move randomly if no prey found
            moveRandomly();
        }
    }
    
    /**
     * Check if there's a herbivore adjacent and eat it
     */
    private boolean eatAdjacentPrey() {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            
            Agent prey = world.getAgentAt(newRow, newCol);
            if (prey instanceof Herbivore) {
                world.removeAgent(prey);
                increaseEnergy(PREY_VALUE);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Find the nearest herbivore within hunting range
     */
    private Agent findNearbyPrey() {
        Agent closestPrey = null;
        int closestDist = HUNT_RANGE + 1;
        
        for (Agent agent : world.getAgents()) {
            if (agent instanceof Herbivore) {
                int dist = distanceTo(agent);
                if (dist < closestDist && dist > 0) {
                    closestDist = dist;
                    closestPrey = agent;
                }
            }
        }
        
        return closestPrey;
    }
    
    /**
     * Move towards a prey agent
     */
    private void moveTowardsPrey(Agent prey) {
        int preyRow = prey.getRow();
        int preyCol = prey.getCol();
        
        int moveRow = row;
        int moveCol = col;
        
        // Move closer to prey
        if (row < preyRow) moveRow++;
        else if (row > preyRow) moveRow--;
        
        if (col < preyCol) moveCol++;
        else if (col > preyCol) moveCol--;
        
        moveTowards(moveRow, moveCol);
    }
    
    /**
     * Move randomly in a valid direction
     */
    private void moveRandomly() {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        // Try a random direction
        int[] dir = directions[(int)(Math.random() * 4)];
        int newRow = row + dir[0];
        int newCol = col + dir[1];
        
        moveTowards(newRow, newCol);
    }
}
