package java_base.singleton;

//
public class Singleton4 {
	private static Singleton4 instance;
	private Singleton4(){
		
	}
	public static Singleton4 getInstance(){
		if(instance == null){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			instance = new Singleton4();
		}
		return instance;
	}
}
