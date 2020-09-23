package controller;

import java.util.concurrent.Semaphore;

public class threadCozinheiro extends Thread{
	
	private int prato;
	Semaphore semaforo;
	private String nomePrato;
	private double esperaFinal, esperaInicial;
	private double porcem;
	
	public threadCozinheiro(int prato, Semaphore semaforo) {
		this.prato=prato;
		this.semaforo=semaforo;
	}
	
	@Override
	public void run() {
		cozinhar();
		try {
			semaforo.acquire();
			entregar();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		
	}

	private void cozinhar() {
		
		if (prato % 2 !=0) {
			nomePrato= "Sopa de Cebola";
			esperaFinal=(int) ((Math.random()*301)+500);
		} else {
			nomePrato= "Lasanha a Bolonhesa";
			esperaFinal=(int) ((Math.random()*601)+600);
		}
		
		
		System.out.println("Inicio o prato: " + nomePrato);
		esperaInicial=esperaFinal/10;
		
		while (esperaInicial <= (esperaFinal+0.1)) {//correção para sempre alcançar o 100%
			try {
				sleep((int) esperaInicial);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			porcem = (esperaInicial*100/esperaFinal)+0.1;//correção pra imprimir sempre de 10 em 10
			System.out.println(nomePrato + " " + (int)porcem+ "%");
			esperaInicial+=(esperaFinal/10);
		}
		System.out.println(nomePrato + " Pronto para entrega");
	}

	
	
	
	private void entregar() {
		try {
			sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(nomePrato + " Entregue");
	}
}
