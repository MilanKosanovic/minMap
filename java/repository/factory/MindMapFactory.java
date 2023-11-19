package repository.factory;

import repository.Implementation.MindMap;
import repository.Implementation.Project;
import repository.composite.MapNode;

public class MindMapFactory extends MapNodeFactory {
    @Override
    public MapNode createMapNode( MapNode parent) {
        return new MindMap("MindMap" + UtilEnumerator.getEnum("mindMap"),parent);
    }
}
