package com.arrayprolc.armorstandmanager.core.java.core.main;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.arrayprolc.armorstandmanager.core.java.core.util.ClickArmorstandEvent;

public class GWCore extends JavaPlugin {

    private static GWCore instance;

    public static GWCore getInstance() {
        return instance;
    }

    @SuppressWarnings("deprecation")
    public void onEnable() {
        instance = this;
    }

    private void setupEvents() {
        Listener[] listeners = new Listener[] { new ClickArmorstandEvent() };

        for (Listener l : listeners)
            Bukkit.getServer().getPluginManager().registerEvents(l, this);

    }

}
