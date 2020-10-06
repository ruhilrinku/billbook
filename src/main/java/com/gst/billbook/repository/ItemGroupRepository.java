package com.gst.billbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gst.billbook.dao.ItemGroup;

@Repository
public interface ItemGroupRepository extends JpaRepository<ItemGroup, String> {

}
