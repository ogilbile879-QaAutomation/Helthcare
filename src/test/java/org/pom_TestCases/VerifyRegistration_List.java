package org.pom_TestCases;

import org.pom_Pages.RegistrationPage;
import org.pom_Pages.DashboardPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyRegistration_List extends Login_Test{


	@Test(priority = 1,enabled = true)
	public void verify_Registration_List() throws InterruptedException {
		DashboardPage dp = new DashboardPage(driver);
		RegistrationPage rp = new RegistrationPage(driver);
		SoftAssert sa = new SoftAssert();
		dp.OPD();
		rp.Queue_Management();
		String PRN_no = rp.PRN_NO();
		System.out.println(PRN_no);
		dp.OPD();
		rp.RegistrationList();
		sa.assertEquals(rp.Registration_List_text(), "Registration List");
	    rp.prn_Input_RegistrationList(PRN_no);
	    rp.Registration_List_Search();

	}
}
