package hardcodedEx;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.CoreMatchers.equalTo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class hardcodedex {

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    public static String token;
    public static String emp_id;

    @Test
    public void agenerateToken(){
        RequestSpecification request=given().header("Content-Type", "application/json").body("{\n" +
                "  \"email\": \"adminrocks@starkindustries.com\",\n" +
                "  \"password\": \"123admin\"\n" +
                "}");
        Response response = request.when().post("/generateToken.php");
        response.prettyPrint();
        token=response.jsonPath().getString("token");
        //token="Bearer "+token;
        //System.out.println(token);

    }

    @Test
    public void bCreateAnEmployee(){
        RequestSpecification request=given().header("Content-Type", "application/json").header("Authorization", token).body("{\n" +
                "  \"emp_firstname\": \"Justin\",\n" +
                "  \"emp_lastname\": \"Case\",\n" +
                "  \"emp_middle_name\": \"N\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"2009-01-30\",\n" +
                "  \"emp_status\": \"active\",\n" +
                "  \"emp_job_title\": \"IT\"\n" +
                "}");
        Response response = request.when().post("/createEmployee.php");
        response.prettyPrint();
// verify that the value of Message is Employee Created
        String actualMessage = response.jsonPath().getString("Message");
        Assert.assertEquals(actualMessage,"Employee Created");
// verify that emp_status is active
        // Rest Assured Assertion
        response.then().assertThat().body("Employee.emp_status",equalTo("active"));

// assertion is same but from junit
        String actualStatus = response.jsonPath().getString("Employee.emp_status");
        Assert.assertEquals(actualStatus, "active");

        //extract the employee id from the response
        emp_id=response.jsonPath().getString("Employee.employee_id");
        System.out.println(emp_id);

    }

    // get the employee that I created


}
