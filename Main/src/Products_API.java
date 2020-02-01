import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Products_API {

    private static final String PRODUCT1_API_BASE = "https://api.bestbuy.com/v1/products((search=";

    private static final String PRODUCT2_API_BASE = ")&(categoryPath.id=";

    private static final String PRODUCT3_API_BASE = "))?apiKey=dTyAe3mCZe2UKGgG1wlYSFbQ&pageSize=1&format=json";

    public static String address;

    public static Items search(String keyword, String category) {
        Items item = new Items();
        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            String sb = PRODUCT1_API_BASE + keyword + PRODUCT2_API_BASE + category + PRODUCT3_API_BASE;
            address = sb;
            URL url = new URL(sb);
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        JSONObject jsonObj = new JSONObject(jsonResults.toString());
        JSONArray predsJsonArray = jsonObj.getJSONArray("products");

        item.description = predsJsonArray.getJSONObject(0).getString("shortDescription");
        item.name = predsJsonArray.getJSONObject(0).getString("name");
        Integer temp = predsJsonArray.getJSONObject(0).getInt("salePrice");
        item.price = temp.toString();
        return item;
    }
}
