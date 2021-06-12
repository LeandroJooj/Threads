import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock_1 {

  private static int i = -1;
  
  private static Lock lock = new ReentrantLock();
  
  public static void main(String[] args) {
    ExecutorService executor = Executors.newCachedThreadPool();
    
    Runnable r1 = () -> {
      lock.lock();//entrando no bloco de syncronized
      // a thread que chegar aqui primeiro irá obter o lock, as outras threads não, elas vão ficar esperando
      // a vantagem do lock em relação ao syncronized é que posso dar unlock em qualquer lugar, em outra classe por exemplo
      //posso fazer vários locks contanto que eu faça vários unlocks também
      String name = Thread.currentThread().getName();
      i++;
      System.out.println(name + ": " + i);
      lock.unlock();//saindo do bloco de syncronized
    };
    
    for (int i = 0; i < 6; i++) {
      executor.execute(r1);// manda executar a tarefa
    }
    
    executor.shutdown();
  }
  
}