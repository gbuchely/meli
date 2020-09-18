package com.meli.challenge.items.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.challenge.items.dto.ChildrenDTO;
import com.meli.challenge.items.dto.ItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class ItemsService {

    private static final Logger logger = LoggerFactory.getLogger(ItemsService.class);

    private @Value("${external.uri.items}") String itemsResource;
    private @Value("${external.uri.children}") String itemsChildrenResource;
    private @Value("${external.date.pattern}") String datePattern;

    @Resource
    private RestTemplate template;

    private ObjectMapper mapper = new ObjectMapper();
    private SimpleDateFormat formatter;


    public CompletableFuture<ItemDTO> getItemWithChildren(String id) {
        logger.info("Retrieving Item from API :: " + id);

        formatter = new SimpleDateFormat(datePattern);

        CompletableFuture<ItemDTO> completableFuture =
            CompletableFuture
                .supplyAsync(() ->
                    parseItem(callExternalAPI(MessageFormat.format(itemsResource, id))
                )
            )
            .thenCombine(
                CompletableFuture
                    .supplyAsync(() ->
                        parseChildren(callExternalAPI(MessageFormat.format(itemsChildrenResource, id))
                    )
                ),
                (itemDTO, children) -> {
                    itemDTO.setChildren(children);
                    return itemDTO;
                });

        return completableFuture;
    }

    private ResponseEntity<String> callExternalAPI(String uri) {
        return template.getForEntity(uri, String.class);
    }

    private ItemDTO parseItem(ResponseEntity<String> response) {
        ItemDTO itemDTO = null;

        try {
            Map<String, String> itemValues = mapper.readValue(response.getBody(), Map.class);

            itemDTO = new ItemDTO(
                itemValues.get("id"),
                itemValues.get("title"),
                itemValues.get("category_id"),
                ((Object)itemValues.get("price")).toString(),
                formatter.parse(itemValues.get("start_time")),
                formatter.parse(itemValues.get("stop_time"))
            );
        } catch (JsonProcessingException | ParseException e) {
            logger.error("ERROR1:: Parsing error:: " + e.getMessage());
        }
        return itemDTO;
    }

    private List<ChildrenDTO> parseChildren(ResponseEntity<String> response) {
        List<Map<String, String>> childrenValues;
        List<ChildrenDTO>  children = null;
        try {
            childrenValues = mapper.readValue(response.getBody(), List.class);

            children = childrenValues.stream()
                .map(this::apply)
                .collect(Collectors.toList());
        } catch (JsonProcessingException e) {
            logger.error("ERROR2:: Parsing error:: " + e.getMessage());
        }
        return children;
    }

    private ChildrenDTO apply(Map<String, String> entry) {
        ChildrenDTO item = null;
        try {
            item =
                new ChildrenDTO(
                    entry.get("id"),
                    formatter.parse(entry.get("stop_time"))
            );
        } catch (ParseException e) {
            logger.error("ERROR3:: Parsing error:: " + e.getMessage());
        }
        return item;
    }
}
