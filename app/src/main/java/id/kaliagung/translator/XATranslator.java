package id.kaliagung.translator;
import java.util.HashMap;
import java.util.ArrayList;
public class XATranslator{
	private String x;
	private String in;
	private StringBuilder out;
	private ArrayList<String> y;
	private String t;
	private int c;
	private HashMap<String, String> AB = new HashMap();
	private HashMap<String, String> BA = new HashMap();
	/*  XATRANSLATION PREPARE  */
	//Prepare mode 1
	public void prepare(){
		prepare(null);
	}
	//Prepare mode 2
	public void prepare(String a){
		x = "abcdefghijklmnopqrstuvwxyz";
		x += x.toUpperCase()+" ";
		if(a!=null) if(a.trim().length() >= 0) x += a;
			else x += "'-";
		else x +="'-";
		x.replace("\n", "");
	}
	/*  LOAD DICTIONARY  */
	//Load dictionary mode 1
	public void loadDictionary(String d){
		loadDictionary(d, false, false);
	}
	//Load dictionary mode 2
	public void loadDictionary(String d, boolean r){
		loadDictionary(d, r, false);
	}
	//Load dictionary mode 3
	public void loadDictionary(String d, boolean r, boolean j){
		in = d.toLowerCase().trim();
        if(!j) if (r) BA.clear();
            else AB.clear();
		System.gc();
        while (in.contains(";")) {
            w(subBefore(in, in.indexOf(";")+1).trim(), r);
            in = subAfter(in, in.indexOf(";")+1).trim();
        }
        in = new String();
	}
	//Load dictionary mode 4
	public void singleDictionary(String d){
		loadDictionary(d, false, false);
		loadDictionary(d, true, false);
	}
	//lod dictionary mode 5
	public void singleDictionary(String d, boolean j){
		loadDictionary(d, false, j);
		loadDictionary(d, true, j);
	}
	//Translate in two direction directly with single short method.
	//Recommended for onClick event
	public String direct(String p, String d1, String d2){
		loadDictionary(d1, false, false);
		loadDictionary(d2, true, false);
		return translate(translate(p, true), false);
	}
	//Translate in one direction directly
	public String go(String p, String d){
		return go(p, d, false);
	}
	//Translate in one direction between reverse or not
	public String go(String p, String d, boolean r){
		loadDictionary(d, r, false);
		return translate(p, r);
	}
	//Translate mode 1
	public String translate(String p){
		return translate(p, false);
	}
	//Translate mode 2 step 1
	public String translate(String p, boolean r) {
		out = new StringBuilder();
		y = new ArrayList<>();
		if(p!= null && x != null)
			if(x.length() >= 0)
				if(p.trim().length() >= 0)
					if (r) t(BA, p);
					else t(AB, p);
		System.gc();
		return out.toString();
    }
	//Translate for single ductionary
	public String translateFrom(String p){
		return translate(p, true);
	}
	public String translateTo(String p){
		return translate(p, false);
	}
	//Get loaded word count
	public int getWordCount(boolean r){
		if(r) return BA.size()/3;
		else return AB.size()/3;
	}
	//Get all fail translation words
	public ArrayList<String> collectNewWord(){
		return (y);
	}
	/*  LOADING DICTIONARY  */
	//Step 2 Check/Confirming before adding to hasmap
	private void w(String w, boolean r) {
        if (correct(w))  if(r) d(BA, b(w), a(w));
			else d(AB, a(w), b(w));
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
	/*  TRANSLATE METHOD  */
	//Step 2 Getting tense
	private void t(HashMap<String, String> h, String p) {
        in = p.trim();
		if(h.isEmpty()) out.append(in.trim());
        while(in.trim().length() > 0) {
            if(c < ((double) in.length())) 
                if(x.indexOf(in.charAt(c)) < 0) r(h, subBefore(in, c));
                else c++;
            else if (in.length() > 0) r(h, in);
        }
    }
	//Step 3 Core of translation code
	private void r(HashMap<String, String> h, String s) {
		t = s;
		while (!h.containsKey(t.trim())) 
			if (t.trim().contains(" ")) t = subBefore(t, t.lastIndexOf(" "));
			else{
                out.append(t);
				o(t);
				break;
            }
		if(h.containsKey(t.trim())) out.append(t.replace(t.trim(), h.get(t.trim())));
		in = subAfter(in, t.length());
		k(0);
    }
	//Step 4 Listing untranslated words
	private void o(String k){
		y.add(k.trim().toLowerCase());
	}
	//Step 5 Skip a/few/many/lot of letter that not listed in prepare() method
	private void k(int d) {
		while (in.length() > 0) 
			if (x.indexOf(in.charAt(d)) < 0) {
				out.append(in.charAt(d));
                in = subAfter(in, d + 1);
            }else break;
        c = d;
	}
	/*  PRIVATE UTILITIES .*/
	//Capitalize first letter
	private static String cap(String a){
		if(a.length() >= 1) return a.toUpperCase().charAt(0)+subAfter(a.toLowerCase(), 1);
		else return a.toUpperCase();
	}
	private static String a(String w){
		return w.substring(0, w.indexOf(":")).trim();
	}
	private static String b(String w){
		return w.substring(w.indexOf(":")+1, w.indexOf(";")).trim();
	}
	private static boolean correct(String w){
		return w.trim().indexOf(":") > 1 && w.trim().indexOf(";") > w.trim().indexOf(":")+2;
	}
	private static String subBefore(String a, int d){
		if(d < a.length()) return a.substring(0, d);
		else return a;
	}
	private static String subAfter(String a, double d){
		if(d < a.length()) return a.substring((int)d, a.length());
		else return "";
	}
}
