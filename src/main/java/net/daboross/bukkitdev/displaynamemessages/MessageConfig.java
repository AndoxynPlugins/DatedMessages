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

import java.io.File;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author daboross
 */
public class MessageConfig {
    
    private final JavaPlugin plugin;
    private final FileUtils fileUtils;
    private File loginFile;
    private List<String> login;
    private File logoutFile;
    private List<String> logout;
    
    public MessageConfig(JavaPlugin plugin) {
        this.plugin = plugin;
        this.fileUtils = new FileUtils(plugin);
    }
    
    public void copyDefaultConfig() {
        plugin.saveResource("login.txt", false);
        plugin.saveResource("logout.txt", false);
    }

    /**
     * Gets a copy of the configuration. This method will reload the
     * configuration if it is not loaded.
     *
     * @return The contents of the file motd.txt when last reloaded.
     */
    public String[] getLoginCopy() {
        loadConfig();
        return login.toArray(new String[login.size()]);
    }

    /**
     * Gets a copy of the configuration. This method will reload the
     * configuration if it is not loaded.
     *
     * @return The contents of the file motd.txt when last reloaded.
     */
    public String[] getLogoutCopy() {
        loadConfig();
        return logout.toArray(new String[logout.size()]);
    }

    /**
     * Reloads the configuration if it is not already loaded. This method will
     * do nothing if the configuration has already been loaded.
     */
    public void loadConfig() {
        if (login == null||logout==null) {
            reloadConfig();
        }
    }

    /**
     * Reloads the configuration from file.
     */
    public void reloadConfig() {
        if (loginFile == null) {
            loginFile = new File(plugin.getDataFolder(), "login.txt");
        }
        if (logoutFile == null) {
            logoutFile = new File(plugin.getDataFolder(), "logout.txt");
        }
        if (!loginFile.exists() || !logoutFile.exists()) {
            copyDefaultConfig();
        }
        login = fileUtils.readFile(loginFile);
        logout = fileUtils.readFile(logoutFile);
        for (int i = 0; i < login.size(); i++) {
            login.set(i, ChatColor.translateAlternateColorCodes('&', login.get(i)));
        }
        for (int i = 0; i < logout.size(); i++) {
            logout.set(i, ChatColor.translateAlternateColorCodes('&', logout.get(i)));
        }
    }
}
