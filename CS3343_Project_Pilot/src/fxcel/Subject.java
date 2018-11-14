package fxcel;

import java.util.*;

public abstract class Subject{
	public List<Cell> list;
    public abstract void attach(Cell c);
    public abstract void detach(Cell c);
    public abstract void notifyObservers();
    
    public Subject(){
        list = new ArrayList<Cell>();
    }

}
