package de.Modex.bungee.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TimeUnit {

    SECOND("Second(s)", 1, "sec", "s", "second", "seconds", "secs"),
    MINUTE("Minute(s)", 60, "min", "m", "minute", "minutes", "mins"),
    HOUR("Hour(s)", 60 * 60, "hour", "h", "hours"),
    DAY("Day(s)", 24 * 60 * 60, "day", "d", "days"),
    WEEK("Week(s)", 7 * 24 * 60 * 60, "week", "w", "weeks");

    private String name;
    private long toSecond;
    private List<String> shorter;

    TimeUnit(String name, long toSecond, String... shorter) {
        this.name = name;
        this.toSecond = toSecond;
        this.shorter = Arrays.asList(shorter);

        for (String unit : this.shorter) {
            this.shorter.set(this.shorter.indexOf(unit), this.shorter.get(this.shorter.indexOf(unit)).toLowerCase());
        }
    }

    public long getToSecond() {
        return toSecond;
    }

    public String getName() {
        return name;
    }

    public List<String> getShorter() {
        return shorter;
    }

    public static List<List<String>> getUnitsAsString() {
        List<List<String>> units = new ArrayList<>();

        for (TimeUnit unit : TimeUnit.values()) {
            units.add(unit.getShorter());
        }

        return units;
    }

    public static TimeUnit getUnit(String unit) {
        for (TimeUnit units : TimeUnit.values()) {
            List<String> shorterList = units.getShorter();

            for(String shorter : shorterList) {
                if(shorter.toLowerCase().equals(unit.toLowerCase())) {
                    return units;
                }
            }
        }

        return null;
    }
}
