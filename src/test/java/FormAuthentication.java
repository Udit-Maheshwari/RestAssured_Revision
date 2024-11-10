import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class FormAuthentication {

    @BeforeClass
    public void beforeClass() {
        RestAssured.requestSpecification = new RequestSpecBuilder().
                setRelaxedHTTPSValidation().//to ignore certificate https certification
                        setBaseUri("https://localhost:8443").
                build();
    }

    @Test
    public void formAuthentication() {
        SessionFilter sessionFilter = new SessionFilter();
        RestAssured.given()
                .auth().form("dan", "dan123",
                        new FormAuthConfig("/signin", "txtUsername", "txtPassword"))
                .filter(sessionFilter)
                .when()
                .get("/login")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

        System.out.println("Session Filter is ==>" + sessionFilter.getSessionId());
    }

    @Test
    public void authenticationUsingCSRFTokenGetProfile() {
        SessionFilter sessionFilter = new SessionFilter();
        RestAssured.given()
                .csrf("/login")
                .auth().form("dan", "dan123",
                        new FormAuthConfig("/signin", "txtUsername", "txtPassword"))
                .filter(sessionFilter)
                .when()
                .get("/login")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
        System.out.println("Session Filter is ==>" + sessionFilter.getSessionId());

        RestAssured.given()
                .sessionId(sessionFilter.getSessionId())
                .log().all()
                .when()
                .get("/profile/index")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("html.body.div.p", Matchers.equalTo("This is User Profile\\Index. Only authenticated people can see this"));

    }

    @Test
    public void handlingCookiesToGetProfile() {
        SessionFilter sessionFilter = new SessionFilter();
        RestAssured.given()
                .csrf("/login")
                .auth().form("dan", "dan123",
                        new FormAuthConfig("/signin", "txtUsername", "txtPassword"))
                .filter(sessionFilter)
                .when()
                .get("/login")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
        System.out.println("Session Filter is ==>" + sessionFilter.getSessionId());

        //Create Cookie method for initializing the value
        Cookie cookie = new Cookie.Builder("JSESSIONID", sessionFilter.getSessionId())
                .setHttpOnly(true).setSecured(true).setComment("My Cookie").build();

        RestAssured.given()
                .cookie(cookie)
               // .cookie("JSESSIONID", sessionFilter.getSessionId()) // we can use Cookies as well instead of session id
             //   .sessionId(sessionFilter.getSessionId())
                .log().all()
                .when()
                .get("/profile/index")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("html.body.div.p", Matchers.equalTo("This is User Profile\\Index. Only authenticated people can see this"));
    }

    @Test
    public void sendMultipleCookies() {
        SessionFilter sessionFilter = new SessionFilter();
        RestAssured.given()
                .log().all()
                .csrf("/login")
                .auth().form("dan", "dan123",
                        new FormAuthConfig("/signin", "txtUsername", "txtPassword"))
                .filter(sessionFilter)
                .when()
                .get("/login")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
        System.out.println("Session Filter is ==>" + sessionFilter.getSessionId());

        //Create Cookie method for initializing the value
        Cookie cookie = new Cookie.Builder("JSESSIONID", sessionFilter.getSessionId())
                .setHttpOnly(true).setSecured(true).setComment("My Cookie").build();
        Cookie cookie1 = new Cookie.Builder("dummy", "dummyValue").build();

        Cookies cookies = new Cookies(cookie, cookie1);

        RestAssured.given()
                .log().all()
                .cookies(cookies)
                .when()
                .get("/profile/index")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("html.body.div.p", Matchers.equalTo("This is User Profile\\Index. Only authenticated people can see this"));
    }

    @Test
    public void fetchSingleCookie() {
        Response response = RestAssured.given()
                .log().all()
                .when()
                .get("/profile/index")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        String cookieValue = response.getCookie("JSESSIONID");
        System.out.println("Cookie Value is --> "+cookieValue);

        Cookie detailedCookieValue = response.getDetailedCookie("JSESSIONID");
        System.out.println("Detailed Cookie Value is --> "+detailedCookieValue.toString());
    }

    @Test
    public void fetchMultipleCookie() {
        Response response = RestAssured.given()
                .log().all()
                .when()
                .get("/profile/index")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

       Map<String, String> cookieMap = response.getCookies();
       for(Map.Entry<String, String> entry : cookieMap.entrySet()) {
           System.out.println("Cookie Name is --> " + entry.getKey());
           System.out.println("Cookie Value is --> " + entry.getValue());
       }

        Cookies detailedCookies = response.getDetailedCookies();
        List<Cookie> cookiesList = detailedCookies.asList();
        for(Cookie cookie : cookiesList) {
            System.out.println("Detailed Cookie Data is --> " + cookie.toString());
        }
    }
}
