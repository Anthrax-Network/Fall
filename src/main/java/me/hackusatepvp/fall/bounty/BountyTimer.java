package me.hackusatepvp.fall.bounty;

import lombok.Getter;
import lombok.Setter;
import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.logging.Logger;

@Getter @Setter
public class BountyTimer extends BukkitRunnable {
    private int left = 0;
    private int total = 0;
    private boolean isBroadcasting;
    private boolean isRunning;
    private String player;

    @Override
    public void run() {
        Logger log = Bukkit.getLogger();
        if (!isRunning) {
            --left;
        }
        if (left == 0) {
            log.info("Started");
            if (Bukkit.getOnlinePlayers().size() >= 1) {
                Iterator iterator = Bukkit.getOnlinePlayers().iterator();
                int count = 0;
                while (iterator.hasNext()) {
                    count++;
                    Player player = (Player) iterator.next();
                    if (!Fall.getInstance().getCombatManager().inCombat(player)) {
                        iterator.remove();
                    } else {
                        Fall.getInstance().getBountyManager().addBounty(player.getUniqueId(), new Bounty(player.getUniqueId()));
                        this.player = player.getName();
                        this.total = 120;
                        this.broadcast();
                    }
                    if (count == Bukkit.getOnlinePlayers().size()) {
                        Player random = Bukkit.getOnlinePlayers().stream().findAny().get().getPlayer();
                        Fall.getInstance().getBountyManager().addBounty(random.getUniqueId(), new Bounty(random.getUniqueId()));
                        this.player = random.getName();
                        this.total = 120;
                        this.broadcast();
                    }
                }
            }
        }
    }

    public void broadcast() {
        if (isBroadcasting()) {
            --total;
        }
         if (total == 0) {
             Bukkit.getOnlinePlayers().forEach(instance -> instance.sendMessage(StringUtil.format("&4&l" + player + " &7has a &a500$ &7bounty on them!")));
             this.total = 30;
         }
    }
}
