
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Executors_SingleThread_Callable {

  public static void main(String[] args) throws Exception {
    ExecutorService executor = null;
    try {
      executor = Executors.newSingleThreadExecutor();//executor de apenas uma thread          
        //o ato de eu estar criando um executor já é multithread, pois ao criálo passarei a ter duas threads
        //quando eu crio um executor eu tenho que depois chamar um método shutdown, executor.shutDown() pode não encerrar a thread, pra isso usamo o awaitTermination para esperar todas as tarefas se finalizarem para encerrar a thread
        //podemos mandar um executor fazer várias coisas, diferente de Thread nós podemos iniciar um executor várias vezes

      //executor.execute(new Tarefa());//execute executa, passamos pra ele uma instância de runnable 
      //uma alternativa ao execute é o submit,
      //com submit temos o retorno de Future<tipo> que nos permite usar isDone e get 
      Future<String> future = executor.submit(new Tarefa());//estamos falando "executor execute esta Tarefa aqui"
      

      //shutdown nada mais faz do que matar o executor e esperar s tarefas antigas terminarem, basicamente faz ele não aceitar mais tarefas novas
      System.out.println(future.isDone());//checa se a tarefa foi finalizada
//      System.out.println(future.get()); //no futre.get() ele já espera para que a tarefa finalize
      System.out.println(future.get(1, TimeUnit.SECONDS));// nesse caso o get com timeout espera no máximo 1 segundo até ele terminar a execução
        //quando o future não retorna depois do tempo nós temos uma exceção
      System.out.println(future.isDone());
      
//      executor.shutdown();
//      executor.awaitTermination(10, TimeUnit.SECONDS);
//      System.out.println(future.isDone());
    } catch (Exception e) {
      throw e;
    } finally {
      if (executor != null) {
//        executor.shutdown();
        executor.shutdownNow();//encerra de maneira abrupta
      }
    }
  }
  
  public static class Tarefa implements Callable<String> {
      //Callable ao invés de ter o método run ele tem o método call e ao invés de apenas executar ele executa e retorna um valor
      //No runnable  eu nunca atenho acesso ao valor calculado pela minha tarefa , no callable ela realiza a tarefa e retorna uma string e com essa string eu consigo fazer alguma coisa
    @Override
    public String call() throws Exception {
      Thread.sleep(1000);
      String name = Thread.currentThread().getName();
      int nextInt = new Random().nextInt(1000);
      return name + ": se cadastrou! " + nextInt;
    }
  }

}

//timout é importante porque as vezes não queremos ficar esperando por muito tempo a tarefa ser executada, pode ser que ela esteja fazendo um cálculo muito complexo
//as vezes uma tarefa demora muito mais do que deveria, com timout não irá ser esperada
