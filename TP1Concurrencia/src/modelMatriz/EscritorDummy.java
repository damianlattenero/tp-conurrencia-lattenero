package matriz;

public class EscritorDummy extends Thread implements Escritor<Integer> {
	
	Matriz<Integer> m;
	
	public EscritorDummy(Matriz<Integer> m) {
		super();
		this.m = m;
	}

	@Override
	public void run() {
		this.m.escribirCelda(this, 1, 1);
	}

	@Override
	public void write(Celda<Integer> c) {
		c.setContenido(5);
		System.out.println("Escribi un 5");
	}

}
