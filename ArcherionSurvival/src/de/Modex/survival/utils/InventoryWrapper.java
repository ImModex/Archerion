package de.Modex.survival.utils;

public class InventoryWrapper {
    private int id;
    private String path;

    public InventoryWrapper(int id, String path) {
        this.id = id;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public String getPath() {
        return path;
    }
}
