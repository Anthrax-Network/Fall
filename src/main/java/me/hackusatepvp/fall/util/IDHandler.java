package me.hackusatepvp.fall.util;

import org.bukkit.entity.Player;

import java.util.Random;

public class IDHandler {

    public int generateID() {
        Random id = new Random();
        return id.nextInt(9000) + 1000;
    }

}
