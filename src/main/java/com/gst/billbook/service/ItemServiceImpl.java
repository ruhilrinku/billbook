package com.gst.billbook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gst.billbook.dao.Item;
import com.gst.billbook.dao.ItemCategory;
import com.gst.billbook.dao.ItemGroup;
import com.gst.billbook.repository.ItemCategoryRepository;
import com.gst.billbook.repository.ItemGroupRepository;
import com.gst.billbook.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepo;
	
	@Autowired
	private ItemGroupRepository groupRepo;
	
	@Autowired
	private ItemCategoryRepository categoryRepo;

	@Override
	public List<Item> getAvailableItems() {
		List<Item> items = new ArrayList<Item>();
		try {
			items = (List<Item>) itemRepo.findAll();
		} catch(Exception ex) {
			throw ex;
		}
		return items;
	}

	@Override
	public Item getItemByCode(String itemCode) {
		Item item = null;
		try {
			item = parseItem(itemRepo.getItemByCode(itemCode));
		} catch(Exception ex) {
			throw ex;
		}
		return item;
	}
	
	private Item parseItem(Object[] obj) {
		Item item = new Item();
		try {
			item.setItemCode(obj[0]!=null ? (String)obj[0]: null);
			item.setItemName(obj[1]!=null ? (String)obj[1]: null);
			item.setItemType(obj[2]!=null ? (String)obj[2]: null);
			item.setItemDesc(obj[3]!=null ? (String)obj[3]: null);
			item.setHsn(obj[4]!=null ? (String)obj[4]: null);
			item.setBrandName(obj[5]!=null ? (String)obj[5]: null);
			item.setAvailableQty(obj[4]!=null ? (Integer)obj[6]: null);
		} catch(Exception ex) {
			throw ex;
		}
		return item;
	}

	@Override
	public List<ItemCategory> getItemCategories() {
		List<ItemCategory> categoryList = new ArrayList<ItemCategory>();
		try {
			categoryList = categoryRepo.findAll();
		} catch(Exception e) {
			throw e;
		}
		return categoryList;
	}

	@Override
	public ItemCategory getCategoryByCode(String catCode) {
		ItemCategory itemCategory = null;
		try {
			itemCategory = categoryRepo.getOne(catCode);
		} catch (Exception e) {
			throw e;
		}
		return itemCategory;
	}

	@Override
	public List<ItemGroup> getItemGroups() {
		List<ItemGroup> groupList = new ArrayList<ItemGroup>();
		try {
			groupList = groupRepo.findAll();
		} catch(Exception e) {
			throw e;
		}
		return groupList;
	}

	@Override
	public ItemGroup getGroupByCode(String groupCode) {
		ItemGroup group = null;
		try {
			group = groupRepo.getOne(groupCode);
		} catch(Exception e) {
			throw e;
		}
		return group;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public String saveItemCategoryDetails(ItemCategory categoryDetails) {
		ItemCategory category = null;
		try {
			category = categoryRepo.save(categoryDetails);
		} catch(Exception e) {
			throw e;
		}
		return category.getCategoryCode();
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public String addNewGrouptoItemCategory(String categoryCode, ItemGroup itemGroup) {
		ItemCategory category = null;
		try {
			category = categoryRepo.getOne(categoryCode);
			category.getItemGroups().add(itemGroup);

			categoryRepo.save(category);
		} catch(Exception e) {
			throw e;
		}
		return category.getCategoryCode();
	}
	
	public String addNewItemtoItemGroup(String groupCode, Item item) {
		ItemGroup group = null;
		try {
			group = groupRepo.getOne(groupCode);
			group.getItems().add(item);
			
			groupRepo.save(group);
		} catch(Exception e) {
			throw e;
		}
		return group.getGroupCode();
	}

}
