package helthcare;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;



@Listeners(listeners.MyListeners.class)
public class homePage {


    @Test(testName = "Run All Tests Sequentially Multiple Times")
    public void runTestsSequentially() {
        int repeatCount = 100; // Change this value to how many times you want to repeat the full cycle
        
        for (int i = 1; i <= repeatCount; i++) {
            System.out.println("Iteration: " + i);

            try {
                TC001();
                //TC002();
                //TC003();
                //TC004();
            } catch (Exception e) {
                System.err.println("Error in Iteration " + i + ": " + e.getMessage());
                e.printStackTrace();
                //TC005();
            }
        }
        
        //TC005(); // Close browser only once at the end
    }

    public void TC001() throws InterruptedException, IOException {
        try {
            System.out.println("Executing TC001 - Login Application");
            DashBoard dashBoard = new DashBoard();
            DashBoard.main();
        } catch (Exception e) {
			System.err.println("Error in TC001 (Login Application): " + e.getMessage());
            e.printStackTrace();
        }
    }

//    public void TC002() throws InterruptedException, IOException {
//        try {
//            System.out.println("Executing TC002 - New Patient Registration");
//            newPatient newPatient = new newPatient();
//            newPatient.newRegistration();
//        } catch (Exception e) {
//            System.err.println("Error in TC002 (New Patient Registration): " + e.getMessage());
//            e.printStackTrace();
//        }
//    }

	/*
	 * public void TC003() throws InterruptedException, IOException {
	 * 
	 * try { System.out.println("Executing TC003 - OPD Bill"); Billing billing = new
	 * Billing(); billing.Bill(); } catch (Exception e) {
	 * System.err.println("Error in TC003 (OPD Bill): " + e.getMessage());
	 * e.printStackTrace(); } }
	 */

	/*
	 * public void TC004() throws InterruptedException, IOException { try {
	 * System.out.println("Executing TC004 - Payment Settlement"); billList billList
	 * = new billList(); billList.billList(); } catch (Exception e) {
	 * System.err.println("Error in TC004 (Payment Settlement): " + e.getMessage());
	 * e.printStackTrace(); } }
	 */

	/*
	 * public void TC005() { try {
	 * System.out.println("Executing TC005 - Closing Browser"); billList billList =
	 * new billList(); billList.closeBrowser(); } catch (Exception e) {
	 * System.err.println("Error in TC005 (Close Browser): " + e.getMessage());
	 * e.printStackTrace(); } }
	 */
}
