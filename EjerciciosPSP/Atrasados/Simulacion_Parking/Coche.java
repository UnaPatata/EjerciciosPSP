package Simulacion_Parking;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Coche extends Thread{
	private int tiempoEstancia;
	private Semaphore semaforo;
	private CountDownLatch waitAllEnd;
	private CyclicBarrier waitAllReady;

	public Coche(int tiempo,Semaphore semaforo,CountDownLatch countDown,CyclicBarrier waitAllReady) {
		this.tiempoEstancia = tiempo;
		this.semaforo = semaforo;
		this.waitAllEnd = countDown;
		this.waitAllReady = waitAllReady;
		this.start();
	}

	public Coche(Semaphore semaforo,CountDownLatch countDown,CyclicBarrier waitAllReady) {
		this.tiempoEstancia = (int) Math.random() * Parking.MAX_ESTANCIA;
		this.semaforo = semaforo;
		this.waitAllEnd = countDown;
		this.waitAllReady = waitAllReady;
		this.start();
	}

	@Override
	public void run() {
		boolean acquired = false;
		try {
			waitAllReady.await();
			while(!acquired) {
				acquired = semaforo.tryAcquire();
				if(acquired) {
					sleep(tiempoEstancia);
					semaforo.release();
				}
			}//end-while
			waitAllEnd.countDown();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}//end-run
}//end-class
