package com.nashss.se.musicplaylistservice.lambda;

import com.nashss.se.musicplaylistservice.activity.requests.GetRecipeDetailsRequest;
import com.nashss.se.musicplaylistservice.activity.results.GetRecipeDetailsResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetRecipeDetailsLambda
        extends LambdaActivityRunner<GetRecipeDetailsRequest, GetRecipeDetailsResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetRecipeDetailsRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetRecipeDetailsRequest> input, Context context) {
        return super.runActivity(
            () -> {
                return input.fromPath(path ->
                        GetRecipeDetailsRequest.builder()
                                .withRecipeId(path.get("recipeId"))
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideGetRecipeDetailsActivity().handleRequest(request)
        );
    }
}

