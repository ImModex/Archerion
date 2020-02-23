package de.Modex.lobby.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChangeListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void on(WeatherChangeEvent e) {
        e.setCancelled(true);
    }
}
