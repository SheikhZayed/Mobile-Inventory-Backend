package me.ashif.service;

import me.ashif.model.ItemsModel;
import me.ashif.model.PurchaseInvoiceModel;
import me.ashif.model.PurchaseModel;
import me.ashif.model.SupplierModel;
import me.ashif.repository.PurchaseRepository;
import me.ashif.status.Error;
import me.ashif.status.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by techjini on 9/1/17.
 */
@SuppressWarnings("all")
@Service
public class PurchaseService {

    Error error = new Error();
    Success success = new Success();
    SupplierModel supplier = new SupplierModel();
    ItemsModel items = new ItemsModel();

    @Autowired
    private PurchaseRepository purchaseRepository;

    public Object setPurchaseEntry(PurchaseModel p, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            List<String> message = new ArrayList<>();
            error.setCode(-1);
            for (FieldError e : errors){
                message.add("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage());
            }
            error.setMessage("Validation Failed");
            error.setCause(message.toString());
            return error;
        }
        else{
            purchaseRepository.save(p);
            success.setCode(1);
            success.setMessage("Data saved Successfully");
            return success;
        }
    }
    public Object getAllSuppliers(){
        ArrayList<PurchaseModel> resultList = (ArrayList<PurchaseModel>) purchaseRepository.findAll();
        ArrayList<String> supplierList = new ArrayList<>();
        for (PurchaseModel p : resultList){
            supplierList.add(p.getSupplierName());
        }
        supplier.setSuppliername(supplierList);
        return supplier;
    }

    public Object getItemsBySupplierName(String supplierName){
        List<PurchaseModel> resultList = purchaseRepository.findBysupplierName(supplierName);
        ArrayList<String> itemsList = new ArrayList<>();
        for (PurchaseModel p : resultList){
            itemsList.add(p.getItemName());
        }
        items.setItems(itemsList);
        return items;
    }

}
