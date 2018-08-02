package codes.matthewp.desertedprefixes;

import codes.matthewp.desertedprefixes.api.Prefix;
import codes.matthewp.desertedprefixes.api.Rank;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Placeholders {

    private static Pattern playerPattern = Pattern.compile("\\{(?i)player}");
    private static Pattern prefixPattern = Pattern.compile("\\{(?i)prefix}");
    private static Pattern rankPattern = Pattern.compile("\\{(?i)rank}");
    private static Pattern msgPattern = Pattern.compile("\\{(?i)msg}");


    public static String replacePlaceholders(String s, Player p, Prefix pre, Rank rank, String msg) {
        Matcher mat = playerPattern.matcher(s);
        s = mat.replaceAll(p.getName());

        Matcher mat2 = prefixPattern.matcher(s);
        s = mat2.replaceAll(pre.getPrefix());

        Matcher mat3 = rankPattern.matcher(s);
        s = mat3.replaceAll(rank.getChatTitle());

        Matcher mat4 = msgPattern.matcher(s);
        s = mat4.replaceAll(msg);
        return s;
    }
}
