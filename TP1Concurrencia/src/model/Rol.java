package model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Rol extends Thread{
	Matriz<?> matriz;
	public static Lock lockCreacionIT = new ReentrantLock();
	protected Condition iteradorExcMutua = lockCreacionIT.newCondition();

	//esta es la forma que encontré para crear un iterador siempre y cuando tengas los permisos.
	
	protected RowIterator crearIterador(int fila) {
		lockCreacionIT.lock();
		RowIterator ri;
		while (!this.puedoReservarFila(fila)){
		try {
				iteradorExcMutua.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		ri = new RowIterator(fila);
		lockCreacionIT.unlock();
		return ri;
	}
	
	protected boolean hayEscritores(int x,int nroFilaAModificar){
		return this.getMatriz().getEstructura()[nroFilaAModificar][x].isHayEscritor();
	}
	
	protected abstract boolean puedoReservarFila(int nroFilaAModificar);

	public Matriz<?> getMatriz() {
		return matriz;
	}

	public void setMatriz(Matriz<?> matriz) {
		this.matriz = matriz;
	}
}
