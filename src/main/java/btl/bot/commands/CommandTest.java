package btl.bot.commands;

import btl.bot.BtlMain;
import btl.bot.utils.Config;
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
        this.aliases = new String[]{"help"};
        this.cooldown = 10;
        this.guildOnly = true;
        this.category = new Category("Informações");
        this.name = "help";
         new Test();
    }
    @Override
    protected void execute(CommandEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
            builder.setAuthor("Aqui estão todos os comando desta bot!", null,
                    event.getJDA().getSelfUser().getAvatarUrl());
            builder.setDescription("`??ajuda` - **Mostra todos os comandos da bot.**\n\n");
            builder.setDescription("`??servidores` - **Mostra a informação dos servidores.**\n\n" +
                    "`??player [nick]` - **Mostra informações sobre o determinado player do servidor.**\n\n" +
                    "`??modpacks` - **Mostra todas as informações dos modpacks.**\n\n" +
                    "`??info` - **Informações da bot.**\n\n"
                   );
            builder.setFooter("Loja: " + BtlMain.getInstance().getConfig().loja, null);
            builder.setColor(Color.cyan);
            event.reply(builder.build());
    }

   public class Test extends Command{

        public Test(){
            this.name = "sugestao";
            this.guildOnly = true;
       }
       @Override
       protected void execute(CommandEvent event) {
           event.reply("Este comando esta em processo de criação!");
       }
   }
}
