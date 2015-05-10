package vn.com.nguacon.kiemthu.integrationtesting.service;

import vn.com.nguacon.kiemthu.integrationtesting.model.Symbol;


public interface SymbolService {

	Symbol getSymbol(String symbol);

	boolean isBelongToHNX(String symbol);
	
}
