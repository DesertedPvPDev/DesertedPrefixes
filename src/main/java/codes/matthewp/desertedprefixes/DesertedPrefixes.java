package codes.matthewp.desertedprefixes;

import codes.matthewp.desertedprefixes.api.Prefix;
import codes.matthewp.desertedprefixes.api.Rank;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class DesertedPrefixes extends JavaPlugin implements Listener {

    private Config config;
    private List<Prefix> prefixList;
    private List<Rank> ranks;
    private String chatFormat;

    @Override
    public void onEnable() {
        config = new Config(this);
        prefixList = new ArrayList<>();
        ranks = new ArrayList<>();
        loadData();
        chatFormat = config.getConfig().getString("chatFormat");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {

    }

    private void loadData() {
        loadPrefixes();
        loadRanks();
    }

    private void loadPrefixes() {
        ConfigurationSection sec = config.getConfig().getConfigurationSection("prefixes");
        for (String key : sec.getKeys(false)) {
            prefixList.add(new Prefix(key, config.getConfig().getConfigurationSection("prefixes." + key)));
        }
    }

    private void loadRanks() {
        ConfigurationSection sec = config.getConfig().getConfigurationSection("ranks");
        for (String key : sec.getKeys(false)) {
            ranks.add(new Rank(key, config.getConfig().getConfigurationSection("ranks." + key)));
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();

        String newFormat = chatFormat;
        newFormat = Placeholders.replacePlaceholders(newFormat, p, getPlayerPrefix(p), getPlayerRank(p), event.getMessage());
        newFormat = ChatColor.translateAlternateColorCodes('&', newFormat);

        event.setFormat(newFormat);
    }

    private Rank getPlayerRank(Player player) {
        List<Rank> pRanks = new ArrayList<>();

        for (Rank rank : ranks) {
            if (player.hasPermission(rank.getPerm())) {
                pRanks.add(rank);
            }
        }

        Rank rank = ranks.get(0);

        for (Rank ran : pRanks) {
            if (ran.getLevel() > rank.getLevel()) {
                rank = ran;
            }
        }
        return rank;
    }

    private Prefix getPlayerPrefix(Player player) {
        List<Prefix> pPrefixes = new ArrayList<>();

        for (Prefix fix : prefixList) {
            if (player.hasPermission(fix.getPerm())) {
                pPrefixes.add(fix);
            }
        }

        Prefix fix = null;

        for (Prefix pre : pPrefixes) {
            if (fix == null) {
                fix = pre;
            } else if (pre.getLevel() > fix.getLevel()) {
                fix = pre;
            }
        }
        return fix;
    }
}
