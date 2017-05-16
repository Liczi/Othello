package si.ai.othello.web.util;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * @author Jakub Licznerski
 *         Created on 17.05.2017.
 */
public class JsonUtil {

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static ResponseTransformer json() {
        return JsonUtil::toJson;
    }
}