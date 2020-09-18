package com.meli.challenge.items.service;

import com.meli.challenge.items.dto.HealthCheckDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HealthService {

    private static final Logger logger = LoggerFactory.getLogger(HealthService.class);

    private @Value("${external.health.slots}") Integer slots;
    private @Value("${external.health.split}") Integer split;

    private LinkedHashMap<Long, HealthCheckDTO> inMemoryMap = new LinkedHashMap<>() {
        @Override
        protected boolean removeEldestEntry(final Map.Entry eldest) {
            return size() > slots;
        }
    };

    public List<HealthCheckDTO> getHealthCheck() {
        return inMemoryMap.values()
                .stream()
                .collect(Collectors.toList());
    }

    public void registerExternalApiCall(
            HttpMethod method,
            URI uri,
            HttpStatus status,
            Long startTime,
            Long stopTime
    ) {
        Long executionTime = stopTime - startTime;
        logger.info("HTTP {} request to {}", method, uri);
        logger.info("-xxx- " + status + " " + executionTime );

        HealthCheckDTO pointer = getSlot(startTime);
        pointer.setAvgResponseTimeApiCalls(recalculateAverage(executionTime, pointer.getAvgResponseTimeApiCalls(), pointer.getTotalCountApiCalls()));
        pointer.setTotalCountApiCalls(pointer.getTotalCountApiCalls() + 1);

        String statusString = status.toString();
        if (pointer.getInfoRequest().containsKey(statusString)) {
            Integer count = pointer.getInfoRequest().get(statusString);
            pointer.getInfoRequest().put(statusString, count + 1);
        } else {
            pointer.getInfoRequest().put(statusString, 1);
        }
    }

    public void registerApiCall(
            Long startTime,
            Long stopTime
    ) {
        Long executionTime = stopTime - startTime;
        logger.info(" API call executed in " + executionTime + "ms");

        HealthCheckDTO pointer = getSlot(startTime);
        pointer.setAvgResponseTime(recalculateAverage(executionTime, pointer.getAvgResponseTime(), pointer.getTotalRequest()));
        pointer.setTotalRequest(pointer.getTotalRequest() + 1);
    }

    private HealthCheckDTO getSlot(Long reference) {

        Long minuteKey = reference - reference % (split * 1000);
        if (!inMemoryMap.containsKey(minuteKey)) {
            HealthCheckDTO slot = new HealthCheckDTO(
                    new Date(minuteKey),
                    0.0, 0, 0.0, 0, new HashMap<>()
            );
            inMemoryMap.put(minuteKey, slot);
            return slot;
        } else {
            return inMemoryMap.get(minuteKey);
        }
    }

    private Double recalculateAverage(Long execution, Double average, Integer total) {
        return ((average * total) + execution) / (total + 1);
    }
}
