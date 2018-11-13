package id.kaliagung.translator;
import java.util.ArrayList;
public class Localizer extends XATranslator{
	public void prepare(){
		new XATranslator();
		prepare();
	}
	public void prepare(String x){
		new XATranslator();
		if(x != null) prepare(x);
		else prepare();
	}
	public void load(String d){
		loadDictionary(d, false, false);
		loadDictionary(d, true, false);
	}
	public void load(String d, boolean j){
		loadDictionary(d, false, j);
		loadDictionary(d, true, j);
	}
	public String localeFrom(String p){
		return translate(p, true);
	}
	public String localeTo(String p){
		return translate(p, false);
	}
	public ArrayList<String> unLocalize(){
		return collectNewWord();
	}
}
