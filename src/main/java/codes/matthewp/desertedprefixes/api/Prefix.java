package codes.matthewp.desertedprefixes.api;

import org.bukkit.configuration.ConfigurationSection;

public class Prefix {

    private String name;

    private String perm;

    private String prefix;

    private int level;

    public Prefix(String name, ConfigurationSection sec) {
        this.name = name;
        this.perm = sec.getString("perm");
        this.prefix = sec.getString("prefix");
        this.level = sec.getInt("level");
    }

    public String getPerm() {
        return perm;
    }

    public void setPerm(String perm) {
        this.perm = perm;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
