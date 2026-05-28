# Agentic World - Grid-Based Survival Simulation

A 2D grid-based simulation game featuring intelligent agents with autonomous decision-making behavior. This project demonstrates object-oriented design principles including inheritance, polymorphism, and encapsulation.

## Project Overview

**Agentic World** is an AP Computer Science A capstone project where agents populate a grid-based world and make intelligent decisions to survive. The simulation showcases a food chain with herbivores and predators in a dynamic environment.

## Features

- **2D Grid World** (20×40): A bordered environment with walls, food, and roaming agents
- **Agent AI**: Agents perceive their environment and make intelligent decisions
- **Two Agent Types**:
  - **Herbivores (H)**: Search for food, eat to survive, avoid predators
  - **Predators (P)**: Hunt herbivores to gain energy
- **Energy System**: Agents consume energy by living and moving; they die when energy reaches 0
- **Dynamic Environment**: Food respawns periodically; agents interact with their surroundings
- **Real-time Statistics**: Track population changes throughout the simulation

## How It Works

### Herbivore Behavior
1. Searches for nearby food within a 5-cell radius
2. Moves toward the closest food source when detected
3. Eats adjacent food to gain energy (15 points)
4. Moves randomly when no food is nearby
5. Loses 1 energy per turn for living and movement

### Predator Behavior
1. Hunts for nearby herbivores within an 8-cell radius
2. Pursues the closest herbivore when detected
3. Eats adjacent herbivores to gain energy (30 points)
4. Moves randomly when no prey is detected
5. Loses 1 energy per turn for living and movement

### World Rules
- Walls surround the entire world as boundaries
- Food appears randomly throughout the world
- Food respawns every 3 turns
- Agents die when energy reaches 0
- Simulation runs for a maximum of 100 turns

## Project Structure

```
/src/
├── Agent.java          - Abstract base class for all agents
├── Herbivore.java      - Herbivore subclass (vegetarian agent)
├── Predator.java       - Predator subclass (carnivore agent)
├── World.java          - 2D grid environment and world management
└── Simulation.java     - Main simulation controller
```

## Compilation & Execution

### Compile
```bash
cd src
javac *.java
```

### Run
```bash
java Simulation
```

The simulation will display:
- Initial world state and legend
- World snapshots every 5 turns
- Agent statistics (population counts)
- Final results when simulation ends

## Class Hierarchy

```
Object
 └─ Agent (abstract)
     ├─ Herbivore
     └─ Predator
```

## Key OOP Concepts Demonstrated

### Inheritance
- `Herbivore` and `Predator` extend the abstract `Agent` class
- Both subclasses inherit common agent properties (position, energy, symbol)

### Polymorphism
- Each agent subclass implements the abstract `act()` method differently
- The simulation calls `act()` on all agents without knowing their specific type

### Encapsulation
- Agent properties (energy, position) are protected and modified through methods
- World maintains private grid data with public accessor methods
- Each class has a clear, focused responsibility

### Abstraction
- `Agent` abstract class defines the agent interface
- Simulation interacts with agents through the abstract interface

## Reflection Questions

1. **Ecosystem Balance**: The simulation demonstrates a food chain. What happens to herbivore and predator populations over time? Why?

2. **Energy Management**: How does the energy system create realistic survival constraints?

3. **Decision-Making**: How do the different search ranges for herbivores (5) vs predators (8) affect gameplay dynamics?

4. **Emergent Behavior**: What patterns emerge from simple individual agent rules?

## Creative Features

- **Energy System**: Agents have realistic energy constraints that create survival pressure
- **Dynamic Food Respawning**: Food respawns periodically to support sustained populations
- **Two-Level Food Chain**: Complete ecosystem with producer (food), consumer (herbivore), and predator
- **Statistics Tracking**: Real-time population monitoring
- **Animated Display**: Turn-by-turn visualization (every 5 turns for readability)

## Possible Extensions

- Add more agent types (omnivores, plants that spread, etc.)
- Implement agent reproduction when energy is abundant
- Add seasons that change food availability
- Create different terrain types affecting movement/vision
- Implement genetic algorithms to evolve agent behavior
- Add a GUI interface for better visualization

## Technical Notes

- Uses Java's built-in `ArrayList` for dynamic agent collection
- 2D char array for efficient grid storage
- Manhattan distance for agent proximity detection
- Random number generation for realistic behavior variation

---

**Due Date**: June 1st  
**Student**: [Your Name]  
**Course**: AP Computer Science A - Capstone Project
