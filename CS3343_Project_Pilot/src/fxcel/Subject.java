/**
 * City University of Hong Kong, Group 22
 * Subject.java
 * Dummy Subject class.
 */

package fxcel;

import java.util.*;

public abstract class Subject{
	public List<Cell> list;
    public abstract void attach(Cell c);
    public abstract void detach(Cell c);
    public abstract void notifyObservers();
    
    /**
     * the construction of a Subject, will be called by Cell
     */
    public Subject(){
        list = new ArrayList<Cell>();
    }

}
