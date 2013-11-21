package battleship;

public class ShootAllWithoutScan implements Estrategia {

	@Override
	public void estrategia(Jugador jugador) {
		int filas = jugador.getServidor().getMatriz().getCantFilas();
		for (int i = filas - 1; i >= 0 && !jugador.preguntarSiTerminoElJuego(); i--) {
			jugador.shootLine(i);
		}
	}

}
