import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoWebShopTests {
    @Test
    void addToCartTest() {
        String body = "product_attribute_5_7_1=1&addtocart_5.EnteredQuantity=1";
        String cookie = "Nop.customer=c93d1d82-d00d-43e6-9095-d8e13ddfa033; ARRAffinity=7f10010dd6b12d83d6aefe199065b2e8fe0d0850a7df2983b482815225e42439; __utma=78382081.1263992415.1610644065.1610644065.1610644065.1; __utmc=78382081; __utmz=78382081.1610644065.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=5&RecentlyViewedProductIds=72; __atuvc=4%7C2; __atuvs=60007a60ca6db4b5003; __utmb=78382081.6.10.1610644065";

        given()
                .body(body)
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie(cookie)
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/5/1")
                .then()
                .statusCode(200);
    }

    @Test
    void addToCartWithSimpleAssertTest() {
        String body = "product_attribute_5_7_1=1&addtocart_5.EnteredQuantity=1";
        String cookie = "Nop.customer=c93d1d82-d00d-43e6-9095-d8e13ddfa033; ARRAffinity=7f10010dd6b12d83d6aefe199065b2e8fe0d0850a7df2983b482815225e42439; __utma=78382081.1263992415.1610644065.1610644065.1610644065.1; __utmc=78382081; __utmz=78382081.1610644065.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=5&RecentlyViewedProductIds=72; __atuvc=4%7C2; __atuvs=60007a60ca6db4b5003; __utmb=78382081.6.10.1610644065";

        Response response = given()
                .body(body)
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie(cookie)
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/5/1")
                .then()
                .statusCode(200)
                .extract().response();

        assertTrue(response.asString().contains("The product has been added to your"));
    }

    @Test
    void addToCartWithBodyAssertTest() {
        String body = "product_attribute_5_7_1=1&addtocart_5.EnteredQuantity=1";
        String cookie = "Nop.customer=c93d1d82-d00d-43e6-9095-d8e13ddfa033; ARRAffinity=7f10010dd6b12d83d6aefe199065b2e8fe0d0850a7df2983b482815225e42439; __utma=78382081.1263992415.1610644065.1610644065.1610644065.1; __utmc=78382081; __utmz=78382081.1610644065.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=5&RecentlyViewedProductIds=72; __atuvc=4%7C2; __atuvs=60007a60ca6db4b5003; __utmb=78382081.6.10.1610644065";

        given()
                .body(body)
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie(cookie)
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/5/1")
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));

    }

    @Test
    void addToCartWithModelAssertTest() {
        String body = "product_attribute_5_7_1=1&addtocart_5.EnteredQuantity=1";
        String cookie = "Nop.customer=c93d1d82-d00d-43e6-9095-d8e13ddfa033; ARRAffinity=7f10010dd6b12d83d6aefe199065b2e8fe0d0850a7df2983b482815225e42439; __utma=78382081.1263992415.1610644065.1610644065.1610644065.1; __utmc=78382081; __utmz=78382081.1610644065.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=5&RecentlyViewedProductIds=72; __atuvc=4%7C2; __atuvs=60007a60ca6db4b5003; __utmb=78382081.6.10.1610644065";

        CartResponse response = given()
                .body(body)
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie(cookie)
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/5/1")
                .then()
                .statusCode(200)
                .extract().as(CartResponse.class);

        System.out.println(response);
        assertEquals(response.getSuccess(), true);
    }
}
