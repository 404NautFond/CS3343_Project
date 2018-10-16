package fxcel;

import java.io.Serializable;
import java.util.*;

public abstract class Subject implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 2013543366613221398L;
	public List<Cell> list;
    public abstract void attach(Cell c);
    public abstract void detach(Cell c);
    public abstract void notifyObservers();
    
    public Subject(){
        list = new ArrayList<Cell>();
    }

}
