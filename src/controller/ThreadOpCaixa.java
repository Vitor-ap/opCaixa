package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadOpCaixa extends Thread{
	static int saldoConta  = new Random().nextInt(10001);;
	private Semaphore semaforo1;
	private Semaphore semaforo2;
	private int rnd;
	private int id;
	private int valor;
	private static int tipo;
	
	
	public ThreadOpCaixa(int i, int rnd, Semaphore semaforo1, Semaphore semaforo2, int valor) {
		this.semaforo1 = semaforo1;
		this.semaforo2 = semaforo2;
		this.rnd = rnd;
		this.id = i;
		this.valor = valor;
		
	}

	@Override
	public void run(){
		decideTransacao();
	
			try {
				semaforo2.acquire();
				transacao();
				}catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo2.release();
			}
		}	

	


	private void transacao() {
		try {
		if (tipo ==3) {
			saldoConta += valor;
			System.out.println("Transacao #"+id+": Depósito de "+valor+". Saldo da conta: "+saldoConta);
			Thread.sleep(2000);
		}
		if (tipo == 2) {
			saldoConta -= valor;
			System.err.println("Transacao #"+id+": Saque de "+valor+". Saldo da conta "+saldoConta);
			Thread.sleep(2000);
		}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void decideTransacao() {
		if (rnd % 2 ==1) {
			tipo = 3;
		}
		else {
			tipo = 2;
		}
		
	}
}
