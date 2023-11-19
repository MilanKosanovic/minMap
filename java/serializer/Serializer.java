package serializer;

import com.google.gson.*;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import gui.swing.view.MainFrame;
import repository.Implementation.*;
import repository.composite.MapNode;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Serializer implements ISerializer{
    private static int brojacVeza = 0;
    final RuntimeTypeAdapterFactory<MapNode> typeAdapterFactory =
            RuntimeTypeAdapterFactory.of(MapNode.class,"type")
            .registerSubtype(Project.class,"project")
            .registerSubtype(MindMap.class,"map")
            .registerSubtype(Pojam.class,"pojam")
            .registerSubtype(Veza.class, "veza");

    private final Gson gson = new GsonBuilder().registerTypeAdapterFactory(typeAdapterFactory).create();
    @Override
    public Project loadProject(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            JsonObject jsonObject;
            jsonObject = gson.fromJson(fileReader, JsonObject.class);
            JsonArray mape = jsonObject.getAsJsonArray("children");

            String name = jsonObject.get("name").getAsString();
            String filePath = jsonObject.get("filePath").getAsString();
            Project project = new Project(name, MainFrame.getInstance().getMapTree().getRootModel());
            project.setFilePath(filePath);

            List<MapNode> children = new ArrayList<>();
            for (JsonElement element : mape) {
                children.add(makeMindMap(element, project));
            }

            project.setChildren(children);

            return project;

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void saveProject(Project project) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(project.getFilePath());
            gson.toJson(project,fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveMapTemplate(MindMap mindMap, String path) {
        try{
            FileWriter fileWriter = new FileWriter(path + "/" + mindMap.getName() + ".json");
            gson.toJson(mindMap,fileWriter);
            fileWriter.flush();
            fileWriter.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public MindMap loadMapTemplate(File file, Project project) {
        try{
            FileReader fileReader = new FileReader(file);
            JsonElement jsonElement = gson.fromJson(fileReader, JsonElement.class);
            return makeMindMap(jsonElement, project);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    MindMap makeMindMap(JsonElement jsonElement, Project project){
        String name = jsonElement.getAsJsonObject().get("name").getAsString();
        MindMap mindMap = new MindMap(name, project);

        JsonArray pojamArray = jsonElement.getAsJsonObject().getAsJsonArray("children");

        for(JsonElement json : pojamArray){
            if(json.getAsJsonObject().get("type").getAsString().equals("pojam")){
                mindMap.addChild(makePojam(json, mindMap));
            }else{
                mindMap.addChild(makeVeza(json, mindMap));
            }
        }

        return mindMap;
    }

    Pojam makePojam(JsonElement jsonElement, MindMap parent){
        String name = jsonElement.getAsJsonObject().get("name").getAsString();
        int x = jsonElement.getAsJsonObject().get("X").getAsInt();
        int y = jsonElement.getAsJsonObject().get("Y").getAsInt();
        Color color = new Color(Integer.parseInt(jsonElement.getAsJsonObject().get("bojaAsString").getAsString()));
        int line = jsonElement.getAsJsonObject().get("lineThickness").getAsInt();

        return new Pojam(name, parent, x, y, line, color);
    }

    Veza makeVeza(JsonElement jsonElement, MindMap parent){

        //String name, MapNode parent, Element element1, Element element2, int xStart, int yStart, int xEnd, int yEnd, Color color, int debljinaLinije
        String name = "Veza"+brojacVeza++;
        Element element1 = makePojam(jsonElement.getAsJsonObject().get("element1"), parent);
        Element element2 = makePojam(jsonElement.getAsJsonObject().get("element2"), parent);
        int xStart = jsonElement.getAsJsonObject().get("xStart").getAsInt();
        int yStart = jsonElement.getAsJsonObject().get("yStart").getAsInt();
        int xEnd = jsonElement.getAsJsonObject().get("xEnd").getAsInt();
        int yEnd = jsonElement.getAsJsonObject().get("yEnd").getAsInt();
        Color color = new Color(Integer.parseInt(jsonElement.getAsJsonObject().get("bojaAsString").getAsString()));
        int line = jsonElement.getAsJsonObject().get("lineThickness").getAsInt();

        return new Veza(name, parent, element1, element2, xStart, yStart, xEnd, yEnd, color, line);
    }

}
