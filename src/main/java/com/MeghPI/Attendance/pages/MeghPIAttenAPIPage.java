package com.MeghPI.Attendance.pages;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

public class MeghPIAttenAPIPage {

	 private static String token;   // ✅ shared runtime token

	    // ✅ Token getter
	    public static String getToken() {
	        return token;
	    }

	    // ✅ Token setter
	    public static void setToken(String authToken) {
	        token = authToken;
	    }

	    // 🔹 Login API
	    public static Response sendLoginRequest(String baseUri, String endpoint, String username, String password, String companyCode) {
	        String body = String.format("{\"UserName\":\"%s\", \"Password\":\"%s\", \"CompanyCode\":\"%s\"}",
	                username, password, companyCode);

	        return RestAssured.given()
	                .baseUri(baseUri)              // ✅ dynamic baseUri
	                .basePath(endpoint)            // ✅ dynamic endpoint
	                .contentType(ContentType.JSON)
	                .body(body)
	                .when()
	                .post();                       // ✅ no hardcoding here
	    }

	    
	    //create employee
	    
	    public Response createEmployee(String baseUri, String endpoint, String token,
                int companyLocation, String firstName, String lastName, String empId,
                String phoneNo, String email, int reportingTo, String doj,
                int entityTypeId, boolean sendEmailInvite) {

// Build employee JSON inside EmployeeList array
String requestBody = "{\n" +
"  \"EmployeeList\": [\n" +
"    {\n" +
"      \"CompanyLocation\": " + companyLocation + ",\n" +
"      \"FirstName\": \"" + firstName + "\",\n" +
"      \"LastName\": \"" + lastName + "\",\n" +
"      \"EmployeeID\": \"" + empId + "\",\n" +
"      \"ContactNo\": \"" + phoneNo + "\",\n" +
"      \"EmailID\": \"" + email + "\",\n" +
"      \"ReportingTo\": " + reportingTo + ",\n" +
"      \"DOJ\": \"" + doj + "\",\n" +
"      \"EntityTypeId\": " + entityTypeId + ",\n" +
"      \"SendEmailInvitation\": " + sendEmailInvite + "\n" +
"    }\n" +
"  ]\n" +
"}";

return RestAssured.given()
.baseUri(baseUri)
.header("Authorization", "Bearer " + token)
.header("Content-Type", "application/json")
.body(requestBody)
.post(endpoint)
.then().extract().response();
}


	 // 🔹 Enroll Device
	    public Response enrollDevice(String baseUri, String endpoint,
	                                 String deviceUniqueId, String employeeUniqueId, String photo) {

	        // Use the shared token from login
	        String authToken = MeghPIAttenAPIPage.getToken();
	        if (authToken == null || authToken.isEmpty()) {
	            throw new IllegalStateException("Auth token is not available. Ensure login is done first.");
	        }

	        // Validate required inputs
	        if (baseUri == null || endpoint == null || deviceUniqueId == null || employeeUniqueId == null || photo == null) {
	            throw new IllegalArgumentException("One or more required parameters are null");
	        }

	        // Set base URI
	        RestAssured.baseURI = baseUri;

	        // Prepare JSON body
	        Map<String, Object> requestBody = new HashMap<>();
	        requestBody.put("DeviceUniqueId", deviceUniqueId);
	        requestBody.put("EmployeeUniqueId", employeeUniqueId);
	        requestBody.put("Photo", photo);

	        // Send POST request and return response
	        return RestAssured.given()
	                .contentType(ContentType.JSON)
	                .header("Authorization", "Bearer " + authToken)
	                .body(requestBody)
	                .when()
	                .post(endpoint)
	                .then()
	                .log().ifError()
	                .extract()
	                .response();
	    }


	 // 🔹 Create & Enroll (using stored token)
	    public Response createAndEnroll(String baseUri, String createEndpoint, String enrollEndpoint,
                String authToken, int companyLocation, String firstName,
                String lastName, String empId, String phoneNo, String email,
                int reportingTo, String doj, int entityTypeId,
                boolean sendEmailInvite, String deviceUniqueId, String photo) throws InterruptedException {

// ✅ Ensure token is stored for enrollDevice
MeghPIAttenAPIPage.setToken(authToken);

// Step 1: Create Employee
Response createResp = createEmployee(baseUri, createEndpoint, authToken,
companyLocation, firstName, lastName, empId, phoneNo, email,
reportingTo, doj, entityTypeId, sendEmailInvite);

if (createResp.getStatusCode() != 200) {
System.out.println("❌ Employee creation failed. Status: " + createResp.getStatusCode());
System.out.println("Response: " + createResp.getBody().asString());
return createResp; // stop if employee creation fails
}

// ✅ Extract EmployeeUniqueId (fallback to EmployeeId if not present)
String employeeUniqueId = createResp.jsonPath().getString("EmployeeList[0].EmployeeUniqueId");
if (employeeUniqueId == null || employeeUniqueId.isEmpty()) {
employeeUniqueId = createResp.jsonPath().getString("EmployeeList[0].EmployeeId");
if (employeeUniqueId == null || employeeUniqueId.isEmpty()) {
throw new IllegalStateException("EmployeeUniqueId not returned from createEmployee. Cannot enroll device.");
}
}

System.out.println("✅ Employee Created Successfully: " + firstName + " " + lastName +
" | EmployeeUniqueId: " + employeeUniqueId + " | EmployeeId: " + empId);

// Optional delay to ensure employee is ready for enrollment
Thread.sleep(5000); // 5 sec

// Step 2: Enroll Device
Response enrollResp = enrollDevice(baseUri, enrollEndpoint, deviceUniqueId, employeeUniqueId, photo);

// Print enroll response like standalone test
System.out.println("Request to enroll device: " + deviceUniqueId + " for employee: " + employeeUniqueId);
System.out.println("Enroll Device Status Code: " + enrollResp.getStatusCode());
System.out.println("Enroll Device Response Body: " + enrollResp.getBody().asString());

return enrollResp;
}


	    //create leave
	    
	    public Response createLeave(String baseUri, String endpoint, String token,
                int type, int entityId, int leaveTypeId,
                int totalDays, String fromDate, String toDate,
                int fromDurationType, int toDurationType,
                int status, String remarks, String documentName,
                String document) {

Map<String, Object> leaveBody = new HashMap<>();
leaveBody.put("Type", type);
leaveBody.put("EntityId", entityId);
leaveBody.put("LeaveTypeId", leaveTypeId);
leaveBody.put("TotalDays", totalDays);
leaveBody.put("FromDate", fromDate);
leaveBody.put("ToDate", toDate);
leaveBody.put("FromDurationType", fromDurationType);
leaveBody.put("ToDurationType", toDurationType);
leaveBody.put("Status", status);
leaveBody.put("Remarks", remarks);
leaveBody.put("DocumentName", documentName);
leaveBody.put("Document", document);

return RestAssured.given()
.baseUri(baseUri)
.header("Authorization", "Bearer " + token)
.contentType(ContentType.JSON)
.body(leaveBody)   // ❌ remove 'request' wrapper
.post(endpoint)
.then()
.log().ifError()
.extract().response();
}


//add custom holiday
	    
	    public Response addCustomHoliday(String baseUri, String endpoint, String token,
                int countryId, List<Map<String, Object>> holidayList) {

Map<String, Object> requestBody = new HashMap<>();
requestBody.put("CountryId", countryId);
requestBody.put("HolidayList", holidayList);

return RestAssured.given()
.baseUri(baseUri)
.header("Authorization", "Bearer " + token)
.contentType(ContentType.JSON)
.body(requestBody)
.post(endpoint)
.then()
.log().ifError()
.extract().response();
}
	    
	    
	    
	 // Update Attendance
	    public Response updateAttendance(String baseUri, String endpoint, String token,
	                                     String employeeUniqueId, String fromDate) {
	        Map<String, Object> body = new HashMap<>();
	        body.put("employeeUniqueId", employeeUniqueId);
	        body.put("FromDate", fromDate); // keep as per API contract

	        return RestAssured.given()
	                .baseUri(baseUri)
	                .header("Authorization", "Bearer " + token)
	                .contentType(ContentType.JSON)
	                .body(body)
	                .when()
	                .post(endpoint) // ✅ use POST instead of PUT
	                .then()
	                .log().ifError()
	                .extract().response();
	    }


	 // Success Transaction API
	    public Response successTransaction(String baseUri, String endpoint, String token,
	                                       String cardNumber, int cardType, String deviceUniqueId,
	                                       String bio1Finger, String bio2Finger,
	                                       String employeeUniqueId, String locationId,
	                                       String inOutTime, String mode, String photo) {
	        Map<String, Object> body = new HashMap<>();
	        body.put("cardNumber", cardNumber);
	        body.put("cardType", cardType);
	        body.put("deviceUniqueId", deviceUniqueId);
	        body.put("bio1Finger", bio1Finger);
	        body.put("bio2Finger", bio2Finger);
	        body.put("employeeUniqueId", employeeUniqueId);
	        body.put("locationId", locationId);
	        body.put("inOutTime", inOutTime);
	        body.put("mode", mode);
	        body.put("photo", photo);

	        return RestAssured.given()
	                .baseUri(baseUri)
	                .header("Authorization", "Bearer " + token)
	                .contentType(ContentType.JSON)
	                .body(body)
	                .when()
	                .post(endpoint)
	                .then()
	                .log().ifError()
	                .extract().response();
	    }


	 // Add Shift Roster API
	    public Response addShiftRoster(String baseUri, String endpoint, String token,
	                                   int shiftRoasterId, String name, String type, String dateStart,
	                                   List<Map<String, String>> shiftPolicyDateDetails,
	                                   List<Map<String, Integer>> shiftPolicyDayDetails) {
	        Map<String, Object> body = new HashMap<>();

	        // ShiftRoasterRepo section
	        Map<String, Object> shiftRoasterRepo = new HashMap<>();
	        shiftRoasterRepo.put("ShiftRoasterId", shiftRoasterId);
	        shiftRoasterRepo.put("Name", name);
	        shiftRoasterRepo.put("Type", type);
	        shiftRoasterRepo.put("DateStart", dateStart);

	        body.put("ShiftRoasterRepo", shiftRoasterRepo);
	        body.put("ShiftPolicyDateDetails", shiftPolicyDateDetails);
	        body.put("ShiftPolicyDayDetails", shiftPolicyDayDetails);

	        return RestAssured.given()
	                .baseUri(baseUri)
	                .header("Authorization", "Bearer " + token)
	                .contentType(ContentType.JSON)
	                .body(body)
	                .when()
	                .post(endpoint)
	                .then()
	                .log().ifError()
	                .extract().response();
	    }
	    
	 // Clear Data API
	    public Response clearData(String baseUri, String endpoint, String token, int month, int year) {
	        Map<String, Object> body = new HashMap<>();
	        body.put("Month", month);
	        body.put("Year", year);

	        return RestAssured.given()
	                .baseUri(baseUri)
	                .header("Authorization", "Bearer " + token)
	                .contentType(ContentType.JSON)
	                .body(body)
	                .post(endpoint)   // ClearData API is a POST
	                .then()
	                .log().ifError()
	                .extract().response();
	    }

	 // GetPrimaryDataList API
	    public Response getPrimaryDataList(String baseUri, String endpoint, String token, String type) {
	        try {
	            RestAssured.baseURI = baseUri;

	            // Create request body
	            Map<String, Object> body = new HashMap<>();
	            body.put("Type", type);

	            Response resp = RestAssured.given()
	                    .header("Authorization", "Bearer " + token)
	                    .header("Content-Type", "application/json")
	                    .body(body)
	                    .when()
	                    .post(endpoint)
	                    .then()
	                    .extract()
	                    .response();

	            return resp;

	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	    
	    
	    //combined method for login and transaction
	    
	    public Response executeSuccessTransaction(String loginBaseUri, String loginEndpoint,
	            String username, String password, String companyCode,
	            String transBaseUri, String transEndpoint,
	            String cardNumber, int cardType, String deviceUniqueId,
	            String bio1Finger, String bio2Finger,
	            String employeeUniqueId, String locationId,
	            String inOutTime, String mode, String photo) {

	        // Step 1: Perform login & log response
	        Response loginResponse = sendLoginRequest(loginBaseUri, loginEndpoint, username, password, companyCode);
	        System.out.println("Login Response: " + loginResponse.asString());

	        if (loginResponse.statusCode() == 200) {
	            // Extract token (update path if nested)
	        	String extractedToken = loginResponse.jsonPath().getString("Token");
	        	if (extractedToken == null || extractedToken.isEmpty()) {
	        	    extractedToken = loginResponse.jsonPath().getString("token");
	        	}


	            if (extractedToken == null || extractedToken.isEmpty()) {
	                throw new RuntimeException("Token not found in login response.");
	            }

	            MeghPIAttenAPIPage.setToken(extractedToken); // store for future use
	            System.out.println("Extracted Token: " + extractedToken);
	        } else {
	            throw new RuntimeException("Login failed: " + loginResponse.asString());
	        }

	        // Step 2: Perform transaction with stored token
	        Response transResponse = successTransaction(transBaseUri, transEndpoint, MeghPIAttenAPIPage.getToken(),
	                cardNumber, cardType, deviceUniqueId,
	                bio1Finger, bio2Finger, employeeUniqueId,
	                locationId, inOutTime, mode, photo);

	        System.out.println("Transaction Response: " + transResponse.asString());
	        return transResponse;
	    }

	    
	    
	    //Combined leave and login
	    public Response executeCreateLeaves(String loginBaseUri, String loginEndpoint,
                String username, String password, String companyCode,
                String leaveBaseUri, String leaveEndpoint,
                int type, int entityId, int leaveTypeId,
                int totalDays, String fromDate, String toDate,
                int fromDurationType, int toDurationType,
                int status, String remarks, String documentName,
                String document) {

// Step 1: Perform login & log response
Response loginResponse = sendLoginRequest(loginBaseUri, loginEndpoint,
username, password, companyCode);
System.out.println("Login Response: " + loginResponse.asString());

if (loginResponse.getStatusCode() == 200) {
String extractedToken = loginResponse.jsonPath().getString("Token");
if (extractedToken == null || extractedToken.isEmpty()) {
extractedToken = loginResponse.jsonPath().getString("token");
}

if (extractedToken == null || extractedToken.isEmpty()) {
throw new RuntimeException("Token not found in login response.");
}

setToken(extractedToken);
System.out.println("Extracted Token: " + extractedToken);
} else {
throw new RuntimeException("Login failed: " + loginResponse.asString());
}

// Step 2: Call Create Leave API
Response leaveResponse = createLeave(leaveBaseUri, leaveEndpoint, getToken(),
type, entityId, leaveTypeId,
totalDays, fromDate, toDate,
fromDurationType, toDurationType,
status, remarks, documentName, document);

// Log leave API response
System.out.println("Leave API Response Code: " + leaveResponse.getStatusCode());
System.out.println("Leave API Response Body: " + leaveResponse.getBody().asString());

return leaveResponse;
}


	    
	    //Get UserStatus API
	    
	    public Response executeGetUserStatus(String loginBaseUri, String loginEndpoint,
                String username, String password, String companyCode,
                String transBaseUri, String transEndpoint,
                String employeeId, String fromDate, String toDate) throws InterruptedException {
	    	Thread.sleep(15000);

// Step 1: Perform login
Response loginResponse = sendLoginRequest(loginBaseUri, loginEndpoint, username, password, companyCode);
System.out.println("Login Response: " + loginResponse.asString());

if (loginResponse.statusCode() == 200) {
// Extract token
String extractedToken = loginResponse.jsonPath().getString("Token");
if (extractedToken == null || extractedToken.isEmpty()) {
extractedToken = loginResponse.jsonPath().getString("token");
}

if (extractedToken == null || extractedToken.isEmpty()) {
throw new RuntimeException("❌ Token not found in login response.");
}

MeghPIAttenAPIPage.setToken(extractedToken); // store token
System.out.println("✅ Extracted Token: " + extractedToken);
} else {
throw new RuntimeException("❌ Login failed: " + loginResponse.asString());
}

// Step 2: Call GetUserStatus API with stored token
Map<String, Object> requestBody = new HashMap<>();
requestBody.put("EmployeeId", employeeId);
requestBody.put("FromDate", fromDate);
requestBody.put("ToDate", toDate);

Response transResponse = io.restassured.RestAssured.given()
.baseUri(transBaseUri)
.header("Authorization", "Bearer " + MeghPIAttenAPIPage.getToken())
.header("Content-Type", "application/json")
.body(requestBody)
.post(transEndpoint);

System.out.println("GetUserStatus Response: " + transResponse.asString());
return transResponse;
}

	    
	    
	    //Transaction and get user status method
	    
	    public boolean executeTransactionAndVerifyStatus(
	            String loginBaseUri, String loginEndpoint,
	            String username, String password, String companyCode,
	            String transBaseUri, String transEndpoint,
	            String cardNumber, int cardType, String deviceUniqueId,
	            String bio1Finger, String bio2Finger,
	            String employeeUniqueId, String locationId,
	            String inOutTime, String mode, String photo,
	            String statusBaseUri, String statusEndpoint,
	            String fromDate, String toDate,
	            String expectedStatus) {

	        try {
	            // Step 1: Login and get token
	            Response loginResponse = sendLoginRequest(loginBaseUri, loginEndpoint, username, password, companyCode);
	            if (loginResponse.statusCode() != 200) {
	                throw new RuntimeException("❌ Login failed: " + loginResponse.asString());
	            }

	            String extractedToken = loginResponse.jsonPath().getString("Token");
	            if (extractedToken == null || extractedToken.isEmpty()) {
	                extractedToken = loginResponse.jsonPath().getString("token");
	            }
	            if (extractedToken == null || extractedToken.isEmpty()) {
	                throw new RuntimeException("❌ Token not found in login response.");
	            }
	            MeghPIAttenAPIPage.setToken(extractedToken);

	            // Step 2: Perform transaction
	            Response transResponse = successTransaction(transBaseUri, transEndpoint, MeghPIAttenAPIPage.getToken(),
	                    cardNumber, cardType, deviceUniqueId,
	                    bio1Finger, bio2Finger, employeeUniqueId,
	                    locationId, inOutTime, mode, photo);

	            if (transResponse.statusCode() != 200) {
	                throw new RuntimeException("❌ Transaction failed: " + transResponse.asString());
	            }
	            System.out.println("✅ Transaction Response: " + transResponse.asString());

	            // Step 3: Call GetUserStatus
	            Response statusResponse = executeGetUserStatus(
	                    loginBaseUri, loginEndpoint,
	                    username, password, companyCode,
	                    statusBaseUri, statusEndpoint,
	                    employeeUniqueId, fromDate, toDate);

	            if (statusResponse.statusCode() != 200) {
	                throw new RuntimeException("❌ GetUserStatus failed: " + statusResponse.asString());
	            }
	            System.out.println("✅ Status Response: " + statusResponse.asString());

	            // Step 4: Validate AttnFinalStatus
	            String actualStatus = statusResponse.jsonPath().getString("AttnFinalStatus");
	            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
	                System.out.println("✅ Validation Passed: Expected = " + expectedStatus + " | Actual = " + actualStatus);
	                return true;
	            } else {
	                throw new RuntimeException("❌ Validation Failed: Expected = " + expectedStatus + " | Actual = " + actualStatus);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	    // update attendance status
	    public Response executeUpdateAttendance(
	            String loginBaseUri, String loginEndpoint,
	            String username, String password, String companyCode,
	            String transBaseUri, String updateAttendanceEndpoint,
	            String employeeId, String fromDate) throws InterruptedException {

	        // Step 1: Perform login
	        Response loginResponse = sendLoginRequest(loginBaseUri, loginEndpoint, username, password, companyCode);
	        System.out.println("Login Response: " + loginResponse.asString());

	        if (loginResponse.statusCode() == 200) {
	            // Extract token
	            String extractedToken = loginResponse.jsonPath().getString("Token");
	            if (extractedToken == null || extractedToken.isEmpty()) {
	                extractedToken = loginResponse.jsonPath().getString("token");
	            }

	            if (extractedToken == null || extractedToken.isEmpty()) {
	                throw new RuntimeException("❌ Token not found in login response.");
	            }

	            MeghPIAttenAPIPage.setToken(extractedToken); // store token
	            System.out.println("✅ Extracted Token: " + extractedToken);
	        } else {
	            throw new RuntimeException("❌ Login failed: " + loginResponse.asString());
	        }

	        // Step 2: Call UpdateAttendance API with stored token
	        Map<String, Object> requestBody = new HashMap<>();
	        requestBody.put("employeeUniqueId", employeeId);
	        requestBody.put("FromDate", fromDate);

	        Response transResponse = io.restassured.RestAssured.given()
	                .baseUri(transBaseUri)
	                .header("Authorization", "Bearer " + MeghPIAttenAPIPage.getToken())
	                .header("Content-Type", "application/json")
	                .body(requestBody)
	                .post(updateAttendanceEndpoint);

	        System.out.println("UpdateAttendance Response: " + transResponse.asString());
	        return transResponse;
	    }



    
}
