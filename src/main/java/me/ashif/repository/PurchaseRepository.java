package me.ashif.repository;


import me.ashif.model.PurchaseModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by techjini on 9/1/17.
 */
@Repository
public interface PurchaseRepository extends CrudRepository<PurchaseModel,Integer> {
    List<PurchaseModel> findBysupplierName(String supplierName);
}
