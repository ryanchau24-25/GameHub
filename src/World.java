import java.util.ArrayList;

/**
 * World class represents the 2D grid-based simulation environment.
 * Contains agents and resources (food) that agents can interact with.
 */
public class World {
    private static final char EMPTY = '.';
    private static final char FOOD = 'F';
    private static final char WALL = '#';
    
    private int rows;
    private int cols;
    private char[][] grid;
    private ArrayList<Agent> agents;
    
    /**
     * Constructor to initialize the world with given dimensions
     */
    public World(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new char[rows][cols];
        this.agents = new ArrayList<>();
        initializeGrid();
    }
    
    /**
     * Initialize the grid with empty spaces, some walls, and food
     */
    private void initializeGrid() {
        // Fill with empty space
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = EMPTY;
            }
        }
        
        // Add walls around the border
        for (int r = 0; r < rows; r++) {
            grid[r][0] = WALL;
            grid[r][cols - 1] = WALL;
        }
        for (int c = 0; c < cols; c++) {
            grid[0][c] = WALL;
            grid[rows - 1][c] = WALL;
        }
        
        // Add random food throughout the world
        for (int i = 0; i < (rows * cols) / 15; i++) {
            int r = 2 + (int)(Math.random() * (rows - 4));
            int c = 2 + (int)(Math.random() * (cols - 4));
            if (grid[r][c] == EMPTY) {
                grid[r][c] = FOOD;
            }
        }
    }
    
    /**
     * Add an agent to the world at a specific position
     */
    public void addAgent(Agent agent, int row, int col) {
        if (isValidPosition(row, col) && grid[row][col] == EMPTY) {
            agent.setPosition(row, col);
            agents.add(agent);
        }
    }
    
    /**
     * Remove an agent from the world
     */
    public void removeAgent(Agent agent) {
        agents.remove(agent);
    }
    
    /**
     * Check if a position is valid and empty
     */
    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols && 
               grid[row][col] != WALL;
    }
    
    /**
     * Check if there's food at a position
     */
    public boolean hasFoodAt(int row, int col) {
        return isValidPosition(row, col) && grid[row][col] == FOOD;
    }
    
    /**
     * Check if there's an agent at a position
     */
    public Agent getAgentAt(int row, int col) {
        for (Agent agent : agents) {
            if (agent.getRow() == row && agent.getCol() == col) {
                return agent;
            }
        }
        return null;
    }
    
    /**
     * Remove food at a position (when eaten)
     */
    public void removeFoodAt(int row, int col) {
        if (grid[row][col] == FOOD) {
            grid[row][col] = EMPTY;
        }
    }
    
    /**
     * Add food to a position (for respawning)
     */
    public void addFoodAt(int row, int col) {
        if (grid[row][col] == EMPTY) {
            grid[row][col] = FOOD;
        }
    }
    
    /**
     * Get a copy of the grid for display
     */
    public char[][] getGridCopy() {
        char[][] copy = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                copy[r][c] = grid[r][c];
            }
        }
        
        // Place agents on the grid
        for (Agent agent : agents) {
            int r = agent.getRow();
            int c = agent.getCol();
            if (r >= 0 && r < rows && c >= 0 && c < cols) {
                copy[r][c] = agent.getSymbol();
            }
        }
        
        return copy;
    }
    
    /**
     * Display the current state of the world
     */
    public void display() {
        char[][] displayGrid = getGridCopy();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print(displayGrid[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    /**
     * Get all agents in the world
     */
    public ArrayList<Agent> getAgents() {
        return agents;
    }
    
    /**
     * Get the number of rows
     */
    public int getRows() {
        return rows;
    }
    
    /**
     * Get the number of columns
     */
    public int getCols() {
        return cols;
    }
    
    /**
     * Get agent statistics
     */
    public void displayStats() {
        int herbCount = 0, predCount = 0;
        for (Agent a : agents) {
            if (a instanceof Herbivore) herbCount++;
            else if (a instanceof Predator) predCount++;
        }
        System.out.println("=== Stats ===");
        System.out.println("Herbivores: " + herbCount);
        System.out.println("Predators: " + predCount);
        System.out.println("Total Agents: " + agents.size());
    }
}
