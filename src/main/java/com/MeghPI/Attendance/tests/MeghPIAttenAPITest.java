package com.MeghPI.Attendance.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.LogResults;
import base.initBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage;

public class MeghPIAttenAPITest {

    private LogResults logResults = new LogResults();
    private static String authToken;   // ✅ Store token here for reuse

    @Parameters({ "device" })
    @BeforeClass(alwaysRun = true)
    public void setup(int device) {
        logResults.createReport(device);
        logResults.setTestMethodErrorCount(0);
    }

 // 🔹 1. Login API
    @Test(enabled = true, priority = 0, groups = { "API" })
    public void API_Login() throws Exception {
        String currTC = "API_Login";
        logResults.createExtentReport(currTC);
        logResults.setScenarioName("Login Employee using API");

        ArrayList<String> data = initBase.loadExcelData("API_login_createemp_enroll", currTC,
                "baseuri,endpoint,companycode,username,password");

        // Call Login API (all values coming from Excel)
        Response resp = MeghPIAttenAPIPage.sendLoginRequest(
                data.get(0), // baseUri
                data.get(1), // endpoint
                data.get(3), // username
                data.get(4), // password
                data.get(2)  // companyCode
        );

        // Log Response
        System.out.println("Status Code: " + resp.getStatusCode());
        System.out.println("Response Body: " + resp.getBody().asString());

        // ✅ Extract Token from JSON
        authToken = resp.jsonPath().getString("Token");
        Assert.assertNotNull(authToken, "❌ Token not returned");

        // ✅ Save Token in API class
        MeghPIAttenAPIPage.setToken(authToken);

        System.out.println("✅ Token Saved: " + authToken);
        logResults.createLogs("✅ Login Successful. Token saved in memory", "", "");
    }


 // 🔹 2. Create Employee
    @Test(enabled = false, priority = 2, groups = { "API" })
    public void API_CreateEmployee() throws Exception {
        String currTC = "API_CreateEmployee";
        logResults.createExtentReport(currTC);
        logResults.setScenarioName("Create Employee using API");

        ArrayList<String> data = initBase.loadExcelData("API_login_createemp_enroll", currTC,
            "baseuri,endpoint,companylocation,firstname,lastname,empid,phoneno,email,reportingto,doj,entitytypeid,sendemailinvite");

        MeghPIAttenAPIPage api = new MeghPIAttenAPIPage();
        Response resp = api.createEmployee(
            data.get(0), data.get(1), authToken,   // ✅ Using stored token
            Integer.parseInt(data.get(2)), data.get(3), data.get(4), data.get(5),
            data.get(6), data.get(7), Integer.parseInt(data.get(8)), data.get(9),
            Integer.parseInt(data.get(10)), Boolean.parseBoolean(data.get(11))
        );

        // 🔹 Print request data
        System.out.println("===== Employee Creation Request Data =====");
        System.out.println("Company Location : " + data.get(2));
        System.out.println("First Name       : " + data.get(3));
        System.out.println("Last Name        : " + data.get(4));
        System.out.println("Emp ID           : " + data.get(5));
        System.out.println("Phone No         : " + data.get(6));
        System.out.println("Email            : " + data.get(7));
        System.out.println("Reporting To     : " + data.get(8));
        System.out.println("Date of Joining  : " + data.get(9));
        System.out.println("Entity Type Id   : " + data.get(10));
        System.out.println("Send Email Invite: " + data.get(11));
        System.out.println("=========================================");

        // 🔹 Response Logging
        System.out.println("Status Code : " + resp.getStatusCode());
        System.out.println("Response Body: " + resp.getBody().asString());

        // ✅ Extract correct fields
        int empId = resp.jsonPath().getInt("Id");  
        boolean isActive = resp.jsonPath().getBoolean("IsActive");

        System.out.println("Employee Creation ID   : " + empId);
        System.out.println("Employee Active Status : " + isActive);

        // ✅ Assertions
        Assert.assertEquals(resp.getStatusCode(), 200, "❌ Employee creation API failed");
        Assert.assertTrue(empId > 0, "❌ Employee ID not returned");
        Assert.assertTrue(isActive, "❌ Employee not marked active");

        logResults.createLogs("✅ Employee Created Successfully - ID: " + empId, "", "");
    }



 // Enrollment Test
    @Test(enabled = false, priority = 3, groups = { "API" })
    public void API_EnrollDevice() throws Exception {
        // Get current test case name dynamically
        String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.println("Executing Test Case: " + currTC);
        System.out.println("Scenario: Enroll the employee");

        // Load test data from Excel
        ArrayList<String> data = initBase.loadExcelData(
                "API_login_createemp_enroll",
                currTC,
                "baseuri,enrollendpoint,deviceuniqueid,employeeuniqueid,photobase"
        );

        // Extract data for readability
        String baseUri = data.get(0);
        String endpoint = data.get(1);
        String deviceUniqueId = data.get(2);
        String employeeUniqueId = data.get(3);
        String photoBase = data.get(4);

        // Call API method (uses token from MeghPIAttenAPI.getToken())
        MeghPIAttenAPIPage api = new MeghPIAttenAPIPage();
        Response resp = api.enrollDevice(baseUri, endpoint, deviceUniqueId, employeeUniqueId, photoBase);

        // Print request and response details
        System.out.println("Request to enroll device: " + deviceUniqueId + " for employee: " + employeeUniqueId);
        System.out.println("Response Status Code: " + resp.statusCode());
        System.out.println("Response Body: " + resp.getBody().asString());

        // Assertion
        Assert.assertEquals(resp.statusCode(), 200, "Device enrollment failed");
    }



//create and enroll

    @Test(enabled = false, priority = 4, groups = { "API" })
    public void API_CreateAndEnroll() throws Exception {
        String currTC = "API_CreateAndEnroll";
        System.out.println("Executing Test Case: " + currTC);
        System.out.println("Scenario: Create Employee & Enroll Device");

        // Load test data
        ArrayList<String> data = initBase.loadExcelData("API_login_createemp_enroll", currTC,
            "baseuri,endpoint,enrollendpoint,companylocation,firstname,lastname,empid,phoneno,email,reportingto,doj,entitytypeid,sendemailinvite,deviceuniqueid,photobase");

        // Extract data
        String baseUri = data.get(0);
        String createEndpoint = data.get(1);
        String enrollEndpoint = data.get(2);
        int companyLocation = Integer.parseInt(data.get(3));
        String firstName = data.get(4);
        String lastName = data.get(5);
        String empId = data.get(6);
        String phoneNo = data.get(7);
        String email = data.get(8);
        int reportingTo = Integer.parseInt(data.get(9));
        String doj = data.get(10);
        int entityTypeId = Integer.parseInt(data.get(11));
        boolean sendEmailInvite = Boolean.parseBoolean(data.get(12));
        String deviceUniqueId = data.get(13);
        String photoBase = data.get(14);

        MeghPIAttenAPIPage api = new MeghPIAttenAPIPage();

        // Call Create & Enroll API
        Response enrollResp = api.createAndEnroll(baseUri, createEndpoint, enrollEndpoint, authToken,
            companyLocation, firstName, lastName, empId, phoneNo, email,
            reportingTo, doj, entityTypeId, sendEmailInvite,
            deviceUniqueId, photoBase);

        // ✅ Print details similar to standalone enrollment test
        System.out.println("Using Token: " + authToken);
        System.out.println("Employee Created: " + firstName + " " + lastName + " | EmployeeId: " + empId);
        System.out.println("Request to enroll device: " + deviceUniqueId);
        System.out.println("Enroll Device Status Code: " + enrollResp.getStatusCode());
        System.out.println("Enroll Device Response: " + enrollResp.getBody().asString());

        // ✅ Assertion
        Assert.assertEquals(enrollResp.getStatusCode(), 200, "❌ Device enrollment failed");

        // Logging
        System.out.println("✅ Employee Created & Device Enrolled Successfully");
    }
    
    
    
 // Leave Creation
    @Test(enabled = false, priority = 5, groups = { "API" })
    public void API_CreateLeave() throws Exception {
        String currTC = "API_CreateLeave";
        logResults.createExtentReport(currTC);
        logResults.setScenarioName("Create Leave for Employee");

        ArrayList<String> data = initBase.loadExcelData("APIaddleave", currTC,
            "baseuri,endpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,status,remarks,documentname,document");

        String baseUri = data.get(0);
        String endpoint = data.get(1);

        int type = Integer.parseInt(data.get(2));
        int entityId = Integer.parseInt(data.get(3));
        int leaveTypeId = Integer.parseInt(data.get(4));
        int totalDays = Integer.parseInt(data.get(5));

        String fromDate = data.get(6);
        String toDate = data.get(7);

        // Ensure ToDate is not empty or earlier than FromDate
        if (toDate == null || toDate.isEmpty()) {
            toDate = fromDate;
        }

        int fromDurationType = Integer.parseInt(data.get(8));
        int toDurationType = Integer.parseInt(data.get(9));
        int status = Integer.parseInt(data.get(10));

        // Ensure remarks is not empty
        String remarks = (data.get(11) == null || data.get(11).trim().isEmpty())
                ? "API test leave"
                : data.get(11);

        String documentName = data.get(12);
        String document = data.get(13);

        MeghPIAttenAPIPage api = new MeghPIAttenAPIPage();
        String token = MeghPIAttenAPIPage.getToken();

        Response resp = api.createLeave(baseUri, endpoint, token,
                type, entityId, leaveTypeId, totalDays,
                fromDate, toDate, fromDurationType, toDurationType,
                status, remarks, documentName, document);

        System.out.println("Status Code: " + resp.getStatusCode());
        System.out.println("Response Body: " + resp.getBody().asString());

        Assert.assertEquals(resp.getStatusCode(), 200, "❌ Create Leave API failed");

        logResults.createLogs("✅ Leave Created Successfully", "", resp.asString());
    }


   
// Add custom holiday
    
        @Test(enabled = false, priority = 6, groups = { "API" })
        public void API_AddCustomHoliday() throws Exception {

            String currTC = "API_AddCustomHoliday";
            logResults.createExtentReport(currTC);
            logResults.setScenarioName("Add Custom Holiday");

            // Load data from Excel
            ArrayList<String> data = initBase.loadExcelData("APIcustomholiday", currTC,
                    "baseuri,endpoint,countryid,holidayname,holidaydate,applicableto,ismandatory,states,holidaydescription,stateid,stateid2,statename1,statename2");

            String baseUri = data.get(0);
            String endpoint = data.get(1);
            int countryId = Integer.parseInt(data.get(2));

            String holidayName = data.get(3);
            String holidayDate = data.get(4);
            String applicableTo = data.get(5);
            boolean isMandatory = Boolean.parseBoolean(data.get(6));

            List<String> states = Arrays.asList(data.get(7).split(",")); // comma-separated in Excel
            String holidayDescription = data.get(8);

            int stateId1 = Integer.parseInt(data.get(9));
            int stateId2 = Integer.parseInt(data.get(10));
            String stateName1 = data.get(11);
            String stateName2 = data.get(12);

            // Prepare HolidayDetail list
            List<Map<String, Object>> holidayDetail = new ArrayList<>();
            Map<String, Object> detail1 = new HashMap<>();
            detail1.put("CountryId", countryId);
            detail1.put("StateId", stateId1);
            detail1.put("StateName", stateName1);

            Map<String, Object> detail2 = new HashMap<>();
            detail2.put("CountryId", countryId);
            detail2.put("StateId", stateId2);
            detail2.put("StateName", stateName2);

            holidayDetail.add(detail1);
            holidayDetail.add(detail2);

            // Prepare Holiday List
            List<Map<String, Object>> holidayList = new ArrayList<>();
            Map<String, Object> holiday = new HashMap<>();
            holiday.put("Name", holidayName);
            holiday.put("Date", holidayDate);
            holiday.put("ApplicableTo", applicableTo);
            holiday.put("IsMandatory", isMandatory);
            holiday.put("StateId", states);
            holiday.put("HolidayDescription", holidayDescription);
            holiday.put("HolidayDetail", holidayDetail);

            holidayList.add(holiday);

            // Call API
            MeghPIAttenAPIPage api = new MeghPIAttenAPIPage();
            String token = MeghPIAttenAPIPage.getToken();

            Response resp = api.addCustomHoliday(baseUri, endpoint, token, countryId, holidayList);

            System.out.println("Status Code: " + resp.getStatusCode());
            System.out.println("Response Body: " + resp.getBody().asString());

            Assert.assertEquals(resp.getStatusCode(), 200, "❌ Add Custom Holiday API failed");

            logResults.createLogs("✅ Custom Holiday Added Successfully", "", resp.asString());
        }
    
        
        // attendance reprocess
        
        @Test(enabled = false, priority = 7, groups = { "API" })
        public void API_UpdateAttendance() throws Exception {
            String currTC = "API_UpdateAttendance";
            logResults.createExtentReport(currTC);
            logResults.setScenarioName("Update Attendance for Employee");

            ArrayList<String> data = initBase.loadExcelData(
                    "APIattendancereprocess", currTC,
                    "baseuri,endpoint,employeeuniqueid,fromdate");

            String baseUri = data.get(0).trim();
            String endpoint = data.get(1).trim();
            String employeeUniqueId = data.get(2).trim();
            String fromDate = data.get(3).trim();

            MeghPIAttenAPIPage api = new MeghPIAttenAPIPage();
            String token = MeghPIAttenAPIPage.getToken();

            Response resp = api.updateAttendance(baseUri, endpoint, token, employeeUniqueId, fromDate);

            System.out.println("Status Code: " + resp.getStatusCode());
            System.out.println("Response Body: " + resp.getBody().asPrettyString());

            Assert.assertEquals(resp.getStatusCode(), 200, "❌ Update Attendance API failed");

            boolean isSuccess = resp.jsonPath().getBoolean("IsSuccess");
            Assert.assertTrue(isSuccess, "❌ Update Attendance response does not contain IsSuccess=true");

            logResults.createLogs("✅ Attendance Updated Successfully", "", resp.asString());
        }

        
        //SuccessTransaction
        
        @Test(enabled = false, priority = 8, groups = { "API" })
        public void API_SuccessTransaction() throws Exception {
            String currTC = "API_SuccessTransaction";
            logResults.createExtentReport(currTC);
            logResults.setScenarioName("Success Transaction API");

            // Load test data from Excel
            ArrayList<String> data = initBase.loadExcelData(
                    "API_Attendancetransaction", currTC,
                    "baseuri,endpoint,cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,locationid,inouttime,mode,photo");

            String baseUri = data.get(0).trim();
            String endpoint = data.get(1).trim();
            String cardNumber = data.get(2).trim();
            int cardType = Integer.parseInt(data.get(3).trim());
            String deviceUniqueId = data.get(4).trim();
            String bio1Finger = data.get(5).trim();
            String bio2Finger = data.get(6).trim();
            String employeeUniqueId = data.get(7).trim();
            String locationId = data.get(8).trim();
            String inOutTime = data.get(9).trim();
            String mode = data.get(10).trim();
            String photo = data.get(11).trim();

            MeghPIAttenAPIPage api = new MeghPIAttenAPIPage();
            String token = MeghPIAttenAPIPage.getToken();

            Response resp = api.successTransaction(baseUri, endpoint, token,
                    cardNumber, cardType, deviceUniqueId,
                    bio1Finger, bio2Finger,
                    employeeUniqueId, locationId, inOutTime, mode, photo);

            System.out.println("Status Code: " + resp.getStatusCode());
            System.out.println("Response Body: " + resp.getBody().asPrettyString());

            // Assertions
            Assert.assertEquals(resp.getStatusCode(), 200, "❌ SuccessTransaction API failed");

            String errorCode = resp.jsonPath().getString("ErrorCode");
            String errorDesc = resp.jsonPath().getString("ErrorDescription");

            Assert.assertEquals(errorCode, "0", "❌ ErrorCode is not 0");
            Assert.assertEquals(errorDesc, "Success", "❌ ErrorDescription is not Success");

            logResults.createLogs("✅ SuccessTransaction executed successfully", "", resp.asString());
        }
   
        
        
        //Add shift roaster

        @Test(enabled = false, priority = 9, groups = { "API" })
        public void API_AddShiftRoster() throws Exception {
            String currTC = "API_AddShiftRoster";
            logResults.createExtentReport(currTC);
            logResults.setScenarioName("Add Shift Roster API");

            // Load test data from Excel
            ArrayList<String> data = initBase.loadExcelData(
                    "API_addshiftroster", currTC,
                    "baseuri,endpoint,shiftroasterid,name,type,datestart," +
                    "fromdate1,todate1,fromdate2,todate2,days");

            String baseUri = data.get(0).trim();
            String endpoint = data.get(1).trim();
            int shiftRoasterId = Integer.parseInt(data.get(2).trim());
            String name = data.get(3).trim();
            String type = data.get(4).trim();
            String dateStart = data.get(5).trim();

            // ShiftPolicyDateDetails
            List<Map<String, String>> shiftPolicyDateDetails = new ArrayList<>();

            Map<String, String> dateDetail1 = new HashMap<>();
            dateDetail1.put("FromDate", data.get(6).trim());
            dateDetail1.put("ToDate", data.get(7).trim());
            shiftPolicyDateDetails.add(dateDetail1);

            Map<String, String> dateDetail2 = new HashMap<>();
            dateDetail2.put("FromDate", data.get(8).trim());
            dateDetail2.put("ToDate", data.get(9).trim());
            shiftPolicyDateDetails.add(dateDetail2);

            // ShiftPolicyDayDetails
            List<Map<String, Integer>> shiftPolicyDayDetails = new ArrayList<>();
            Map<String, Integer> dayDetail = new HashMap<>();
            dayDetail.put("Days", Integer.parseInt(data.get(10).trim()));
            shiftPolicyDayDetails.add(dayDetail);

            // Call API
            MeghPIAttenAPIPage api = new MeghPIAttenAPIPage();
            String token = MeghPIAttenAPIPage.getToken();

            Response resp = api.addShiftRoster(baseUri, endpoint, token,
                    shiftRoasterId, name, type, dateStart,
                    shiftPolicyDateDetails, shiftPolicyDayDetails);

            System.out.println("Status Code: " + resp.getStatusCode());
            System.out.println("Response Body: " + resp.getBody().asPrettyString());

            // Assertions
            Assert.assertEquals(resp.getStatusCode(), 200, "❌ AddShiftRoster API failed");

            boolean isActive = resp.jsonPath().getBoolean("IsActive");
            int id = resp.jsonPath().getInt("Id");

            Assert.assertTrue(isActive, "❌ Shift Roster is not Active");
            Assert.assertTrue(id > 0, "❌ Id not generated for Shift Roster");

            logResults.createLogs("✅ AddShiftRoster executed successfully", "", resp.asString());
        }
     
    
     // Clear Data API Test
        @Test(enabled = false, priority = 10, groups = { "API" })
        public void API_ClearData() throws Exception {
            String currTC = "API_ClearData";
            logResults.createExtentReport(currTC);
            logResults.setScenarioName("Clear Data for Month & Year");

            // Load test data from Excel
            ArrayList<String> data = initBase.loadExcelData("API_cleardataandgetdata", currTC,
                    "baseuri,endpoint,month,year");

            String baseUri = data.get(0);
            String endpoint = data.get(1);
            int month = Integer.parseInt(data.get(2));
            int year = Integer.parseInt(data.get(3));

            MeghPIAttenAPIPage api = new MeghPIAttenAPIPage();
            String token = MeghPIAttenAPIPage.getToken();

            Response resp = api.clearData(baseUri, endpoint, token, month, year);

            System.out.println("Status Code: " + resp.getStatusCode());
            System.out.println("Response Body: " + resp.getBody().asString());

            Assert.assertEquals(resp.getStatusCode(), 200, "❌ Clear Data API failed");

            // Validate response body is true
            String result = resp.asString().trim();   // get response body
            System.out.println("Response Body: " + result);

            // Assert response == "true"
            Assert.assertEquals(result, "true", "❌ Clear Data API did not return true");

        }

        
        //Get Primary Data
        @Test(enabled = false, priority = 11, groups = { "API" })
        public void API_GetPrimaryDataList() throws Exception {
            String currTC = "API_GetPrimaryDataList";
            logResults.createExtentReport(currTC);
            logResults.setScenarioName("Fetch Primary Data List");

            // Load test data from Excel (baseuri, endpoint, type)
            ArrayList<String> data = initBase.loadExcelData("API_cleardataandgetdata", currTC,
                    "baseuri,endpoint,type");

            String baseUri = data.get(0);
            String endpoint = data.get(1);
            String type = data.get(2);

            MeghPIAttenAPIPage api = new MeghPIAttenAPIPage();
            String token = MeghPIAttenAPIPage.getToken();

            Response resp = api.getPrimaryDataList(baseUri, endpoint, token, type);

            System.out.println("Status Code: " + resp.getStatusCode());
            System.out.println("Response Body: " + resp.getBody().asString());

            // Assertions
            Assert.assertEquals(resp.getStatusCode(), 200, "❌ GetPrimaryDataList API failed");

            // Validate response is not empty array
            List<Map<String, Object>> responseList = resp.jsonPath().getList("$");
            Assert.assertTrue(responseList.size() > 0, "❌ Primary data list is empty");

            // Example: Validate first record has Id, Name, Type
            Assert.assertNotNull(responseList.get(0).get("Id"), "❌ Id is missing");
            Assert.assertNotNull(responseList.get(0).get("Name"), "❌ Name is missing");
            Assert.assertNotNull(responseList.get(0).get("Type"), "❌ Type is missing");

            logResults.createLogs("✅ GetPrimaryDataList executed successfully", "", resp.asString());
        }
    
        //TransanctionAPImethod to use in other TestClass
        
   

            public Response executeSuccessTransaction(
                    String baseUri, String endpoint, String token,
                    String cardNumber, int cardType, String deviceUniqueId,
                    String bio1Finger, String bio2Finger,
                    String employeeUniqueId, String locationId,
                    String inOutTime, String mode, String photo) {
                try {
                    Response resp = RestAssured.given()
                            .baseUri(baseUri)
                            .header("Authorization", "Bearer " + token)
                            .contentType("application/json")
                            .body("{"
                                    + "\"CardNumber\":\"" + cardNumber + "\","
                                    + "\"CardType\":" + cardType + ","
                                    + "\"DeviceUniqueId\":\"" + deviceUniqueId + "\","
                                    + "\"Bio1Finger\":\"" + bio1Finger + "\","
                                    + "\"Bio2Finger\":\"" + bio2Finger + "\","
                                    + "\"EmployeeUniqueId\":\"" + employeeUniqueId + "\","
                                    + "\"LocationId\":\"" + locationId + "\","
                                    + "\"InOutTime\":\"" + inOutTime + "\","
                                    + "\"Mode\":\"" + mode + "\","
                                    + "\"Photo\":\"" + photo + "\""
                                    + "}")
                            .post(endpoint);

                    return resp;
                } catch (Exception e) {
                    System.out.println("Exception in executeSuccessTransaction: " + e.getMessage());
                    return null;
                }
            }
            
         // GetUserStatus Test
            @Test(enabled = true, priority = 12, groups = { "API" })
            public void API_GetUserStatus() throws Exception {
                // Get current test case name dynamically
                String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
                logResults.createExtentReport(currTC);
                logResults.setScenarioName("Fetch User Status");
                System.out.println("Executing Test Case: " + currTC);
                System.out.println("Scenario: Fetch User Status");

                // Load test data from Excel
                ArrayList<String> data = initBase.loadExcelData(
                        "API_GetUserStatus",   // Sheet name
                        currTC,                     // Test case ID
                        "baseuri,loginendpoint,username,password,companycode,"
                        + "getuserstatusendpoint,employeeid,fromdate,todate"
                );

                // Extract data for readability
                String loginBaseUri = data.get(0);
                String loginEndpoint = data.get(1);
                String username = data.get(2);
                String password = data.get(3);
                String companyCode = data.get(4);

                
                String transEndpoint = data.get(5);
                String employeeId = data.get(6);
                String fromDate = data.get(7);
                String toDate = data.get(8);

                // Call API method
                MeghPIAttenAPIPage api = new MeghPIAttenAPIPage();
                Response resp = api.executeGetUserStatus(
                        loginBaseUri, loginEndpoint, username, password, companyCode,
                        loginBaseUri, transEndpoint, employeeId, fromDate, toDate
                );

                // Print request and response details
                System.out.println("GetUserStatus for employee: " + employeeId 
                        + " from " + fromDate + " to " + toDate);
                System.out.println("Response Status Code: " + resp.statusCode());
                System.out.println("Response Body: " + resp.getBody().asString());

                // Assertion
                Assert.assertEquals(resp.statusCode(), 200, "❌ GetUserStatus API failed");
            }

        

        

}
