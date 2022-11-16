package org.acme;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        var JSON = """
                {
                    "context" : {
                        "a": 123,
                        "b": 987,
                        "order": {
                            "gross" : 1000,
                            "tax" : 200
                        }
                    },
                    "expr" : "order.gross - order.tax"
                }
                """;
        var response = given()
          .when()
          .body(JSON)
          .contentType(ContentType.JSON)
          .post("/mvel")
          .then()
             .statusCode(200)
             .extract().asString();
        System.out.println("Response as string: " + response);
        assertThat(response).isEqualTo("800");
    }

}