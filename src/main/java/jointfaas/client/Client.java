package jointfaas.client;

import jointfaas.client.pojo.*;

import java.nio.ByteBuffer;
import java.util.List;

public interface Client {
    CreateFunctionOutput createFunction(CreateFunctionInput createFunctionInput);

    Function getFunction(String id);

    List<Function> getFunctions();

    InvokeFunctionOutput invokeFunction(InvokeFunctionInput invokeFunctionInput);

    DeleteFunctionOutput deleteFunction(DeleteFunctionInput deleteFunctionInput);
}
