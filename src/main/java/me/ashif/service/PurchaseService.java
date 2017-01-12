package me.ashif.service;

import me.ashif.model.ItemsModel;
import me.ashif.model.PurchaseModel;
import me.ashif.model.SupplierModel;
import me.ashif.repository.PurchaseRepository;
import me.ashif.response.EntityCode;
import me.ashif.status.Error;
import me.ashif.status.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

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
    public Object getDetails(String supplierName,String itemName){
        List<PurchaseModel> resultList = purchaseRepository.findBysupplierName(supplierName);
        ArrayList<PurchaseModel> detailsList = new ArrayList<>();
        for (PurchaseModel p : resultList){
            if (p.getItemName().equalsIgnoreCase(itemName)){
                detailsList.add(p);
            }
        }
        return detailsList;
    }

    public Object getPurchase(String supplierName,String supplierCode) {
        List<PurchaseModel> result = purchaseRepository.findBysupplierName(supplierName);
        ArrayList<PurchaseModel> filteredList = new ArrayList<>();
        if (result.isEmpty()){
            error.setMessage("No result for that name");
            error.setCode(-3);
            return error;
        }
        else
        {
            for (PurchaseModel p : result){
                if (p.getSupplierCode().equalsIgnoreCase(supplierCode)){
                    filteredList.add(p);
                }
            }
        }
        return filteredList;
    }
    public Object updatePurchase(PurchaseModel p,Integer id,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            List<String> message = new ArrayList<>();
            error.setCode(-2);
            for (FieldError e : errors){
                message.add("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage());
            }
            error.setMessage("Update Failed");
            error.setCause(message.toString());
            return error;
        }
        else
        {
            PurchaseModel model = purchaseRepository.findOne(id);
            model = p;
            purchaseRepository.save(p);
            success.setMessage("Updated Successfully");
            success.setCode(2);
            return success;
        }
    }

    public Object deletePurchase(Integer id){
        PurchaseModel model = purchaseRepository.findOne(id);
        purchaseRepository.delete(model);
        success.setMessage("Deleted Successfully");
        success.setCode(2);
        return success;
    }
    public Object getSupplierCode(String supplierName){
        List<PurchaseModel> result = purchaseRepository.findBysupplierName(supplierName);
        ArrayList<String> supplierCodeList = new ArrayList<>();
        for (PurchaseModel p : result) {
            supplierCodeList.add(p.getSupplierCode());
        }
        EntityCode entityCode = new EntityCode();
        entityCode.setEntityCode(supplierCodeList);
        return entityCode;
    }

    public Object getAllSupplierCode(){
        List<PurchaseModel> result = (List<PurchaseModel>) purchaseRepository.findAll();
        ArrayList<String> supplierCodeList = new ArrayList<>();
        for (PurchaseModel p : result) {
            supplierCodeList.add(p.getSupplierCode());
        }
        EntityCode entityCode = new EntityCode();
        entityCode.setEntityCode(supplierCodeList);
        return entityCode;
    }
}
