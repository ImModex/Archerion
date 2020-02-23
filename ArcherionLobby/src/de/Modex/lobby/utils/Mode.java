package de.Modex.lobby.utils;

import org.bukkit.Location;
import org.bukkit.Material;

public class Mode {

    private Location loc;
    private int slot;
    private Material item;
    private String server;
    private int port;

    public Mode(Location _loc, int _slot, Material _item, String _server, int _port) {
        this.loc = _loc;
        this.slot = _slot;
        this.item = _item;
        this.server = _server;
        this.port = _port;
    }

    public Mode() {
        loc = null;
        slot = 0;
        item = null;
    }

    public Location getLocation() {
        return loc;
    }

    public void setLocation(Location loc) {
        this.loc = loc;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public Material getItem() {
        return item;
    }

    public void setItem(Material item) {
        this.item = item;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
