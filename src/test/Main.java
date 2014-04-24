package test;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
	Integer a = new Integer(1);
	int b = a;
	System.out.print(b);
    }
}

class Stack {
    private X[] stack;
    private int length;
    private static int volume = 3;

    public Stack() {
	this.stack = new X[this.volume];
	this.length = 0;
    }

    public void push(X x) {
	if (this.length < this.volume) {
	    this.stack[this.length] = x;
	    this.length++;
	}
    }

    public X pop() {
	if (this.length > 0) {
	    this.length--;
	    return this.stack[this.length];
	}
	return null;
    }
}

class X {
    private X[] xs;
    private X brother;
    private int x;

    public X() {
	this.xs = new X[3];
    }

    public X getBrother() {
	return brother;
    }

    public void setBrother(X brother) {
	this.brother = brother;
    }

    public int getX() {
	return x;
    }

    public void setX(int x) {
	this.x = x;
    }

    public X[] getXs() {
	return xs;
    }

    public void setXs(X[] xs) {
	this.xs = xs;
    }

}
