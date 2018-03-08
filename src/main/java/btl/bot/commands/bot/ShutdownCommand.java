package btl.bot.commands.bot;

import com.jagrosh.jdautilities.commandclient.Command;
import com.jagrosh.jdautilities.commandclient.CommandEvent;

public class ShutdownCommand extends Command {

   public ShutdownCommand(){
       this.name = "shutdown";
       this.ownerCommand = true;
       this.guildOnly = false;
       this.help = "Desliga a bot!";
       this.hidden = false;
   }
    @Override
    protected void execute(CommandEvent event) {
       event.reply("Esta bot esta sendo desligada...");
       event.getJDA().shutdown();
    }
}
