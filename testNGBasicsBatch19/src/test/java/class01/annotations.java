package class01;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class annotations {

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("I am the Before method");
    }

    @Test(groups = "smoke")
    public void ATestCase(){
        System.out.println("I am test case A");
    }

    @Test (groups = "smoke")
    public  void BTestCase(){
        System.out.println("I am test case B");
    }


}
