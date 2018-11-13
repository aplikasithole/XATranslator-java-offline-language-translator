package id.kaliagung.translator;
public class XATool{
	private StringBuilder g;
	public String change(String a, String b, String c){
		g = new StringBuilder();
		g.append(a);
		while (g.indexOf(b) > -1) g.replace(g.indexOf(b), g.indexOf(b) + 2, c);
		return g.toString();
	}
	public String cap(String a){
		if(a.length() >= 1) return a.toUpperCase().charAt(0)+subAfter(a.toLowerCase(), 1);
		else return a.toUpperCase();
	}
	public String a(String w){
		return w.substring(0, w.indexOf(":")).trim();
	}
	public String b(String w){
		return w.substring(w.indexOf(":")+1, w.indexOf(";")).trim();
	}
	public boolean correct(String w){
		return w.trim().indexOf(":") > 1 && w.trim().indexOf(";") > w.trim().indexOf(":")+2;
	}
	public String subBefore(String a, int d){
		if(d < a.length()) return a.substring(0, d);
		else return a;
	}
	public String subAfter(String a, double d){
		if(d < a.length()) return a.substring((int)d, a.length());
		else return "";
	}
}
