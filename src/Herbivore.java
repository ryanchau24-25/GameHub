/**
 * Herbivore agent - eats food to survive.
 * Searches for food and moves towards it.
 * Dies if energy reaches 0.
 */
public class Herbivore extends Agent {
    private static final int SEARCH_RANGE = 5;
    private static final int FOOD_VALUE = 15;
    
    /**
     * Constructor for Herbivore
     */
    public Herbivore(World world) {
        super('H', world, 25);
    }
    
    /**
     * Each turn, the herbivore searches for food or moves randomly
     */
    @Override
    public void act() {
        // Lose energy just for living
        decreaseEnergy(1);
        
        // Check if there's food adjacent to this agent
        if (eatAdjacentFood()) {
            return;
        }
        
        // Search for nearby food
        int[] foodPos = findNearbyFood();
        
        if (foodPos != null) {
            // Move towards the food
            moveTowards(foodPos[0], foodPos[1]);
        } else {
            // Move randomly if no food found
            moveRandomly();
        }
    }
    
    /**
     * Check if there's food adjacent and eat it
     */
    private boolean eatAdjacentFood() {
        // Check all 4 adjacent cells
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            
            if (world.hasFoodAt(newRow, newCol)) {
                world.removeFoodAt(newRow, newCol);
                increaseEnergy(FOOD_VALUE);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Search for food within a certain range
     */
    private int[] findNearbyFood() {
        int bestDist = SEARCH_RANGE + 1;
        int[] bestFood = null;
        
        // Check a range around the agent
        for (int r = row - SEARCH_RANGE; r <= row + SEARCH_RANGE; r++) {
            for (int c = col - SEARCH_RANGE; c <= col + SEARCH_RANGE; c++) {
                if (world.hasFoodAt(r, c)) {
                    int dist = Math.abs(r - row) + Math.abs(c - col);
                    if (dist < bestDist) {
                        bestDist = dist;
                        bestFood = new int[]{r, c};
                    }
                }
            }
        }
        
        return bestFood;
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
