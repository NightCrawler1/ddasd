package btl.bot;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginStart extends JavaPlugin{
    //Quem estiver lendo isso so quero que não mude nehum codigo desta classe caso de erro entre em contato
    // com o @NightCrawler#4776 no discord obrigado! :)


    @Override
    public void onEnable() {
        try {
            getLogger().info("Bot ligando...");
            BtlMain.main(new String[]{"sexy"});
            getLogger().info("Ligado com sucesso!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("Bot desligando...");
    }

    @Override
    public void onLoad() {
        getLogger().info("Reniciando parametros!\n" +
                "caso ouver erro contante o @NightCrawler#4776 não mecha em nehum codigo <3");
    }
}
