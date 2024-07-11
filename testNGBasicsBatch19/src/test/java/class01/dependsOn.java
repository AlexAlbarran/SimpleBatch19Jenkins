package class01;

import org.testng.annotations.Test;

public class dependsOn {

    @Test
    public void login(){
        System.out.println(10/0); // There are no double quotes which causes an error
    }

    @Test(dependsOnMethods = {"login"})
    public void dashBoard(){
        System.out.println("i am dashboard");
    }
}
