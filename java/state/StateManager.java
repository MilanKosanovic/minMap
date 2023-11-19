package state;

import state.implementation.*;

public class StateManager {
    State addState;
    State connectState;
    State dragState;
    State removeState;
    State selectState;
    State zoomState;

    State currentState;

    public StateManager(){
     addState = new AddState();
     connectState = new ConnectState();
     dragState = new DragState();
     removeState = new RemoveState();
     selectState = new SelectState();
     zoomState = new ZoomState();

     currentState = selectState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setAddState(){currentState = addState;}
    public void setConnectState(){currentState = connectState;}
    public void setDragState(){currentState = dragState;}
    public void setRemoveState(){currentState = removeState;}
    public void setSelectState(){currentState = selectState;}
    public void setZoomState(){currentState = zoomState;}
}
