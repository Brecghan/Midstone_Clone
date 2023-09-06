package com.nashss.se.musicplaylistservice.lambda;

import com.nashss.se.musicplaylistservice.activity.requests.UpdatePantryRequest;
import com.nashss.se.musicplaylistservice.activity.results.UpdatePantryResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UpdatePantryLambda
        extends LambdaActivityRunner<UpdatePantryRequest, UpdatePantryResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdatePantryRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdatePantryRequest> input, Context context) {
        return super.runActivity(
            () -> {
                UpdatePantryRequest unauthenticatedRequest = input.fromBody(UpdatePantryRequest.class);
                return input.fromUserClaims(claims ->
                        UpdatePantryRequest.builder()
                                .withPantryId(unauthenticatedRequest.getPantryId())
                                .withPantryName(unauthenticatedRequest.getPantryName())
                                .withUserId(claims.get("email"))
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideUpdatePantryActivity().handleRequest(request)
        );
    }
}

