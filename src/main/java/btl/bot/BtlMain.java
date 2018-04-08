package btl.bot;

import br.com.brunoxkk0.btl.econ.Econ;
import br.com.brunoxkk0.btl.gpp.ClaimBlocks;
import btl.bot.commands.CommandTest;
import btl.bot.commands.bot.*;
import btl.bot.utils.Config;
import com.jagrosh.jdautilities.commandclient.CommandClient;
import com.jagrosh.jdautilities.commandclient.CommandClientBuilder;
import com.jagrosh.jdautilities.commandclient.examples.PingCommand;
import lombok.NonNull;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.*;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BtlMain {
    public static String version = "@version@";
    private static BtlMain instance;
    private Config config;


    public static BtlMain getInstance(){
        return instance;
    }

    private BtlMain(Config config){
        this.config = config;
    }

    public Config getConfig(){return config;}

    private static CommandClient createClient() throws IOException{
        CommandClientBuilder client = new CommandClientBuilder();
        client.setAlternativePrefix("??");
        client.setPrefix(instance.config.prefix);
        client.addCommands(new PingCommand(),
                           new CommandTest(),
                           new ShutdownCommand(),
                           new ServerCommand(),
                           new PlayerInfoCommand(),
                           new ModpackCommand()

        );
        client.setEmojis(":white_check_mark:" , ":warning:" , ":x:");
        client.setOwnerId("337425118450024450");
        client.useHelpBuilder(false);
        client.setGame(Game.watching("BTL Servers."));
        return client.build();
    }



    public static void main(String[] args)throws InterruptedException , LoginException , IOException{

        Config c = new Config(Paths.get("config.json").toAbsolutePath());

        instance = new BtlMain(c);
        new JDABuilder(AccountType.BOT)
                .setToken(c.token)
                .setGame(Game.watching("BTL Servers."))
                .addEventListener(createClient())
                .setCorePoolSize(5)
                .setGame(Game.watching("BTL Server."))
                .buildBlocking(JDA.Status.CONNECTED);
    }

    @NonNull
    public boolean isOwner(User user) {
        return config.owners.contains(user.getId());
    }

}
