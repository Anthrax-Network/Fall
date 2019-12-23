package me.hackusatepvp.fall.scoreboard;

import io.github.thatkawaiisam.assemble.AssembleAdapter;
import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.games.GameState;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.util.TimeUtils;
import org.bukkit.entity.Player;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BoardLink implements AssembleAdapter {

    @Override
    public String getTitle(Player player) {
        Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
        if (profile != null) {

            if (profile.isScoreboard()) {
                if (profile.getBoardstyle().equalsIgnoreCase("blue")) {
                    return ("&9&lFateKits");
                } else if (profile.getBoardstyle().equalsIgnoreCase("red")) {
                    return ("&4&lFateKits");
                } else if (profile.getBoardstyle().equalsIgnoreCase("green")) {
                    return ("&a&lFateKits");
                } else if (profile.getBoardstyle().equalsIgnoreCase("white")) {
                    return ("&f&lFateKits");
                }
            } else {
                return null;
            }
        } else {
            return "Loading...";
        }

        return null;
    }

    @Override
    public List<String> getLines(Player player) {
        Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
        NumberFormat format = NumberFormat.getCurrencyInstance();
        if (profile != null) {
            if (profile.isScoreboard()) {
                if (!Fall.getInstance().getGameManager().getGame().contains(player)) {
                    if (profile.getBoardstyle().equalsIgnoreCase("blue")) {
                        final List<String> blue = new ArrayList<>();
                        blue.add("&7&m--------------------");
                        blue.add("&7Kills: &b" + profile.getKills());
                        blue.add("&7Deaths: &b" + profile.getDeaths());
                        blue.add("&7KDR: &b" + profile.getKdr());
                        blue.add("");
                        if (!profile.getDonor().equals("starter")) {
                            blue.add("&7Rank: &b" + profile.getDonor());
                        } else {
                            blue.add("&7Rank: &b" + profile.getActiveQuest());
                        }
                        blue.add("&7Money: &b" + format.format(profile.getBalance()));
                        if (Fall.getInstance().getCombatManager().inCombat(player)) {
                            blue.add("&7Combat: &b" + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getCombatManager().getCombatTime(player)));
                        } else {
                            blue.add("&7Combat: &bfalse");
                        }
                        blue.add("&7Pearl: &b" + "false");
                        blue.add("&7&m--------------------");
                        return blue;
                    } else if (profile.getBoardstyle().equalsIgnoreCase("red")) {
                        final List<String> red = new ArrayList<>();
                        red.add("&7&m--------------------");
                        red.add("&7Kills: &c" + profile.getKills());
                        red.add("&7Deaths: &c" + profile.getDeaths());
                        red.add("&7KDR: &c" + profile.getKdr());
                        red.add("");
                        if (!profile.getDonor().equals("starter")) {
                            red.add("&7Rank: &c" + profile.getDonor());
                        } else {
                            red.add("&7Rank: &c" + profile.getActiveQuest());
                        }
                        red.add("&7Money: &c" + format.format(profile.getBalance()));
                        if (Fall.getInstance().getCombatManager().inCombat(player)) {
                            red.add("&7Combat: &c" + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getCombatManager().getCombatTime(player)));
                        } else {
                            red.add("&7Combat: &cfalse");
                        }
                        red.add("&7Pearl: &c" + "false");
                        red.add("&7&m--------------------");
                        return red;
                    } else if (profile.getBoardstyle().equalsIgnoreCase("green")) {
                        final List<String> green = new ArrayList<>();
                        green.add("&7&m--------------------");
                        green.add("&7Kills: &a" + profile.getKills());
                        green.add("&7Deaths: &a" + profile.getDeaths());
                        green.add("&7KDR: &a" + profile.getKdr());
                        green.add("");
                        if (!profile.getDonor().equals("starter")) {
                            green.add("&7Rank: &a" + profile.getDonor());
                        } else {
                            green.add("&7Rank: &a" + profile.getActiveQuest());
                        }
                        green.add("&7Money: &a" + format.format(profile.getBalance()));
                        if (Fall.getInstance().getCombatManager().inCombat(player)) {
                            green.add("&7Combat: &a" + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getCombatManager().getCombatTime(player)));
                        } else {
                            green.add("&7Combat: &afalse");
                        }
                        green.add("&7Pearl: &a" + "false");
                        green.add("&7&m--------------------");
                        return green;
                    } else if (profile.getBoardstyle().equalsIgnoreCase("white")) {
                        final List<String> white = new ArrayList<>();
                        white.add("&7&m--------------------");
                        white.add("&7Kills: &f" + profile.getKills());
                        white.add("&7Deaths: &f" + profile.getDeaths());
                        white.add("&7KDR: &f" + profile.getKdr());
                        white.add("");
                        if (!profile.getDonor().equals("starter")) {
                            white.add("&7Rank: &a" + profile.getDonor());
                        } else {
                            white.add("&7Rank: &a" + profile.getActiveQuest());
                        }
                        white.add("&7Money: &f" + format.format(profile.getBalance()));
                        if (Fall.getInstance().getCombatManager().inCombat(player)) {
                            white.add("&7Combat: &f" + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getCombatManager().getCombatTime(player)));
                        } else {
                            white.add("&7Combat: &ffalse");
                        }
                        white.add("&7Pearl: &f" + "false");
                        white.add("&7&m--------------------");
                        return white;
                    }
                } else {
                    if (Fall.getInstance().getGameManager().getGameState() == GameState.WAITING) {
                        if (profile.getBoardstyle().equalsIgnoreCase("blue")) {
                            final List<String> waiting = new ArrayList<>();
                            waiting.add("&7&m--------------------");
                            waiting.add("&4Players: &7(&c" + Fall.getInstance().getGameManager().getGame().size() + "&7/&b16&7)");
                            waiting.add("&9Starting: &7" + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getGameTimer().getTotal()));
                            waiting.add("&7&m--------------------");
                            return waiting;
                        }
                        if (profile.getBoardstyle().equalsIgnoreCase("red")) {
                            final List<String> waiting = new ArrayList<>();
                            waiting.add("&7&m--------------------");
                            waiting.add("&4Players: &7(&c" + Fall.getInstance().getGameManager().getGame().size() + "&7/&c16&7)");
                            waiting.add("&4Starting: &7" + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getGameTimer().getTotal()));
                            waiting.add("&7&m--------------------");
                            return waiting;
                        }
                        if (profile.getBoardstyle().equalsIgnoreCase("green")) {
                            final List<String> waiting = new ArrayList<>();
                            waiting.add("&7&m--------------------");
                            waiting.add("&4Players: &7(&c" + Fall.getInstance().getGameManager().getGame().size() + "&7/&a16&7)");
                            waiting.add("&2Starting: &7" + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getGameTimer().getTotal()));
                            waiting.add("&7&m--------------------");
                            return waiting;
                        }
                        if (profile.getBoardstyle().equalsIgnoreCase("white")) {
                            final List<String> waiting = new ArrayList<>();
                            waiting.add("&7&m--------------------");
                            waiting.add("&4Players: &7(&c" + Fall.getInstance().getGameManager().getGame().size() + "&7/&f16&7)");
                            waiting.add("&fStarting: &7" + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getGameTimer().getTotal()));
                            waiting.add("&7&m--------------------");
                            return waiting;
                        }
                    }
                    if (Fall.getInstance().getGameManager().getGameState() == GameState.STARTING) {
                        if (profile.getBoardstyle().equalsIgnoreCase("blue")) {
                            final List<String> starting = new ArrayList<>();
                            starting.add("&7&m--------------------");
                            starting.add("&9Player 1: &7" + Fall.getInstance().getGameTimer().getPlayer1());
                            starting.add("&9Player 2: &7" + Fall.getInstance().getGameTimer().getPlayer2());
                            starting.add("&9Starting &7" + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getGameTimer().getLeft()));
                            starting.add("&7&m--------------------");
                            return starting;
                        }
                        if (profile.getBoardstyle().equalsIgnoreCase("red")) {
                            final List<String> starting = new ArrayList<>();
                            starting.add("&7&m--------------------");
                            starting.add("&4Player 1: &7" + Fall.getInstance().getGameTimer().getPlayer1());
                            starting.add("&4Player 2: &7" + Fall.getInstance().getGameTimer().getPlayer2());
                            starting.add("&4Starting &7" + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getGameTimer().getLeft()));
                            starting.add("&7&m--------------------");
                            return starting;
                        }
                        if (profile.getBoardstyle().equalsIgnoreCase("green")) {
                            final List<String> starting = new ArrayList<>();
                            starting.add("&7&m--------------------");
                            starting.add("&2Player 1: &7" + Fall.getInstance().getGameTimer().getPlayer1());
                            starting.add("&2Player 2: &7" + Fall.getInstance().getGameTimer().getPlayer2());
                            starting.add("&2Starting &7" + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getGameTimer().getLeft()));
                            starting.add("&7&m--------------------");
                            return starting;
                        }
                        if (profile.getBoardstyle().equalsIgnoreCase("white")) {
                            final List<String> starting = new ArrayList<>();
                            starting.add("&7&m--------------------");
                            starting.add("&fPlayer 1: &7" + Fall.getInstance().getGameTimer().getPlayer1());
                            starting.add("&fPlayer 2: &7" + Fall.getInstance().getGameTimer().getPlayer2());
                            starting.add("&fStarting &7" + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getGameTimer().getLeft()));
                            starting.add("&7&m--------------------");
                            return starting;
                        }
                    }
                    if (Fall.getInstance().getGameManager().getGameState() == GameState.GAME) {
                        if (player.getName().equals(Fall.getInstance().getGameTimer().getPlayer1())) {
                            if (profile.getBoardstyle().equalsIgnoreCase("blue")) {
                                final List<String> game = new ArrayList<>();
                                game.add("&7&m--------------------");
                                game.add("&9Game Time &7" + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getGameTimer().getLeft()));
                                game.add("&9Opponent &7" + Fall.getInstance().getGameTimer().getPlayer2());
                                game.add("&7&m--------------------");
                                return game;
                            }
                            if (profile.getBoardstyle().equalsIgnoreCase("red")) {
                                final List<String> game = new ArrayList<>();
                                game.add("&7&m--------------------");
                                game.add("&4Game Time &7" + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getGameTimer().getLeft()));
                                game.add("&4Opponent &7" + Fall.getInstance().getGameTimer().getPlayer2());
                                game.add("&7&m--------------------");
                                return game;
                            }
                            if (profile.getBoardstyle().equalsIgnoreCase("green")) {
                                final List<String> game = new ArrayList<>();
                                game.add("&7&m--------------------");
                                game.add("&2Game Time &7" + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getGameTimer().getLeft()));
                                game.add("&2Opponent &7" + Fall.getInstance().getGameTimer().getPlayer2());
                                game.add("&7&m--------------------");
                                return game;
                            }
                            if (profile.getBoardstyle().equalsIgnoreCase("white")) {
                                final List<String> game = new ArrayList<>();
                                game.add("&7&m--------------------");
                                game.add("&fGame Time &7" + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getGameTimer().getLeft()));
                                game.add("&fOpponent &7" + Fall.getInstance().getGameTimer().getPlayer1());
                                game.add("&7&m--------------------");
                                return game;
                            }
                        } else if (player.getName().equals(Fall.getInstance().getGameTimer().getPlayer1())) {
                            if (profile.getBoardstyle().equalsIgnoreCase("blue")) {
                                final List<String> game = new ArrayList<>();
                                game.add("&7&m--------------------");
                                game.add("&9Game Time &7" + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getGameTimer().getLeft()));
                                game.add("&9Opponent &7" + Fall.getInstance().getGameTimer().getPlayer1());
                                game.add("&7&m--------------------");
                                return game;
                            }
                            if (profile.getBoardstyle().equalsIgnoreCase("red")) {
                                final List<String> game = new ArrayList<>();
                                game.add("&7&m--------------------");
                                game.add("&4Game Time &7" + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getGameTimer().getLeft()));
                                game.add("&4Opponent &7" + Fall.getInstance().getGameTimer().getPlayer1());
                                game.add("&7&m--------------------");
                                return game;
                            }
                            if (profile.getBoardstyle().equalsIgnoreCase("green")) {
                                final List<String> game = new ArrayList<>();
                                game.add("&7&m--------------------");
                                game.add("&2Game Time &7" + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getGameTimer().getLeft()));
                                game.add("&2Opponent &7" + Fall.getInstance().getGameTimer().getPlayer1());
                                game.add("&7&m--------------------");
                                return game;
                            }
                            if (profile.getBoardstyle().equalsIgnoreCase("white")) {
                                final List<String> game = new ArrayList<>();
                                game.add("&7&m--------------------");
                                game.add("&fGame Time &7" + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getGameTimer().getLeft()));
                                game.add("&fOpponent &7" + Fall.getInstance().getGameTimer().getPlayer1());
                                game.add("&7&m--------------------");
                                return game;
                            }
                        } else {
                            if (profile.getBoardstyle().equalsIgnoreCase("blue")) {
                                final List<String> game = new ArrayList<>();
                                game.add("&7&m--------------------");
                                game.add("&9Game Time &7" + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getGameTimer().getLeft()));
                                game.add("&9Player 1: &7" + Fall.getInstance().getGameTimer().getPlayer1());
                                game.add("&9Player 2: &7" + Fall.getInstance().getGameTimer().getPlayer2());
                                game.add("&7&m--------------------");
                                return game;
                            }
                            if (profile.getBoardstyle().equalsIgnoreCase("red")) {
                                final List<String> game = new ArrayList<>();
                                game.add("&7&m--------------------");
                                game.add("&4Game Time &7" + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getGameTimer().getLeft()));
                                game.add("&4Player 1: &7" + Fall.getInstance().getGameTimer().getPlayer1());
                                game.add("&4Player 2: &7" + Fall.getInstance().getGameTimer().getPlayer2());
                                game.add("&7&m--------------------");
                                return game;
                            }
                            if (profile.getBoardstyle().equalsIgnoreCase("green")) {
                                final List<String> game = new ArrayList<>();
                                game.add("&7&m--------------------");
                                game.add("&2Game Time &7" + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getGameTimer().getLeft()));
                                game.add("&2Player 1: &7" + Fall.getInstance().getGameTimer().getPlayer1());
                                game.add("&2Player 2: &7" + Fall.getInstance().getGameTimer().getPlayer2());
                                game.add("&7&m--------------------");
                                return game;
                            }
                            if (profile.getBoardstyle().equalsIgnoreCase("white")) {
                                final List<String> game = new ArrayList<>();
                                game.add("&7&m--------------------");
                                game.add("&2Game Time &7" + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getGameTimer().getLeft()));
                                game.add("&2Player 1: &7" + Fall.getInstance().getGameTimer().getPlayer1());
                                game.add("&2Player 2: &7" + Fall.getInstance().getGameTimer().getPlayer2());
                                game.add("&7&m--------------------");
                                return game;
                            }
                        }
                    }
                }
            } else {
                return null;
            }
            return null;
        } else {
            final List<String> lines = new ArrayList<>();
            lines.add("&7No Work!");
            return lines;
        }
    }
}