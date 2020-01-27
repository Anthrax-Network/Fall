package me.hackusatepvp.fall.listeners;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.quests.Quest;
import me.hackusatepvp.fall.staff.Staff;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class PlayerListener implements Listener {

    private HashMap<Player, Integer> pearls = new HashMap<>();
// i'm making a new profile object for the player on join, obviously you have to store those profile objects in some kinda database or all info will be lost on plugin disable
    // but this is just an example, I create a new profile object and give it a uuid of the player
      /*  player.sendMessage(ChatColor.GREEN + "Creating profile...");


        */




   @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
       Player player = event.getPlayer();
       UUID uuid = player.getUniqueId();
       if (Fall.getInstance().getProfileManager().hasProfile(player.getUniqueId())) {
           Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
           if (profile.getBoardstyle() == null) {
               player.sendMessage(ChatColor.RED + "I'm sorry, but your profile failed to save your scoreboard settings, it has been reverted to default.");
               profile.setBoardstyle("red");
           }
           if (profile.getActiveQuest() != null) {
               switch (profile.getActiveQuest()) {
                   case "starter":
                       profile.setActiveQuest(Quest.STARTER_QUEST.getName());
                       break;
                   case "master":
                       profile.setActiveQuest(Quest.MASTER_QUEST.getName());
                   case "rider":
                       profile.setActiveQuest(Quest.RIDER_QUEST.getName());
                       break;
                   case "caster":
                       profile.setActiveQuest(Quest.CASTER_QUEST.getName());
                       break;
                   case "archer":
                       profile.setActiveQuest(Quest.ARCHER_QUEST.getName());
                       break;
                   case "assassin":
                       profile.setActiveQuest(Quest.ASSASSIN_QUEST.getName());
                       break;
                   case "lancer":
                       profile.setActiveQuest(Quest.LANCER_QUEST.getName());
                       break;
                   case "berserker":
                       profile.setActiveQuest(Quest.BERSERKER_QUEST.getName());
                       break;
                   case "saber":
                       profile.setActiveQuest(Quest.SABER_QUEST.getName());
                       break;
                   case "fate":
                       profile.setActiveQuest(Quest.FATE_QUEST.getName());
                       break;
               }
               return;
           }
           if (!profile.getNick().equalsIgnoreCase("null")) {
               player.sendMessage(ChatColor.GREEN + "Your nick has been set.");
               player.setCustomName(profile.getNick());
               player.setCustomNameVisible(true);
           }
           player.sendMessage(ChatColor.RED + ChatColor.UNDERLINE.toString() + "Due to a database issue, your profile is broken. After it is fixed the server will automatically kick you, please do not log out until then.");
           profile.setQuest("starter");
           profile.setActiveQuest("starter");
           Bukkit.getScheduler().scheduleSyncDelayedTask(Fall.getInstance(), new BukkitRunnable() {
               @Override
               public void run() {
                   player.kickPlayer("Join back!");
               }
           }, 60L);
       }
   }

   @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
       if (event.getEntity() instanceof Player) {
           Player player = (Player) event.getEntity();
           if (event.getDamager() instanceof Player) {
               Player damager = (Player) event.getDamager();
               Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
               Profile tar = Fall.getInstance().getProfileManager().getProfile(damager.getUniqueId());
               if (profile.getClan().equals(tar.getClan()) && !profile.getClan().equals("null")) {
                   player.sendMessage(StringUtil.format("&7You cannot attack &c&n" + player.getName() + "&c."));
               } else {
                   Fall.getInstance().getCombatManager().addToCombat(player);
                   Fall.getInstance().getCombatManager().addToCombat(damager);
                   if (!Fall.getInstance().getCombatManager().inCombat(player)) {
                       player.sendMessage(StringUtil.format("&cYou are now in combat."));
                   }
                   if (!Fall.getInstance().getCombatManager().inCombat(damager)) {
                       damager.sendMessage(StringUtil.format("&cYou are now in combat."));
                   }
               }
           }
       }
   }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();
        if (projectile instanceof EnderPearl) {
            EnderPearl enderPearl = (EnderPearl) projectile;
            ProjectileSource source = enderPearl.getShooter();
            if (source instanceof Player) {
                Player shooter = (Player) source;
                if (Fall.getInstance().getCombatManager().isPearlCooldown(shooter)) {
                    shooter.sendMessage(StringUtil.format("&cYou cannot do that for another " + Fall.getInstance().getCombatManager().getPearCooldown(shooter) + "s"));
                    event.setCancelled(true);
                    shooter.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
                } else {
                    Fall.getInstance().getCombatManager().setPearlCooldown(shooter, 16);
                }
            }
        }
    }

   @EventHandler
    public void onQuit(PlayerQuitEvent event) {
       Player player = event.getPlayer();
       if (Fall.getInstance().getCombatManager().inCombat(player)) {
           player.getInventory().removeItem(player.getInventory().getContents());
       }
   }
}
