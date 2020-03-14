package me.hackusatepvp.fall;

import com.bizarrealex.azazel.Azazel;
import io.github.thatkawaiisam.assemble.Assemble;
import io.github.thatkawaiisam.assemble.AssembleStyle;
import lombok.Getter;
import me.hackusatepvp.fall.bounty.BountyCommand;
import me.hackusatepvp.fall.bounty.BountyListCommand;
import me.hackusatepvp.fall.bounty.BountyManager;
import me.hackusatepvp.fall.clans.ClanListener;
import me.hackusatepvp.fall.clans.ClanManager;
import me.hackusatepvp.fall.clans.ClansCommand;
import me.hackusatepvp.fall.classes.ClassesGUI;
import me.hackusatepvp.fall.classes.ClassesManager;
import me.hackusatepvp.fall.classes.ClassesTask;
import me.hackusatepvp.fall.colors.ColorGUI;
import me.hackusatepvp.fall.colors.ColorListener;
import me.hackusatepvp.fall.combat.CombatManager;
import me.hackusatepvp.fall.combat.CombatTask;
import me.hackusatepvp.fall.command.*;
import me.hackusatepvp.fall.configs.BountyConfiguration;
import me.hackusatepvp.fall.economy.EconomyManager;
import me.hackusatepvp.fall.games.GameManager;
import me.hackusatepvp.fall.games.GameState;
import me.hackusatepvp.fall.games.GameTimer;
import me.hackusatepvp.fall.games.PlayerManager;
import me.hackusatepvp.fall.games.sumo.SumoCommand;
import me.hackusatepvp.fall.games.sumo.SumoListener;
import me.hackusatepvp.fall.info.InfoGUI;
import me.hackusatepvp.fall.info.InfoManager;
import me.hackusatepvp.fall.kits.KitsGUI;
import me.hackusatepvp.fall.kits.KitsListener;
import me.hackusatepvp.fall.listeners.*;
import me.hackusatepvp.fall.profile.ProfileListener;
import me.hackusatepvp.fall.profile.ProfileManager;
import me.hackusatepvp.fall.quests.QuestListener;
import me.hackusatepvp.fall.quests.QuestTask;
import me.hackusatepvp.fall.scoreboard.BoardLink;
import me.hackusatepvp.fall.settings.SettingsGUI;
import me.hackusatepvp.fall.shop.ShopGUI;
import me.hackusatepvp.fall.shop.ShopListener;
import me.hackusatepvp.fall.staff.commands.HideStaffCommand;
import me.hackusatepvp.fall.staff.commands.StaffCommand;
import me.hackusatepvp.fall.staff.commands.VanishCommand;
import me.hackusatepvp.fall.staff.listeners.StaffItemsListener;
import me.hackusatepvp.fall.staff.listeners.StaffPatch;
import me.hackusatepvp.fall.staff.managers.StaffManager;
import me.hackusatepvp.fall.tab.TabLink;
import me.hackusatepvp.fall.tags.TagsGUI;
import me.hackusatepvp.fall.tags.TagsListener;
import me.hackusatepvp.fall.util.IDHandler;
import me.hackusatepvp.fall.util.MySQL;
import me.hackusatepvp.fall.util.SaveTask;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.logging.Logger;

@Getter
public final class Fall extends JavaPlugin {
    private final Logger log = Bukkit.getLogger();
    private static Permission perms = null;
    private ProfileManager profileManager;
    private InfoGUI infoGUI;
    private InfoManager infoManager;
    private ClanManager clanManager;
    private SettingsGUI settingsGUI;
    private MySQL mySQL;
    private QuestTask questTask;
    private EconomyManager economyManager;
    private IDHandler idHandler;
    private GameManager gameManager;
    private GameTimer gameTimer;
    private CombatManager combatManager;
    private CombatTask combatTask;
    private ClassesManager classesManager;
    private ClassesTask classesTask;
    private ClassesGUI classesGUI;
    private ShopGUI shopGUI;
    private TagsGUI tagsGUI;
    private KitsGUI kitsGUI;
    private ColorGUI colorGUI;
    private PlayerManager playerManager;
    private BountyConfiguration bountyConfiguration;
    private BountyManager bountyManager;
    private StaffManager staffManager;
    private SaveTask saveTask;
    private static Fall instance;

    public void onEnable() {
        instance = this;
        log.info("[Fall] starting plugin...");
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        this.saveDefaultConfig();
        this.reloadConfig();
        if (!setupPermissions()) {
            log.severe("[Fall] No vault supported permissions system found.");
            getServer().getPluginManager().disablePlugin(this);
        } else {
            log.info("[Fall] starting managers...");
            this.registerManagers();
            log.info("[Fall] starting mysql...");
            mySQL.profiles();
            log.info("[Fall] loading events...");
            this.registerListeners();
            log.info("[Fall] loading commands...");
            this.registerCommands();
            log.info("[Fall] mysql patches...");
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player != null) {
                    if (!this.getProfileManager().existsUser(player)) {
                        this.getProfileManager().createStatsUser(player);
                        this.getProfileManager().load(player);
                    } else {
                        this.getProfileManager().load(player);
                    }
                }
            }
            log.info("[Fall] Loading scoreboard and tab...");
            getS();
            this.getGameManager().setGameState(GameState.STOP);
            log.info("[Fall] done!");
        }
    }

    private void registerManagers() {
        mySQL = new MySQL(this);
        profileManager = new ProfileManager();
        infoGUI = new InfoGUI();
        infoManager = new InfoManager();
        clanManager = new ClanManager();
        settingsGUI = new SettingsGUI();
        economyManager = new EconomyManager();
        questTask = new QuestTask();
        questTask.runTaskTimer(this, 0, 20);
        idHandler = new IDHandler();
        gameManager = new GameManager();
        gameTimer = new GameTimer();
        combatManager = new CombatManager();
        combatTask = new CombatTask();
        combatTask.runTaskTimer(this, 0 ,20);
        classesManager = new ClassesManager();
        classesTask = new ClassesTask();
        classesTask.runTaskTimer(this, 0, 20);
        classesGUI = new ClassesGUI();
        saveTask = new SaveTask();
        saveTask.runTaskTimer(this, 0, 20);
        shopGUI = new ShopGUI();
        tagsGUI = new TagsGUI();
        kitsGUI = new KitsGUI();
        colorGUI = new ColorGUI();
        playerManager = new PlayerManager();
        staffManager = new StaffManager();
        bountyManager = new BountyManager();
    }

    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            this.getProfileManager().unload(player);
        }
        mySQL.disconnectProfiles();
        mySQL.disconnectClans();
        instance = null;
    }

    private void getS() {
        Assemble assemble = new Assemble(this, new BoardLink());
        assemble.setTicks(2);
        assemble.setAssembleStyle(AssembleStyle.MODERN);
        new Azazel(this, new TabLink());
    }


    private void registerListeners() {
        Arrays.asList(new ProfileListener(), new ServerListener(), new InfoGUI(), new DeathEvevnt(), new ChatEvent(), new SettingsGUI(), new QuestListener(), new PlayerListener(), new ClanListener(),
                new PatchEvent(), new SumoListener(), new ClassesGUI(), new ShopListener(), new TagsListener(), new KitsListener(), new ColorListener(), new StaffPatch(), new StaffItemsListener())
                .forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    private void registerCommands() {
        getCommand("bounty").setExecutor(new BountyCommand());
        getCommand("bountylist").setExecutor(new BountyListCommand());
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("clan").setExecutor(new ClansCommand());
        getCommand("class").setExecutor(new ClassCommand());
        getCommand("console").setExecutor(new ConsoleCommand());
        getCommand("color").setExecutor(new ColorCommand());
        getCommand("combat").setExecutor(new CombatCommand());
        getCommand("creative").setExecutor(new CreativeCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("eat").setExecutor(new EatCommand());
        getCommand("economy").setExecutor(new EconomyCommand());
        getCommand("enderchest").setExecutor(new EnderchestCommand());
        getCommand("fall").setExecutor(new FallCommand());
        getCommand("fixhand").setExecutor(new FixHandCommand());
        getCommand("god").setExecutor(new GodCommand());
        getCommand("hidestaff").setExecutor(new HideStaffCommand());
        getCommand("info").setExecutor(new InfoCommand());
        getCommand("kits").setExecutor(new KitsCommand());
        getCommand("level").setExecutor(new LevelCommand());
        getCommand("list").setExecutor(new ListCommand());
        getCommand("message").setExecutor(new MessageCommand());
        getCommand("nick").setExecutor(new NickCommand());
        getCommand("pay").setExecutor(new PayCommand());
        getCommand("ping").setExecutor(new PingCommand());
        getCommand("quest").setExecutor(new QuestCommand());
        getCommand("rank").setExecutor(new RankCommand());
        getCommand("ranks").setExecutor(new RanksCommand());
        getCommand("reply").setExecutor(new ReplyCommand());
        getCommand("rules").setExecutor(new RulesCommand());
        getCommand("settings").setExecutor(new SettingsCommand());
        getCommand("shop").setExecutor(new ShopCommand());
        getCommand("staff").setExecutor(new StaffCommand());
        getCommand("stats").setExecutor(new StatsCommand());
        getCommand("sumo").setExecutor(new SumoCommand());
        getCommand("survival").setExecutor(new SurvivalCommand());
        getCommand("tags").setExecutor(new TagsCommand());
        getCommand("teleport").setExecutor(new TeleportCommand());
        getCommand("togglemessages").setExecutor(new ToggleMessageCommand());
        getCommand("togglescoreboard").setExecutor(new ToggleScoreboardCommand());
        getCommand("vanish").setExecutor(new VanishCommand());
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public static Permission getPermissions() {
        return perms;
    }


    public static Fall getInstance() {
        return instance;
    }
}
