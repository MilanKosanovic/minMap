package repository.factory;

import repository.composite.MapNode;

public abstract class MapNodeFactory {

    public abstract MapNode createMapNode(MapNode parent);

}
