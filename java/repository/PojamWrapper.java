package repository;

import lombok.Getter;
import lombok.Setter;
import repository.Implementation.Pojam;
import repository.Implementation.Veza;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class PojamWrapper {
    Pojam pojam;
    List<Pojam> connections;
    List<Veza> veze;

    public PojamWrapper(Pojam pojam, List<Veza> veze) {
        this.pojam = pojam;
        connections = new ArrayList<>();
        this.veze = veze;
        initConnections();
    }

    private void initConnections(){
        for(Veza veza : veze){
            if(veza.getElement1().getName().equals(pojam.getName())){
                connections.add((Pojam) veza.getElement2());
            }
            if(veza.getElement2().getName().equals(pojam.getName())){
                connections.add((Pojam) veza.getElement1());
            }
        }
    }

}
