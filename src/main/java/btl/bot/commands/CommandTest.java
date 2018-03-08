package btl.bot.commands;

import com.jagrosh.jdautilities.commandclient.Command;
import com.jagrosh.jdautilities.commandclient.CommandBuilder;
import com.jagrosh.jdautilities.commandclient.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandTest extends Command {
    public CommandTest(){
        this.aliases = new String[]{"ajuda"};
        this.cooldown = 10;
        this.guildOnly = true;
        this.category = new Category("Informações");
        this.name = "ajuda";

    }
    @Override
    protected void execute(CommandEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
            builder.setAuthor("Aqui estão todos os comando desta bot!", null,
                    event.getJDA().getSelfUser().getAvatarUrl());
            List<Command> cmd = event.getClient().getCommands();
            builder.setDescription("`!ajuda` - **Mostra todos os comandos da bot.**\n\n");
            builder.setDescription("`!servidores` - **Mostra a informação dos servidores.**\n\n" +
                    "`!modpacks` - **Mostra todas as informações dos modpacks.**\n\n" +
                    "`!info` - **Informações da bot.**\n\n" +
                    "`!denuncia` - **Use este comando para denunciar jogadores.**\n\n" +
                    "`!sugestao` = **Use este comando para dar uma suguestão aos staffs.**");
            builder.setFooter("Loja: Ñ sei" , null);
            builder.setColor(Color.cyan);
            event.reply(builder.build());
    }
}
