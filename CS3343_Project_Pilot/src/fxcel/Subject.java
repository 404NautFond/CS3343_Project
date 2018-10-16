package fxcel;

import java.io.Serializable;
import java.util.*;

abstract class Subject implements Serializable{
    
    public List<Cell> list;
    public abstract void attach(Cell c);
    public abstract void detach(Cell c);
    public abstract void notifyObservers();
    
    public Subject(){
        list = new ArrayList<Cell>();
    }
}
