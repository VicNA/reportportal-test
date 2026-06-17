package ru.effectivemobile.utils;

import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.effectivemobile.api.data.LaunchFactory;
import ru.effectivemobile.api.exceptions.ApiException;
import ru.effectivemobile.api.models.CreateLaunchResponse;
import ru.effectivemobile.api.models.LaunchResponse;
import ru.effectivemobile.api.services.LaunchesService;

import java.util.LinkedHashMap;
import java.util.UUID;

@AllArgsConstructor
public class LaunchesHelper {

    private static final Logger logger = LogManager.getLogger(LaunchesHelper.class);

    private final LinkedHashMap<UUID, LaunchResponse> responseMap = new LinkedHashMap<>();

    private final LaunchesService launchesService;

    public CreateLaunchResponse createLaunch() {
        return launchesService.createLaunch(LaunchFactory.create())
                .as(CreateLaunchResponse.class);
    }

    public void addLaunch(CreateLaunchResponse response) {
        getLaunchById(response.getId());
    }

    public LaunchResponse getLaunchById(UUID id) {
        return asLaunchResponse(launchesService.getLaunchById(id));
    }

    public LaunchResponse asLaunchResponse(Response response) {
        LaunchResponse launchResponse = response.as(LaunchResponse.class);

        responseMap.put(launchResponse.getUuid(), launchResponse);

        return launchResponse;
    }

    public int responseSize() {
        return responseMap.size();
    }

    public LaunchResponse getFirstLaunch() {
        return responseMap.firstEntry().getValue();
    }

    public void clear() {
        try {
            responseMap.values().forEach(resp -> {
                launchesService.finishLaunch(resp.getUuid(), LaunchFactory.finish());
                Response response = launchesService.deleteLaunch(resp.getId());
                if (response.statusCode() != 200) {
                    throw new ApiException("Request failed. Status: %d. Body: %s"
                            .formatted(response.statusCode(), response.body().asString())
                    );
                }
            });
        } catch (ApiException e) {
            logger.error(e.getMessage());
        }
        responseMap.clear();
    }
}
