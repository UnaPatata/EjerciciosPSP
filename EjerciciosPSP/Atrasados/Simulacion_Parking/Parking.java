package Simulacion_Parking;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Parking {
//	/* ENUNCIADO */
//	CONTROL DE UN APARCAMIENTO
//	Un aparcamiento gratuito tiene capacidad para 50 coches. El parking tiene además un cartel que indica si el
//	parking está lleno o no.
//
//	Al parking llegan coches con una periodicidad aleatoria de 50 msg cada uno (no todos en
//	50msg. OJO!!) y se queda en el parking no más de 500 msg.
//
//	El cartel está apagado hasta que llega el coche número 50 que en ese momento se enciende la
//	palabra COMPLETO. En cuanto hay plazas indica parking LIBRE.
//
//	Codificar el programa para simular 250 coches (o los que queramos en función de una variable) que llegan, están un tiempo y
//	salen del parking.
//
//	Cuando salga el último coche, debe terminar también el proceso del cartel.
//	Se pide :
//
//	    1. Simula el programa empezando con el aparcamiento vacío.
//	    2. Simula el programa comenzando con el aparcamiento lleno En este caso hay dos tipo de coches,
//			los que solo salen después de haber estado no más de 500 msg. y los que llegan como en el apartado a). 
	
	public static int CAPACIDAD = 50;
	public static int MAX_WAIT = 50;
	public static int MAX_ESTANCIA = 500;
	public static int MAX_COCHES = 250;
	public static CountDownLatch waitAllEnd = new CountDownLatch(MAX_COCHES);
	public String cartel;
	public Semaphore semaforo = new Semaphore(CAPACIDAD);
	public CyclicBarrier waitAllReady = new CyclicBarrier(MAX_COCHES);
	
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < MAX_COCHES; i++) {
			Thread.sleep((long) (Math.random() * MAX_WAIT));
			
		}
		waitAllEnd.await();
	}//end-main
}//end-class
