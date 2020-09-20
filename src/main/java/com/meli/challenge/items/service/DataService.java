package com.meli.challenge.items.service;

import com.meli.challenge.items.dto.ChildrenDTO;
import com.meli.challenge.items.dto.ItemDTO;
import com.meli.challenge.items.model.Children;
import com.meli.challenge.items.model.Item;
import com.meli.challenge.items.repository.ChildrenRepository;
import com.meli.challenge.items.repository.ItemsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataService {

    private static final Logger logger = LoggerFactory.getLogger(DataService.class);

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private ChildrenRepository childrenRepository;


    public Boolean hasItem(String id) {
        return !itemsRepository.findById(id).isEmpty();
    }

    @Transactional
    public ItemDTO getItem(String id) {
        logger.info("Retrieving Item from DB :: " + id);

        Item item = itemsRepository.findById(id).get();

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItemId(item.getItemId());
        itemDTO.setTitle(item.getTitle());
        itemDTO.setCategoryId(item.getCategoryId());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setStartTime(item.getStartTime());
        itemDTO.setStopTime(item.getStopTime());

        List<Children> childrenList =
                childrenRepository.findByItemId(item);

        itemDTO.setChildren(
                childrenList
                        .stream()
                        .map(val -> new ChildrenDTO(val.getChildrenId(), val.getStopTime()))
                        .collect(Collectors.toList()));

        return itemDTO;
    }


    public void setItem(ItemDTO itemDTO) {

        Item item = new Item();
        item.setItemId(itemDTO.getItemId());
        item.setTitle(itemDTO.getTitle());
        item.setCategoryId(itemDTO.getCategoryId());
        item.setPrice(itemDTO.getPrice());
        item.setStartTime(itemDTO.getStartTime());
        item.setStopTime(itemDTO.getStopTime());
        item.setLastModification(new Date());

        List<Children> childrenList =
            itemDTO.getChildren()
                .stream()
                .map(val -> new Children(val.getItemId(), val.getStopTime(), item))
                .collect(Collectors.toList());

        item.setChildren(childrenList);

        itemsRepository.save(item);
    }
}
