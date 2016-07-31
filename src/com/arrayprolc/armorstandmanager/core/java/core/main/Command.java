package com.arrayprolc.armorstandmanager.core.java.core.main;

import org.bukkit.command.CommandSender;

public abstract class Command {

    public abstract boolean onCommand(CommandSender sender, String label, String[] args);
    
}
