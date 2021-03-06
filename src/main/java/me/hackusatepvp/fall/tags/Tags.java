package me.hackusatepvp.fall.tags;

import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.tags.common.impl.*;
import me.hackusatepvp.fall.tags.legendary.impl.ExcaliburTag;
import me.hackusatepvp.fall.tags.legendary.impl.HowlTag;
import me.hackusatepvp.fall.tags.legendary.impl.LegendTag;
import me.hackusatepvp.fall.tags.legendary.impl.ReaperTag;
import me.hackusatepvp.fall.tags.rare.impl.HeroTag;
import me.hackusatepvp.fall.tags.rare.impl.KillerTag;
import me.hackusatepvp.fall.tags.rare.impl.ShrugTag;
import me.hackusatepvp.fall.tags.rare.impl.VillainTag;
import me.hackusatepvp.fall.tags.special.impl.ChristmasTag;
import me.hackusatepvp.fall.tags.special.impl.EasterTag;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class Tags {

    private static Map<String, Tags> byName = new HashMap<>();

    private static final Tags HEART_TAG = new HeartTag();
    private static final Tags SOLDIER_TAG = new SoldierTag();
    private static final Tags CAT_TAG = new CatTag();
    private static final Tags DAWN_TAG = new DawnTag();
    private static final Tags DOG_TAG = new DogTag();
    private static final Tags OOF_TAG = new OofTag();
    private static final Tags UWU_TAG = new UwuTag();

    private static final Tags HERO_TAG = new HeroTag();
    private static final Tags KILLER_TAG = new KillerTag();
    private static final Tags SHRUG_TAG = new ShrugTag();
    private static final Tags VILLAIN_TAG = new VillainTag();

    private static final Tags LEGEND_TAG = new LegendTag();
    private static final Tags HOWL_TAG = new HowlTag();
    private static final Tags REAPER_TAG = new ReaperTag();
    private static final Tags EXCALIBUR_TAG =  new ExcaliburTag();

    private static final Tags CHRISTMAS_TAG = new ChristmasTag();
    private static final Tags EASTER_TAG = new EasterTag();

    private String name;

    public Tags(String name) {
        this.name = name;

        byName.put(name, this);
    }

    public static Tags getByName(String name) {
        return byName.get(name);
    }

    public abstract String getName();

    public abstract String getPrefix();

    public abstract String getPermission();

    public abstract boolean isEnabled();

    public abstract ItemStack getIcon();

    public void onClick(Player player, Profile profile, String tag) {

    }
}
