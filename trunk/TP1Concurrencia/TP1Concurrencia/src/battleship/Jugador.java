package battleship;

import java.util.List;

public class Jugador extends Thread{

	private int puntos = 0;
	private ServidorBattleship servidor;
	private Estrategia estrategia;
	private String nombre;

	public Jugador(ServidorBattleship s, Estrategia e, String nombre) {
		super();
		this.servidor = s;
		this.estrategia = e;
		this.setNombre(nombre);
	}
	
	@Override
	public void run() {
		while((this.servidor.channel2.receive())){
			this.estrategia.estategia(this);
		}
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
			this.servidor.setCantBarcos(servidor.getCantBarcos() - 1);
		}
		return b;
	}

	public int shootLine(int nrofila) {
		Integer hundidos = getServidor().execute(new ShootLine(nrofila));
		this.puntos = this.puntos + hundidos;
		this.servidor.setCantBarcos(servidor.getCantBarcos() - hundidos);
		return hundidos;
	}

	public ServidorBattleship getServidor() {
		return servidor;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

}
