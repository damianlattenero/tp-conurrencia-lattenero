package battleship;

import java.util.ArrayList;
import java.util.List;

import matriz.IteradorLector;
import matriz.Matriz;
import ar.edu.unq.pconc.Channel;

public class ServidorBattleship extends Thread{

	final static int UT = 300;
	private Matriz<Barco> matriz;
	private int cantBarcos;
	final static Channel<String> channel = new Channel<String>(1);
	final static Channel<Boolean> channel2 = new Channel<Boolean>(2);
	private List<Jugador> jugadores;

	public ServidorBattleship(int filas, int columnas, int cantBarcos) {
		super();
		this.matriz = new Matriz<Barco>(filas, columnas);
		this.cantBarcos = cantBarcos;
		this.setJugadores(new ArrayList<Jugador>());
	}
	
	public Matriz<Barco> getMatriz() {
		return matriz;
	}

	public int getCantBarcos() {
		return cantBarcos;
	}	

	private void dibujarTablero() {
		for(int i=0; i<this.matriz.getCantFilas(); i++){
			this.dibujarFila(i);
		}
	}
	
	@Override
	public void run() {
		//no llenar buffer de trues innecesarios
		
		while(!this.terminoElJuego()){
			for(Jugador j : this.jugadores){
				j.getServidor().channel2.send(!this.terminoElJuego());
			}
		}
		for(Jugador j : this.jugadores){
			System.out.println(j.getNombre() + " " + j.getPuntos());
		}
	}
	

	private void dibujarFila(final int i) {
		final IteradorLector<Barco> iterador = this.matriz.getIteradorLectorDeFila(i);
		while (iterador.hasCurrent()) {
			iterador.current().leer(new DibujarBarco(channel, i, iterador.getIndiceActual()));
			iterador.next();
		}
		iterador.end();
	}

	private void dibujarGrilla() {
		channel.send("draw" + "-" + this.matriz.getCantFilas() + "-"
				+ this.matriz.getCantColumnas());
	}
	
	private void llenarTablero() {
		for(int i=0; i < this.cantBarcos; i++) {
			int posI = (int) Math.round(Math.random() * (this.getMatriz().getCantFilas() - 1));
			int posJ = (int) Math.round(Math.random() * (this.getMatriz().getCantColumnas() - 1));
			this.getMatriz().escribirCelda(new PonerBarco(), posI, posJ);
		}
	}	
	
	public void inicializarJuego() {
		this.dibujarGrilla();
		this.llenarTablero();
		this.dibujarTablero();
	}
	
	public <T> T execute(Command<Matriz<Barco>, T> c) {
		T result = c.apply(this.matriz);
		this.dibujarTablero();
		return result;
	}
	
	public static void dormir(double d) {
		try {
			Thread.sleep((long) (ServidorBattleship.UT * d));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}		
	
	public static void main(String[] args) {
		ServidorBattleship s = new ServidorBattleship(9, 9, 16);
		s.inicializarJuego();
		Jugador j1 = new Jugador(s, new ShootAllWithoutScan(), "jug 1");
		Jugador j2 = new Jugador(s, new SelectScanAndShoot(), "jug 2");
		Jugador j3 = new Jugador(s, new SelectScanAndShoot(), " jug 3");
		s.getJugadores().add(j1);
		s.getJugadores().add(j2);
		s.getJugadores().add(j3);
		s.start();
		//j1.start();
		j2.start();
		j3.start();
	}

	public void setCantBarcos(int cantBarcos) {
		this.cantBarcos = cantBarcos;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	

	public boolean terminoElJuego() {
		return this.getCantBarcos() <= 0;
	}
	
	

}
