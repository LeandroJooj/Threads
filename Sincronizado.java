class Sincronizado{ // exemplo


	static int i = -1;
	public static void main(String[] args) {
		MeuRunnable runnable = new MeuRunnable();
		for (int i; i <5 ;i++ ) {
			runnable.run();
		}

		Thread T0 = new Thread(runnable); 
		Thread T1 = new Thread(runnable);
		Thread T2 = new Thread(runnable);
		Thread T3 = new Thread(runnable);

		T0.start();
		T1.start();
		T2.start();
		T3.start();
		}


	public static void imprime(){
		i++; 
		String name = Thread.currentThread().getName();
		System.out.println(name + ":" + i);

	}




	public static class MeuRunnable implements Runnable{
		static Object Lock1 = new Object(); 
		static Object Lock2 = new Object(); 

		@Override
		// public synchronized void run(){
		public void run(){

			imprime();		

			synchronized(Lock1){ // só pode executar dentro dessa instância de runnable
			// Se os dois forem Lock então poderemos ter duas threads por vez acessando as variáveis
				i++; 
				String name = Thread.currentThread().getName();
				System.out.println(name + ":" + i);
			}
			
			// synchronized(Lock2){ 
			// //se os dois  forem synchronized(this) então é a mesma coisa de um só, já que this é um objeto que vai acessar a variável e só pode uma por vez
			// 	i++; 
			// 	String name = Thread.currentThread().getName();
			// 	System.out.println(name + ":" + i);
			// }


		}
	}

	public static class OutroRunnable implements Runnable{
		static Object Lock3= new Object(); 

		synchronized(Lock3){// só pode executar nessa instância
			System.out.println(i++);
		}

		synchronized(Sincronizado.class){//pode ser na JVM inteira

		}

	}	
}