package btl.bot.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Created by Leagueoof on 21/07/2017.
 */
public class RequestUtils {
    static final String charset;
    static final String contentType = "application/json";

    public RequestUtils() {
    }

    public static String get(String url) {
        return get(url, (String) null);
    }

    public static String get(String url, String auth) {
        if (!checkURL(url)) {
            return null;
        } else {
            try {
                URLConnection conn = open(url, auth);
                InputStream response = conn.getInputStream();
                Scanner scanner = new Scanner(response);
                String responseBody = scanner.useDelimiter("\\A").next();
                scanner.close();
                return responseBody;
            } catch (IOException var6) {
                var6.printStackTrace();
                return null;
            }
        }
    }

    public static String post(String url, String query) {
        return post(url, query, (String) null);
    }

    public static String post(String url, String query, String auth) {
        try {
            URLConnection conn = open(url, auth);
            OutputStream output = conn.getOutputStream();
            output.write(query.getBytes(charset));
            output.close();
            InputStream response = conn.getInputStream();
            String outputStr = null;
            if (charset != null) {
                BufferedReader reader;
                String line;
                for (reader = new BufferedReader(new InputStreamReader(response,
                        charset)); (line = reader.readLine()) != null; outputStr = line) {
                    ;
                }

                reader.close();
            }

            return outputStr;
        } catch (IOException var9) {
            var9.printStackTrace();
            return null;
        }
    }

    private static URLConnection open(String url, String auth) throws MalformedURLException, IOException {
        URLConnection conn = (new URL(url)).openConnection();
        conn.setDoOutput(true);
        conn.setRequestProperty("Accept-Charset", charset);
        conn.setRequestProperty("Content-Type", "application/json");
        if (auth != null) {
            conn.setRequestProperty("Authorization", auth);
        }

        return conn;
    }

    public static boolean checkURL(String url) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection con = (HttpURLConnection) (new URL(url)).openConnection();
            con.setRequestMethod("HEAD");
            return con.getResponseCode() == 200;
        } catch (Exception var2) {
            return false;
        }
    }

    public static String checkPngExtension(String url) {
        if (!checkURL(url)) {
            url = url + ".png";
            if (!checkURL(url)) {
                return null;
            }
        }

        return url;
    }

    static {
        charset = StandardCharsets.UTF_8.name();
    }
}
