package com.gamehub.controller;

import com.gamehub.SimulationManager;
import com.gamehub.dto.SimulationStateDTO;
import org.springframework.web.bind.annotation.*;

/**
 * SimulationController - REST API endpoints for controlling the simulation
 */
@RestController
@RequestMapping("/api/simulation")
@CrossOrigin(origins = "*")
public class SimulationController {
    
    private static final SimulationManager simulationManager = new SimulationManager();
    
    /**
     * GET /api/simulation/state - Get current simulation state
     */
    @GetMapping("/state")
    public SimulationStateDTO getState() {
        return new SimulationStateDTO(
            simulationManager.getGrid(),
            simulationManager.getTurn(),
            simulationManager.getMaxTurns(),
            simulationManager.getHerbivoreCount(),
            simulationManager.getPredatorCount(),
            simulationManager.isPlayerAlive(),
            simulationManager.getPlayerEnergy(),
            simulationManager.isPaused(),
            simulationManager.isFinished(),
            simulationManager.getRows(),
            simulationManager.getCols()
        );
    }

    /**
     * POST /api/simulation/move - Move the player using a direction.
     */
    @PostMapping("/move")
    public SimulationStateDTO move(@RequestParam String direction) {
        simulationManager.movePlayer(direction);
        return getState();
    }
    
    /**
     * POST /api/simulation/start - Start the simulation
     */
    @PostMapping("/start")
    public SimulationStateDTO start() {
        simulationManager.start();
        return getState();
    }
    
    /**
     * POST /api/simulation/pause - Pause the simulation
     */
    @PostMapping("/pause")
    public SimulationStateDTO pause() {
        simulationManager.pause();
        return getState();
    }
    
    /**
     * POST /api/simulation/step - Run one turn
     */
    @PostMapping("/step")
    public SimulationStateDTO step() {
        if (!simulationManager.isFinished()) {
            simulationManager.step();
        }
        return getState();
    }
    
    /**
     * POST /api/simulation/reset - Reset the simulation
     */
    @PostMapping("/reset")
    public SimulationStateDTO reset() {
        simulationManager.reset();
        return getState();
    }
    
    /**
     * POST /api/simulation/run - Run multiple turns
     */
    @PostMapping("/run")
    public SimulationStateDTO run(@RequestParam(defaultValue = "5") int turns) {
        for (int i = 0; i < turns && !simulationManager.isFinished(); i++) {
            simulationManager.step();
        }
        return getState();
    }
}
