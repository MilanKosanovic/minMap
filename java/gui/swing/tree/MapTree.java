package gui.swing.tree;

import gui.swing.tree.model.MapTreeItem;
import gui.swing.tree.view.MapTreeView;
import gui.swing.view.ElementView;
import gui.swing.view.PojamView;
import gui.swing.view.VezaView;
import repository.Implementation.*;
import repository.composite.MapNode;
import repository.composite.MapNodeComposite;

import java.util.List;

public interface MapTree {
    MapTreeView generateTree(ProjectExplorer projectExplorer);
    void addChild(MapTreeItem parent);
    void removeChild(MapTreeItem node);
    MapTreeItem getSelectedNode();
    void expand();
    void refresh();
    void addElement(Element element, MindMap mindMap);
    List<Element> removeElement(MindMap mindMap, List<PojamView> pojmovi, List<VezaView> veze);
    void loadProject(Project node);
    MapTreeView getTreeView();
    void removeSingleElement(MindMap mindMap ,Element element);
    MapNode getRootModel();
    void addChildToParent(MapNode child, MapNodeComposite parent);
}
