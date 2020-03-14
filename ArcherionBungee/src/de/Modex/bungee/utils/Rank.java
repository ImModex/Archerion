package de.Modex.bungee.utils;

import java.util.List;

public enum Rank {

    OWNER("Owner", "§4", 5),
    STAFF("Staff", "§6", 4),
    FRIEND("Friend", "§a", 3),
    DONATOR("Donator", "§b", 2),
    PLAYER("Player", "§7", 1);

    private String name;
    private String prefix;
    private int hierarchy;

    Rank(String name, String prefix, int hierarchy) {
        this.name = name;
        this.prefix = prefix;
        this.hierarchy = hierarchy;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getHierarchy() {
        return hierarchy;
    }

    public static Rank getHighest(List<String> ranks) {

        if (ranks.contains("Owner"))
            return Rank.OWNER;
        if (ranks.contains("Staff"))
            return Rank.STAFF;
        if (ranks.contains("Friend"))
            return Rank.FRIEND;
        if (ranks.contains("Donator"))
            return Rank.DONATOR;
        if (ranks.contains("Player"))
            return Rank.PLAYER;

        return null;
    }
}
