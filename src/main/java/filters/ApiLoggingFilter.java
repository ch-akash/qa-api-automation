package filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class ApiLoggingFilter implements Filter {

    @Override
    public Response filter(
            FilterableRequestSpecification requestSpec,
            FilterableResponseSpecification responseSpec,
            FilterContext ctx) {
//        Replacing header in request
//        requestSpec.replaceHeader("Content-Type", "test");
        var response = ctx.next(requestSpec, responseSpec);

//        if (response.statusCode() == 200) {
//            System.out.printf("Request with status code != 200 %s%n", requestSpec.getDerivedPath());
        //Modifying response and setting new body
//            return new ResponseBuilder().clone(response).setBody("this is cloned response")
//                                        .build();
//        } else
        return response;
    }
}
