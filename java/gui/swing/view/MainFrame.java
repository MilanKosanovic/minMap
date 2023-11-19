package gui.swing.view;

import core.ApplicationFramework;
import gui.swing.command.CommandManager;
import gui.swing.controller.ActionManager;
import gui.swing.tree.MapTree;
import gui.swing.tree.MapTreeImplementation;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
@Setter
@Getter
public class MainFrame extends JFrame {

    private static MainFrame instance = null;

    ActionManager actionManager;

    CommandManager commandManager;
    ToolBar toolBar;
    MyMenuBar myMenuBar;
    ProjectView projectView;
    private MapTree mapTree;

    public MainFrame(){

    }


    private void init(){

        actionManager = new ActionManager();
        commandManager = new CommandManager();
        mapTree = new MapTreeImplementation();
        initialiseGUI();

    }

    private void initialiseGUI(){
        ///osnovna podesavanja porozora
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        this.setSize(screenSize.width/2, screenSize.height/2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("GeRuMap application");

        myMenuBar = new MyMenuBar();
        this.setJMenuBar(myMenuBar);

        toolBar = new ToolBar();
        this.add(toolBar,BorderLayout.NORTH);

        JTree projectExplorer = mapTree.generateTree(ApplicationFramework.getInstance().getMapRepository().getProjectExplorer());


        JPanel panel = new JPanel();

        JScrollPane scrollPane = new JScrollPane(projectExplorer);
        scrollPane.setMinimumSize(new Dimension(200,150));

        projectView = new ProjectView();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scrollPane,projectView);
        getContentPane().add(splitPane,BorderLayout.CENTER);
        splitPane.setDividerLocation(250);
        splitPane.setOneTouchExpandable(true);
    }


    public static MainFrame getInstance() {
        if(instance == null){
            instance = new MainFrame();
            instance.init();
        }
        return instance;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public void setActionManager(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

    public void setToolBar(ToolBar toolBar) {
        this.toolBar = toolBar;
    }




   // public MyMenuBar getMenuBar() {
   //    return myMenuBar;
   // }

    public void setMenuBar(MyMenuBar myMenuBar) {
        this.myMenuBar = myMenuBar;
    }
}
