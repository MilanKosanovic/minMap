package serializer;

import repository.Implementation.MindMap;
import repository.Implementation.Project;
import repository.Implementation.ProjectExplorer;

import java.io.File;
import java.io.IOException;

public interface ISerializer {
    void saveProject(Project project) throws IOException;
    Project loadProject(File file);
    void saveMapTemplate(MindMap mindMap, String path);
    MindMap loadMapTemplate(File file, Project project);
}
