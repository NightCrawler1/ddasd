package btl.bot.commands.bot;

import br.com.brjdevs.java.utils.texts.StringUtils;
import btl.bot.utils.RequestUtils;
import com.jagrosh.jdautilities.commandclient.Command;
import com.jagrosh.jdautilities.commandclient.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import org.json.JSONObject;

import java.awt.*;

public class ServerCommand extends Command {

    public ServerCommand(){
        this.name = "servidores";
        this.cooldown = 10;
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        String[] args = StringUtils.splitArgs(event.getArgs(), 2);
        String imageurl = "https://pbs.twimg.com/profile_images/658664874856333313/MhnCHMVD.png";
        EmbedBuilder builder = new EmbedBuilder();
        String arg = args[0];
        String url = "https://use.gameapis.net/mc/query/info/198.50.225.99:25565";
        String request = RequestUtils.get(url);
        if (request == null) {
            event.reply(builder.setDescription("Estou com erros ao entrar em contato com os servidores!").setColor(Color.red).build());
            return;
        }
        JSONObject server = new JSONObject(request);
        boolean statuss = server.getBoolean("status");
        JSONObject players = server.getJSONObject("players");
        int online = players.getInt("online");
        int max = players.getInt("max");
        int ping = server.getInt("ping");
        builder.setTitle("BTL Server");
        builder.appendDescription("Servidores (`ip & modpacks`)\n\n");
        builder.addField("`BTL - Sky`" , "IP: 198.50.225.99:25565\n" +
                "Status: `" + (statuss == false ? "offline" : "online") + "`\n" +
                "Players (Online/Max): `"+ online + "/" + max + "`\n" +
                "Ping: `" + ping + "`\n" +
                "ModPack: [\t[ModPack]\t](https://www.technicpack.net/modpack/horizonmc-skyv2.1139709)\n\n" , false);

        builder.addField("`BTL - World`\n\n" , "IP: `Em-breve.`\n" +
                "Status: `Em construção.`\n" +
                "ModPack: Em-breve." , false);

        builder.setFooter("Caso precise de ajuda use o comando !modpacks" , null);
        builder.setColor(Color.cyan);
        event.reply(builder.build());

    }
}
