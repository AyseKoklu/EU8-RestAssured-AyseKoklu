package com.cybertek.day4;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath extends HRTestBase {
    @DisplayName("GET request to countries with Path method")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .when()
                .get("/countries");

        assertEquals(200, response.statusCode());

        // print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        // print hasMore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        // print first country_id
        String firstCountryId = response.path("items[0].country_id");
        System.out.println("firstCountryId = " + firstCountryId);

        // print second country_name
        String secondCountryName = response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        // print "http://52.207.61.129:1000/ords/hr/countries/CA"
        String countryCA = response.path("items[2].links[0].href");
        System.out.println("countryCA = " + countryCA);

        // get all country names
        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        // assert that all regions' ids are equal to 2
        List<Integer> allRegionIds = response.path("items.region_id");

        for (Integer regionId : allRegionIds) {
            assertTrue(regionId.equals(2));
        }
    }

    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));
        assertTrue(response.body().asString().contains("IT_PROG"));

        // make sure there is only IT_PROG as a job_id
        List<String> allJobIDs = response.path("items.job_id");

        for (String jobID : allJobIDs) {
            System.out.println("jobID = " + jobID);
            assertEquals("IT_PROG", jobID);
        }

        //HW
        //print name of each IT_PROGs


    }
}