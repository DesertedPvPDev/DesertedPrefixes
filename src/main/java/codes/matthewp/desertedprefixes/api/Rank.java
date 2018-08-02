package codes.matthewp.desertedprefixes.api;

import org.bukkit.configuration.ConfigurationSection;

public class Rank {

    private String name;

    private String perm;

    private String chatTitle;

    private int level;

    public Rank(String name, ConfigurationSection sec) {
        this.name = name;
        this.perm = sec.getString("perm");
        this.chatTitle = sec.getString("chatTitle");
        this.level = sec.getInt("level");
    }

    public String getPerm() {
        return perm;
    }

    public void setPerm(String perm) {
        this.perm = perm;
    }

    public String getChatTitle() {
        return chatTitle;
    }

    public void setChatTitle(String prefix) {
        this.chatTitle = prefix;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
