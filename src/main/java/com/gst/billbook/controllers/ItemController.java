package com.gst.billbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gst.billbook.dao.Item;
import com.gst.billbook.dao.ItemCategory;
import com.gst.billbook.dao.ItemGroup;
import com.gst.billbook.service.ItemService;

@RestController
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping(path = "/items", produces = "application/json")
	public ResponseEntity<List<Item>> getAvailableItems() {
		List<Item> items = null;
		try {
			items = itemService.getAvailableItems();
		} catch(Exception ex) {
			return new ResponseEntity<List<Item>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Item>> (items, HttpStatus.OK);
	}
	
	@GetMapping(path = "/itemcategorys", produces = "application/json")
	public ResponseEntity<List<ItemCategory>> getAvailableItemCategories() {
		List<ItemCategory> itemCategoList = null;
		try {
			itemCategoList = itemService.getItemCategories();
		}
		catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<> (itemCategoList, HttpStatus.OK);
	}
	
	@GetMapping(path = "/itemcategory/{categoryCode}", produces = "application/json")
	public ResponseEntity<ItemCategory> getItemCategory(@PathVariable("categoryCode") String categoryCode) {
		ItemCategory itemCatego = null;
		try {
			itemCatego = itemService.getCategoryByCode(categoryCode);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<> (itemCatego, HttpStatus.OK);
	}
	
	@GetMapping(path = "/itemgroups", produces = "application/json")
	public ResponseEntity<List<ItemGroup>> getAvailableItemGroups() {
		List<ItemGroup> itemGroupList = null;
		try {
			itemGroupList = itemService.getItemGroups();
		}
		catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<> (itemGroupList, HttpStatus.OK);
		
	}
	
	@GetMapping(path = "/itemgroup/{groupcode}", produces = "application/json")
	public ResponseEntity<ItemGroup> getItemGroup(@PathVariable("groupcode") String groupCode) {
		ItemGroup itemGroup = null;
		try {
			itemGroup = itemService.getGroupByCode(groupCode);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<> (itemGroup, HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/itemcategory/save", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> saveCategoryDetails(@RequestBody String json) {
		ItemCategory category = null;
		String categoryCode = null;
		try {
			category = ItemCategory.toObject(json);
			categoryCode = itemService.saveItemCategoryDetails(category);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<> (categoryCode, HttpStatus.OK);
	}

}
