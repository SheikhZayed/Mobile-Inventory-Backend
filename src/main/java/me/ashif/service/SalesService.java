package me.ashif.service;

import me.ashif.model.CustomerModel;
import me.ashif.model.ItemsModel;
import me.ashif.model.PurchaseModel;
import me.ashif.model.SalesModel;
import me.ashif.repository.SalesRepository;
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
public class SalesService {

    Error error = new Error();
    Success success = new Success();
    CustomerModel customer = new CustomerModel();
    ItemsModel items = new ItemsModel();

    @Autowired
    private SalesRepository salesRepository;

    public Object setSalesEntry(SalesModel s, BindingResult bindingResult){
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
            salesRepository.save(s);
            success.setCode(1);
            success.setMessage("Data saved Successfully");
            return success;
        }
    }

    public Object getAllCustomers(){
        ArrayList<SalesModel> resultList = (ArrayList<SalesModel>) salesRepository.findAll();
        ArrayList<String> customerList = new ArrayList<>();
        for (SalesModel s : resultList){
            customerList.add(s.getCustomerName());
        }
        customer.setCustomername(customerList);
        return customer;
    }

    public Object getItemsByCustomerName(String customerName){
        List<SalesModel> resultList = salesRepository.findBycustomerName(customerName);
        ArrayList<String> itemsList = new ArrayList<>();
        for (SalesModel s : resultList){
            itemsList.add(s.getItemName());
        }
        items.setItems(itemsList);
        return items;
    }
    public Object getDetails(String customerName,String itemName){
        List<SalesModel> resultList = salesRepository.findBycustomerName(customerName);
        ArrayList<SalesModel> detailsList = new ArrayList<>();
        for (SalesModel s : resultList){
            if (s.getItemName().equalsIgnoreCase(itemName)){
                detailsList.add(s);
            }
        }
        return detailsList;
    }

    public Object getAllSales(String customerName) {
        List<SalesModel> result = salesRepository.findBycustomerName(customerName);
        if (result.isEmpty()){
            error.setMessage("No result for that name");
            error.setCode(-3);
            return error;
        }
        return result;
    }
    public Object updateSales(SalesModel p,Integer id,BindingResult bindingResult){
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
            SalesModel model = salesRepository.findOne(id);
            model = p;
            salesRepository.save(p);
            success.setMessage("Updated Successfully");
            success.setCode(2);
            return success;
        }
    }

    public Object deleteSales(Integer id){
        SalesModel model = salesRepository.findOne(id);
        salesRepository.delete(model);
        success.setMessage("Deleted Successfully");
        success.setCode(2);
        return success;
    }

    public Object getAllCustomerCodes(){
        List<SalesModel> result = (List<SalesModel>) salesRepository.findAll();
        ArrayList<String> customerCodeList = new ArrayList<>();
        for (SalesModel s : result) {
            customerCodeList.add(s.getCustomerCode());
        }
        EntityCode entityCode = new EntityCode();
        entityCode.setEntityCode(customerCodeList);
        return entityCode;
    }

    public Object getCustomerCode(String customerName){
        List<SalesModel> result = salesRepository.findBycustomerName(customerName);
        ArrayList<String> customerCodeList = new ArrayList<>();
        for (SalesModel s : result) {
            customerCodeList.add(s.getCustomerCode());
        }
        EntityCode entityCode = new EntityCode();
        entityCode.setEntityCode(customerCodeList);
        return entityCode;
    }

    public Object getAllSalesReport(){
        return salesRepository.findAll();
    }
}
