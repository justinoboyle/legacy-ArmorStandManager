package com.arrayprolc.armorstandmanager.core.java.core.main;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.arrayprolc.armorstandmanager.core.java.core.util.ItemUtils;

public class CommandAdminKit extends Command {

    @Override
    public boolean onCommand(CommandSender sender, String label, String[] args) {
        String[] alts = new String[] { "adminkit", "ak" };
        boolean cont = false;
        for (String s : alts) {
            if (label.equalsIgnoreCase(s)) {
                cont = true;
                break;
            }
        }

        if (!cont)
            return false;

        if (!(sender instanceof Player))
            return true;

        Player p = (Player) sender;
        if (!p.isOp()) {
            sender.sendMessage("§cError > §7You don't have permission to do that.");
            return true;
        }
        p.getInventory().clear();
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("astools") || args[0].equalsIgnoreCase("as")) {
                ItemStack[] add = new ItemStack[] { ItemUtils.setName(new ItemStack(Material.ARMOR_STAND), "§7Armor Stand"), ItemUtils.setName(new ItemStack(Material.STICK), "§dArms Adjuster"),
                        ItemUtils.setName(new ItemStack(Material.FIREWORK_CHARGE), "§dSize Adjuster"), ItemUtils.setName(new ItemStack(Material.STONE_PLATE), "§dBase Plate Adjuster"),
                        new ItemStack(Material.BOOK_AND_QUILL), ItemUtils.setName(new ItemStack(Material.SPIDER_EYE), "§dVisibility Adjuster"),
                        ItemUtils.setName(new ItemStack(Material.MONSTER_EGG), "§b§lTranslate Kit"), };
                int i = 0;
                for (ItemStack in : add) {
                    p.getInventory().setItem(i, in);
                    i++;
                }
                p.getInventory().setHeldItemSlot(0);
                return true;
            }
            if (args[0].equalsIgnoreCase("astranslate")) {
                ItemStack[] add = new ItemStack[] { ItemUtils.setName(new ItemStack(Material.ARMOR_STAND), "§7Armor Stand"), ItemUtils.setName(new ItemStack(Material.NAME_TAG), "§aTool Switch"),
                        ItemUtils.setName(new ItemStack(Material.GHAST_TEAR), "§dAdjust X"), ItemUtils.setName(new ItemStack(Material.SHEARS), "§dAdjust Y"),
                        ItemUtils.setName(new ItemStack(Material.LEASH), "§dAdjust Z"), ItemUtils.setName(new ItemStack(Material.MONSTER_EGG), "§b§lTools Kit"), };
                int i = 0;
                for (ItemStack in : add) {
                    p.getInventory().setItem(i, in);
                    i++;
                }
                p.getInventory().setHeldItemSlot(0);
                return true;
            }
            sender.sendMessage("§cUsage > §7/adminkit [as/astools/astranslate]");
            return true;
        }
        ItemStack[] add = new ItemStack[] { ItemUtils.setName(new ItemStack(Material.STONE_AXE), "§dWorldEdit Range Wand"),
                ItemUtils.setName(new ItemStack(Material.WOOD_AXE), "§dWorldEdit Close Wand"), ItemUtils.setName(new ItemStack(Material.ARROW), "§dVoxelSniper Arrow"),
                ItemUtils.setName(new ItemStack(Material.SULPHUR), "§dVoxelSniper Gunpowder"), ItemUtils.setName(new ItemStack(Material.COMPASS), "§dAdmin Compass"), };
        int i = 0;
        for (ItemStack in : add) {
            p.getInventory().setItem(i, in);
            i++;
        }
        p.getInventory().setHeldItemSlot(0);
        p.performCommand("worldedit:farwand");
        return true;
    }
}
