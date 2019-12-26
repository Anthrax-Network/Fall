package me.hackusatepvp.fall.quests;

import me.hackusatepvp.fall.Fall;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.logging.Logger;

public class QuestListener implements Listener {

    // the purpose of this whole class is to make it easier for you to use events instead of having to create listener classes for each quest.
    // this class calls the methods provided in the quest class so you literally don't have to register or do anything for future quests except for
    // overriding the method in the quest class and just straight up using it.

   /* @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {

        if (event.getEntity().getKiller() == null)
            return;

        LivingEntity victim = event.getEntity();
        Player killer = victim.getKiller();
        Quest activeQuest = Quest.getByName(Fall.getInstance().getProfileManager().getProfile(killer.getUniqueId()).getActiveQuest());

        if (activeQuest != null) {
            activeQuest.onKill(killer, victim, event);
        }
    } */

    /*@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDeath(PlayerDeathEvent event) {
        Logger log = Bukkit.getLogger();
        log.info("Event works");
        if (event.getEntity() == null) {
            log.info("Player no found.");
            return;
        }
        log.info("Event setting player");
        Player victim = event.getEntity();
        if (victim.getKiller() == null) {
            log.info("Killer no found.");
            return;
        }

        log.info("Event player found");
        log.info("Event setting killer");
        Player killer = victim.getKiller();
        log.info("Event killer found");
        Quest quest = Quest.getByName(Fall.getInstance().getProfileManager().getProfile(killer.getUniqueId()).getActiveQuest());
        log.info("Event Quest found.");
        if (quest != null) {
            quest.onKill(killer, victim, event);
            log.info("Event onKilled called.");
        }
    } */

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof  LivingEntity)) {
            return;
        }
        if (!(event.getEntity() instanceof LivingEntity)) {
            return;
        }
        LivingEntity damager = (LivingEntity) event.getDamager();
        LivingEntity victim = (LivingEntity) event.getEntity();

        if (damager instanceof Player) {
            Player playerDamager = (Player) damager;
            Quest quest = Quest.getByName(Fall.getInstance().getProfileManager().getProfile(playerDamager.getUniqueId()).getActiveQuest());

            if (quest != null) {
                quest.onAttack(playerDamager, victim, event);
            }
        }

        if (victim instanceof Player) {
            Player playerVictim = (Player) victim;
            Quest activeQuest = Quest.getByName(Fall.getInstance().getProfileManager().getProfile(playerVictim.getUniqueId()).getActiveQuest());

            if (activeQuest != null) {
                activeQuest.onAttacked(damager, playerVictim, event);
            }
        }
    }
}