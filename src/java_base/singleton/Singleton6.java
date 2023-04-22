package java_base.singleton;

public class Singleton6{
	private Singleton6(){
	}
	private static class ITEM{
		private static final Singleton6 INSTANCE =  new Singleton6();
	}
	public Singleton6 getSingle(){
		return ITEM.INSTANCE;
	}

	public static void main(String[] args) {
		Singleton6 s = new Singleton6();
		s.getSingle();
		String ss = new String("123");
		String cc = new String("123");
		ss.hashCode();
		Object ob = new Object();
		System.out.println(ss.equals(cc));
	}
}
