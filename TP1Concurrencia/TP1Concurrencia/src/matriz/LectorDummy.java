package matriz;

public class LectorDummy extends Thread implements Lector<Integer, Void> {
	
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
	public Void read(Integer e) {
		return null;
	}

}
