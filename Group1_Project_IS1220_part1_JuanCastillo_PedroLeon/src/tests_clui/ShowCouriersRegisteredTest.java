package tests_clui;

import org.junit.Test;

import clui.MyFoodora;
import clui.ShowCouriersRegistered;

public class ShowCouriersRegisteredTest {

	@Test
	public void test() {
		MyFoodora mf = new MyFoodora();
		String[] args1 = new String [1];
		args1[0] = "";
		ShowCouriersRegistered scr = new ShowCouriersRegistered();
		mf.treatCmd("logIn \"deputy\" \"987654321\"");
		mf.treatCmd("registerCourier \"Mr\" \"Amazon\" \"courier1\" \"2,1\" \"pass1\" \"0695436143\"");
		mf.treatCmd("registerCourier \"Ms\" \"Fedex\" \"courier2\" \"4,7\" \"pass2\" \"0616299143\"");
		
		System.out.println(scr.process(args1));
	}

}
