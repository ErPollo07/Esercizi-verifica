package tools;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ToolsJson {
    public static JSONArray readJSONArr(String path) {
        // Create the json parser
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = new JSONArray();

        // Read and save the Agenda.json content in jsonObj
        try (FileReader fileReader = new FileReader(path)) {
            Object obj = jsonParser.parse(fileReader);
            // assign to the JSONObject (global variable)
            // the file which we want ot extract information from
            jsonArray = (JSONArray) obj;
        } catch (IOException | ParseException e) {
            System.out.println("C'e' stato un problema con i file di accesso. Ci scusiamo per il disagio");
        }

        return jsonArray;
    }
}
