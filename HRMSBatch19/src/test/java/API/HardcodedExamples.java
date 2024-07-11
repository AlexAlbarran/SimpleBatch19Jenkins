package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardcodedExamples {

    //the intention is to learn how API's work
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MjAyNzgwMzQsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTcyMDMyMTIzNCwidXNlcklkIjoiNjYyMSJ9.7JxIa4h9rqEs2C3xnRPn4F4uPTgzp42T82Kb1IWLYrE";
    static String employee_id;

    @Test
    //in order to create the employee
    //first prepare the request, then hit the endpoint, validate the response
    public void acreateEmployee(){
        //preparing the request
        RequestSpecification preparedRequest = given().
                header("Content-Type","application/json").
                header("Authorization", token).
                body("{\n" +
                        "  \"emp_firstname\": \"Justin\",\n" +
                        "  \"emp_lastname\": \"Case\",\n" +
                        "  \"emp_middle_name\": \"N\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2009-01-30\",\n" +
                        "  \"emp_status\": \"active\",\n" +
                        "  \"emp_job_title\": \"IT\"\n" +
                        "}");
        //hitting the endpoint, sending the request
        Response response = preparedRequest.when().post("/createEmployee.php");

        //validate status code
        response.then().assertThat().statusCode(201);
        // System.out.println(response);
        response.prettyPrint();
        //json - keys, it returns the value
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);
        //validate header // Connection: Keep-Alive
        response.then().assertThat().header("Connection","Keep-Alive");
        //validate body
        response.then().assertThat().body("Employee.emp_firstname",equalTo("Justin"));
        response.then().assertThat().body("Employee.emp_middle_name",equalTo("N"));
        response.then().assertThat().body("Employee.emp_lastname",equalTo("Case"));

    }

    @Test
    // getting one employee
    public void bgetOneEmployee() {
        // prepare the request
        RequestSpecification request = given().
                header("Content-Type", "application/json").
                header("Authorization", token).
                queryParam("employee_id", employee_id);

        // hitting the endpoint
        Response response = request.when().get("/getOneEmployee.php");
        response.prettyPrint();

        // validate the status code
        response.then().assertThat().statusCode(200);
        // get the employee id from body
        String empId = response.jsonPath().getString("employee.employee_id");
        Assert.assertEquals(empId, employee_id);
        response.then().assertThat().body("employee.emp_firstname",equalTo("Justin"));
        response.then().assertThat().body("employee.emp_middle_name",equalTo("N"));
        response.then().assertThat().body("employee.emp_lastname",equalTo("Case"));

    }

@Test
    public void cupdateEmployee(){
        //prepare the request
    RequestSpecification request = given().
            header("Content-Type", "application/json").
            header("Authorization", token).
            body("{\n" +
                    "  \"employee_id\": \""+employee_id+"\",\n" +
                    "  \"emp_firstname\": \"Patrick\",\n" +
                    "  \"emp_lastname\": \"A\",\n" +
                    "  \"emp_middle_name\": \"Star\",\n" +
                    "  \"emp_gender\": \"M\",\n" +
                    "  \"emp_birthday\": \"2012-01-14\",\n" +
                    "  \"emp_status\": \"Unemployed\",\n" +
                    "  \"emp_job_title\": \"LazyBoy\"\n" +
                    "}");

    // sending the request
    Response response = request.when().put("/updateEmployee.php");
    // validate the response
    response.then().assertThat().statusCode(200);
    response.prettyPrint();

}

@Test
public void dgetUpdatedEmployee(){
    // prepare the request
    RequestSpecification request = given().
            header("Content-Type", "application/json").
            header("Authorization", token).
            queryParam("employee_id", employee_id);

    // hitting the endpoint
    Response response = request.when().get("/getOneEmployee.php");
    response.prettyPrint();

    // validate the status code
    response.then().assertThat().statusCode(200);

}
@Test
public void egetjobTitle(){
    RequestSpecification request = given().
            header("Content-Type", "application/json").
            header("Authorization", token);

            // hitting the endpoint
    Response response = request.when().get("/jobTitle.php");
    response.prettyPrint();

}

@Test
    public void fgetAllEmployees(){
    RequestSpecification request = given().
            header("Content-Type", "application/json").
            header("Authorization", token);

    // hitting the endpoint
    Response response = request.when().get("/getAllEmployees.php");
    response.prettyPrint();

}



}
