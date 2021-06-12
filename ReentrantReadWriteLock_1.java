
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLock_1 {

private static int i = -1;
  
  private static ReadWriteLock lock = new ReentrantReadWriteLock();
  
  public static void main(String[] args) {
    ExecutorService executor = Executors.newCachedThreadPool();
    
    Runnable r1 = () -> {
      Lock writeLock = lock.writeLock();
      writeLock.lock();
      String name = Thread.currentThread().getName();
      System.out.println(name + " - Escrevendo: " + i);
      i++;
      System.out.println(name + " - Escrito: " + i);
      writeLock.unlock();
    };
    Runnable r2 = () -> {
      Lock readLock = lock.readLock();
      readLock.lock();
      System.out.println("Lendo: " + i);
      System.out.println("Lido: " + i);
      readLock.unlock();
    };


      //posso usar um realock e writelock, que são locks específicos de leitura e escrita
        // o readlock não bloqueia outro lock de leitura, agora se for obter um lock de escrita ninguém pode obter ou segurar um lock de leitura ou escrita
        // caso já tenha alguém segurando algum lock, o lock de leitura vai esperar esses outros locks terminarem suas tarefas, para que ele possa escrever.

    /*
      posso ter um monte de gente lendo e ninguém escrevendo
      na hora da escrita nós paramos o paralelismo para não ter concorrência
    */

    
    for (int i = 0; i < 6; i++) {
      executor.execute(r1);
      executor.execute(r2);
    }
    
    executor.shutdown();
  }
  
}