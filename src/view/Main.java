package view;

import java.util.concurrent.Semaphore;

import controller.threadCozinheiro;

public class Main {

	public static void main(String[] args) {
		
		Semaphore semaforo = new Semaphore(1);
		
		for (int prato=1;prato <=5;prato++) {
			Thread tCozin = new threadCozinheiro(prato, semaforo);
			tCozin.start();
		}
	}

}
