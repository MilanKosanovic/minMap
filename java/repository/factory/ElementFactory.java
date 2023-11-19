package repository.factory;

import repository.Implementation.Element;
import repository.Implementation.Project;
import repository.composite.MapNode;

public class ElementFactory extends MapNodeFactory{
    @Override
    public MapNode createMapNode(MapNode parent) {
        return new Element("Element" + UtilEnumerator.getEnum("element"),parent);
    }
}
