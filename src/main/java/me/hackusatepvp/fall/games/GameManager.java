package me.hackusatepvp.fall.games;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.HashSet;

public class GameManager {
    @Setter @Getter private String name;

    @Getter private HashSet<Player> game = new HashSet<>();

    @Getter private HashMap<Player, ItemStack[]> playerinvs = new HashMap<>();
    @Getter private HashMap<Player, ItemStack[]> playerArmour = new HashMap<>();

    private int gameState;

    public void setGameState(GameState state){
        switch (state) {
            default:
            case STOP:
                this.gameState = 0;
                break;
            case WAITING:
                this.gameState = 1;
                break;
            case STARTING:
                this.gameState = 2;
                break;
            case GAME:
                this.gameState = 3;
                break;
            case RUNNING:
                this.gameState = 4;
                break;
        }
    }

    public int getGameStateInt(){
        return this.gameState;
    }

    public GameState getGameState() {
        switch(this.gameState){
            default:
            case 0:
                return GameState.STOP;
            case 1:
                return GameState.WAITING;
            case 2:
                return GameState.STARTING;
            case 3:
                return GameState.GAME;
            case 4:
                return GameState.RUNNING;
        }
    }

    public void setGameState(int i){
        if(i >= 0 && i < 3){
            this.gameState = i;
        }
    }

    public HashSet<Player> getGame() {
        return this.game;
    }
}

