package battleship;

import java.util.List;

public class ScanAndShoot implements Estrategia {

	@Override
	public void estrategia(Jugador jugador) {
		int filas = jugador.getServidor().getMatriz().getCantFilas();

		for (int i = 0; i < filas && !jugador.preguntarSiTerminoElJuego(); i++) {
			if(this.dispararBarcos(jugador.scanLine(i), i, jugador)){
				break;
			}
		}
	}

	private boolean dispararBarcos(List<Integer> scanLine, int fila,
			Jugador jugador) {
		
		boolean b = false;
		for (Integer i : scanLine) {
			jugador.shoot(fila, i);
			b = jugador.preguntarSiTerminoElJuego();
			if(b){
				break;
			}
		}
		return b;
	}
}
