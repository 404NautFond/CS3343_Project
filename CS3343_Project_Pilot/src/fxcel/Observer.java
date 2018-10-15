package fxcel;

import java.util.ArrayList;

public interface Observer{
		private ArrayList<Cell> subscriber = new ArrayList<Cell>();
		public Observer() {}
		public boolean subscribe(Cell c) {
			try {
				subscriber.add(c);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		public boolean updateAll() {
			try {
				for(Cell c:subscriber) {
					c.computeVal();
				}
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	};
}
