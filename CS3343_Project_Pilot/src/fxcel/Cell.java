package fxcel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import fxcelException.*;

public class Cell extends Subject implements Observer {
    /**
     * For serialization
     */
    private static final long serialVersionUID = 1088607801192340601L;
    private StringTokenizer myToken1,myToken2;
    private String expression;
    private double value;	//default is 0 when computing, but not display
    private List<Cell> dependent;
    private String[] expressionArray;
    private Fxcel instance;

    /**
     * Default Constructor
     */
    protected Cell() {
        super();
        this.expression = null;
        this.instance = Fxcel.getInstance();
    }

    protected void assign(String expression) {
        char identifier = expression.charAt(0);
        if (identifier == ':') {
            this.expression = expression;
        } else if (identifier == '=') {
            //TODO: check dependency
            String tmp = "";
            ArrayList<String> list = new ArrayList<String>();

            myToken1 = new StringTokenizer(expression);

            while (myToken1.hasMoreElements()) {
                tmp += myToken1.nextToken();
            }

            myToken2 = new StringTokenizer(tmp, "+-*/%^:=()", true);

            while (myToken2.hasMoreElements()) {
                list.add(myToken2.nextToken());
            }

            String[] expressionArray = new String[list.size()];
            expressionArray = (String[]) list.toArray(expressionArray);

            for (String str: expressionArray) {
                if (instance.getCell(str) != null) {
                    Cell dependentCell = instance.getCell(str);
                    if (checkDep(dependentCell)) {
                        dependent.add(dependentCell);
                    }
                    dependentCell.attach(this);
                }
            }

//          this.value = this.cal.calculate(this.expressionArray);
        } else {
            this.expression = expression;
        }
    }

    protected boolean checkDep(Cell c) {
        for (Cell dep : dependent) {
            if (dep == c) {
                if (this != c) {
                    return false;
                }
            }
            if (!dep.checkDep(c)) {
                return false;
            }
        }
        return true;
    }

    protected void clear() {
        this.expression = null;
        for (Cell d : dependent) {
            d = null;
        }
    }

    @Override
    public String toString() {
        return null;
    }

    /**
     * Constructor with String input
     *
     * @param input the intended content
     */
    protected void writeCell(String input) {
        this.expression = input;
    }

    protected String readContent() {
        return this.expression;
    }
    //    protected double readVal() {
    //    		try {
    //				computeVal();
    //			} catch (FxcelException e) {
    //				// TODO Auto-generated catch block
    //				e.printStackTrace();
    //			}
    //    		return this.value;
    //    }
    //
    //    private Cell findCell(String loc) {
    //        int row = loc.charAt(0) - 'A';
    //        int col = Integer.parseInt(loc.substring(1));	//TODO:?
    //        return Fxcel.getInstance().getCell(row, col);
    //    }
    //
    //    /**
    //     * Compute the value of the content included in the cell
    //     *
    //     * @return the value of the cell, if not a number, return null
    //     */
    //    protected double computeVal() throws FxcelException {
    //    		try {
    //    			value = Double.parseDouble(expression);
    //    		}catch(NumberFormatException e) {
    //    	        if (expression.charAt(0) == '=') {
    //    	            myToken = new StringTokenizer(expression, "=+-*/%^()!", true);
    //    	            //token 1 can be Cell Location and Function Name
    //    	            this.value = Calculator.computeToken(myToken);
    //    	        } else 
    //    	        	throw new NonParsableException();
    //    		}
    //        return value;
    //    }

    @Override
    public void attach(Cell c) {
        for (Cell cell : list) {
            if (cell == c) {
                return;
            }
        }
        list.add(c);
    }

    @Override
    public void detach(Cell c) {
        list.remove(c);
    }

    @Override
    public void notifyObservers() {
        for (Cell c : list) {
            c.update();
        }
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
//        this.value = cal.calculate(expressionArray);
    }
//>>>>>>> 89f56de88177fc6769885c659d76f30f53985aaa

}
