public class MeuRunnable implements Runnable{
	@Override //avisa que vai ter override 
	public void run(){
		String name = Thread.currentThread().getName();
		System.out.println(name);
	}
}