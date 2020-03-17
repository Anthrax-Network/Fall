# Changelog

## Update [1.3]()
#### Added/Changes
- Re-coded [Info](https://github.com/Anthrax-Network/Fall/tree/master/src/main/java/me/hackusatepvp/fall/info)
- Added [RenameCommand](https://github.com/Anthrax-Network/Fall/blob/master/src/main/java/me/hackusatepvp/fall/command/RenameCommand.java)
- Tags and ChatColor have been re-worked
- Added Info into staff items
- Added more Tags
#### Fxied
- Fixed member stacking with clans; removes the player from the clan list when they disconnect
- Fxied clan online count
- Fixed multiple bounty messages
- Fixed [StaffItemsListener](https://github.com/Anthrax-Network/Fall/blob/master/src/main/java/me/hackusatepvp/fall/staff/listeners/StaffItemsListener.java) 
- Staff items will only work if player is in staffmode
- Fixed /clan join
- Disconnecting no longer unloads the entire clan. It will only unload the clan when there are no members online.
- Fixed clan invite
- Fixed the clan member loop in [TabLink](https://github.com/Anthrax-Network/Fall/blob/master/src/main/java/me/hackusatepvp/fall/tab/TabLink.java)
#### Incomplete
- Classes is incomplete
- Colors is incomplete
- Shop is incomplete
- Vouchers is incomplete
#### TODOS
- Get Clans in a working state
- Start on classes
- Add more colors for players to chose from
- Add more Tags for players to chose from
- ~~Test the new changes in Staff~~
- Start debugging Shop

## Update [1.2](Coming soon)
#### Added
- Added [SurvivalCommand](https://github.com/Anthrax-Network/Fall/blob/1.2/src/main/java/me/hackusatepvp/fall/command/SurvivalCommand.java)
#### Fixed
- All clan members on tbalist no longer appear on one line
- Fixed tablist with clan members; they will no longer appear more than once
- HideStaff will now only hide players who are in staffemode
#### Incomplete
- Classes is incomplete
- Colors is incomplete
- Shop is incomplete
- Vouchers is incomplete
#### TODOS
- Get Clans in a working state
- Start on classes
- Add more colors for players to chose from
- Add more Tags for players to chose from
- ~~Test the new changes in Staff~~
- Start debugging Shop

## Update [1.1](https://github.com/Anthrax-Network/Fall/releases/tag/1.1)
#### Added
- MySQL task update
- Added [HideStaffCommand](https://github.com/Anthrax-Network/Fall/blob/1.1/src/main/java/me/hackusatepvp/fall/staff/commands/HideStaffCommand.java) (untested)
#### Fxied
- Clan bugs (disbanding, creating, logical errors in [ClanManager](https://github.com/Anthrax-Network/Fall/blob/1.1/src/main/java/me/hackusatepvp/fall/clans/ClanManager.java))
- Fixed erros in [TabLink](https://github.com/Anthrax-Network/Fall/blob/1.1/src/main/java/me/hackusatepvp/fall/tab/TabLink.java) dealing with clans
- Fixed problems with Profile unloading when player disconnects
- Fxied clan issues unloading a clan from the server
- Fixed logical errors in [StaffManager](https://github.com/Anthrax-Network/Fall/blob/1.1/src/main/java/me/hackusatepvp/fall/staff/managers/StaffManager.java)
#### Bugs
- Clan bugs may still exist 
- Changing some settings via /settings will break the player's profile
- Tab spams console when joining the server within 20 seconds of startup
- Bows do not work
#### Incomplete
- Classes is incomplete
- Colors is incomplete
- Shop is incomplete
- Vouchers is incomplete
#### TODOS
- Get Clans in a working state
- Start on classes
- Add more colors for players to chose from
- Add more Tags for players to chose from
- Test the new changes in Staff
- Start debugging Shop# Changelog
