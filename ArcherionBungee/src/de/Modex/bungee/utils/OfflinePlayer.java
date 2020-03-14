package de.Modex.bungee.utils;

import java.util.UUID;

public class OfflinePlayer {

    private String name;
    private UUID uuid;

    public OfflinePlayer(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }
}
