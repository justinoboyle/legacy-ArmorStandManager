package com.arrayprolc.armorstandmanager.core.java.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {

    private ItemUtils() {
    }

    /**
     * Set the name of a chosen item stack
     * 
     * @param is
     *            the itemStack you wish to rename
     * @param name
     *            the name you want this itemStack to have (auto-colors)
     * @return the item stack once the name has been changed
     */
    public static ItemStack setName(ItemStack is, String name) {
        name = ChatColor.translateAlternateColorCodes('&', name);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(name);
        is.setItemMeta(im);
        return is;
    }

    /**
     * Set the lore of the chosen item stack
     * 
     * @param is
     *            the item stack you want to change the lore of
     * @param lore
     *            what you wish the lore to be (separate lines with commas E.G.
     *            "line one", "line two", "line three")
     * @return the item stack with the lore changed to what you put
     */
    public static ItemStack setLore(ItemStack is, String... lore) {
        ItemMeta meta = is.getItemMeta();
        List<String> newLore = new ArrayList<String>();
        if (lore.length >= 1) {
            List<String> lines = Arrays.asList(lore);
            for (String s : lines) {
                newLore.add(ChatColor.translateAlternateColorCodes('&', s));
            }
        }
        meta.setLore(newLore);
        is.setItemMeta(meta);
        return is;
    }
    
    public static ItemStack setLore(ItemStack is, ArrayList<String> lines) {
        ItemMeta meta = is.getItemMeta();
        List<String> newLore = new ArrayList<String>();
            for (String s : lines) {
                newLore.add(ChatColor.translateAlternateColorCodes('&', s));
            }
        meta.setLore(newLore);
        is.setItemMeta(meta);
        return is;
    }

    /**
     * Set the name and lore of the chosen Item Stack
     * 
     * @param itemStack
     *            the ItemStack you want to change the name and lore of
     * @param name
     *            the name you want this ItemStack to have
     * @param lore
     *            the lore you want this ItemStack to have (separate lines with
     *            commas E.G. "line one", "line two", "line three")
     * @return the ItemStack with the new name and lore applied
     */
    public static ItemStack setNameAndLore(ItemStack itemStack, String name,
            String... lore) {
        itemStack = setName(itemStack, name);
        itemStack = setLore(itemStack, lore);
        return itemStack;
    }
    
    /**
     * Set the name and lore of the chosen Item Stack
     * 
     * @param itemStack
     *            the ItemStack you want to change the name and lore of
     * @param name
     *            the name you want this ItemStack to have
     * @param lore
     *            the lore you want this ItemStack to have 
     * @return the ItemStack with the new name and lore applied
     */
    public static ItemStack setNameAndLore(ItemStack itemStack, String name,
            ArrayList<String> lore) {
        itemStack = setName(itemStack, name);
        itemStack = setLore(itemStack, lore);
        return itemStack;
    }
}
