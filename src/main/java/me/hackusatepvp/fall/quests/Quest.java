package me.hackusatepvp.fall.quests;

import lombok.Getter;
import lombok.Setter;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.quests.impl.*;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public abstract class Quest {

    private static Map<String, Quest> byName = new HashMap<>();

    //registers all things in impl aka quests
    public static final Quest STARTER_QUEST = new StarterQuest();
    public static final Quest MASTER_QUEST = new MasterQuest();
    public static final Quest RIDER_QUEST = new RiderQuest();
    public static final Quest CASTER_QUEST = new CasterQuest();
    public static final Quest ARCHER_QUEST = new ArcherQuest();
    public static final Quest ASSASSIN_QUEST = new AssassinQuest();
    public static final Quest LANCER_QUEST = new LancerQuest();
    public static final Quest BERSERKER_QUEST = new BerserkerQuest();
    public static final Quest SABER_QUEST = new SaberQuest();
    public static final Quest FATE_QUEST = new FateQuest();

    private String name;

    public Quest(String name) {
        this.name = name;

        byName.put(name, this);
    }


    public static Quest getByName(String name) {
        return byName.get(name);
    }


    public static Quest getActiveQuest(Profile profile) {
        return Quest.getByName(profile.getActiveQuest());
    }

    // Non-optional methods, must implement for each quest/task but you can still return null if you don't want one of these, like the getNext, you can return null for no quest after this one

    public abstract Quest getNext(Profile profile);

    public abstract ItemStack getIcon();

    public abstract String getCompletion(Player player, Object data);

    public abstract int getGoal();

    public abstract Double getProgress(Profile profile);

    public abstract String rankUpMessage();

    public abstract String getPrefix();

    // Optional methods for each quest/task, you can override them or not, your choice


    public void onTick(Player player, Profile profile) {

    }

    public void onKill(Player killer, LivingEntity victom, EntityDeathEvent event) {

    }

    public void onAttack(Player attacker, LivingEntity victom, EntityDamageByEntityEvent event) {

    }

    public void onAttacked(LivingEntity attacker, Player victim, EntityDamageByEntityEvent event) {

    }
}
