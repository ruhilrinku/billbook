package com.gst.billbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gst.billbook.dao.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
	
	@Query(nativeQuery = true, value=" select item_code, item_name, item_type, item_desc, item_hsn, brand, available_quantity from item where item_code = ?1 ")
	public Object[] getItemByCode(String itemCode);
	
	@Query(nativeQuery = true, value=" select ig.item_group, i.item_code, i.item_name, i.item_type, i.item_desc, i.item_hsn, i.brand, i.available_quantity from item_group ig "
			+ " join item_group_item_map igim on ig.group_code = igim.group_code "
			+ " join item i on i.item_code = igin.item_code "
			+ " where ig.group_code = ?1 ")
	public List<Object[]> getItemsByGroup(String groupCode);
	
	@Query(nativeQuery = true, value = " select ic.category_code, ig.item_group, i.item_code, i.item_name, i.item_type, i.item_desc, i.item_hsn, i.brand, i.available_quantity from item_category ic " + 
			" join item_category_item_group_map icigm on ic.category_code = icigm.category_code " + 
			" join item_group ig on ig.group_code = icigm.group_code " + 
			" join item_group_item_map igim on ig.group_code = igim.group_code " + 
			" join item i on i.item_code = igin.item_code " +
			" where ic.category_code = ?1 and ig.group_code = ?2 ")
	public List<Object[]> getItemsByCategoryAndGroup(String categoryCode, String groupCode);
	
	@Query(nativeQuery = true, value=" select * from item_group where group_code = ?1 ")
	public Object[] getGroupByCode(String groupCode);
	
	@Query(nativeQuery = true, value=" select * from item_category where category_code = ?1 ")
	public Object[] getCategoryByCode(String categoryCode);
	
}
