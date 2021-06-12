import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class Executors_Scheduled {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);//agendamento, passamos por parâmetro a quantidade de threads que o deixaremos usar

//    executor.schedule(new Tarefa(), 2, TimeUnit.SECONDS);//agende essa tarefa para daqui 2 segundos
                //schedule faz apenas uma execução



//    executor.scheduleAtFixedRate(new Tarefa(), 0, 1, TimeUnit.SECONDS);
              //faz uma execução com uma taxa de execução fixa 
          //scheduleAtFixedRate(new Tarefa(), initialDelay, period, TimeUnit.SECONDS)
          //initialDelay = Quanto Tempo Vai Demorar Pra Executar A Primeira Tarefa
          //period= De quanto em quanto tempo ele executa essa tarefa
          //Se a tarefa demorou menos de um segundo ele vai esperar pelo menos um segundo,pra executar denovo
          //Se a tarefa por si só demorou mais de  2 segundos ele nem tem que esperar mais(no contexto que estamos usando)

    


    ScheduledFuture<String> future = executor.schedule(new Tarefa(), 2, TimeUnit.SECONDS);

    

    executor.scheduleWithFixedDelay(new Tarefa(), 0, 1, TimeUnit.SECONDS);
                //faz uma execução com uma taxa de intervalo fixa 
          //com o fixedDelay sempre vai ter um intervalo entre as tarefas que serão executadas
          //então se a tarefa demorar 2 segundos para ser completada vai levar 3 segundos(nesse caso) entre uma tarefa e outra


    executor.shutdown();
  }

  public static class Tarefa implements Runnable {
    @Override
    public void run() {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(LocalTime.now());
      String name = Thread.currentThread().getName();
      int nextInt = new Random().nextInt(1000);
      System.out.println(name + ": teste de tarefa! " + nextInt);
    }
  }

}