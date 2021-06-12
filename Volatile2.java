import java.lang.Thread.State;
//analise porque quando deixamos de comentar static int numero o programa volta exceções
public class Volatile2 {

  private static volatile int numero = 0; // volatile serve para dizer pro programa não confiar no que está na memória cache local, mas sim no que estiver na memória ram.
  //você garante que o programa sempre leia o valor mais recente da variável

  //volatile é mais lento porque estou deixando de ler um dado que está no processador para ler um dado que está na memória ram, terei que percorrer um caminho mais longo

  private static volatile boolean preparado = false;
//  private static int numero = 0;
//  private static boolean preparado = false;

  private static class MeuRunnable implements Runnable {

    @Override
    public void run() {
      while (!preparado) {
        Thread.yield();
      }

      if (numero != 42) {
        //quando você vai ler ou escrever na variavel número o que o processador faz na verdade é escrever/ler no cache que está dentro do processador. 
        // Em algum momento o valor que está no cache vai de fato para a memória ram, e quando você tá lendo o valor em algum momento você vai ler o valor que tiver lá na memoria ram
        // mas algumas vezes você vai ler esse valor do seu cache local


//        System.out.println(numero);
        throw new IllegalStateException("hello world!");
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    while (true) {
      Thread t0 = new Thread(new MeuRunnable());
      t0.start();
      Thread t1 = new Thread(new MeuRunnable());
      t1.start();
      Thread t2 = new Thread(new MeuRunnable());
      t2.start();
      
      numero = 42;
      preparado = true;
      
      while (
          t0.getState() != State.TERMINATED
          || t1.getState() != State.TERMINATED
          || t2.getState() != State.TERMINATED
          ) {
        // espera
      }
      
      numero = 0;
      preparado = false;
    }
  }
}