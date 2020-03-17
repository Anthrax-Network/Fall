# Fall

## Description
Fall is a competitive kitpvp plugin. 

## Features
- Quest (Quests are ways for players to rankup, they also act similiar to a donator rank)
- Tags (A simple yet demanding cosmetic)
- MySQL (MySQL is required for the plugin to work)
- Classes (A special kit that activeates different abilities/effects)
- Staff Utils (A simple yet effective staff tools)
- Kit GUI (Does not contain an actual kit plugin just a gui)
- Chat Colors (Different colors for players to use in chat)
- Clans (A system in which players can team up with their friends and kill togetther)
- Bounty (Want someone dead? Pay the right price)

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

## Quests
This is a deatiled explantation on how quests works. First quests is and is not a donator rank at the same time. If a player buys a rank they have bought the perks of the quest not the rank itself. Meaning, if I buy Fate Rank when I'm at rider quest my quest will still be rider and I wil still have to rank up through the quests in order to get all of the quest items.

## Ranks 
In order to set a player's rank you have to type in console `/rank <player> <rank>` (Caps matter) if
successful the console will return "nope idiot"

Note: There will be additional permission nodes you will have to add.

## Staff 
In order to set a player as staff you have to type in console `/console staff <player> ` (You will have to change the uuid to your uuid or remove that statement in [ConsoleCommand](https://github.com/Anthrax-Network/Fall/blob/1.2/src/main/java/me/hackusatepvp/fall/command/ConsoleCommand.java)) 
```javascript
if (player.getUniqueId().equals(UUID.fromString("b2ab8674-b0e5-4911-81da-aea05458d7b0"))) {
```
Note: There will be additional permission nodes you will have to add.

## Download
You can now download the latest releases [here](https://github.com/Anthrax-Network/Fall/releases)
