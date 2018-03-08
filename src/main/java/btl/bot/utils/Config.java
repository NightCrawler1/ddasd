package btl.bot.utils;

import lombok.Getter;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Config {
    public String token , prefix;
    public List<String> owners;

    public Config(Path path)throws IOException{
        if(!path.toFile().exists()){
            if(!path.toFile().createNewFile())
                throw  new IOException("Não foi possivel criar o arquivo");
            else{
                JSONObject object = new JSONObject();
                try {
                    object.put("token", "");
                    object.put("prefix" , "!");
                    object.put("owners" , new ArrayList<>());
                    IOUtils.write(path , object.toString());
                }catch (JSONException e){
                    e.printStackTrace();
                }
                return;
            }
        }
        try{
            JSONObject json = new JSONObject(IOUtils.read(path));
            this.token = json.getString("token");
            this.owners = new ArrayList<>();
            for(Object o : json.getJSONArray("owners")){
                if(o instanceof String)
                    this.owners.add((String) o);
            }
            this.prefix = json.getString("prefix");
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
