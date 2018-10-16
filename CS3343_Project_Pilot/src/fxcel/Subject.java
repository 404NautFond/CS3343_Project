package fxcel;

import java.io.Serializable;
import java.util.List;

public class Subject implements Serializable{

	private static final long serialVersionUID = -2487001394519029398L;
	
	private List<Cell> list;
	
	public void attach() {
		
	}
	
	public void detach() {
		
	}
	
	public void notify_subject() {
		for(Cell auto: list) {
			auto.update();
		}
	}
}
