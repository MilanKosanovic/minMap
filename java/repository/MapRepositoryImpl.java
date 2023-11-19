package repository;

import core.MapRepository;
import lombok.Getter;
import lombok.Setter;
import repository.Implementation.MindMap;
import repository.Implementation.Project;
import repository.Implementation.ProjectExplorer;
import repository.composite.MapNode;
import repository.composite.MapNodeComposite;
import repository.factory.ElementFactory;
import repository.factory.MapNodeFactory;
import repository.factory.MindMapFactory;
import repository.factory.ProjectFactory;

@Setter
@Getter
public class MapRepositoryImpl implements MapRepository {

    private ProjectExplorer projectExplorer;

    public MapRepositoryImpl() {
        projectExplorer = new ProjectExplorer("My Project Explorer");
    }

    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }

    @Override
    public void addChild(MapNodeComposite parent, MapNode child) {

    }

    public MapNodeFactory getFactory(MapNode mapNode){
        if(mapNode instanceof ProjectExplorer) return new ProjectFactory();
        if(mapNode instanceof Project) return new MindMapFactory();
        if(mapNode instanceof MindMap) return new ElementFactory();
        return null;
    }
}
