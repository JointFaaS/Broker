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
import org.springframework.web.reactive.function.client.WebClientResponseException;

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
        try {
            return wc
                    .post()
                    .uri("/create")
                    .body(BodyInserters.fromValue(createFunctionInput))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(CreateFunctionOutput.class)
                    .block();
        }catch (WebClientResponseException e) {
            System.out.println(e.getResponseBodyAsString());
            // TODO
            return new CreateFunctionOutput();
        }
    }

    @Override
    public Function getFunction(String id) {
        try {
            return wc
                    .get()
                    .uri(uriBuilder -> uriBuilder.path("/get")
                            .queryParam("funcName", id)
                            .build())
                    .retrieve()
                    .bodyToMono(Function.class)
                    .block();
        }catch (WebClientResponseException e) {
            System.out.println(e.getResponseBodyAsString());
            // TODO
            return new Function();
        }
    }

    @Override
    public List<Function> getFunctions() {
        try {
            return new ArrayList<Function>(Arrays.asList(wc
                    .get()
                    .uri("/list")
                    .retrieve()
                    .bodyToMono(Function[].class)
                    .block()));
        }catch (WebClientResponseException e) {
            System.out.println(e.getResponseBodyAsString());
            // TODO
            return new ArrayList<>();
        }
    }

    @Override
    public InvokeFunctionOutput invokeFunction(InvokeFunctionInput invokeFunctionInput) {
        try {
            return new InvokeFunctionOutput(200, wc
                    .post()
                    .uri("/invoke")
                    .body(BodyInserters.fromValue(invokeFunctionInput))
                    .retrieve()
                    .bodyToMono(byte[].class)
                    .block());
        }catch (WebClientResponseException e) {
            System.out.println(e.getResponseBodyAsString());
            // TODO
            return new InvokeFunctionOutput(400, e.getResponseBodyAsString());
        }
    }

    @Override
    public DeleteFunctionOutput deleteFunction(DeleteFunctionInput deleteFunctionInput) {
        try {
            return new DeleteFunctionOutput(wc
                    .post()
                    .uri(uriBuilder -> uriBuilder.path("/delete")
                                    .queryParam("funcName", deleteFunctionInput.getFuncName())
                                    .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block());
        }catch (WebClientResponseException e) {
            System.out.println(e.getResponseBodyAsString());
            // TODO
            return new DeleteFunctionOutput();
        }
    }

}
