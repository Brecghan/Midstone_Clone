package com.nashss.se.musicplaylistservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.GetRecipesRequest;
import com.nashss.se.musicplaylistservice.activity.results.GetRecipesResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetRecipesLambda
        extends LambdaActivityRunner<GetRecipesRequest, GetRecipesResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetRecipesRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetRecipesRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
                () -> {
                    GetRecipesRequest unauthenticatedRequest = input.fromBody(GetRecipesRequest.class);
                    return input.fromUserClaims(claims ->
                            GetRecipesRequest.builder()
                                    .withRecipeRegion(unauthenticatedRequest.getRecipeRegion())
                                    .build());
                },
            (request, serviceComponent) ->
                    serviceComponent.provideGetRecipesActivity().handleRequest(request)
        );
    }
}

