package me.ashif.repository;

import me.ashif.model.PurchaseInvoiceModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by techjini on 9/1/17.
 */


@Repository
public interface PurchaseInvoiceRepository extends CrudRepository<PurchaseInvoiceModel,Long> {
    List<PurchaseInvoiceModel> findBysupplierName(String supplierName);
}
