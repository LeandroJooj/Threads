import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//CyclicBarrier ser e pra quando temos varias threads em paralelo e em algum momento você precisa que uma espere pela outra
// é como se quando uma thread chegar a um ponto ela vai esperar as outras threads chegarem à aquele ponto também

/*
  é como fazer operações com o banco de dados, várias threads fazendo várias operações de uma vez e você quer que ela só faça o commit depois de um ponto 
*/
public class CyclicBarrier_1 {

  // (432*3) + (3^14) + (45*127/12) = ?
  public static void main(String[] args) {
    CyclicBarrier cyclicBarrier = new CyclicBarrier(3);//o "3" é a quantidade de participantes da barreira
                                                       //é quantas threads vão ter que avisar a barreira para que possam continuar 
    

    ExecutorService executor = Executors.newFixedThreadPool(3);
    
    Runnable r1 = () -> {
      System.out.println(432d*3d);// o "d" no final serve para transformar esses números em double
      await(cyclicBarrier);// quando ele chega nessa ele chama o await do CyclicBarrier, ele vai ficar parado até que 3 threads chamem o await
      System.out.println("Terminei o processamento.");
    };
    Runnable r2 = () -> {
      System.out.println(Math.pow(3d, 14d));
      await(cyclicBarrier);
      System.out.println("Terminei o processamento.");
    };
    Runnable r3 = () -> {
      System.out.println(45d*127d/12d);
      await(cyclicBarrier);
      System.out.println("Terminei o processamento.");
    };
    
    executor.submit(r1);
    executor.submit(r2);
    executor.submit(r3);
    
    executor.shutdown();//shutdown pro programa não ficar executando pra sempre
  }

  private static void await(CyclicBarrier cyclicBarrier) {
    try {
      cyclicBarrier.await();//await é o que vai fazer a thread ficar esperando
    } catch (InterruptedException | BrokenBarrierException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
  }

}