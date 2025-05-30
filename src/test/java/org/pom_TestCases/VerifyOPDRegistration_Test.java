package org.pom_TestCases;

import org.pom_Pages.DashboardPage;
import org.pom_Pages.RegistrationPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyOPDRegistration_Test extends Login_Test{

	@Test(priority = 1)
	public void verify_Opd_registration() throws InterruptedException {
		DashboardPage dp = new DashboardPage(driver);
		RegistrationPage rp=new RegistrationPage(driver);
		SoftAssert sa = new SoftAssert();
		
		dp.OPD();
		dp.OPDRegistration();
		rp.select_Title("Miss");
		rp.firstName();
		rp.middleName();
		rp.lastName();
		rp.select_Gender("Female");
		rp.mobileNo();
		rp.Year("22");
		rp.Months("11");
		rp.Days("10");
		rp.aadharNo();
		rp.address();
		rp.Department("CARDIOLOGY");
		rp.Doctor();
		rp.VisitType("Follow up");
		rp.PatientSource("Self");
		rp.savebutton();
		sa.assertEquals(rp.OPD_Registration(), "OPD Registration");
	}

}
