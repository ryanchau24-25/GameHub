package com.gamehub;

/**
 * PlayerAgent - controlled by the user through the web UI.
 * It does not act automatically during simulation turns.
 */
public class PlayerAgent extends Agent {
    public PlayerAgent(World world) {
        super('@', world, 40);
    }

    @Override
    public void act() {
        // Player is controlled manually through API requests.
    }
}
