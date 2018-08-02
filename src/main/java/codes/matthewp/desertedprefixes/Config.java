package codes.matthewp.desertedprefixes;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Config {

    private File file;

    private FileConfiguration config;

    public Config(JavaPlugin pl) {
        file = new File(pl.getDataFolder() + File.separator + "config.yml");
        if(!pl.getDataFolder().exists()) {
            pl.getDataFolder().mkdir();
        }

        if(!file.exists()) {
            pl.saveResource("config.yml", true);
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        return config;
    }
}
