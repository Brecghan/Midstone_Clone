package com.nashss.se.musicplaylistservice.lambda;

import com.nashss.se.musicplaylistservice.activity.requests.GetPantryInventoryRequest;
import com.nashss.se.musicplaylistservice.activity.requests.GetPantryRequest;
import com.nashss.se.musicplaylistservice.activity.results.GetPantryInventoryResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetPantryInventoryLambda
        extends LambdaActivityRunner<GetPantryInventoryRequest, GetPantryInventoryResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetPantryInventoryRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetPantryInventoryRequest> input, Context context) {
        return super.runActivity(
            () -> {
                GetPantryInventoryRequest unauthenticatedRequest = input.fromPath(path ->
                        GetPantryInventoryRequest.builder()
                                .withPantryId(path.get("pantryId"))
                                .build());
                return input.fromUserClaims(claims ->
                        GetPantryInventoryRequest.builder()
                                .withPantryId(unauthenticatedRequest.getPantryId())
                                .withUserId(claims.get("email"))
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideGetPantryInventoryActivity().handleRequest(request)
        );
    }
}

