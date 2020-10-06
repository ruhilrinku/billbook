package com.gst.billbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gst.billbook.dao.ItemCategory;

@Repository
public interface ItemCategoryRepository extends JpaRepository<ItemCategory, String> {
	
}
