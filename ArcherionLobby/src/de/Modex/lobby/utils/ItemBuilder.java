package de.Modex.lobby.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

public class ItemBuilder {
    private ItemStack item;

    public ItemBuilder(Material m) {
        this(m, 1);
    }

    public ItemBuilder(ItemStack item) {
        this.item = item;
    }

    public ItemBuilder(Material m, int amount) {
        this.item = new ItemStack(m, amount);
    }

    public ItemBuilder(Material m, int amount, byte subid) {
        this.item = new ItemStack(m, 1, subid);
    }

    public ItemBuilder setName(String name) {
        ItemMeta meta = this.item.getItemMeta();
        meta.setDisplayName(name);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setSkullOwner(String owner) {
        SkullMeta meta = (SkullMeta) this.item.getItemMeta();
        meta.setOwningPlayer(getOfflinePlayer(owner));
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        ItemMeta meta = this.item.getItemMeta();
        meta.setLore(Arrays.asList(lore));
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemStack build() {
        return this.item;
    }

    private OfflinePlayer getOfflinePlayer(String name) {
        OfflinePlayer ret = null;

        try {
            ret = Bukkit.getServer().getOfflinePlayer((UUID) JsonRequest.readJsonFromUrl("https://api.mojang.com/users/profiles/minecraft/" + name).get("id"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
