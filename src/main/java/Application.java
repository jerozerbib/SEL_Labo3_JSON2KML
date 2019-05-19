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
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class Application {

    public static void main(String[] args) {
        final String filename = "countries.geojson";
        //JSON parser object pour lire le fichier
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(filename)) {

            // lecture du fichier
            Object obj         = jsonParser.parse(reader);
            JSONObject feature = (JSONObject) obj;
            JSONArray features = (JSONArray) feature.get("features");

            // Construction initiale du KML avec un factory
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder builder        = factory.newDocumentBuilder();
            final Document document             = builder.newDocument();

            // Creation du tag <kml> avec la version voulue
            final Element kmlTag = document.createElement("kml");
            kmlTag.setAttribute("xmlns", "http://www.opengis.net/kml/2.2");
            document.appendChild(kmlTag);

            // Creation du tag <Document>
            final Element documentTag = document.createElement("Document");
            kmlTag.appendChild(documentTag);

            // Creation de la balise <Style> qui permet d'appliquer differents styles suivant les IDs
            final Element styleTag = document.createElement("Style");
            styleTag.setAttribute("id", "style1");
            documentTag.appendChild(styleTag);

            final Element polyStyleTag = document.createElement("PolyStyle");
            styleTag.appendChild(polyStyleTag);

            final Element fillTag = document.createElement("fill");
            fillTag.appendChild(document.createTextNode("0"));
            polyStyleTag.appendChild(fillTag);

            final Element outlineTag = document.createElement("outline");
            outlineTag.appendChild(document.createTextNode("1"));
            polyStyleTag.appendChild(outlineTag);

            // Parcours de tous les features
            for (Object feat : features) {
                // Creation de la balise <PlaceMark>
                final Element placeMark = document.createElement("Placemark");
                documentTag.appendChild(placeMark);

                // Creation de la balise <styleUrl> permettant d'appliquer un style sur un placemark
                final Element styleURLTag = document.createElement("styleUrl");
                styleURLTag.appendChild(document.createTextNode("#style1"));
                placeMark.appendChild(styleURLTag);

                // Conversion des features de Object vers JSONObject
                JSONObject featJ    = (JSONObject) feat;
                // Recuperation des prorietes de l'objet feature
                JSONObject featJSON = (JSONObject) featJ.get("properties");

                // Recuperation des valeurs de la propriete.
                String admin = (String) featJSON.get("ADMIN");
                String isoA3 = (String) featJSON.get("ISO_A3");

                // Creation de propriete
                Properties properties = new Properties(admin , isoA3);

                // Entete de la ligne pour la console
                String titre          = properties.toString();
                System.out.println(titre);

                JSONObject coordJSON = (JSONObject) featJ.get("geometry");
                String type          = (String) coordJSON.get("type");
                JSONArray coords     = (JSONArray) coordJSON.get("coordinates");

                // Ajout de la balise <ExtendedData>
                Element extData = document.createElement("ExtendedData");
                placeMark.appendChild(extData);

                // Ajout de la balise <Data> pour ADMIN
                Element dataAdmin = document.createElement("Data");
                dataAdmin.setAttribute("name", "ADMIN");
                extData.appendChild(dataAdmin);

                // Ajout de la balise <value> contenue dans <Data> de ADMIN
                Element valueAdmin = document.createElement("value");
                valueAdmin.appendChild(document.createTextNode(properties.getAdmin()));
                dataAdmin.appendChild(valueAdmin);

                // Ajout de la balise <Data> pour ISO_A3
                Element dataISO = document.createElement("Data");
                dataISO.setAttribute("name", "ISO_A3");
                extData.appendChild(dataISO);

                // Ajout de la balise <value> contenue dans <Data> de ISO_A3
                Element valueISO = document.createElement("value");
                valueISO.appendChild(document.createTextNode(properties.getIsoA3()));
                dataISO.appendChild(valueISO);
                
                if (type.equals("Polygon")){
                    Element polygon = document.createElement("Polygon");
                    placeMark.appendChild(polygon);

                    Element outerBoundaryIs = document.createElement("outerBoundaryIs");
                    polygon.appendChild(outerBoundaryIs);

                    Element linearRing = document.createElement("LinearRing");
                    outerBoundaryIs.appendChild(linearRing);

                    Element coordinates = document.createElement("coordinates");

                    getCoordinates(document, linearRing, coordinates, coords);
                } else if (type.equals("MultiPolygon")){
                    Element multiGeometry = document.createElement("MultiGeometry");
                    placeMark.appendChild(multiGeometry);

                    Element polygon = document.createElement("Polygon");
                    multiGeometry.appendChild(polygon);

                    Element outerBoundaryIs = document.createElement("outerBoundaryIs");
                    polygon.appendChild(outerBoundaryIs);

                    Element linearRing = document.createElement("LinearRing");
                    outerBoundaryIs.appendChild(linearRing);

                    // Parcours obligatoire pour les <MultiGeometry> pour parcourir le premier tableau
                    for (Object coord : coords) {
                        Element coordinates = document.createElement("coordinates");
                        JSONArray coordJ = (JSONArray) coord;
                        getCoordinates(document, linearRing, coordinates, coordJ);
                    }
                } else {
                    throw new Error("Type mal forme !");
                }
            }

            // Etape 7 : finalisation
            final TransformerFactory transformerFactory = TransformerFactory.newInstance();
            final Transformer transformer = transformerFactory.newTransformer();
            final DOMSource source        = new DOMSource(document);
            final StreamResult sortie     = new StreamResult(new File("src/output.kml"));

            //prologue
            document.setXmlStandalone(true);
            document.setXmlVersion("1.0");
            transformer.setOutputProperty(OutputKeys.ENCODING,   "UTF-8");

            //formatage
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            //sortie
            transformer.transform(source, sortie);

        } catch (IOException | ParseException | ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private static void getCoordinates(Document document, Element linearRing, Element coordinates, JSONArray coords) {
        for (Object coord: coords){
            JSONArray coordJ = (JSONArray) coord;
            for (Object o : coordJ) {
                StringBuilder sb = new StringBuilder(o.toString());
                sb.deleteCharAt(0);
                sb.deleteCharAt(sb.length() - 1);
                sb.append(" ");
                coordinates.appendChild(document.createTextNode(sb.toString()));
            }
            linearRing.appendChild(coordinates);

            String nbCoord = "\t - " + coordJ.size() + " coordinates";
            System.out.println(nbCoord);

        }
    }

}
