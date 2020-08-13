package test;

public class TestUtils {
	public static void main(String[] args) {
		String str="a.b.c.jpg";
		int lastIndexOf = str.lastIndexOf(".");//最后的点的位置
		System.out.println(lastIndexOf);
		String substring = str.substring(lastIndexOf);//从此位置截取
		System.out.println(substring);
	}

}
