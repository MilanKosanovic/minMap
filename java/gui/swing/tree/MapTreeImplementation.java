package gui.swing.tree;

import core.ApplicationFramework;
import gui.swing.tree.controller.TreeMouseListener;
import gui.swing.tree.model.MapTreeItem;
import gui.swing.tree.view.MapTreeView;
import gui.swing.view.MainFrame;
import gui.swing.view.PojamView;
import gui.swing.view.VezaView;
import lombok.Getter;
import messageGenerator.MessageType;
import repository.Implementation.*;
import repository.composite.MapNode;
import repository.composite.MapNodeComposite;
import repository.factory.MapNodeFactory;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.ArrayList;
import java.util.List;
@Getter
public class MapTreeImplementation implements MapTree{
    private MapTreeView treeView;
    private DefaultTreeModel treeModel;
    private MapNode mapRootModel;


    @Override
    public MapNode getRootModel() {
        return mapRootModel;
    }

    @Override
    public MapTreeView generateTree(ProjectExplorer projectExplorer) {
        MapTreeItem root = new MapTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new MapTreeView(treeModel);
        treeView.addMouseListener(new TreeMouseListener());
        mapRootModel = projectExplorer;

        return treeView;
    }

    @Override
    public void addChild(MapTreeItem parent) {

        if (!(parent.getMapNode() instanceof MapNodeComposite)){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.CANT_ADD_CHILD);
            return;
        }

        MapNodeFactory factory = ApplicationFramework.getInstance().getMapRepository().getFactory(parent.getMapNode());
        MapNode child = factory.createMapNode(parent.getMapNode());

        parent.add(new MapTreeItem(child));     //dodavanje u stablo
        ((MapNodeComposite) parent.getMapNode()).addChild(child);       //dodavanje u model
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void addElement(Element element, MindMap mindMap) {
        mindMap.addChild(element);
        for (int i = 0 ; i < MainFrame.getInstance().getMapTree().getSelectedNode().getChildCount() ; i++){
            if(((MapTreeItem)MainFrame.getInstance().getMapTree().getSelectedNode().getChildAt(i)).getMapNode().equals(mindMap)){
                ((MapTreeItem)MainFrame.getInstance().getMapTree().getSelectedNode().getChildAt(i)).add(new MapTreeItem(element));
            }
        }
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void removeChild(MapTreeItem node) {

        if(node.getMapNode() instanceof ProjectExplorer){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.PROJECT_EXPLORER_DELETE);
            return;
        }

        ((DefaultMutableTreeNode)node.getParent()).remove(node);        //brisanje iz stabla
        ((MapNodeComposite)node.getMapNode().getParent()).removeChild(node.getMapNode());       //brisanje iz modela

        if(node.getMapNode().getParent() instanceof ProjectExplorer){       //ne svidja nam se, ali nismo znali kako da projectExplorer javi u projectView da project ne postoji
            MainFrame.getInstance().getProjectView().setProject(null);
        }

        SwingUtilities.updateComponentTreeUI(treeView);     //ovo ne sme da stoji ako observer radi
    }

    @Override
    public List<Element> removeElement(MindMap mindMap, List<PojamView> pojmovi, List<VezaView> veze){

        System.out.println(pojmovi);
        System.out.println(veze);
        List<Element> obrisano = new ArrayList<>();

        for(VezaView vezaView : veze){
            mindMap.removeChild(vezaView.getVeza());
            obrisano.add(vezaView.getVeza());
        }
        for(PojamView pojamView : pojmovi){
            mindMap.removeChild(pojamView.getPojam());
            obrisano.add(pojamView.getPojam());
        }

        List<MapNode> mindMapChildren = new ArrayList<MapNode>(mindMap.getChildren());

        for(int i = 0 ; i < mindMapChildren.size() ; i++) {
            if (mindMapChildren.get(i) instanceof Veza) {
                Veza veza = (Veza) mindMapChildren.get(i);
                if(!(mindMap.getChildren().contains(veza.getElement1()) && mindMap.getChildren().contains(veza.getElement2()))){
                    mindMap.removeChild(veza);
                    obrisano.add(veza);
                }
            }
        }

        MapTreeItem mapTreeItem = null;
        for (int i = 0 ; i < MainFrame.getInstance().getMapTree().getSelectedNode().getChildCount() ; i++){
            if(((MapTreeItem)MainFrame.getInstance().getMapTree().getSelectedNode().getChildAt(i)).getMapNode().equals(mindMap)){
                mapTreeItem = ((MapTreeItem)MainFrame.getInstance().getMapTree().getSelectedNode().getChildAt(i));

            }
        }

        for(int i = 0 ; i < mapTreeItem.getChildCount() ; i ++){
            for(PojamView pojamView : pojmovi){
                if(((MapTreeItem)mapTreeItem.getChildAt(i)).getMapNode().getName().equals(pojamView.getPojam().getName())){
                    this.removeChild((MapTreeItem) mapTreeItem.getChildAt(i));
                }
            }
        }

        return obrisano;

    }

    @Override
    public void removeSingleElement(MindMap mindMap ,Element element) {
        mindMap.removeChild(element);
    }

    @Override
    public void loadProject(Project node) {
        MapTreeItem loadedProject = new MapTreeItem(node);
        ((DefaultMutableTreeNode)treeModel.getRoot()).add(loadedProject);

        MapNodeComposite mapNode = (MapNodeComposite) ((MapTreeItem)treeModel.getRoot()).getMapNode();
        mapNode.addChild(node);

        for(MapNode mapnode : node.getChildren()){
            (loadedProject).add(new MapTreeItem(mapnode));
        }

        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void addChildToParent(MapNode child, MapNodeComposite parent) {
        parent.addChild(child);     //dodavanje u model
        this.getSelectedNode().add(new MapTreeItem(child));     //dodavanje u stablo
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);

    }

    @Override
    public MapTreeItem getSelectedNode() {
        return (MapTreeItem) treeView.getLastSelectedPathComponent();
    }


    @Override
    public void expand() {
        treeView.expandPath(treeView.getSelectionPath());
    }

    @Override
    public void refresh() {
        SwingUtilities.updateComponentTreeUI(treeView);
    }
}
