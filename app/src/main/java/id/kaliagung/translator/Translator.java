package id.kaliagung.translator;
import java.util.HashMap;
import java.util.ArrayList;
public class Translator extends XATool{
	private HashMap<String, String> a = new HashMap<>();
	private String i;
	private String t;
	private StringBuilder o;
	private int c;
	private ArrayList<String> y = new ArrayList<>();
	public String translate(HashMap<String, String> h, String p, String x){
		a.clear();
		a = new HashMap<>();
		y.clear();
		y = new ArrayList<>();
		a = h;
		o = new StringBuilder();
		t(a, change(p, "  ", " ").trim(), x);
		return o.toString();
	}
	public ArrayList<String> newWords(){
		return y;
	}
	/*  TRANSLATE METHOD  */
	//Step 2 Getting tense
	private void t(HashMap<String, String> h, String p, String x) {
        i = p.trim();
		c = 0;
		if(h.isEmpty()) o.append(i.trim());
        while(i.trim().length() > 0) {
            if(c < ((double) i.length())) 
                if(x.indexOf(i.charAt(c)) < 0) r(h, subBefore(i, c), x);
                else c++;
            else if (i.length() > 0) r(h, i, x);
        }
    }
	//Step 3 Core of translation code
	private void r(HashMap<String, String> h, String s, String x) {
		t = s;
		while (!h.containsKey(t.trim())) 
			if (t.trim().contains(" ")) t = subBefore(t, t.lastIndexOf(" "));
			else{
                o.append(t);
				o(t);
				break;
            }
		if(h.containsKey(t.trim())) o.append(t.replace(t.trim(), h.get(t.trim())));
		i = subAfter(i, t.length());
		k(0, x);
    }
	//Step 4 Listing untranslated words
	private void o(String k){
		y.add(k.trim().toLowerCase());
	}
	//Step 5 Skip a/few/many/lot of letter that not listed in prepare() method
	private void k(int d, String x) {
		while (i.length() > 0) 
			if (x.indexOf(i.charAt(d)) < 0) {
				o.append(i.charAt(d));
                i = subAfter(i, d + 1);
            }else break;
        c = d;
	}
}
