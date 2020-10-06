package com.gst.billbook.service;

import java.util.List;

import com.gst.billbook.dao.Item;
import com.gst.billbook.dao.ItemCategory;
import com.gst.billbook.dao.ItemGroup;

public interface ItemService {
	
	public List<Item> getAvailableItems();
	
	public Item getItemByCode(String itemCode);
	
	public List<ItemCategory> getItemCategories();
	
	public ItemCategory getCategoryByCode(String catCode);
	
	public List<ItemGroup> getItemGroups();
	
	public ItemGroup getGroupByCode(String groupCode);
	
	public String saveItemCategoryDetails(ItemCategory categoryDetails);
	
	public String addNewGrouptoItemCategory(String categoryCode, ItemGroup itemGroup);
	
	public String addNewItemtoItemGroup(String categoryCode, Item item);
	
}
