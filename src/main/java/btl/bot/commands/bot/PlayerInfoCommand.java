package btl.bot.commands.bot;
import br.com.brunoxkk0.btl.clan.Clan;
import br.com.brunoxkk0.btl.econ.*;
import br.com.brunoxkk0.btl.gpp.ClaimBlocks;
import br.com.brunoxkk0.btl.server.Server;
import br.com.brunoxkk0.btl.util.Utils;
import com.jagrosh.jdautilities.commandclient.Command;
import com.jagrosh.jdautilities.commandclient.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.awt.*;

public class PlayerInfoCommand extends Command {

    public PlayerInfoCommand(){
        this.cooldown = 10;
        this.name = "player";
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        if(event.getArgs().isEmpty()){
            event.replyError("Você precisa colocar o nome de um nick do minecraft cadastrado no servidor, ou digite `??player [nick].`");
            return;
        }
        try {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("Informações do Player **" +Bukkit.getPlayer(event.getArgs()).getDisplayName() + "** ".replace("[", "").replace("]" , ""));
            builder.setDescription("*Essas informações podem estar com atraso!*");
            builder.setThumbnail("https://visage.surgeplay.com/full/512/" + event.getArgs());
            builder.addField("Vida:" , ""+ Bukkit.getPlayer(event.getArgs()).getHealth() + "/`" + Utils.percentageCalculator(Bukkit.getPlayer(event.getArgs()).getHealth(), Bukkit.getPlayer(event.getArgs()).getMaxHealth()) +"%" + "`" , true);
           builder.addField("Clan Tag:" , Clan.getClanTag(Bukkit.getPlayer(event.getArgs())) , true);
            builder.addField("Money:" , Econ.getMoney(Bukkit.getPlayer(event.getArgs())) + "", true);
            builder.addField("Blocos de Proteção:" , ClaimBlocks.getClaimBlocks(Bukkit.getPlayer(event.getArgs())) + "" , true);
            builder.setFooter("Informações por BTL Api", event.getAuthor().getAvatarUrl());
            builder.setColor(Color.cyan);
            event.reply(builder.build());
        }catch (Exception e){
            e.printStackTrace();
            event.replyError("O nick não foi encontrado jogando no servidor no momento tente mais tarde!");
            Server.dispathConsoleCommand(e.getMessage() + "\n\n" + e.getCause());
        }
    }
}
