package repository.factory;

import repository.Implementation.Project;
import repository.composite.MapNode;

public class ProjectFactory extends MapNodeFactory{
    @Override
    public MapNode createMapNode(MapNode parent) {
        return new Project("Project" + UtilEnumerator.getEnum("project"),parent);
    }
}
