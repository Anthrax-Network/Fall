# Fall

## Description
Fall is a competitive kitpvp plugin. 

## Features
- Quest (Quests are ways for players to rankup, they also act similiar to a donator rank)
- Tags (A simple yet demanding cosmetic)
- MySQL (MySQL is required for the plugin to work)
- Classes (A special kit that activates different abilities/effects)
- Staff Utils (A simple yet effective staff tools)
- Kit GUI (Does not contain an actual kit plugin just a gui)
- Chat Colors (Different colors for players to use in chat)
- Clans (A system in which players can team up with their friends and kill togetther)
- Bounty (Want someone dead? Pay the right price)
- Economy (Contains its own economy system)

## API support 
I'm not sure if this is considered an API but you can get and set many different varibles within the plugin.
Use `Fall.getInstance()` to view all the managers you can access.

## Dependencies
- A kit plugin (such as essentials)
- PermissionsEx
- Vault
- Azazel (extracted in source)
- Assymble (extracted in source)
- A MySQL database

## Economy
Fall uses its own economy plugin system. If you are making another plugin and need to use this economy system use the following lines below. You can also add, subtract and set the balance using the EconomyManager.
```java
public Double getBalance(Player player) {
  Profile profile = Fall.getInstance().getProfileManager(player.getUniqueId());
  return Fall.getInstance().getEconomyManager().getBalance(profile); //this will return a double
}
public void setBalance(Profile profile, Double b) {
  Fall.getInstance().getEconomyManager().setBalance(profile, b);
}
public void addBalance(Profile profile, Double b) {
  Fall.getInstance().getEconomyManager().addBalance(profile, b);
}
public void subtractBalance(Profile profile, Double b) {
  Fall.getInstance().getEconomyManager().subtractBalance(profile, b);
}
```

## Quests
This is a deatiled explantation on how quests work. First quests is and is not a donator rank at the same time. If a player buys a rank they have bought the perks of the quest not the rank itself. Meaning, if I buy Fate Rank when I'm at rider quest my quest will still be rider and I wil still have to rank up through the quests in order to get all of the quest items, the diffrences is my rank on Scoreboard, Tablistm and Chat will appear as Fate; I will also get the fate kit.

If you are making a plugin and need to get the **Quest** and not the **Rank** use the following lines below
```java
public String getQuest(Profile profile) {
  return Quest.getByName(profile.getActiveQuest()).getName();
}
```

## Ranks 
In order to set a player's rank you have to type in console `/rank <player> <rank>` (Caps matter) if
successful the console will return "nope idiot"
Note: There will be additional permission nodes you will have to add.

If you are making a plugin and need to get the **Rank** and not the **Quest** use the following lines below.
```java
public String getRank(Player player) {
  Profile profile = Fall.getInstance().getProfileManager(player.getUniqueId());
  return profile.getDonor(); //this will return a string which is the rank
}
```

## Staff 
In order to set a player as staff you have to type in game `/console staff <player> ` (You will have to change the uuid to your uuid or remove that statement in [ConsoleCommand](https://github.com/Anthrax-Network/Fall/blob/1.2/src/main/java/me/hackusatepvp/fall/command/ConsoleCommand.java)) 
```javascript
if (player.getUniqueId().equals(UUID.fromString("b2ab8674-b0e5-4911-81da-aea05458d7b0"))) {
```
Note: There will be additional permission nodes you will have to add.

## Download
You can now download the latest releases [here](https://github.com/Anthrax-Network/Fall/releases)
