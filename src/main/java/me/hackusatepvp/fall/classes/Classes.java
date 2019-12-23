package me.hackusatepvp.fall.classes;

import me.hackusatepvp.fall.classes.impl.*;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.quests.impl.StarterQuest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public abstract class Classes {
    private static HashMap<String, Classes> byName = new HashMap<>();

    public static final Classes MASTER_CLASS = new MasterClass();
    public static final Classes RIDER_CLASS = new RiderClass();
    public static final Classes CASTER_CLASS = new CasterClass();
    public static final Classes ARCHER_CLASS = new ArcherClass();
    public static final Classes ASSASSIN_CLASS = new AssassinClass();
    public static final Classes LANCER_CLASS = new LancerClass();
    public static final Classes BERSERKER_CLASS = new BerserkerClass();
    public static final Classes SABER_CLASS = new SaberClass();
    public static final Classes FATE_CLASS = new FateClass();


    private String name;

    public Classes(String name) {
        this.name = name;

        byName.put(name, this);
    }

    public static Classes getByName(String name) {
        return byName.get(name);
    }

    public abstract String getName();

    public abstract ItemStack getIcon();

    public abstract List<ItemStack> getItems();

    public abstract String getPrefix();

    public abstract Integer getCooldown();

    public abstract String getPermission();

    public void onTick(Player player, Profile profile) {}

    public void onItemUse(Player player, Classes classes, ItemStack itemStack) {}
}
