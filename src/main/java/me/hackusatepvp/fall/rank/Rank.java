package me.hackusatepvp.fall.rank;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

// @Data makes a getter and a setter, as well as a constructor with all fields, I just want the string as a parameter thats why I did my own.

@Data
public class Rank {

    // ik you're tired, and so am i but one last example is a very simple rank systen, notice how it's VERY VERY similar to the kits? that's what OOP is lmao
    //Do you think you can show me how I can set a player to a rank with this contrcutor so I have more to go off of

    // yeah I gotchu, I wouidn't set the rank through the constructor though, let me show u how I would do it.
    //Okay making this info command should be easy
    // btw the command library I imported and used i've literally never used before but it seems very very good because look

    private final String name;
    private String prefix, suffix;
    private List<String> permissions;

    public Rank(String name) {
        this.name = name;

        permissions = new ArrayList<>();
    }
}
