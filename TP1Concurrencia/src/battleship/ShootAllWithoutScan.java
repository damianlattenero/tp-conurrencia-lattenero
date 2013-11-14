package battleship;

public class ShootAllWithoutScan implements Estrategia{

	@Override
	public void estategia(Jugador jugador) {
	int filas = jugador.getServidor().getMatriz().getCantFilas();
		
		for(int i=0;i<filas;i++){
			jugador.shootLine(i);
		}
		
	}

}
