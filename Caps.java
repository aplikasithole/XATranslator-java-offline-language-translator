public class Caps
{
	public static String cap(String a){
		if(a.length() >= 1) return a.toUpperCase().substring(0,1)+a.toLowerCase().substring(1,a.length());
		else return a.toUpperCase();
	}

	public static String a(String w){
		return w.substring(0, w.indexOf(":")).trim();
	}

	public static String b(String w){
		return w.substring(w.indexOf(":")+1, w.indexOf(";")).trim();
	}

	public static boolean correct(String w){
		return w.trim().indexOf(":") > 1 && w.trim().indexOf(";") > w.trim().indexOf(":")+2;
	}

	public static String subBefore(String a, int d){
		return a.substring(0, d);
	}

	public static String subAfter(String a, double d){
		if(d < a.length()) return a.substring((int)d, a.length());
		else return "";
	}
}
