package vn.com.nguacon.kiemthu.integrationtesting.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import vn.com.nguacon.kiemthu.integrationtesting.model.Symbol;

public class SymbolServiceImpl implements SymbolService {
	private static final Map<String, Symbol> hnxSymbols = new HashMap<String, Symbol>();

	public SymbolServiceImpl() {
		initHnxSymbols();
	}

	public Symbol getSymbol(String symbol) {
		return hnxSymbols.get(symbol);
	}

	public boolean isBelongToHNX(String symbol) {
		return hnxSymbols.containsKey(symbol);
	}

	private void initHnxSymbols() {
		InputStream in = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("hnxSymbols.txt");
		Scanner scanner = new Scanner(in);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] stockInfo = line.split(" ");
			hnxSymbols.put(stockInfo[0],
					new Symbol(stockInfo[0], Double.valueOf(stockInfo[1]) * 1000,
							Double.valueOf(stockInfo[2]) * 1000));
		}

	}
}
