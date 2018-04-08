package btl.bot.commands.bot;

import btl.bot.utils.RequestUtils;
import com.jagrosh.jdautilities.commandclient.Command;
import com.jagrosh.jdautilities.commandclient.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import org.json.JSONObject;

public class ModpackCommand extends Command {
    public ModpackCommand() {
        this.name = "modpacks";
        this.cooldown = 10;
        this.guildOnly = true;
    }

    public String onlineornot(){
        String url = "https://use.gameapis.net/mc/query/info/198.50.225.99:25565";
        String request = RequestUtils.get(url);
        if(request == null){
            return "Sem resposta do servidor!";
        }
        JSONObject server = new JSONObject(request);
        boolean statuss = server.getBoolean("status");
        return (statuss == false ? "`offline`" : "`online`");
    }

    @Override
    protected void execute(CommandEvent event) {
        EmbedBuilder builder = new EmbedBuilder();

        builder.setTitle("Beyond The Limits");
        builder.addField("`Sky - 1.10.2`" , "IP: 198.50.225.99:25565\n" +
                "ModPack: [ModPack](https://www.technicpack.net/modpack/horizonmc-skyv2.1139709)\n" +
                "Status do Servidor: " +
                onlineornot() , true);
        builder.setFooter("Caso tenha dividas use `??help`." , (event.getGuild().getIconUrl().isEmpty() ? null : event.getGuild().getIconUrl()));
    event.reply(builder.build());
    }
}
