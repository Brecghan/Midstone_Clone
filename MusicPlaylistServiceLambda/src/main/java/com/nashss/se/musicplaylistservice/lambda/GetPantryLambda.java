package com.nashss.se.musicplaylistservice.lambda;

import com.nashss.se.musicplaylistservice.activity.requests.GetPantryRequest;
import com.nashss.se.musicplaylistservice.activity.results.GetPantryResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetPantryLambda
        extends LambdaActivityRunner<GetPantryRequest, GetPantryResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetPantryRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetPantryRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
            () -> {
                GetPantryRequest unauthenticatedRequest = input.fromPath(path ->
                        GetPantryRequest.builder()
                                .withPantryId(path.get("pantryId"))
                                .build());
                return input.fromUserClaims(claims ->
                        GetPantryRequest.builder()
                                .withPantryId(unauthenticatedRequest.getPantryId())
                                .withUserId(claims.get("email"))
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideGetPantryActivity().handleRequest(request)
        );
    }
}

