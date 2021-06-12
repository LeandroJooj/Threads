
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
class SincComList{
		private static List<String> lista = new  ArrayList<>();


		public static void main(String[] args) throws InterruptedException{
		
		lista = Collections.synchronizedList(lista);// faz com que essa lista seja acessada em algumas operações por uma única thread
		// fazendo isso você protege a lista


//utilize a versão do synchronizedXXX de acordo com o sey tipo de coleção
		
		// lista = Collections.synchronizedMap(lista);
		// lista = Collections.synchronizedCollection(lista);
		// lista = Collections.synchronizedSet(lista);

		CalculaIdade runnable = new CalculaIdade();

		Thread T0 = new Thread(runnable); 
		Thread T1 = new Thread(runnable);
		Thread T2 = new Thread(runnable);
		

		T0.start();
		T1.start();
		T2.start();
		Thread.sleep(500); //isso lança a exceção de interrupção 
		System.out.println(lista);
		
		}

		public static class CalculaIdade implements Runnable{
		@Override
		public void run(){
			lista.add("yeah");
			String nome = Thread.currentThread().getName();
			System.out.println(nome + "inseriu");
		}
	}
}