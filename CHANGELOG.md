# Changelog

## Update [1.1](https://github.com/Anthrax-Network/Fall/releases/tag/1.1)
###### Added
- Added [SaveTask](https://github.com/Anthrax-Network/Fall/blob/master/src/main/java/me/hackusatepvp/fall/util/MySQL.java)
- Added [HideStaffCommand](https://github.com/Anthrax-Network/Fall/blob/1.1/src/main/java/me/hackusatepvp/fall/staff/commands/HideStaffCommand.java) (untested)
###### Fxied
- Clan bugs (disbanding, creating, logical errors in [ClanManager](https://github.com/Anthrax-Network/Fall/blob/1.1/src/main/java/me/hackusatepvp/fall/clans/ClanManager.java))
- Fixed erros in [TabLink](https://github.com/Anthrax-Network/Fall/blob/1.1/src/main/java/me/hackusatepvp/fall/tab/TabLink.java) dealing with clans
- Fixed problems with Profile unloading when player disconnects
- Fxied clan issues unloading a clan from the server
- Fixed logical errors in [StaffManager](https://github.com/Anthrax-Network/Fall/blob/1.1/src/main/java/me/hackusatepvp/fall/staff/managers/StaffManager.java)
###### Bugs
- Clan bugs may still exist 
- Changing some settings via /settings will break the player's profile
- Tab spams console when joining the server within 20 seconds of startup
- Bows do not work
- [SaveTask](https://github.com/Anthrax-Network/Fall/blob/master/src/main/java/me/hackusatepvp/fall/util/MySQL.java) will produce errors randomly (still looking into it)
###### Incomplete
- Classes is incomplete
- Colors is incomplete
- Shop is incomplete
- Vouchers is incomplete
###### TODOS
- Get Clans in a working state
- Start on classes
- Add more colors for players to chose from
- Add more Tags for players to chose from
- Test the new changes in Staff
- Start debugging Shop

## Update [1.2](https://github.com/Anthrax-Network/Fall/releases/tag/1.2)
###### Added
- Added [SurvivalCommand](https://github.com/Anthrax-Network/Fall/blob/1.2/src/main/java/me/hackusatepvp/fall/command/SurvivalCommand.java)
###### Fixed
- All clan members on tablist no longer appear on one line. They will now be sepreated on multiple lines.
- Fixed tablist with clan members; they will no longer appear more than once
- HideStaff will now only hide staff-members who are in staffemode
###### Incomplete
- Classes is incomplete
- Colors is incomplete
- Shop is incomplete
- Vouchers is incomplete
###### TODOS
- Get Clans in a working state
- Start on classes
- Add more colors for players to chose from
- Add more Tags for players to chose from
- ~~Test the new changes in Staff~~
- Start debugging Shop
