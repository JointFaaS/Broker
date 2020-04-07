package jointfaas.client.impl;

import com.alibaba.fastjson.support.spring.FastjsonSockJsMessageCodec;
import jointfaas.client.Client;
import jointfaas.client.pojo.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientWebClientImpl implements Client {
    public ClientWebClientImpl(String managerURL) {
        this.wc = WebClient.builder()
                .baseUrl(managerURL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    private WebClient wc;


    @Override
    public CreateFunctionOutput createFunction(CreateFunctionInput createFunctionInput) {
        return wc
                .post()
                .uri("/create")
                .body(BodyInserters.fromValue(createFunctionInput))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CreateFunctionOutput.class)
                .block();
    }

    @Override
    public Function getFunction(String id) {
        return wc
                .get()
                .uri(uriBuilder -> uriBuilder.path("/get")
                                .queryParam("funcName", id)
                                .build())
                .retrieve()
                .bodyToMono(Function.class)
                .block();
    }

    @Override
    public List<Function> getFunctions() {
        return new ArrayList<Function>(Arrays.asList(wc
                .get()
                .uri("/list")
                .retrieve()
                .bodyToMono(Function[].class)
                .block()));
    }

    @Override
    public InvokeFunctionOutput invokeFunction(InvokeFunctionInput invokeFunctionInput) {
        return new InvokeFunctionOutput(200, wc
                .post()
                .uri("/create")
                .body(BodyInserters.fromValue(invokeFunctionInput))
                .retrieve()
                .bodyToMono(byte[].class)
                .block());
    }

    @Override
    public DeleteFunctionOutput deleteFunction(DeleteFunctionInput deleteFunctionInput) {
        return wc
                .post()
                .uri("/delete")
                .body(BodyInserters.fromValue(deleteFunctionInput))
                .retrieve()
                .bodyToMono(DeleteFunctionOutput.class)
                .block();
    }

}
