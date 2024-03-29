package com.arrayprolc.armorstandmanager.core.java.core.util;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class ClickArmorstandEvent implements Listener {

    public static HashMap<UUID, ArmorStandTool> tool = new HashMap<UUID, ArmorStandTool>();

    @EventHandler
    public void interact2(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (!p.isOp()) {
            e.setCancelled(true);
            return;
        }
        if(!p.getGameMode().equals(GameMode.CREATIVE)){
            e.setCancelled(true);
            return;
        }
        ItemStack i = p.getItemInHand();
        if(!i.hasItemMeta()) return;
        if(!i.getItemMeta().hasDisplayName()) return;
        String s = i.getItemMeta().getDisplayName();
        if(s.contains("Translate Kit")){
            p.performCommand("adminkit astranslate");
            p.updateInventory();
            p.getInventory().setHeldItemSlot(5);
            return;
        }
        if(s.contains("Tools Kit")){
            p.performCommand("adminkit astools");
            p.updateInventory();
            p.getInventory().setHeldItemSlot(8);
            return;
        }
    }
    
    @EventHandler
    public void interact(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        if (!(e.getRightClicked() instanceof ArmorStand)) {
            return;
        }
        if(!p.getGameMode().equals(GameMode.CREATIVE)){
            e.setCancelled(true);
            return;
        }
        if (!p.isOp()) {
            e.setCancelled(true);
            return;
        }
        ArmorStand a = (ArmorStand) e.getRightClicked();
        a.setGravity(false);
        if (!tool.containsKey(e.getPlayer().getUniqueId())) {
            tool.put(p.getUniqueId(), ArmorStandTool.ROTATE_ARM_RIGHT);
        }
        if (p.getItemInHand().getType().equals(Material.NAME_TAG)) {
            boolean next = false;
            for (ArmorStandTool t : ArmorStandTool.values()) {
                if (next) {
                    tool.remove(p.getUniqueId());
                    tool.put(p.getUniqueId(), t);
                    BUtils.sendActionBar(p, "�7Switched tool to: �b" + t.toString());
                    e.setCancelled(true);
                    return;
                }
                if (t.toString().equalsIgnoreCase(tool.get(p.getUniqueId()).toString())) {
                    next = true;
                    if (t == ArmorStandTool.values()[ArmorStandTool.values().length - 1]) {
                        tool.remove(p.getUniqueId());
                        tool.put(p.getUniqueId(), ArmorStandTool.values()[0]);
                        BUtils.sendActionBar(p, "�7Switched tool to: �b" + ArmorStandTool.values()[0]);
                        e.setCancelled(true);
                        return;
                    }
                }
            }
            e.setCancelled(true);
            return;
        }
        if (p.getItemInHand().getType().equals(Material.STICK)) {
            a.setArms(!a.hasArms());
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 2, 2);
            sendBlockBreakParticles(p, Material.WOOD, a.getLocation());
            sendBlockBreakParticles(p, Material.WOOD, a.getLocation().add(0, 1, 0));
            e.setCancelled(true);
            return;
        }
        if (p.getItemInHand().getType().equals(Material.FIREWORK_CHARGE)) {
            a.setSmall(!a.isSmall());
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 2, 2);
            sendBlockBreakParticles(p, Material.WOOD, a.getLocation());
            if(!a.isSmall())
            sendBlockBreakParticles(p, Material.WOOD, a.getLocation().add(0, 1, 0));
            e.setCancelled(true);
            return;
        }
        if (p.getItemInHand().getType().equals(Material.STONE_PLATE)) {
            a.setBasePlate(!a.hasBasePlate());
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 2, 2);
            sendBlockBreakParticles(p, Material.WOOD, a.getLocation());
            e.setCancelled(true);
            return;
        }
        if (p.getItemInHand().getType().equals(Material.BOOK_AND_QUILL)) {
            BookMeta m = (BookMeta) p.getItemInHand().getItemMeta();
            if (p.isSneaking()) {
                if (a.getCustomName() != null) {
                    m.setPage(1, a.getCustomName().replace("�", "&"));
                    ItemStack i = p.getItemInHand();
                    i.setItemMeta(m);
                    p.setItemInHand(i);
                }
            } else {
                String s = m.getPages().get(m.getPageCount() - 1).replace("\u0026", "�").replace("\"", "");
                if (s.equals("none")) {
                    a.setCustomName(null);
                    a.setCustomNameVisible(false);
                } else {
                    a.setCustomName(s);
                    a.setCustomNameVisible(true);
                }

            }
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 2, 2);
            sendBlockBreakParticles(p, Material.WOOD, a.getLocation());
            sendBlockBreakParticles(p, Material.WOOD, a.getLocation().add(0, 1, 0));
            e.setCancelled(true);
            return;
        }
        if (p.getItemInHand().getType().equals(Material.SPIDER_EYE)) {
            a.setVisible(!a.isVisible());
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 2, 2);
            sendBlockBreakParticles(p, Material.WOOD, a.getLocation());
            sendBlockBreakParticles(p, Material.WOOD, a.getLocation().add(0, 1, 0));
            e.setCancelled(true);
            return;
        }

        if (p.getItemInHand().getType().equals(Material.GHAST_TEAR)) {
            armorStand(0.1, 0, 0, tool.get(p.getUniqueId()), p, a);
            p.playSound(p.getLocation(), Sound.CLICK, 2, 2);
            e.setCancelled(true);
            return;
        }

        if (p.getItemInHand().getType().equals(Material.SHEARS)) {
            armorStand(0, 0.1, 0, tool.get(p.getUniqueId()), p, a);
            p.playSound(p.getLocation(), Sound.CLICK, 2, 2);
            e.setCancelled(true);
            return;
        }

        if (p.getItemInHand().getType().equals(Material.LEASH)) {
            armorStand(0, 0, 0.1, tool.get(p.getUniqueId()), p, a);
            p.playSound(p.getLocation(), Sound.CLICK, 2, 2);
            e.setCancelled(true);
            return;
        }
        if(p.isSneaking() && p.getItemInHand() != null && !p.getItemInHand().getType().equals(Material.BOOK_AND_QUILL)){
            a.setHelmet(p.getItemInHand());
            e.setCancelled(true);
            return;
        }

    }

    @SuppressWarnings("deprecation")
    public static void sendBlockBreakParticles(Player p, Material m, Location loc) {
        p.getWorld().playEffect(loc, Effect.STEP_SOUND, m.getId());
    }

    public enum ArmorStandTool {
        ROTATE_ARM_RIGHT, ROTATE_ARM_LEFT, ROTATE_HEAD, ROTATE_BODY, TRANSLATE;
    }

    public void armorStand(double x, double y, double z, ArmorStandTool tool, Player p, ArmorStand a) {
        switch (tool) {
        case ROTATE_ARM_RIGHT: {
            if (!p.isSneaking()) {
                a.setRightArmPose(a.getRightArmPose().add(x, y, z));
            } else {
                a.setRightArmPose(a.getRightArmPose().add(-x, -y, -z));
            }
            break;
        }
        case ROTATE_ARM_LEFT: {
            if (!p.isSneaking()) {
                a.setLeftArmPose(a.getLeftArmPose().add(x, y, z));
            } else {
                a.setLeftArmPose(a.getLeftArmPose().add(-x, -y, -z));
            }
            break;
        }
        case ROTATE_HEAD: {
            if (!p.isSneaking()) {
                a.setHeadPose(a.getHeadPose().add(x, y, z));
            } else {
                a.setHeadPose(a.getHeadPose().add(-x, -y, -z));
            }
            break;
        }
        case ROTATE_BODY: {
            if (!p.isSneaking()) {
                a.setBodyPose(a.getBodyPose().add(x, y, z));
                a.setRightArmPose(a.getRightArmPose().add(x, y, z));
                a.setLeftArmPose(a.getLeftArmPose().add(x, y, z));
                a.setLeftLegPose(a.getLeftLegPose().add(x, y, z));
                a.setRightLegPose(a.getRightLegPose().add(x, y, z));
                a.setHeadPose(a.getHeadPose().add(x, y, z));
            } else {
                a.setBodyPose(a.getBodyPose().add(-x, -y, -z));
                a.setRightArmPose(a.getRightArmPose().add(-x, -y, -z));
                a.setLeftArmPose(a.getLeftArmPose().add(-x, -y, -z));
                a.setLeftLegPose(a.getLeftLegPose().add(-x, -y, -z));
                a.setRightLegPose(a.getRightLegPose().add(-x, -y, -z));
                a.setHeadPose(a.getHeadPose().add(-x, -y, -z));
            }
            break;
        }
        case TRANSLATE: {
            if (!p.isSneaking()) {
                a.teleport(a.getLocation().add(x, y, z));
            } else {
                a.teleport(a.getLocation().add(-x, -y, -z));
            }
            break;
        }
        default: {
            if (!p.isSneaking()) {
                a.setRightArmPose(a.getRightArmPose().add(x, y, z));
            } else {
                a.setRightArmPose(a.getRightArmPose().add(-x, -y, -z));
            }
        }

        }
    }

}
