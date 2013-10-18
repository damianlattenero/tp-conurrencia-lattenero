package model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Rol extends Thread{
	public static Lock lockCreacionIT = new ReentrantLock();
	protected Condition iteradorExcMutua = lockCreacionIT.newCondition();

	//esta es la forma que encontré para crear un iterador siempre y cuando tengas los permisos.
	
	protected RowIterator crearIterador(Matriz<?> matriz2, int fila) throws InterruptedException {
		lockCreacionIT.lock();
		RowIterator<?> ri;
		while (!this.puedoReservarFila(matriz2, fila)){
				iteradorExcMutua.await();
		}
	
		ri = new RowIterator(matriz2, fila);
		lockCreacionIT.unlock();
		return ri;
	}
	
	
	
	protected abstract boolean puedoReservarFila(Matriz<?> matriz, int nroFilaAModificar);


}
