public class threads{
	public static void main(String[] args) {
		// Thread atual 
		Thread t = Thread.currentThread();	
		System.out.println(t.getName());

		//nova thread 
		Thread t1/*criou um objeto que representa uma nova thread*/ = new Thread(new MeuRunnable()); /*passei uma implementação de runnable para a thread*/
		//t1.run();// continua na  mesma thread
		t1.start();//thread diferente 
		

		Thread t2 = new Thread(
			() -> System.out.println(Thread.currentThread().getName())); 
		t2.start(); 
		//não posso iniciar a mesma thread mais de uma vez 

	}
}