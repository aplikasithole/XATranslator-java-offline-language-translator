package id.kaliagung.translator;
import java.util.HashMap;
public class Dictionary extends XATool{
	private String a;
	private HashMap<String, String> b = new HashMap<>();
	public HashMap<String, String> make(String d, boolean r){
		b.clear();
		b = new HashMap<>();
		a = change(d, "  ", " ").toLowerCase().trim();
		while (a.contains(";")) {
            w(subBefore(a, a.indexOf(";")+1).trim(), r);
            a = subAfter(a, a.indexOf(";")+1).trim();
        }
		return b;
	}
	//Step 2 Check/Confirming before adding to hasmap
	private void w(String w, boolean r) {
        if (correct(w))  if(r) d(b, b(w), a(w));
			else d(b, a(w), b(w));
    }
	//Step 3 Core/end of loading dictionary
	private void d(HashMap<String, String> h, String f, String t){
		g(h, f.toLowerCase(), t.toLowerCase());
		g(h, cap(f), cap(t));
		g(h, f.toUpperCase(), t.toUpperCase());
	}
	//Step 4 Removing old word
	private void g(HashMap<String, String> h, String f, String t){
		if(h.containsKey(f))h.remove(f);
		h.put(f,t);
	}
}
