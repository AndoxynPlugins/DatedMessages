/*
 * Copyright (C) 2013 Dabo Ross <http://www.daboross.net/>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.daboross.bukkitdev.displaynamemessages;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 *
 * @author daboross
 */
public class Parser {

    public static String[] getParsed(Player p, String[] message) {
        GregorianCalendar calendar = null;
        String server = null, player = null, date = null, ampmLower = null, ampmUpper = null, time = null, online = null;
        for (int i = 0; i < message.length; i++) {
            String line = message[i];
            if (line.contains("%server")) {
                line = line.replace("%server", server == null ? server = Bukkit.getServerName() : server);
            }
            if (line.contains("%player")) {
                line = line.replace("%player", player == null ? player = p.getDisplayName() : player);
            }
            if (line.contains("%date")) {
                line = line.replace("%date", date == null ? date = getDate(calendar == null ? calendar = new GregorianCalendar() : calendar) : date);
            }
            if (line.contains("%ampm")) {
                line = line.replace("%ampm", ampmLower == null ? ampmLower = getAmPmLower(calendar == null ? calendar = new GregorianCalendar() : calendar) : ampmLower);
            }
            if (line.contains("%AMPM")) {
                line = line.replace("%AMPM", ampmUpper == null ? ampmUpper = getAmPmUpper(calendar == null ? calendar = new GregorianCalendar() : calendar) : ampmUpper);
            }
            if (line.contains("%time")) {
                line = line.replace("%time", time == null ? time = getTime(calendar == null ? calendar = new GregorianCalendar() : calendar) : time);
            }
            if (line.contains("%online")) {
                line = line.replace("%online", online == null ? online = getOnline() : online);
            }
            message[i] = line;
        }
        return message;
    }

    private static String getDate(Calendar calendar) {
        return calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.YEAR);
    }

    private static String getAmPmLower(Calendar calendar) {
        return calendar.get(Calendar.AM_PM) == Calendar.AM ? "am" : "pm";
    }

    private static String getAmPmUpper(Calendar calendar) {
        return calendar.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
    }

    private static String getTime(Calendar calendar) {
        return calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE);
    }

    private static String getOnline() {
        Player[] players = Bukkit.getOnlinePlayers();
        if (players.length == 0) {
            return "";
        } else if (players.length == 1) {
            return players[0].getName();
        } else {
            StringBuilder builder = new StringBuilder(ChatColor.WHITE.toString()).append(players[0].getName());
            for (Player p : Bukkit.getOnlinePlayers()) {
                builder.append(ChatColor.GRAY).append(", ").append(ChatColor.WHITE).append(p.getName());
            }
            return builder.toString();
        }
    }
}
