import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class CyclicBarrier_2 {

  private static BlockingQueue<Double> resultados = new LinkedBlockingQueue<>();
                                        //boa pra guardar os resultados 
  
  // (432*3) + (3^14) + (45*127/12) = ?
  public static void main(String[] args) {
    Runnable finalizacao = () -> { //lembrando que aqui estamos apenas declarando o método

      System.out.println("Somando tudo.");
      double resultadoFinal = 0;
      resultadoFinal += resultados.poll();
      resultadoFinal += resultados.poll();
      resultadoFinal += resultados.poll();
      System.out.println("Processamento finalizado. "+ "Resultado final: " + resultadoFinal);
    };

    CyclicBarrier cyclicBarrier = new CyclicBarrier(4, finalizacao);// cyclicBarrier vai chamar o método de finalização depois que três threads chamarem o await;
    //é cíclico porque ele não encerra depois que 3 threads chamam o await 
    ExecutorService executor = Executors.newFixedThreadPool(3);
    
    Runnable r1 = () -> {
     System.out.println(Thread.currentThread().getName());
      resultados.add(432d*3d);
      await(cyclicBarrier);
     System.out.println(Thread.currentThread().getName());
    };
    Runnable r2 = () -> {
     System.out.println(Thread.currentThread().getName());
      resultados.add(Math.pow(3d, 14d));
      await(cyclicBarrier);
     System.out.println(Thread.currentThread().getName());
    };
    Runnable r3 = () -> {
     System.out.println(Thread.currentThread().getName());
      resultados.add(45d*127d/12d);
      await(cyclicBarrier);
     System.out.println(Thread.currentThread().getName());
    };
    
    executor.submit(r1);
    executor.submit(r2);
    executor.submit(r3);
    
    executor.shutdown();
  }

  private static void await(CyclicBarrier cyclicBarrier) {
    try {
      cyclicBarrier.await();
    } catch (InterruptedException | BrokenBarrierException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
  }

  
}