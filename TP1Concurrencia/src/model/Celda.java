package model;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Celda<E extends ReadWrite> {
	
	int nroFila;
	int nroColumna;
	E contenido;
	int nroLectores;
	boolean hayEscritor;
	
	public Celda(int nroC, int nroF){
		this.contenido = null;
		this.nroColumna = nroC;
		this.nroFila = nroF;
		this.hayEscritor = false;
		this.nroLectores = 0;
	}
	
	public static Lock lock = new ReentrantLock();
	private Condition conditionLectura = lock.newCondition();
	private Condition conditionEscritura = lock.newCondition();
	
	//separar la operacion de lectura
	
	public void leer() throws InterruptedException{
		this.empezarALeer();
			this.getContenido().leer();
		this.terminarDeLeer();
	}
	
	public synchronized void empezarALeer() throws InterruptedException{
		
		while(isHayEscritor() || this.getContenido() == null){
				conditionLectura.await();
		}
		this.setNroLectores(this.getNroLectores()+1);
	}
	
	public synchronized void terminarDeLeer(){
		
		this.setNroLectores(this.getNroLectores()-1);
		conditionEscritura.signalAll();
		
	}
	
	public void escribir() throws InterruptedException{
		this.empezarAEscribir();
	
			this.getContenido().escribir();
		
		this.terminarDeEscribir();
	}
	
	private synchronized void empezarAEscribir() throws InterruptedException {
		
		while(this.getNroLectores() > 0 || this.hayEscritor){
			wait();
		}
		this.setHayEscritor(true);


	}


	private synchronized void terminarDeEscribir()  throws InterruptedException {

		this.setHayEscritor(false);
		notifyAll();
		
	}

	
	//getters y setters
	
	
	
	public int getNroLectores() {
		return nroLectores;
	}

	public E getContenido() {
		return contenido;
	}

	public void setNroLectores(int nroLectores) {
		this.nroLectores = nroLectores;
	}

	public boolean isHayEscritor() {
		return hayEscritor;
	}

	public void setHayEscritor(boolean hayEscritor) {
		this.hayEscritor = hayEscritor;
	}
	
	public int getNroFila() {
		return nroFila;
	}

	public void setNroFila(int nroFila) {
		this.nroFila = nroFila;
	}

	public int getNroColumna() {
		return nroColumna;
	}

	public void setNroColumna(int nroColumna) {
		this.nroColumna = nroColumna;
	}

	

	public void setContenido(E object) {
		this.contenido = object;
	}

	public static Lock getLock() {
		return lock;
	}

	public static void setLock(Lock lock) {
		Celda.lock = lock;
	}

	public Condition getConditionLectura() {
		return conditionLectura;
	}

	public void setConditionLectura(Condition conditionLectura) {
		this.conditionLectura = conditionLectura;
	}

	public Condition getConditionEscritura() {
		return conditionEscritura;
	}

	public void setConditionEscritura(Condition conditionEscritura) {
		this.conditionEscritura = conditionEscritura;
	}
	
	

}
