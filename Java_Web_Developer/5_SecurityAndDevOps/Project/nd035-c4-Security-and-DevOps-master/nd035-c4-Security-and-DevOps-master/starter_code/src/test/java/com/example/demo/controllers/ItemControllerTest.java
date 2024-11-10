package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.repositories.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemControllerTest {

    private ItemController itemController;
    private final ItemRepository itemsRepo = mock(ItemRepository.class);

    @Before
    public void setUp(){
        itemController = new ItemController(itemsRepo);
        //Prepare to inject some dependencies away from the spring
        TestUtils.injectObjects(itemController, "itemRepository", itemsRepo);
    }

    @Test
    public void testGetItemsById(){
        // Set the first item to be first entry of data.sql
        Item item1=new Item(1L, "Square Widget", new BigDecimal("1.99"),"A widget that is square");
        //then mock the item repo, when requestint o find the first ID, return that item
        when(itemsRepo.findById(1L)).thenReturn(java.util.Optional.of(item1));
        //create a request to get the item of ID 1
        ResponseEntity<Item> response = itemController.getItemById(1L);
        //the resposne should not be Null
        assertNotNull(response);
        // and should be success (200)
        assertEquals(200, response.getStatusCodeValue());
        //and the body of the response...
        Item obtainedItem= response.getBody();
        // Not Null
        assertNotNull(obtainedItem);
        // and equals to the first item
        assertEquals(item1, obtainedItem);
    }

    @Test
    public void testGetItemByName(){
        // Set the first item to be first entry of data.sql
        Item item1=new Item(1L, "Square Widget", new BigDecimal("1.99"),"A widget that is square");
        //then mock the item repo, when requestint o find the first ID, return that item
        List<Item> itemList= new ArrayList<>(); itemList.add(item1);
        //then mock the item repo, when requestint o find the name of the item, return that item
        when(itemsRepo.findByName("Square Widget")).thenReturn(itemList);
        //create a request to get that item by name
        ResponseEntity<List<Item>> response = itemController.getItemsByName("Square Widget");
        //and the response is not null
        Assertions.assertNotNull(response);
        //and = 200
        Assertions.assertEquals(200, response.getStatusCodeValue());
        //get the body
        List<Item> obtainedItems= response.getBody();
        //Shouldn't be null
        assertNotNull(obtainedItems);
        // anbd equals my Local item
        assertEquals(item1, obtainedItems.get(0));
    }
    @Test
    public void getItems (){
        //Set some items ...
        Item item1 = new Item(0L, "Whey", new BigDecimal("6.0"), "Isolated");
        Item item2 = new Item(1L, "Creatine", new BigDecimal("5.1"),"Bulk Up");
        Item item3 = new Item(2L, "Preworkout", new BigDecimal("10.5"),"Pump Up");
        List<Item> itemList = new ArrayList<>();
        itemList.add(0, item1);
        itemList.add(1, item2);
        itemList.add(2, item3);
        when(itemsRepo.findAll()).thenReturn(itemList);
        // Request to get them All
        ResponseEntity<List<Item>> responseEntity = itemController.getItems();
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
        List<Item> resultItems = responseEntity.getBody();
        assertNotNull(resultItems);
        //Compare each one of them
        assertEquals(item1, resultItems.get(0));
        assertEquals(item2, resultItems.get(1));
        assertEquals(item3, resultItems.get(2));
    }
}
