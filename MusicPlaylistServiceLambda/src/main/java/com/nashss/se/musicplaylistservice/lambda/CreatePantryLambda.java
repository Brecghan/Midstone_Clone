package com.nashss.se.musicplaylistservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.CreatePantryRequest;
import com.nashss.se.musicplaylistservice.activity.results.CreatePantryResult;

public class CreatePantryLambda
        extends LambdaActivityRunner<CreatePantryRequest, CreatePantryResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreatePantryRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreatePantryRequest> input, Context context) {
        return super.runActivity(
            () -> {
                CreatePantryRequest unauthenticatedRequest = input.fromBody(CreatePantryRequest.class);
                return input.fromUserClaims(claims ->
                        CreatePantryRequest.builder()
                                .withPantryName(unauthenticatedRequest.getPantryName())
                                .withUserId(claims.get("email"))
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideCreatePantryActivity().handleRequest(request)
        );
    }
}

