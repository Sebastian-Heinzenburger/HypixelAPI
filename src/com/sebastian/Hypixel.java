package com.sebastian;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * @author      Sebastian Heinzenburger <Sebastian.Heinzenburger @ gmail.com>
 * @version     1.0
 * @since       1.0
 */
public class Hypixel {
    String apiKey;

    public Hypixel(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Reads the data returned by the hypixel server for a given player name.
     * @param name  the name of the player the data is requestet about
     * @return  the data as JSON String
     */
    public String getJSONPlayerData(String name) {
        StringBuilder JSONresponse = new StringBuilder();
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://api.hypixel.net/player?key=" + apiKey + "&name=" + name).openConnection();
            con.setRequestMethod("GET");
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String line = in.readLine();
                while (line != null) {
                    JSONresponse.append("\n");
                    JSONresponse.append(line);
                    line = in.readLine();
                }
            }

        } catch (IOException e) {
            System.out.println("Error: " + JSONresponse);
            return null;
        }
        return JSONresponse.toString();
    }

    /**
     * Sets the Player object's JSONData to the JSONData returned by the server.
     * @param player    the PLayer object to update
     */
    public void updatePlayerData(Player player) {
        player.JSONData = getJSONPlayerData(player.name);
    }

    /**
     * @author      Sebastian Heinzenburger <Sebastian.Heinzenburger @ gmail.com>
     * @version     1.0
     * @since       1.0
     */
    public static class Player {
        String JSONData;
        String name;
        long oldLoginTime = 0;
        long oldLogoutTime = 0;
        String oldLastGameMode = "";

        /**
         * Creates a Player object
         * @param name  displayname of the player
         */
        Player(String name) {
            this.name = name;
        }

        /**
         * parse JSONData to get the UNIX-timestamp of the latest login.
         * @return  long holding the UNIX-timestamp of the latest login.
         */
        public long getLastLoginTimeStamp(){
            String _JSONData = JSONData.substring(JSONData.indexOf("\"lastLogin\":") + "\"lastLogin\":".length());
            _JSONData = _JSONData.substring(0, _JSONData.indexOf(","));
            return Long.parseLong(_JSONData.replace("\"", ""));
        }

        /**
         * parse JSONData to get the UNIX-timestamp of the latest logout.
         * @return  long holding the UNIX-timestamp of the latest logout.
         */
        public long getLastLogoutTimeStamp(){
            String _JSONData = JSONData.substring(JSONData.indexOf("\"lastLogout\":") + "\"lastLogout\":".length());
            _JSONData = _JSONData.substring(0, _JSONData.indexOf(","));
            return Long.parseLong(_JSONData.replace("\"", ""));
        }

        /**
         * returns a Date with the last login
         * @return  Date holding the last login timestamp
         */
        public Date getLastLogin() {
            long lli = getLastLoginTimeStamp();
            Date d = new Date();
            d.setTime(lli);
            return d;
        }

        /**
         * returns a Date with the last logout
         * @return  Date holding the last logout timestamp
         */
        public Date getLastLogout() {
            long lli = getLastLogoutTimeStamp();
            Date d = new Date();
            d.setTime(lli);
            return d;
        }

        public String getLastGameMode(){
            String _JSONData = JSONData.substring(JSONData.indexOf("\"mostRecentGameType\":") + "\"mostRecentGameType\":".length());
            try{
                _JSONData = _JSONData.substring(0, _JSONData.indexOf(","));
            } catch (IndexOutOfBoundsException e) {
                _JSONData = _JSONData.replace("}", "");
            }
            return _JSONData.replace("\"", "");
        }
    }

}
