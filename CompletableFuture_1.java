import java.util.concurrent.CompletableFuture;
public class CompletableFuture_1 {
// timerTasks funcionam de maneira similares a executores e consegue agendar coisas, mas é melhor usar executores
// CompletableFuture  = API gigantesca,  retorna uma varável que será completada/processada em algum momento do futuro 


  public static void main(String[] args) {
    CompletableFuture<String> processe = processe();
    
    CompletableFuture<String> thenApply = 
        processe.thenApply(s -> s + " Mercedez > Ferrari!");// pega a string que retorna no processe e concatena com Mercedez > Ferrari
    
    CompletableFuture<Void> thenAccept = 
        thenApply.thenAccept(s -> System.out.println(s));
    
    System.out.println("____________________");
    
    sleep();
    sleep();
    sleep();
  }

  private static CompletableFuture<String> processe() {
    return CompletableFuture.supplyAsync(() -> {
      sleep();
      return "Motores vrum vrum";
    });
  }
  
  private static final void sleep() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
  }

}