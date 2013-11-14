package battleship;

import java.util.List;

public class Jugador {

	private int puntos = 0;
	private ServidorBattleship servidor;
	private Estrategia estrategia;

	public Jugador(ServidorBattleship s, Estrategia e) {
		super();
		this.servidor = s;
		this.estrategia = e;
	}

	public void estrategia() {
		this.estrategia.estategia(this);
	}

	public boolean scan(int i, int j) {
		return getServidor().execute(new ScanCelda(i, j));
	}

	public List<Integer> scanLine(int nrofila) {
		return getServidor().execute(new ScanLine(nrofila));
	}

	public boolean shoot(int i, int j) {
		Boolean b = getServidor().execute(new Shoot(i, j));
		if (b) {
			this.puntos++;
		}
		return b;
	}

	public int shootLine(int nrofila) {
		return getServidor().execute(new ShootLine(nrofila));
	}

	public ServidorBattleship getServidor() {
		return servidor;
	}

	public int getPuntos() {
		return puntos;
	}

}
