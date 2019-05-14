/*
 -----------------------------------------------------------------------------------
 Laboratoire : SER - Laboratoire 3 - JSON to KML
 Fichier     : Application.java
 Auteur(s)   : Jeremy Zerbib, Crescence Yimnaing
 Date        : 30/04/2019
 But         : Convertir et parser un GeoJSON en KML
 Remarque(s) :
 -----------------------------------------------------------------------------------

 {
    "type": "Feature",
    "properties": { "ADMIN": "Anguilla", "ISO_A3": "AIA" },
    "geometry":   { "type": "MultiPolygon",
        "coordinates":
        [
         [[
            [ -63.037668423999946, 18.212958075000031 ], [ -63.099517381999959, 18.176174221000124 ],
            [ -63.102365688999896, 18.180405992000161 ], [ -63.109120245999918, 18.185044664000188 ],
            [ -63.113840298999946, 18.189846096000068 ], [ -63.136830206999917, 18.173407294000086 ],
            [ -63.150502081999946, 18.169094143000095 ], [ -63.167836066999939, 18.169338283000044 ],
            [ -63.142079230999911, 18.198146877000156 ], [ -63.134348110999895, 18.204087632 ],
            [ -63.122792120999861, 18.20807526200015 ], [ -63.09723873599998, 18.212469794000143 ],
            [ -63.085845506999902, 18.217718817000119 ], [ -63.0677791009999, 18.2364769550001 ],
            [ -63.055083787999934, 18.254339911000059 ], [ -63.038197394999941, 18.267726955000157 ],
            [ -63.007394985999952, 18.273016669000029 ], [ -62.983998175999886, 18.276434637000037 ],
            [ -62.972645636999914, 18.275864976000079 ], [ -62.972889777999853, 18.269273179 ],
            [ -62.992909308999913, 18.236883856000091 ], [ -63.000559048999946, 18.227362372000087 ],
            [ -63.011545376999919, 18.220445054 ], [ -63.037668423999946, 18.212958075000031 ]
           ]],
       [[
           [ -63.423573370999861, 18.600043036000059 ], [ -63.427967902999939, 18.592840887000122 ],
           [ -63.428822394999941, 18.601263739000061 ], [ -63.423573370999861, 18.600043036000059 ]
        ]]
         ] } },
*/

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Application {

    public static void main(String[] args) {

        //JSON parser object pour lire le fichier
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("countries.geojson")) {
            // lecture du fichier
            Object obj = jsonParser.parse(reader);
            JSONObject feature = (JSONObject) obj;

            JSONArray jsonArray = (JSONArray) feature.get("features");

            for (Object feat : jsonArray) {
                parseFeatureObject((JSONObject) feat);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseFeatureObject(JSONObject feat) {
        int compteur = 0;
        JSONObject featJSON = (JSONObject) feat.get("properties");

        Properties properties = new Properties((String) featJSON.get("ADMIN"), (String) featJSON.get("ISO_A3"));

        JSONObject coordJSON = (JSONObject) feat.get("geometry");

        String type = (String) coordJSON.get("type");

        if (featJSON.get("ADMIN").equals("Anguilla")) {
            JSONArray coords = (JSONArray) coordJSON.get("coordinates");
            for (Object coord : coords) {
                JSONArray coordJ = (JSONArray) coord;
                for (Object c : coordJ){
                    JSONArray c1 = (JSONArray) c;
                    System.out.println(c1.size());
                }
            }
        }

        if (type.equals("Polygon")){

        } else if (type.equals("MultiPolygon")){
            JSONArray coords = (JSONArray) coordJSON.get("coordinates");
            for (Object coord : coords) {
                JSONArray coordJ = (JSONArray) coord;
                for (Object c : coordJ){
                    JSONArray c1 = (JSONArray) c;
                }
            }
        } else {
            throw new Error("Type mal forme !");
        }

    }


}
