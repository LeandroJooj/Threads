import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.Thread;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;


class ColecoesConcorrencia{
private static List<String> lista = new  CopyOnWriteArrayList<>();
		// CopyOnWriteArrayList é uma classe que é thread-safe
		// thread-safe = classes que são seguras para serem utilizadas em um cenário de multithread
		// é extremamente pesado pois quando você faz qualquer mudança no array ele realiza uma cópia do array inteiro 
		// você não utiliza essa classe se tiver muitas operações de escrita e/ou remoção no array
		// utilizamos ela quando realizamos poucas ou nenhuma alteração na lista contra uma quantidade imensa de leituras(operações de get, indexof,...)


private static Map<Integer, String> mapa = new  ConcurrentHashMap<>();
//esta é thread-safe, ela sempre vai ser um pouco mais lenta porque em alguma parte dela ela terá que ser sincronizada 
		

private static BlockingQueue<String> fila = new  LinkedBlockingQueue<>();
// é uma boa alternativa pois também é thread-safe, e por ser fila é boa para retirar os elementos com o fila.poll 
// LinkedBlockingQueue<>(Tamanho da fila)

// também tem a LinkedBlockingDeque que é uma fila de duas saídas, podemos cortar essa fila, colocando um elemento no início, meio ou fim dela. 

		public static void main(String[] args) throws InterruptedException{
		
		
		
		CalculaIdade runnable = new CalculaIdade();

		Thread T0 = new Thread(runnable); 
		Thread T1 = new Thread(runnable);
		Thread T2 = new Thread(runnable);
		
		

		T0.start();
		T1.start();
		T2.start();
		Thread.sleep(500); 
		System.out.println(fila);
		
		}

		public static class CalculaIdade implements Runnable{
		@Override
		public void run(){
			//lista.add("Salve ");
			//mapa.put(new Random().nextInt()," Salve");
			fila.add("Carros !!");
			String name = Thread.currentThread().getName(); 
			System.out.println(name + " inseriu na lista !");
		}
	}
}