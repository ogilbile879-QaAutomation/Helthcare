package org.pom_TestCases;

import org.pom_Pages.BookAppointmentPage;
import org.pom_Pages.DashboardPage;
import org.pom_Pages.RegistrationPage;
import org.pom_Pages.TeleMedicinePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyRegistrationOnly_Test extends Login_Test{

	@Test(priority = 1)
	public void verify_Opd_registration() throws InterruptedException {
		DashboardPage dp = new DashboardPage(driver);
		RegistrationPage rp=new RegistrationPage(driver);
		BookAppointmentPage ap=new BookAppointmentPage (driver);
		TeleMedicinePage tp=new TeleMedicinePage(driver);
		SoftAssert sa = new SoftAssert();
		
		dp.OPD();
		dp.OPDRegistration();
		rp.select_Title("Mrs");
		rp.firstName();
		rp.middleName();
		String lname=rp.lastName();
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
		rp.RegisterOnlyCheckbox();
		String barcode = rp.savebutton();
		rp.RegistrationList();
		rp.AppointmentButton(barcode);
		rp.OPD_ServiceWindowNextBtn();
		ap.SelectService();
		ap.selectoption("First Consultation");
		ap.InPersonRadioBtn();
		ap.DescribeMedicalComplaint("Mild fever and dry cough with slight body pain and tiredness throughout the day.");
		ap.Departmentdropdown();
		ap.selectDepartmentOption("GENERAL MEDICINE");
		ap.SubDepartmentdropdown();
		ap.selectDepartmentOption("GENERAL MEDICINE UNIT 1");
		ap.SelectToProceedByName();
		ap.selectAvailableTimeSlot();
		ap.ConfirmTimeSlot();
		dp.OPD();
		dp.TeleMedicine();
		tp.ListOfAppointment();
		tp.checkBookedOrConfirm("Test "+lname);
		tp.isOrderConfirmed("Test "+lname);
		
//		String PRN_no = rp.PRN_NO();
//		System.out.println(PRN_no);
//		sa.assertEquals(rp.OPD_Registration(), "OPD Registration");
	}

}
