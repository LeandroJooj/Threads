class Volatil{

	private static int numero = 0;
	private static boolean preparado = false;

	private static class MeuRunnable implements Runnable{

		@Override
		public void run(){
			while(!preparado){
				Thread.yield();
				//A Thread avisa pro processador que não tem mais nada pra fazer e pede para ele liberar espaço para que outra Thread possa trabalhar, depois que a outra Thread terminar de trabalhar a nossa Thread volta para o trabalho.
				//Ela sugere para o processador pausar ela para ele priorizar outra thread
			}

			System.out.println(numero);

		}

		
	}



	public static void main(String[] args) {
		Thread t0 = new Thread(new MeuRunnable());
		t0.start();
		numero = 42;
		preparado = true;
	}

}