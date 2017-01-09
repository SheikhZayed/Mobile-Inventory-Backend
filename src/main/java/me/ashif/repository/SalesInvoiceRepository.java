package me.ashif.repository;

import me.ashif.model.SalesInvoiceModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by techjini on 9/1/17.
 */
@Repository
public interface SalesInvoiceRepository extends CrudRepository<SalesInvoiceModel,Long> {
    List<SalesInvoiceModel> findBycustomerName(String customerName);
}
