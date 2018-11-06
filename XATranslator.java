import java.util.HashMap;

public class XATranslator
{
	private String x;
	private String in;
	private StringBuilder out;
	private StringBuilder y;
	private String t;
	private int c;
	private HashMap<String, String> AB = new HashMap();
    private HashMap<String, String> BA = new HashMap();

	public void prepare(String a){
		x = "abcdefghijklmnopqrstuvwxyz";
		x += x.toUpperCase()+" ";
		if(a!=null)
			if(a.trim().length() >= 0) x += a;
			else x += "'-";
		else x +="'-";
		x.replace("\n", "");
	}

	//Head of method to load dictionary
	public void loadDictionary(String d, boolean r, boolean j){
		in = d.toLowerCase().trim();
        if(!j)
            if (r) BA.clear();
            else AB.clear();
        while (in.contains(";")) {
            w(Caps.subBefore(in, in.indexOf(";")+1).trim(), r);
            in = Caps.subAfter(in, in.indexOf(";")+1).trim();
        }
        in = new String();
	}

	//Check/Confirming before adding to hasmap
	private void w(String w, boolean r) {
        if (Caps.correct(w))  if(r) d(BA, Caps.b(w), Caps.a(w));
        else d(AB, Caps.a(w), Caps.b(w));
    }

	//Core/end of loading dictionary
	private void d(HashMap<String, String> h, String f, String t){
		g(h, f.toLowerCase(), t.toLowerCase());
		g(h, Caps.cap(f), Caps.cap(t));
		g(h, f.toUpperCase(), t.toUpperCase());
	}

	//Removing old key cause hasmap only getting last key if they many same key
	private void g(HashMap<String, String> h, String f, String t){
		if(h.containsKey(f))h.remove(f);
		h.put(f,t);
	}

	//Translate in two direction directly with single short method, recommended for onClick event
	public String direct(String p, String d1, String d2){
		loadDictionary(d1, false, false);
		loadDictionary(d2, true, false);
		return translate(translate(p, true), false);
	}

	//Translate in one direction directly
	public String go(String p, String d, boolean r){
		loadDictionary(d, r, false);
		return translate(p, r);
	}

	//Head of translation method
	public String translate(String p, boolean r) {
		out = new StringBuilder();
		y = new StringBuilder();
		if(p!= null && x.length() >= 0)
			if(p.trim().length() >= 0)
				if (r) t(BA, p);
				else t(AB, p);
		System.gc();
		return out.toString();
    }

	//Getting tense
	private void t(HashMap<String, String> h, String p) {
        in = p.trim();
		if(h.isEmpty()) out.append(in.trim());
        while(in.trim().length() > 0) {
            if(c < ((double) in.length())) 
                if(x.indexOf(in.charAt(c)) < 0) r(h, Caps.subBefore(in, c));
                else c++;
            else if (in.length() > 0) r(h, in);
        }
    }

	//Core of translation code
	private void r(HashMap<String, String> h, String s) {
		t = s;
		while (!h.containsKey(t.trim())) 
			if (t.trim().contains(" ")) t = Caps.subBefore(t, t.lastIndexOf(" "));
			else{
                out.append(t);
				o(h, t);
				break;
            }
        in = Caps.subAfter(in, t.length());
		if(h.containsKey(t.trim())) out = out.append(t.replace(t.trim(), h.get(t.trim())));
		k(0);
    }

	//To skip a/few/many/lot of letter that not listed in prepare() method
	private void k(int d) {
		while (in.length() > 0) 
			if (x.indexOf(in.charAt(d)) < 0) {
				out.append(in.charAt(d));
                in = Caps.subAfter(in, d + 1);
            }else break;
        c = d;
	}

	//Listing untranslated words
	private void o(HashMap<String, String> h, String k){
		if(k.trim().length() >= 0 && y.indexOf(";" + k.toLowerCase().trim() + ";") < 0) y.append(k.trim().toLowerCase() + ";");
	}

	//Get word count
	public int getWordCount(boolean r){
		if(r) return BA.size()/3;
		else return AB.size()/3;
	}

	//Get all word that untranslated
	public String collectNewWord(){
		return y.toString();
	}
}
