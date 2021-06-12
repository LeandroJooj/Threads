import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

class OperacoesAtomicas{
	static AtomicInteger i =new AtomicInteger(-1);
	//Também tem AtomicLong

	static AtomicBoolean b =new AtomicBoolean(false);

	static AtomicReference<Object> o = new AtomicReference<>(new Object);
	//AtomicReference<Object> pode ser qualquer classe que quiser como  AtomicReference<MinhaClasse>



	public static void main(String[] args) {
			MeuRunnable runnable = new MeuRunnable();

			Thread T0 = new Thread(runnable); 
			Thread T1 = new Thread(runnable);
			Thread T2 = new Thread(runnable);
			Thread T3 = new Thread(runnable);

			T0.start();
			T1.start();
			T2.start();
			T3.start();
		}

	public static class MeuRunnable implements Runnable{
		@Override
		public void run(){	
			// i++; //Não dá mais pra incrementar pq deixou de ser int
			String name = Thread.currentThread().getName();
			// System.out.println(name + ":" + i.incrementAndGet());
				//incrementAndGet faz com que os números não se repitam, incrementAndGet faz a operação de uma vez só, ele incrementa e já pega, é uma operação que vai ser feita sem nenhuma thread te atrapalhar no meio do caminho 
			// System.out.println(name + ":" + b.compareAndExchange(false, true));			
				//compareAndExchange(valorEsperado, ValorQueVaiSair) __ se entrar false sai true
			System.out.println(name + ":" + o.getAndSet(new Object));
				//getAndSet() pega o valor que tava lá e coloca um novo
				
		}
	}

	//quando queremos realizar ações que não podem ser feitas de forma assíncrona
	//podemos usar as classes atômicas pois são mais perfomáticas que
	//synchronized repare que usando classes atomicas não há o problema de o número se repetir
}