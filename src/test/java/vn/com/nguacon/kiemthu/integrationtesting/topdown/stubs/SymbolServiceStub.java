package vn.com.nguacon.kiemthu.integrationtesting.topdown.stubs;

import vn.com.nguacon.kiemthu.integrationtesting.model.Symbol;
import vn.com.nguacon.kiemthu.integrationtesting.service.SymbolService;

public class SymbolServiceStub implements SymbolService {

	public Symbol getSymbol(String symbol) {
		return new Symbol("VND", 11000, 15000);
	}

	public boolean isBelongToHNX(String symbol) {
		if("VND".equalsIgnoreCase(symbol)){
			return true;
		}
		return false;
	}

}
