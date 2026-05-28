# Quick Start Guide

## Setup & Run

1. **Navigate to the source directory**:
   ```bash
   cd /workspaces/GameHub/src
   ```

2. **Compile all Java files**:
   ```bash
   javac *.java
   ```

3. **Run the simulation**:
   ```bash
   java Simulation
   ```

## What to Expect

The simulation will display:

```
=== AGENTIC WORLD SIMULATION ===
World Size: 20 x 40
Max Turns: 100

Legend:
  # = Wall (boundaries)
  F = Food
  H = Herbivore
  P = Predator
  . = Empty space

========== TURN 0 ==========
[Grid display...]
=== Stats ===
Herbivores: 8
Predators: 2
Total Agents: 10
```

The game runs for up to 100 turns, showing world state every 5 turns.

## Game Mechanics

### Agents Start With
- **8 Herbivores** starting energy: 25
- **2 Predators** starting energy: 40

### Energy Rules
- **Movement**: Costs 1 energy per turn
- **Living**: Costs 1 energy per turn
- **Herbivore eating**: Gains 15 energy from food
- **Predator eating**: Gains 30 energy from herbivore
- **Death**: When energy reaches 0

### Environment
- **World**: 20 rows × 40 columns
- **Food**: Scattered throughout, respawns every 3 turns
- **Walls**: Border around entire world

## Observing Behavior

Watch for:
- **Herbivores moving toward food**: They search 5 cells away for food
- **Predators hunting herbivores**: They search 8 cells away for prey
- **Population cycles**: Herbivores increase (if food plentiful), predators follow
- **Agent deaths**: Stats decrease when agents run out of energy

## Key Files

- `Simulation.java` - Main entry point and game controller
- `World.java` - Grid and resource management
- `Agent.java` - Base class for all agents
- `Herbivore.java` - Plant-eating agent
- `Predator.java` - Meat-eating agent

## Customizing the Simulation

Edit constants in `Simulation.java`:

```java
private static final int WORLD_ROWS = 20;      // Grid height
private static final int WORLD_COLS = 40;      // Grid width
private static final int MAX_TURNS = 100;      // Simulation length
private static final int NUM_HERBIVORES = 8;   // Initial herbivores
private static final int NUM_PREDATORS = 2;    // Initial predators
private static final int FOOD_RESPAWN_RATE = 3; // Food spawn frequency
```

---

Enjoy the simulation!
