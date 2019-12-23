package me.hackusatepvp.fall.combat;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CombatTask extends BukkitRunnable {

    @Override
    public void run() {
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (Fall.getInstance().getCombatManager().inCombat(online)) {
                int left = Fall.getInstance().getCombatManager().getCombat().get(online);
                --left;
                Fall.getInstance().getCombatManager().setCombatTime(online, left);
                if (left == 0) {
                    Fall.getInstance().getCombatManager().removeFromCombat(online);
                    online.sendMessage(StringUtil.format("&aYou are no longer in combat."));
                }
            }
            if (Fall.getInstance().getCombatManager().isPearlCooldown(online)) {
                int left = Fall.getInstance().getCombatManager().getPearCooldown(online);
                --left;
                Fall.getInstance().getCombatManager().setPearlCooldown(online, left);
                if (left == 0) {
                    Fall.getInstance().getCombatManager().removePearlCooldown(online);
                    online.sendMessage(StringUtil.format("&aYour pearl cooldown has expired."));
                }
            }
        }
    }
}
