package battleship;

import java.util.List;

import ar.edu.unq.pconc.Channel;

public class Jugador extends Thread{

	private int puntos = 0;
	private ServidorBattleship servidor;
	private Estrategia estrategia;
	private boolean terminarDeAtacar = false;
	private String nombre;
	private Channel<Boolean> channelParaTerminar;

	public Jugador(ServidorBattleship s, Estrategia e, String nombre, int canal) {
		super();
		this.servidor = s;
		this.estrategia = e;
		this.nombre = nombre;
		this.channelParaTerminar = new Channel<>(canal,true);
	}
	
	@Override
	public void run() {
		while(!terminarDeAtacar){
			System.out.println("Empieza " + this.getNombre());
			this.estrategia.estrategia(this);
			this.preguntarSiTerminoElJuego();
		}
	}
	
	public boolean preguntarSiTerminoElJuego(){
		this.getChannelParaTerminar().send(true);
		this.terminarDeAtacar = this.getChannelParaTerminar().receive();
		return this.terminarDeAtacar;
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
			this.servidor.restarBarcos(1);
		}
		return b;
	}

	public int shootLine(int nrofila) {
		Integer hundidos = getServidor().execute(new ShootLine(nrofila));
		this.puntos = this.puntos + hundidos;
		this.servidor.restarBarcos(hundidos);
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
	
	public Channel<Boolean> getChannelParaTerminar() {
		return channelParaTerminar;
	}
	
}
