package model;

public class Prueba {
	
	public static void main(String[] args) {
		
		Matriz<Integer> m = new Matriz<Integer>(3, 3);
		
		LectorDummy r1 = new LectorDummy(m);
		LectorDummy r2 = new LectorDummy(m);
		LectorDummy r3 = new LectorDummy(m);
		LectorDummy r4 = new LectorDummy(m);
		LectorDummy r5 = new LectorDummy(m);
		
		EscritorDummy w = new EscritorDummy(m);
		
		r1.start();
		r2.start();
		r3.start();
		r4.start();
		r5.start();
		
		w.start();
	}

}
