package repository.factory;

public class UtilEnumerator {
    private static int project = 1;
    private static int mindMap = 1;
    private static int element = 1;

    public static int getEnum(String type){
        if(type.equals("project"))
            return project++;
        if(type.equals("mindMap"))
            return mindMap++;
        if(type.equals("element"))
            return element++;
        return -1;
    }

    public static void decEnum(String type){
        if(type.equals("project"))
            project--;
        if(type.equals("mindMap"))
            mindMap--;
        if(type.equals("element"))
            element--;
    }
}
