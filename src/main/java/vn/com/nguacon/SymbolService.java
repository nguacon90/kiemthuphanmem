package vn.com.nguacon;

import java.util.HashMap;
import java.util.Map;

public class SymbolService {
	private static Map<String, Symbol> hnxSymbols = new HashMap<String, Symbol>();
	
	public SymbolService(){
		initHnxSymbols();
	}

	public boolean isBelongToHNX(String symbol){
		return SymbolService.hnxSymbols.containsKey(symbol);
	}
	
	public Symbol getSymbol(String code){
		return SymbolService.hnxSymbols.get(code);
	}
	
	private void initHnxSymbols() {
		Symbol vnd = new Symbol("VND", 11000, 13500);
		Symbol aaa = new Symbol("AAA", 12000, 14500);
		Symbol klf = new Symbol("KLF", 28800, 35200);
		Symbol fit = new Symbol("FIT", 16100, 20100);
		hnxSymbols.put(vnd.getCode(), vnd);
		hnxSymbols.put(aaa.getCode(), aaa);
		hnxSymbols.put(klf.getCode(), klf);
		hnxSymbols.put(fit.getCode(), fit);
	}
	
	
}
