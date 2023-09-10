package com.nashss.se.musicplaylistservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.GetRecipeDetailsRequest;
import com.nashss.se.musicplaylistservice.activity.results.GetRecipeDetailsResult;

public class GetRecipeDetailsLambda
        extends LambdaActivityRunner<GetRecipeDetailsRequest, GetRecipeDetailsResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetRecipeDetailsRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetRecipeDetailsRequest> input, Context context) {
        return super.runActivity(
            () -> {
                GetRecipeDetailsRequest unauthenticatedRequest = input.fromBody(GetRecipeDetailsRequest.class);
                return input.fromUserClaims(claims ->
                        GetRecipeDetailsRequest.builder()
                                .withRecipeId(unauthenticatedRequest.getRecipeId())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideGetRecipeDetailsActivity().handleRequest(request)
        );
    }
}

