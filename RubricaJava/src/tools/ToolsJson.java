package tools;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ToolsJson {
    public static JSONArray readJSONArr(String path) {
        // Create the json parser
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = new JSONArray();

        // Read and save the file content in jsonObj
        try (FileReader fileReader = new FileReader(path)) {
            Object obj = jsonParser.parse(fileReader);
            // assign to the JSONObject (global variable)
            // the file which we want ot extract information from
            jsonArray = (JSONArray) obj;
        } catch (IOException | ParseException e) {
            System.out.println("There was a problem with the files. We apologize for the inconvenience.");
        }

        return jsonArray;
    }

    public static void writeJSONArr(String path, JSONArray jsonArray) {
        // Write the jsonArray into the file
        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(JSONValue.toJSONString(jsonArray));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
