package com.nashss.se.musicplaylistservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.CreateMealPlanRequest;
import com.nashss.se.musicplaylistservice.activity.results.CreateMealPlanResult;

public class CreateMealPlanLambda
        extends LambdaActivityRunner<CreateMealPlanRequest, CreateMealPlanResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateMealPlanRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateMealPlanRequest> input, Context context) {
        return super.runActivity(
            () -> {
                CreateMealPlanRequest unauthenticatedRequest = input.fromBody(CreateMealPlanRequest.class);
                return input.fromUserClaims(claims ->
                        CreateMealPlanRequest.builder()
                                .withMealPlanName(unauthenticatedRequest.getMealPlanName())
                                .withUserId(claims.get("email"))
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideCreateMealPlanActivity().handleRequest(request)
        );
    }
}

