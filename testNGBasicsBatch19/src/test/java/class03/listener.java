package class03;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class listener implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("the test case has passed"+ result.getName()); // this gets us the Name of the test case
    }
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("the testcase has failed "+ result.getName());
    }
}
