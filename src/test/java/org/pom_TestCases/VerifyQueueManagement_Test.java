package org.pom_TestCases;

import org.pom_Pages.DashboardPage;
import org.pom_Pages.RegistrationPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyQueueManagement_Test extends Login_Test{
	@Test(enabled = true)
	public void verify_queue_Management() throws InterruptedException {
		DashboardPage dp=new DashboardPage(driver);
		RegistrationPage rp= new RegistrationPage(driver);
		SoftAssert sa = new SoftAssert();
		
		dp.OPD();
		dp.Queue_Management();
		String PRN_no = rp.PRN_NO();
		System.out.println(PRN_no);
		rp.Opd_bill();
		//sa.assertEquals(rp.OPD_Bill_text(), "OPD Bill");

	}
}
