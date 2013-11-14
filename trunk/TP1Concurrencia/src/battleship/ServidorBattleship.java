package battleship;

import matriz.IteradorLector;
import matriz.Matriz;
import ar.edu.unq.pconc.Channel;

public class ServidorBattleship {

	final static int UT = 1000;
	private Matriz<Barco> matriz;
	private int cantBarcos;
	final static Channel<String> channel = new Channel<>(1);

	public ServidorBattleship(int filas, int columnas, int cantBarcos) {
		super();
		this.matriz = new Matriz<Barco>(filas, columnas);
		this.cantBarcos = cantBarcos;
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
		ServidorBattleship s = new ServidorBattleship(9, 9, 9);
		s.inicializarJuego();
		Jugador j1 = new Jugador(s, new ShootAllWithoutScan());
		Jugador j2 = new Jugador(s, new ScanAndShoot());
		Jugador j3 = new Jugador(s, new SelectScanAndShoot());
		j1.estrategia();
		j2.estrategia();
		j3.estrategia();
	}

}
