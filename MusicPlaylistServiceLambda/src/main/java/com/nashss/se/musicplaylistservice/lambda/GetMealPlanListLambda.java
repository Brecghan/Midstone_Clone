package com.nashss.se.musicplaylistservice.lambda;

import com.nashss.se.musicplaylistservice.activity.requests.GetMealPlanListRequest;
import com.nashss.se.musicplaylistservice.activity.results.GetMealPlanListResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetMealPlanListLambda
        extends LambdaActivityRunner<GetMealPlanListRequest, GetMealPlanListResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetMealPlanListRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetMealPlanListRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
            () -> input.fromUserClaims(claims ->
                        GetMealPlanListRequest.builder()
                                .withUserId(claims.get("email"))
                                .build()),
            (request, serviceComponent) ->
                    serviceComponent.provideGetMealPlanListActivity().handleRequest(request)
        );
    }
}

