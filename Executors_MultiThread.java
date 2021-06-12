import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Executors_MultiThread {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    ExecutorService executor = null;
    try {
//      executor = Executors.newFixedThreadPool(3);
      executor = Executors.newCachedThreadPool();// a diferença entre newFixedThreadPool e newCachedThreadPool é que no newCachedThreadPool não é necessário passar o tamanho da pool que você quer usar
      // esses dois definem quantas threads irei usar no meu programa 
      // no cached basicamente falamos para o executor criar uma thread nova sempre que precisar 
          //exemplo do cached: você manda o executor fazer três tarefas, ele executa a primeira e a segunda, no momento que ele vai executar a terceira e vê que terminou de executar a primeira ele usa a primeira thread para executar a terceira tarefa, se nenhuma das duas threads anteriores tiverem acabado ele cria uma terceira thread para a terceira tarefa
          //se a thread ficar muito tempo ser utilizada ele mesmo vai lá e mata thread, ele não tem limite de threads então CUIDADO na hora de usá-lo para não fazer ele ficar criando um monte de thread


      List<Tarefa> lista = new ArrayList<>();
      //invokeAll executa todas as tarefas de uma vez só
      for (int i = 0; i < 10; i++) {
        lista.add(new Tarefa());//colocamos todas as tarefas na lista 
      }
      
//      String string = executor.invokeAny(lista); //funciona como o invokeAll, mas ele invoca a primeira tarefa que terminar
//      System.out.println(string);
      
      List<Future<String>> list = executor.invokeAll(lista);
      



      for (Future<String> future : list) {
        System.out.println(future.get());
      }
//      Future<String> f1 = executor.submit(new Tarefa());
//      System.out.println(f1.get());
//      Future<String> f2 = executor.submit(new Tarefa());
//      Future<String> f3 = executor.submit(new Tarefa());
//      System.out.println(f2.get());
//      System.out.println(f3.get());
      executor.shutdown();
    } catch (Exception e) {
      throw e;
    } finally {
      if (executor != null) {
        executor.shutdownNow();
      }
    }
  }

  public static class Tarefa implements Callable<String> {
    @Override
    public String call() throws Exception {
      String name = Thread.currentThread().getName();
      int nextInt = new Random().nextInt(1000);
      return name + ": teste de tarefa! " + nextInt;
    }
  }  
  
}