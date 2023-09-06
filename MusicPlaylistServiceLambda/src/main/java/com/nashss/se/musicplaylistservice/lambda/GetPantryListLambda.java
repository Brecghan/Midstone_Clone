package com.nashss.se.musicplaylistservice.lambda;

import com.nashss.se.musicplaylistservice.activity.requests.GetPantryListRequest;
import com.nashss.se.musicplaylistservice.activity.results.GetPantryListResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetPantryListLambda
        extends LambdaActivityRunner<GetPantryListRequest, GetPantryListResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetPantryListRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetPantryListRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
            () -> input.fromUserClaims(claims ->
                        GetPantryListRequest.builder()
                                .withUserId(claims.get("email"))
                                .build()),
            (request, serviceComponent) ->
                    serviceComponent.provideGetPantryListActivity().handleRequest(request)
        );
    }
}

