package view;

import java.util.Random;
import java.util.concurrent.Semaphore;

import controller.ThreadOpCaixa;


public class MainOpCaixa {

	
	public static void main(String[] args) {
		Semaphore semaforo1 = new Semaphore(1);
		Semaphore semaforo2 = new Semaphore(2);
		
		
		
		for(int i = 1; i <=20; i++) {
			int rnd = new Random().nextInt(101);
			int valor = new Random().nextInt(500);
			Thread tCaixa = new ThreadOpCaixa(i,rnd,semaforo1,semaforo2,valor);
			tCaixa.start();
		}

	}

}
