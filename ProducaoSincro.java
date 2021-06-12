class ProducaoSincro{
	static int i = 0;

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
			int a = 1;	
			synchronized(this){ 
				i++; 
				a += i;
			}
			int elevacao= i*70*a; //aqui já não importa se alguém mexeu no seu i ou a pq você já fez o que quera 

		}
	}
}