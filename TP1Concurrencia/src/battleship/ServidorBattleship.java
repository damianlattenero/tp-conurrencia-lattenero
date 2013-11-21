package battleship;

import java.util.ArrayList;
import java.util.List;

import matriz.IteradorLector;
import matriz.Lector;
import matriz.Matriz;
import ar.edu.unq.pconc.Channel;

public class ServidorBattleship {

	final static int UT = 300;
	private Matriz<Barco> matriz;
	private int cantBarcos;
	final static Channel<String> channel = new Channel<String>(1);
	final private List<RecibirPedidosDeTerminacion> channelsDeJugadores = new ArrayList<>();
	private List<Jugador> jugadores = new ArrayList<Jugador>();

	public ServidorBattleship(int filas, int columnas, int cantBarcos) {
		super();
		this.matriz = new Matriz<Barco>(filas, columnas);
		this.cantBarcos = cantBarcos;
	}

	public Matriz<Barco> getMatriz() {
		return matriz;
	}

	public synchronized int getCantBarcos() {
		return cantBarcos;
	}

	public synchronized void restarBarcos(int n) {
		this.cantBarcos -= n;
		System.out.println(cantBarcos);
	}

	private void dibujarTablero() {
		for (int i = 0; i < this.matriz.getCantFilas(); i++) {
			this.dibujarFila(i);
		}
	}

	public void imprimirJugadores() {
		for (Jugador j : this.jugadores) {
			System.out.println(j.getNombre() + " " + j.getPuntos());
		}
	}

	private void dibujarFila(final int i) {
		final IteradorLector<Barco> iterador = this.matriz
				.getIteradorLectorDeFila(i);
		while (iterador.hasCurrent()) {
			iterador.current().leer(
					new DibujarBarco(channel, i, iterador.getIndiceActual()));
			iterador.next();
		}
		iterador.end();
	}

	private void dibujarGrilla() {
		channel.send("draw" + "-" + this.matriz.getCantFilas() + "-"
				+ this.matriz.getCantColumnas());
	}

	private void llenarTablero() {
		for (int i = 0; i < this.cantBarcos; i++) {
			int posI = (int) Math.round(Math.random()
					* (this.getMatriz().getCantFilas() - 1));
			int posJ = (int) Math.round(Math.random()
					* (this.getMatriz().getCantColumnas() - 1));
			while (this.getMatriz().leerCelda(new Lector<Barco, Boolean>() {
				@Override
				public Boolean read(Barco e) {
					return e != null;
				}
			}, posI, posJ)) {
				posI = (int) Math.round(Math.random()
						* (this.getMatriz().getCantFilas() - 1));
				posJ = (int) Math.round(Math.random()
						* (this.getMatriz().getCantColumnas() - 1));
			}
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
		ServidorBattleship s = new ServidorBattleship(6, 6, 25);
		s.inicializarJuego();
		Jugador j1 = new Jugador(s, new ShootAllWithoutScan(), "jug 1", 4);
		Jugador j2 = new Jugador(s, new ScanAndShoot(), "jug 2", 5);
		Jugador j3 = new Jugador(s, new RandomScanAndShoot(), "jug 3", 6);
		s.getJugadores().add(j1);
		s.getJugadores().add(j2);
		s.getJugadores().add(j3);
		s.getChannelsdejugadores().add(new RecibirPedidosDeTerminacion(s, 4));
		s.getChannelsdejugadores().add(new RecibirPedidosDeTerminacion(s, 5));
		s.getChannelsdejugadores().add(new RecibirPedidosDeTerminacion(s, 6));

		for (Jugador j : s.getJugadores()) {
			j.start();
		}

		for (RecibirPedidosDeTerminacion r : s.getChannelsdejugadores()) {
			r.start();
		}
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public List<RecibirPedidosDeTerminacion> getChannelsdejugadores() {
		return channelsDeJugadores;
	}

	public synchronized boolean terminoElJuego() {
		return this.getCantBarcos() <= 0;
	}

	public synchronized void eliminarChannelDeJugador(
			RecibirPedidosDeTerminacion recibirPedidosDeTerminacion) {
		this.channelsDeJugadores.remove(recibirPedidosDeTerminacion);
		if (this.channelsDeJugadores.isEmpty()) {
			this.imprimirJugadores();
		}
	}

}
