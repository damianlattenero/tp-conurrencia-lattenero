package model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Celda<E> {

	E contenido;
	int nroLectores;
	boolean hayEscritor;

	public Celda() {
		this.contenido = null;
		this.hayEscritor = false;
		this.nroLectores = 0;
	}

	public static Lock lockLectura = new ReentrantLock();
	private Condition conditionLectura = lockLectura.newCondition();
	
	public static Lock lockEscritura = new ReentrantLock();
	private Condition conditionEscritura = lockEscritura.newCondition();

	// separar la operacion de lectura

	public void leer(Lector<E> r) {
		r.read(this.getContenido());
	}

	public void empezarALeer() {
		lockLectura.lock();

		while (isHayEscritor() || this.getContenido() == null) {
			conditionLectura.awaitUninterruptibly();
		}
		this.setNroLectores(this.getNroLectores() + 1);
		
		lockLectura.unlock();
	}

	public void terminarDeLeer() {
		lockLectura.lock();
		lockEscritura.lock();
		

		this.setNroLectores(this.getNroLectores() - 1);
		if (this.getNroLectores() == 0) {
			conditionEscritura.signal();
		}
		
		lockEscritura.unlock();
		lockLectura.unlock();
	}

	// inicializar Celda
	public void escribir(Escritor<E> w) {
		w.write(this);
	}

	public void empezarAEscribir() {
		lockEscritura.lock();

		while (this.getNroLectores() > 0 || this.hayEscritor) {
			conditionEscritura.awaitUninterruptibly();
		}
		this.setHayEscritor(true);
		
		lockEscritura.unlock();
	}

	public void terminarDeEscribir() {
		lockEscritura.lock();
		lockLectura.lock();

		this.setHayEscritor(false);
		conditionLectura.signalAll();
		conditionEscritura.signalAll();
		
		lockLectura.unlock();
		lockEscritura.unlock();
	}

	public E getContenido() {
		return contenido;
	}

	public void setContenido(E contenido) {
		this.contenido = contenido;
	}

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
