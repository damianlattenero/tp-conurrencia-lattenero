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
	
	public void leer(){
		lock.lock();
		while(isHayEscritor() || this.getContenido() == null){
			try {
				conditionLectura.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.setNroLectores(this.getNroLectores()+1);
			this.getContenido().leer();
			this.setNroLectores(this.getNroLectores()-1);
			if(this.getNroLectores() == 0){
				conditionEscritura.signal();
			}
		}
		lock.unlock();
	}
	
	public void escribir(ReadWrite contenido2){
		lock.lock();
		while(this.isHayEscritor() || this.getNroLectores() > 0){
			try {
				conditionEscritura.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.getContenido().escribir();
			contenido2.escribir();
			conditionLectura.signalAll();
		}
		lock.unlock();
	}

	//getters y setters
	
	public int getNroLectores() {
		return nroLectores;
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

	public E getContenido() {
		return contenido;
	}

	public void setContenido(E contenido) {
		this.contenido = contenido;
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
