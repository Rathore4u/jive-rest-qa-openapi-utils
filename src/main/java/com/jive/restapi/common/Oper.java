package com.jive.restapi.common;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import java.util.function.Consumer;
import java.util.function.Function;

public interface Oper<T extends Oper> {
    T reqSpec(Consumer<RequestSpecBuilder> consumer);
    <R> R execute(Function<Response, R> handler);
}
