# Documentation

## `public class Hypixel`

 * **Author:** Sebastian Heinzenburger <Sebastian.Heinzenburger @ gmail.com>
 * **Version:** 1.0
 * **Since:** 1.0

## `public String getJSONPlayerData(String name)`

Reads the data returned by the hypixel server for a given player name.

 * **Parameters:** `name` — the name of the player the data is requestet about
 * **Returns:** the data as JSON String

## `public void updatePlayerData(Player player)`

Sets the Player object's JSONData to the JSONData returned by the server.

 * **Parameters:** `player` — the PLayer object to update

## `public static class Player`

 * **Author:** Sebastian Heinzenburger <Sebastian.Heinzenburger @ gmail.com>
 * **Version:** 1.0
 * **Since:** 1.0

## `Player(String name)`

Creates a Player object

 * **Parameters:** `name` — displayname of the player

## `public long getLastLoginTimeStamp()`

parse JSONData to get the UNIX-timestamp of the latest login.

 * **Returns:** long holding the UNIX-timestamp of the latest login.

## `public long getLastLogoutTimeStamp()`

parse JSONData to get the UNIX-timestamp of the latest logout.

 * **Returns:** long holding the UNIX-timestamp of the latest logout.

## `public Date getLastLogin()`

returns a Date with the last login

 * **Returns:** Date holding the last login timestamp

## `public Date getLastLogout()`

returns a Date with the last logout.

 * **Returns:** Date holding the last logout timestamp.

## `public String getLastGameMode()`

returns a String with the last GameMode.

 * **Returns:** String holding the last GameMode.

## `public String getMCVersion()`

returns a String with the players Minecraft version.

 * **Returns:** String holding the players Minecraft version.
