import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueue_1 {
// serve para trocar informações entre threads 


  private static final SynchronousQueue<String> FILA = 
      new SynchronousQueue<>();

  public static void main(String[] args) {
    ExecutorService executor = Executors.newCachedThreadPool();

    Runnable r1 = () -> {
      put();// só cosigo escrever alguma coisa nessa fila quando outra thread quer ler alguma coisam dessa fila 
      // Na verdade nunca tem nada dentro dessa fila, o que acontece aqui é apenas uma thread passando para a outra através de uma fila 
      System.out.println("Escreveu na fila!");
    };
    Runnable r2 = () -> {
      String msg = take();
      System.out.println("Pegou da fila! " + msg);
    };

    executor.execute(r1);
    executor.execute(r2);

    executor.shutdown();
  }

  private static String take() {
    try {
      return FILA.take();
//      return FILA.poll(timeout, unit);
          //poll serve para tirar da fila mas ele tem o limite de tempo , se ele não onseguir tirar nada da fila nesse limite de tempo ele vai retornar null
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
      return "Exceção!";
    }
  }

  private static void put() {
    try {
      FILA.put("HEY THERE");
//      FILA.offer(e, timeout, unit);
        //offer serve para colocar na fila mas ele tem o limite de tempo , se ele não onseguir colocar nada da fila nesse limite de tempo ele vai retornar false
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
  }

}