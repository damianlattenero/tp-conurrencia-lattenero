package model;

public class LectorDummy extends Thread implements Lector<Integer> {
	
	Matriz<Integer> m;
	
	public LectorDummy(Matriz<Integer> m) {
		super();
		this.m = m;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			m.leerCelda(this, 1, 1);
		}
	}

	@Override
	public void read(Integer e) {
		System.out.println("Soy el lector " + this.getId());
		System.out.println("Estoy leyendo el contenido: " + e);
	}

}
