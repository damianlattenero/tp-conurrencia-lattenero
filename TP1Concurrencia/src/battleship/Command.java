package battleship;

public interface Command<A,B> {
	
	B apply(A x);

}
