package id.kaliagung.translator;
import java.util.HashMap;
import java.util.ArrayList;
public class XATranslator extends XATool{
	private String x;
	private ArrayList<String> y;
	private HashMap<String, String> AB = new HashMap();
	private HashMap<String, String> BA = new HashMap();
	private Dictionary e;
	private Translator l = new Translator();
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
		e = new Dictionary();
        if(!j) if (r) BA.clear();
            else AB.clear();
		System.gc();
		if(r) BA = e.make(d, r);
		else AB = e.make(d, r);
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
		l = new Translator();
		y = new ArrayList<>();
		if(x.length() > 0)
			if(p.trim().length() >= 0)
				if (r) return l.translate(BA, p.trim(), x);
				else return l.translate(AB, p.trim(), x);
			else return p.trim();
		else return p.trim();
    }
	//Get loaded word count
	public int getWordCount(boolean r){
		if(r) return BA.size()/3;
		else return AB.size()/3;
	}
	//Get all fail translation words
	public ArrayList<String> collectNewWord(){
		return l.newWords();
	}
}
