package ru.effectivemobile.api.client;

import io.restassured.specification.RequestSpecification;
import ru.effectivemobile.api.specs.RequestSpecifications;

public abstract class ApiClient {

    protected RequestSpecification requestSpec = RequestSpecifications.defaultSpec();
}
