package com.MeghPI.Attendance.pages;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

public class MeghPIAttenAPIPage {
	private  String exceptionDesc;
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

    // ✅ Convert empty strings to null for optional fields
    leaveBody.put("DocumentName",
            (documentName == null || documentName.trim().isEmpty()) ? null : documentName);
    leaveBody.put("Document",
            (document == null || document.trim().isEmpty()) ? null : document);

    return RestAssured.given()
            .baseUri(baseUri)
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .body(leaveBody)
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

// If any optional string field is empty, set it to null
body.put("cardNumber", (cardNumber == null || cardNumber.trim().isEmpty()) ? null : cardNumber);
body.put("cardType", cardType); // numeric, keep as is
body.put("deviceUniqueId", deviceUniqueId); // mandatory
body.put("bio1Finger", (bio1Finger == null || bio1Finger.trim().isEmpty()) ? null : bio1Finger);
body.put("bio2Finger", (bio2Finger == null || bio2Finger.trim().isEmpty()) ? null : bio2Finger);
body.put("employeeUniqueId", employeeUniqueId); // mandatory
body.put("locationId", (locationId == null || locationId.trim().isEmpty()) ? null : locationId);
body.put("inOutTime", inOutTime); // mandatory
body.put("mode", mode); // mandatory
body.put("photo", (photo == null || photo.trim().isEmpty()) ? null : photo);

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
	    
	    public Response executeSuccessTransaction(
	            String loginBaseUri, String loginEndpoint,
	            String username, String password, String companyCode,
	            String transBaseUri, String transEndpoint,
	            String cardNumber, int cardType, String deviceUniqueId,
	            String bio1Finger, String bio2Finger,
	            String employeeUniqueId, String locationId,
	            String inOutTime, String mode, String photo) {

	        Response transResponse = null;

	        try {
	            // Step 1: Perform login
	            Response loginResponse = sendLoginRequest(loginBaseUri, loginEndpoint, username, password, companyCode);
	            System.out.println("Login Response: " + loginResponse.asString());

	            if (loginResponse.statusCode() == 200) {
	                // Extract token (case-insensitive)
	                String extractedToken = loginResponse.jsonPath().getString("Token");
	                if (extractedToken == null || extractedToken.isEmpty()) {
	                    extractedToken = loginResponse.jsonPath().getString("token");
	                }

	                if (extractedToken == null || extractedToken.isEmpty()) {
	                    exceptionDesc = "❌ Token not found in login response.";
	                    System.err.println(exceptionDesc);
	                    return null;
	                }

	                MeghPIAttenAPIPage.setToken(extractedToken);
	                System.out.println("✅ Extracted Token: " + extractedToken);
	            } else {
	                exceptionDesc = "❌ Login failed. Response: " + loginResponse.asString();
	                System.err.println(exceptionDesc);
	                return null;
	            }

	            // Step 2: Perform transaction
	            transResponse = successTransaction(
	                    transBaseUri, transEndpoint, MeghPIAttenAPIPage.getToken(),
	                    cardNumber, cardType, deviceUniqueId,
	                    bio1Finger, bio2Finger, employeeUniqueId,
	                    locationId, inOutTime, mode, photo);

	            System.out.println("Transaction Response: " + transResponse.asString());

	            // Step 3: Validate transaction response
	            if (transResponse.getStatusCode() == 200) {
	                String errorCode = transResponse.jsonPath().getString("ErrorCode");
	                String errorDesc = transResponse.jsonPath().getString("ErrorDescription");

	                if ("0".equals(errorCode) && "Success".equalsIgnoreCase(errorDesc)) {
	                    System.out.println("✅ Transaction successful for Employee: " + employeeUniqueId + " at " + inOutTime);
	                } else {
	                    exceptionDesc = "❌ Transaction failed. ErrorCode=" + errorCode + ", Description=" + errorDesc;
	                    System.err.println(exceptionDesc);
	                }
	            } else {
	                exceptionDesc = "❌ Transaction failed. HTTP " + transResponse.getStatusCode()
	                        + " | Response: " + transResponse.asString();
	                System.err.println(exceptionDesc);
	            }

	        } catch (Exception e) {
	            exceptionDesc = "❌ Exception in executeSuccessTransaction: " + e.getMessage();
	            System.err.println(exceptionDesc);
	        }

	        return transResponse;
	    }



	    
	    
	    //Combined leave and login
	    public Response executeCreateLeaves(
	            String loginBaseUri, String loginEndpoint,
	            String username, String password, String companyCode,
	            String leaveBaseUri, String leaveEndpoint,
	            int type, int entityId, int leaveTypeId,
	            int totalDays, String fromDate, String toDate,
	            int fromDurationType, int toDurationType,
	            int status, String remarks, String documentName,
	            String document) {

	        Response leaveResponse = null;

	        try {
	            // Step 1: Perform login
	            Response loginResponse = sendLoginRequest(loginBaseUri, loginEndpoint, username, password, companyCode);
	            System.out.println("Login Response: " + loginResponse.asString());

	            if (loginResponse.getStatusCode() == 200) {
	                // Extract token (case-insensitive)
	                String extractedToken = loginResponse.jsonPath().getString("Token");
	                if (extractedToken == null || extractedToken.isEmpty()) {
	                    extractedToken = loginResponse.jsonPath().getString("token");
	                }

	                if (extractedToken == null || extractedToken.isEmpty()) {
	                    exceptionDesc = "❌ Token not found in login response.";
	                    System.err.println(exceptionDesc);
	                    return null;
	                }

	                setToken(extractedToken);
	                System.out.println("✅ Extracted Token: " + extractedToken);
	            } else {
	                exceptionDesc = "❌ Login failed. Response: " + loginResponse.asString();
	                System.err.println(exceptionDesc);
	                return null;
	            }

	            // Step 2: Call Create Leave API
	            leaveResponse = createLeave(
	                    leaveBaseUri, leaveEndpoint, getToken(),
	                    type, entityId, leaveTypeId,
	                    totalDays, fromDate, toDate,
	                    fromDurationType, toDurationType,
	                    status, remarks, documentName, document);

	            System.out.println("Leave API Response Code: " + leaveResponse.getStatusCode());
	            System.out.println("Leave API Response Body: " + leaveResponse.getBody().asString());

	            // Step 3: Validate response
	            if (leaveResponse.getStatusCode() == 200) {
	                String errorCode = leaveResponse.jsonPath().getString("ErrorCode");
	                String errorDesc = leaveResponse.jsonPath().getString("ErrorDescription");

	                if ("0".equals(errorCode) && "Success".equalsIgnoreCase(errorDesc)) {
	                    System.out.println("✅ Leave created successfully for EntityId: " + entityId);
	                } else {
	                    exceptionDesc = "❌ Leave creation failed. ErrorCode=" + errorCode + ", Description=" + errorDesc;
	                    System.err.println(exceptionDesc);
	                }
	            } else {
	                exceptionDesc = "❌ Leave creation failed. HTTP " + leaveResponse.getStatusCode()
	                        + " | Response: " + leaveResponse.asString();
	                System.err.println(exceptionDesc);
	            }

	        } catch (Exception e) {
	            exceptionDesc = "❌ Exception in executeCreateLeaves: " + e.getMessage();
	            System.err.println(exceptionDesc);
	        }

	        return leaveResponse;
	    }



	    
	    //Get UserStatus API
	    
	    public Response executeGetUserStatus(
	            String loginBaseUri, String loginEndpoint,
	            String username, String password, String companyCode,
	            String transBaseUri, String transEndpoint,
	            String employeeId, String fromDate, String toDate) {

	        Response transResponse = null;

	        try {
	            // --- Step 1: Ensure valid token ---
	            if (MeghPIAttenAPIPage.getToken() == null || MeghPIAttenAPIPage.getToken().isEmpty()) {
	                System.out.println("🔄 Token not found. Performing login...");

	                Response loginResponse = sendLoginRequest(loginBaseUri, loginEndpoint, username, password, companyCode);
	                System.out.println("Login Response: " + loginResponse.asString());

	                if (loginResponse.getStatusCode() == 200) {
	                    String extractedToken = loginResponse.jsonPath().getString("Token");
	                    if (extractedToken == null || extractedToken.isEmpty()) {
	                        extractedToken = loginResponse.jsonPath().getString("token");
	                    }

	                    if (extractedToken != null && !extractedToken.isEmpty()) {
	                        MeghPIAttenAPIPage.setToken(extractedToken);
	                        System.out.println("✅ Extracted Token: " + extractedToken);
	                    } else {
	                        System.out.println("❌ Token missing in login response.");
	                        return null;
	                    }
	                } else {
	                    System.out.println("❌ Login failed. Response: " + loginResponse.asString());
	                    return null;
	                }
	            } else {
	                System.out.println("✅ Using existing token: " + MeghPIAttenAPIPage.getToken());
	            }

	            // --- Step 2: Retry GetUserStatus API until expected AttnDate appears ---
	            int maxAttempts = 10;
	            int waitBetweenAttempts = 6000;

	            for (int attempt = 1; attempt <= maxAttempts; attempt++) {
	                Map<String, Object> requestBody = new HashMap<>();
	                requestBody.put("EmployeeId", employeeId);
	                requestBody.put("FromDate", fromDate);
	                requestBody.put("ToDate", toDate);

	                transResponse = io.restassured.RestAssured.given()
	                        .baseUri(transBaseUri)
	                        .header("Authorization", "Bearer " + MeghPIAttenAPIPage.getToken())
	                        .header("Content-Type", "application/json")
	                        .body(requestBody)
	                        .post(transEndpoint);

	                String rawResponse = transResponse.asString();
	                System.out.println("Attempt " + attempt + " - GetUserStatus Response: " + rawResponse);

	                if (transResponse.getStatusCode() == 200 &&
	                        rawResponse != null &&
	                        !rawResponse.trim().isEmpty() &&
	                        !rawResponse.trim().equalsIgnoreCase("null") &&
	                        !rawResponse.trim().equals("[]")) {

	                    List<Map<String, Object>> list = transResponse.jsonPath().getList("$");
	                    if (list != null && !list.isEmpty()) {
	                        String attnDate = (String) list.get(0).get("AttnDate");
	                        String attnDatePart = (attnDate != null && attnDate.contains(" ")) ? attnDate.split(" ")[0] : attnDate;
	                        String fromDatePart = fromDate.contains("T") ? fromDate.split("T")[0] : fromDate;

	                        if (attnDatePart != null && attnDatePart.equals(fromDatePart)) {
	                            System.out.println("✅ Matched AttnDate found: " + attnDatePart);
	                            return transResponse;
	                        }
	                    }
	                }

	                if (attempt < maxAttempts) {
	                    System.out.println("⏳ Retrying in " + (waitBetweenAttempts / 1000) + " seconds...");
	                    try {
	                        Thread.sleep(waitBetweenAttempts);
	                    } catch (InterruptedException ie) {
	                        System.out.println("⚠️ Sleep interrupted between retries: " + ie.getMessage());
	                        Thread.currentThread().interrupt(); // restore interrupt flag (best practice)
	                        break;
	                    }
	                }
	            }

	            System.out.println("⚠️ Max attempts reached. Returning last response.");
	            return transResponse;

	        } catch (Exception e) {
	            System.out.println("❌ Exception occurred in executeGetUserStatus: " + e.getMessage());
	            e.printStackTrace();
	            return transResponse;
	        }
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
	                System.out.println("❌ Login failed: " + loginResponse.asString());
	                return false;
	            }

	            String extractedToken = loginResponse.jsonPath().getString("Token");
	            if (extractedToken == null || extractedToken.isEmpty()) {
	                extractedToken = loginResponse.jsonPath().getString("token");
	            }
	            if (extractedToken == null || extractedToken.isEmpty()) {
	                System.out.println("❌ Token not found in login response.");
	                return false;
	            }
	            MeghPIAttenAPIPage.setToken(extractedToken);

	            // Step 2: Perform transaction
	            Response transResponse = successTransaction(transBaseUri, transEndpoint, MeghPIAttenAPIPage.getToken(),
	                    cardNumber, cardType, deviceUniqueId,
	                    bio1Finger, bio2Finger, employeeUniqueId,
	                    locationId, inOutTime, mode, photo);

	            if (transResponse.statusCode() != 200) {
	                System.out.println("❌ Transaction failed: " + transResponse.asString());
	                return false;
	            }
	            System.out.println("✅ Transaction Response: " + transResponse.asString());

	            // Step 3: Call GetUserStatus
	            Response statusResponse = executeGetUserStatus(
	                    loginBaseUri, loginEndpoint,
	                    username, password, companyCode,
	                    statusBaseUri, statusEndpoint,
	                    employeeUniqueId, fromDate, toDate);

	            if (statusResponse.statusCode() != 200) {
	                System.out.println("❌ GetUserStatus failed: " + statusResponse.asString());
	                return false;
	            }
	            System.out.println("✅ Status Response: " + statusResponse.asString());

	            // Step 4: Validate AttnFinalStatus
	            String actualStatus = statusResponse.jsonPath().getString("AttnFinalStatus");
	            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
	                System.out.println("✅ Validation Passed: Expected = " + expectedStatus + " | Actual = " + actualStatus);
	                return true;
	            } else {
	                System.out.println("❌ Validation Failed: Expected = " + expectedStatus + " | Actual = " + actualStatus);
	                return false;
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    
	    // update attendance status
	 // update attendance status with retry but DO NOT fail even after last attempt
	    public Response executeUpdateAttendance(
	            String loginBaseUri, String loginEndpoint,
	            String username, String password, String companyCode,
	            String transBaseUri, String updateAttendanceEndpoint,
	            String employeeId, String fromDate) {

	        System.out.println("==== executeUpdateAttendance() called ====");

	        try {
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
	                    System.out.println("⚠️ Token not found in login response, continuing but API may fail");
	                } else {
	                    MeghPIAttenAPIPage.setToken(extractedToken);
	                    System.out.println("✅ Extracted Token: " + extractedToken);
	                }
	            } else {
	                System.out.println("⚠️ Login failed: " + loginResponse.asString());
	                // continue anyway, but token may be invalid
	            }

	            // Step 2: Prepare request body
	            Map<String, Object> requestBody = new HashMap<>();
	            requestBody.put("employeeUniqueId", employeeId);
	            requestBody.put("FromDate", fromDate);

	            // Step 3: Retry logic for UpdateAttendance API
	            int maxRetries = 6;   // number of attempts
	            int waitSeconds = 6;  // wait between attempts
	            Response transResponse = null;

	            for (int attempt = 1; attempt <= maxRetries; attempt++) {
	                transResponse = io.restassured.RestAssured.given()
	                        .baseUri(transBaseUri)
	                        .header("Authorization", "Bearer " + MeghPIAttenAPIPage.getToken())
	                        .header("Content-Type", "application/json")
	                        .body(requestBody)
	                        .post(updateAttendanceEndpoint);

	                System.out.println("Attempt " + attempt + " - UpdateAttendance Response: " + transResponse.asString());

	                if (transResponse.statusCode() == 200) {
	                    String errorCode = transResponse.jsonPath().getString("ErrorCode");
	                    if (errorCode == null || "0".equals(errorCode)) {
	                        System.out.println("✅ UpdateAttendance success on attempt " + attempt);
	                        break;
	                    } else {
	                        System.out.println("⚠️ Business error (ErrorCode=" + errorCode + "), will retry...");
	                    }
	                } else {
	                    System.out.println("⚠️ HTTP status " + transResponse.statusCode() + ", will retry...");
	                }

	                if (attempt < maxRetries) {
	                    try {
	                        Thread.sleep(waitSeconds * 1000L);
	                    } catch (InterruptedException ie) {
	                        System.out.println("⚠️ Sleep interrupted between retries: " + ie.getMessage());
	                        Thread.currentThread().interrupt(); // restore interrupted state (best practice)
	                        break;
	                    }
	                }
	            }

	            if (transResponse == null) {
	                System.out.println("⚠️ UpdateAttendance returned null after all attempts");
	            }

	            return transResponse;

	        } catch (Exception e) {
	            System.out.println("❌ Exception in executeUpdateAttendance: " + e.getMessage());
	            e.printStackTrace();
	            return null;
	        }
	    }




	    
	    // clear data API
	    public Response executeClearData(String loginBaseUri, String loginEndpoint,
	            String username, String password, String companyCode,
	            String transBaseUri, String transEndpoint,
	            String dateFromExcel) {

	        System.out.println("==== executeClearData() called ====");

	        Response clearResponse = null;

	        try {
	            // ✅ Parse the date properly
	            LocalDate parsedDate = LocalDate.parse(dateFromExcel); // Format: yyyy-MM-dd
	            int year = parsedDate.getYear();
	            int month = parsedDate.getMonthValue();

	            System.out.println("Date From Excel: " + dateFromExcel);
	            System.out.println("Parsed Year: " + year + " Parsed Month: " + month);

	            // ✅ Login
	            Response loginResponse = sendLoginRequest(loginBaseUri, loginEndpoint, username, password, companyCode);
	            System.out.println("Login Response Status: " + loginResponse.statusCode());
	            System.out.println("Login Response Body: " + loginResponse.asString());

	            if (loginResponse.statusCode() != 200) {
	                System.out.println("❌ Login failed: " + loginResponse.asString());
	                return loginResponse;
	            }

	            // ✅ Extract Token
	            String extractedToken = loginResponse.jsonPath().getString("Token");
	            if (extractedToken == null || extractedToken.isEmpty()) {
	                extractedToken = loginResponse.jsonPath().getString("token");
	            }
	            if (extractedToken == null || extractedToken.isEmpty()) {
	                System.out.println("❌ Token not found in login response.");
	                return loginResponse;
	            }

	            MeghPIAttenAPIPage.setToken(extractedToken);
	            System.out.println("✅ Using Token: " + extractedToken);

	            // ✅ Build body
	            Map<String, Object> requestBody = new HashMap<>();
	            requestBody.put("Month", month);
	            requestBody.put("Year", year);
	            System.out.println("Request Body: " + requestBody);

	            // ✅ Call ClearData API
	            clearResponse = RestAssured.given()
	                    .baseUri(transBaseUri)
	                    .header("Authorization", "Bearer " + MeghPIAttenAPIPage.getToken())
	                    .contentType(ContentType.JSON)
	                    .body(requestBody)
	                    .post(transEndpoint);

	            System.out.println("ClearData Status Code: " + clearResponse.getStatusCode());
	            System.out.println("ClearData Response: " + clearResponse.asString());

	        } catch (Exception e) {
	            System.out.println("❌ Exception occurred while executing ClearData: " + e.getMessage());
	            e.printStackTrace();
	        }

	        return clearResponse;
	    }







//HolidayCreationAPI
	    public Response executeCreateHoliday(
	            String loginBaseUri, String loginEndpoint,
	            String username, String password, String companyCode,
	            String transBaseUri, String transEndpoint,
	            String holidayName, String holidayDate, String applicableTo,
	            String holidayDescription, String statesCsv, String stateIdsCsv) {

	        Response transResponse = null;

	        try {
	            // --- Step 1: Perform login (if token not available) ---
	            if (MeghPIAttenAPIPage.getToken() == null || MeghPIAttenAPIPage.getToken().isEmpty()) {
	                System.out.println("🔄 Token not found. Performing login...");

	                Response loginResponse = sendLoginRequest(loginBaseUri, loginEndpoint, username, password, companyCode);
	                System.out.println("Login Response: " + loginResponse.asString());

	                if (loginResponse.getStatusCode() == 200) {
	                    String extractedToken = loginResponse.jsonPath().getString("Token");
	                    if (extractedToken == null || extractedToken.isEmpty()) {
	                        extractedToken = loginResponse.jsonPath().getString("token");
	                    }

	                    if (extractedToken != null && !extractedToken.isEmpty()) {
	                        MeghPIAttenAPIPage.setToken(extractedToken);
	                        System.out.println("✅ Extracted Token: " + extractedToken);
	                    } else {
	                        System.out.println("❌ Token not found in login response.");
	                        return null;
	                    }
	                } else {
	                    System.out.println("❌ Login failed. Response: " + loginResponse.asString());
	                    return null;
	                }
	            } else {
	                System.out.println("✅ Using existing token: " + MeghPIAttenAPIPage.getToken());
	            }

	            // --- Step 2: Convert Excel values into lists ---
	            List<String> states = Arrays.asList(statesCsv.split(","));
	            List<String> stateIds = Arrays.asList(stateIdsCsv.split(","));

	            if (states.size() != stateIds.size()) {
	                System.out.println("❌ States and StateIds count mismatch in Excel data.");
	                return null;
	            }

	            List<Map<String, Object>> holidayDetails = new ArrayList<>();
	            for (int i = 0; i < states.size(); i++) {
	                Map<String, Object> detail = new HashMap<>();
	                detail.put("CountryId", 1); // Can be parameterized if needed
	                detail.put("StateId", Integer.parseInt(stateIds.get(i).trim()));
	                detail.put("StateName", states.get(i).trim());
	                holidayDetails.add(detail);
	            }

	            // --- Step 3: Build request body ---
	            Map<String, Object> holiday = new HashMap<>();
	            holiday.put("Name", holidayName);
	            holiday.put("Date", holidayDate);
	            holiday.put("ApplicableTo", applicableTo);
	            holiday.put("IsMandatory", true);
	            holiday.put("StateId", states);
	            holiday.put("HolidayDetail", holidayDetails);
	            holiday.put("HolidayDescription", holidayDescription);

	            Map<String, Object> requestBody = new HashMap<>();
	            requestBody.put("CountryId", 1);
	            requestBody.put("HolidayList", Collections.singletonList(holiday));

	            // --- Step 4: Perform API call ---
	            transResponse = io.restassured.RestAssured.given()
	                    .baseUri(transBaseUri)
	                    .header("Authorization", "Bearer " + MeghPIAttenAPIPage.getToken())
	                    .header("Content-Type", "application/json")
	                    .body(requestBody)
	                    .post(transEndpoint);

	            System.out.println("Holiday Creation Response: " + transResponse.asString());

	            // --- Step 5: Validate response ---
	            if (transResponse.getStatusCode() == 200) {
	                Boolean isActive = transResponse.jsonPath().getBoolean("IsActive");
	                String id = transResponse.jsonPath().getString("Id");

	                if (Boolean.TRUE.equals(isActive)) {
	                    System.out.println("✅ Holiday created successfully with Id=" + id + ", Name=" + holidayName);
	                } else {
	                    System.out.println("⚠️ Holiday creation failed. Response: " + transResponse.asString());
	                }
	            } else {
	                System.out.println("⚠️ Holiday creation failed with HTTP status "
	                        + transResponse.getStatusCode() + ": " + transResponse.asString());
	            }

	            return transResponse;

	        } catch (Exception e) {
	            System.out.println("❌ Exception occurred in executeCreateHoliday: " + e.getMessage());
	            e.printStackTrace();
	            return transResponse;
	        }
	    }


	    
	    public static Response executeCreateEntityAndEnroll(
	            String loginBaseUri, String loginEndpoint,
	            String username, String password, String companyCode,
	            String entityBaseUri, String entityEndpoint,
	            String enrollBaseUri, String enrollEndpoint,
	            int companyLocation, String firstName, String lastName,
	            int reportingTo, String doj, String contactNo,
	            String emailId, String employeeId, int entityTypeId,
	            boolean sendEmailInvitation,
	            String deviceUniqueId, String photo) {

	        Response enrollResponse = null;

	        try {
	            // Step 1: Perform login
	            Response loginResponse = sendLoginRequest(loginBaseUri, loginEndpoint, username, password, companyCode);
	            System.out.println("Login Response: " + loginResponse.asString());

	            if (loginResponse.statusCode() == 200) {
	                String extractedToken = loginResponse.jsonPath().getString("Token");
	                if (extractedToken == null || extractedToken.isEmpty()) {
	                    extractedToken = loginResponse.jsonPath().getString("token");
	                }
	                if (extractedToken != null && !extractedToken.isEmpty()) {
	                    MeghPIAttenAPIPage.setToken(extractedToken);
	                    System.out.println("✅ Extracted Token: " + extractedToken);
	                } else {
	                    System.out.println("❌ Token not found in login response.");
	                    return null;
	                }
	            } else {
	                System.out.println("❌ Login failed: " + loginResponse.asString());
	                return null;
	            }

	            // Step 2: Prepare Create Entity request body
	            Map<String, Object> employee = new HashMap<>();
	            employee.put("CompanyLocation", companyLocation);
	            employee.put("FirstName", firstName);
	            employee.put("LastName", lastName);
	            employee.put("ReportingTo", reportingTo);
	            employee.put("DOJ", doj);
	            employee.put("ContactNo", contactNo);
	            employee.put("EmailID", emailId);
	            employee.put("EmployeeID", employeeId);
	            employee.put("EntityTypeId", entityTypeId);
	            employee.put("SendEmailInvitation", sendEmailInvitation);

	            Map<String, Object> entityRequest = new HashMap<>();
	            entityRequest.put("EmployeeList", Collections.singletonList(employee));

	            // Step 3: Call Create Entity API
	            Response entityResponse = io.restassured.RestAssured.given()
	                    .baseUri(entityBaseUri)
	                    .header("Authorization", "Bearer " + MeghPIAttenAPIPage.getToken())
	                    .header("Content-Type", "application/json")
	                    .body(entityRequest)
	                    .post(entityEndpoint);

	            System.out.println("Create Entity Response: " + entityResponse.asString());

	            if (entityResponse.statusCode() != 200) {
	                System.out.println("❌ Create Entity API failed: " + entityResponse.asString());
	                return entityResponse;
	            }

	            // ✅ Validate Create Entity response
	            String createErrorCode = entityResponse.jsonPath().getString("ErrorCode");
	            String createErrorDesc = entityResponse.jsonPath().getString("ErrorDescription");

	            if (createErrorCode != null && !"0".equals(createErrorCode)) {
	                System.out.println("❌ Create Entity failed. ErrorCode=" + createErrorCode + ", ErrorDescription=" + createErrorDesc);
	                return entityResponse;
	            }

	            int createdEntityId = entityResponse.jsonPath().getInt("Id");
	            if (createdEntityId <= 0) {
	                System.out.println("❌ Invalid Create Entity Response: " + entityResponse.asString());
	                return entityResponse;
	            }

	            System.out.println("✅ Created Entity ID: " + createdEntityId);

	            // Step 4: Prepare Enrollment request body
	            Map<String, Object> enrollRequest = new HashMap<>();
	            enrollRequest.put("deviceUniqueId", deviceUniqueId);
	            enrollRequest.put("employeeUniqueId", employeeId);
	            enrollRequest.put("photo", photo);

	            // Step 5: Call Enrollment API
	            enrollResponse = io.restassured.RestAssured.given()
	                    .baseUri(enrollBaseUri)
	                    .header("Authorization", "Bearer " + MeghPIAttenAPIPage.getToken())
	                    .header("Content-Type", "application/json")
	                    .body(enrollRequest)
	                    .post(enrollEndpoint);

	            System.out.println("Enrollment Response: " + enrollResponse.asString());

	            // Step 6: Validate Enrollment Response
	            if (enrollResponse.statusCode() == 200) {
	                String errorCode = enrollResponse.jsonPath().getString("ErrorCode");
	                String errorDesc = enrollResponse.jsonPath().getString("ErrorDescription");

	                if ("0".equals(errorCode) && "Success".equalsIgnoreCase(errorDesc)) {
	                    System.out.println("✅ Enrollment successful for Employee: " + employeeId);
	                } else {
	                    System.out.println("❌ Enrollment failed. ErrorCode=" + errorCode + ", ErrorDescription=" + errorDesc);
	                    if ("-101".equals(errorCode)) {
	                        System.out.println("⚠️ Device Not Registered — Please register the device before enrollment.");
	                    }
	                }
	            } else {
	                System.out.println("⚠️ Enrollment API failed with HTTP status "
	                        + enrollResponse.statusCode() + ": " + enrollResponse.asString());
	            }

	        } catch (Exception e) {
	            System.out.println("❌ Exception occurred in executeCreateEntityAndEnroll: " + e.getMessage());
	            e.printStackTrace();
	        }

	        return enrollResponse;
	    }


	    
	    //getTable list
	    public static class TableFieldIds {
	        public Integer companyLocationId;
	        public Integer entityId;
	        public Integer entityTypeId;
	    }

	    public static TableFieldIds executeGetTableFieldIds(
	            String loginBaseUri, String loginEndpoint,
	            String username, String password, String companyCode,
	            String transBaseUri, String transEndpoint,
	            String companyLocationName, String entityName, String entityTypeName) {

	        TableFieldIds ids = new TableFieldIds();

	        try {
	            // Step 1: Login
	            Response loginResponse = sendLoginRequest(loginBaseUri, loginEndpoint, username, password, companyCode);
	            System.out.println("Login Response: " + loginResponse.asString());

	            if (loginResponse.statusCode() != 200) {
	                System.err.println("❌ Login failed: " + loginResponse.asString());
	                return ids;
	            }

	            // Extract token
	            String token = loginResponse.jsonPath().getString("Token");
	            if (token == null || token.isEmpty()) {
	                token = loginResponse.jsonPath().getString("token");
	            }
	            if (token == null || token.isEmpty()) {
	                System.err.println("❌ Token not found in login response.");
	                return ids;
	            }

	            MeghPIAttenAPIPage.setToken(token);
	            System.out.println("✅ Extracted Token: " + token);

	            int retryCount = 0;

	            // 🔄 RETRY LOOP ADDED — up to 5 attempts if any ID is null
	            while (retryCount < 5) {

	                // Step 2: Call gettablefieldlist API
	                Map<String, Object> requestBody = new HashMap<>();
	                requestBody.put("Type", "");

	                Response tableFieldResponse = io.restassured.RestAssured.given()
	                        .baseUri(transBaseUri)
	                        .header("Authorization", "Bearer " + MeghPIAttenAPIPage.getToken())
	                        .header("Content-Type", "application/json")
	                        .body(requestBody)
	                        .post(transEndpoint);

	                System.out.println("GetTableFieldList Response: " + tableFieldResponse.asString());

	                if (tableFieldResponse.statusCode() != 200) {
	                    System.err.println("❌ GetTableFieldList API failed with status " + tableFieldResponse.getStatusCode());
	                    return ids;
	                }

	                // Step 3: Parse IDs
	                List<Map<String, Object>> responseList = tableFieldResponse.jsonPath().getList("$");

	                ids = new TableFieldIds(); // clear previous IDs

	                for (Map<String, Object> item : responseList) {
	                    String type = (String) item.get("Type");
	                    String name = (String) item.get("Name");
	                    Integer id = (Integer) item.get("Id");

	                    System.out.println("TableField Item: Id=" + id + " Name=" + name + " Type=" + type);

	                    if ("CompanyLocation".equalsIgnoreCase(type) && name.equalsIgnoreCase(companyLocationName)) {
	                        ids.companyLocationId = id;
	                    } else if ("Entity".equalsIgnoreCase(type) && name.contains(entityName)) {
	                        ids.entityId = id;
	                    } else if ("EntityType".equalsIgnoreCase(type) && name.equalsIgnoreCase(entityTypeName)) {
	                        ids.entityTypeId = id;
	                    }
	                }

	                // If all IDs found → return
	                if (ids.companyLocationId != null && ids.entityId != null && ids.entityTypeId != null) {
	                    System.out.println("✅ Extracted IDs -> CompanyLocationId=" + ids.companyLocationId +
	                            ", EntityId=" + ids.entityId +
	                            ", EntityTypeId=" + ids.entityTypeId);
	                    return ids;
	                }

	                // ❗ If any ID is null → retry
	                System.err.println("⚠️ Some IDs are null. Retrying attempt: " + (retryCount + 1));
	                retryCount++;

	                Thread.sleep(1500); // wait before retry (optional but recommended)
	            }

	            // After 5 retries → log missing IDs
	            if (ids.companyLocationId == null)
	                System.err.println("⚠️ CompanyLocation ID not found for: " + companyLocationName);
	            if (ids.entityId == null)
	                System.err.println("⚠️ Entity ID not found for: " + entityName);
	            if (ids.entityTypeId == null)
	                System.err.println("⚠️ EntityType ID not found for: " + entityTypeName);

	        } catch (Exception e) {
	            System.err.println("❌ Exception in executeGetTableFieldIds: " + e.getMessage());
	        }

	        return ids;
	    }




	    public String getExceptionDesc() {
			return this.exceptionDesc;
		}

		public  void setExceptionDesc(String exceptionDesc) {  
			exceptionDesc = this.exceptionDesc;
		}
    
}
