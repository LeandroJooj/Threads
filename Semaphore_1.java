import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

//semáforo serve para fazer o controle da concocorrência e paralelismo
// usamos o semaforo quando sabemos a quantidade de threads queremos que utilize um trecho de código ao mesmo tempo
// serve como uma barreira que permite ou não que a thread continue o processamento baseado na quantidade de threads que nós permitamos que prossiga
public class Semaphore_1 {

  private static final Semaphore SEMAFORO = new Semaphore(3);//somente 3 threads podem passar nesse semaforo
  
  public static void main(String[] args) {
    ExecutorService executor = Executors.newCachedThreadPool();

    Runnable r1 = () -> {
      String name = Thread.currentThread().getName();
      int usuario = new Random().nextInt(10000);
      
      acquire();//na execução do programa, enquanto cada tarefa espera sua vez para executar, cada tarefa fica presa na própria linha 26
      System.out.println("Usuário " + usuario
          + " se cadastrou com a thread " + name + "\n");
      sleep();
      SEMAFORO.release();// release é o que libera a vaga depois de completar a tarefa, como "beleza, terminei o que tinha que fazer, vou dar a minha vaga para a próxima tarefa que precisa ser executada".
    };
    
    for (int i = 0; i < 500; i++) {
      executor.execute(r1);
    }
    
    executor.shutdown();
  }

  private static void sleep() {
    // espera de 1 a 6 segundos
    try {
      int tempoEspera = new Random().nextInt(6);
      tempoEspera++;
      Thread.sleep(1000 * tempoEspera);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
  }

  private static void acquire() {
    try {
      SEMAFORO.acquire();// SEMAFORO.acquire() vai tentar passar no semáforo lembrando a quantidade de vagas
        //as três primeiras threads conseguem passar pelo semáforo, as outras ficam esperando no acquire
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
  }

}