package com.nashss.se.musicplaylistservice.lambda;

import com.nashss.se.musicplaylistservice.activity.requests.CompareRecipeAndInventoryRequest;
import com.nashss.se.musicplaylistservice.activity.results.CompareRecipeAndInventoryResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CompareRecipeAndInventoryLambda
        extends LambdaActivityRunner<CompareRecipeAndInventoryRequest, CompareRecipeAndInventoryResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CompareRecipeAndInventoryRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CompareRecipeAndInventoryRequest> input,
                                        Context context) {
        return super.runActivity(
            () -> {
                CompareRecipeAndInventoryRequest unauthenticatedRequest =
                        input.fromBody(CompareRecipeAndInventoryRequest.class);
                return input.fromUserClaims(claims ->
                        CompareRecipeAndInventoryRequest.builder()
                                .withRecipeId(unauthenticatedRequest.getRecipeId())
                                .withPantryId(unauthenticatedRequest.getPantryId())
                                .withUserId(claims.get("email"))
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideCompareRecipeAndInventoryActivity().handleRequest(request)
        );
    }
}

