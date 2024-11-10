import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class DemoClass {

    @Test
    public void lotto_resource_returns_200_with_expected_id_and_winners() {

        RestAssured.given().
                when().
                get("/lotto/{id}", 5).
                then().
                statusCode(200).
                body("lotto.lottoId", equalTo(5),
                        "lotto.winners.winnerId", hasItems(23, 54))
                ;

    }
}
