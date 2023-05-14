package javabase.singleton;

//构造函数私有化 懒汉
public class Singleton1 {
	public static final Singleton1 INSTANCE = new Singleton1();
	private Singleton1(){
	}
}
