package com.cybertek.utilities;

import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.baseURI;

public class HRTestBase {

    @BeforeAll
    public static void init() {
        // save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://54.159.143.93:1000/ords/hr";

        // get IP address from configurations

    }

}
