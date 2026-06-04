package ru.effectivemobile.api.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecifications {

    private RequestSpecifications() {
    }

    public static RequestSpecification defaultSpec() {
        return new RequestSpecBuilder()
//                .setBaseUri("http://localhost:8080/")
                .setBaseUri("https://demo.reportportal.io/")
                .addHeader("Authorization",
//                        "Bearer " + "rp-test_PxS6WrN7ToS8B5qM6cgc3n8WYt8AUmJnH2Ca022mkhhT3bzmziypGNh_r_4FYfcx")
                        "Bearer " + "VikN-Api_zgwrtDTSRE-cllQMRbmTCQ85HnDZ3kWrfGEpGyiMRc4VZtbSr0Wm2z3o6OZNDirJ")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }
}
