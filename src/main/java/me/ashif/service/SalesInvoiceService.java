package me.ashif.service;

import me.ashif.model.PurchaseInvoiceModel;
import me.ashif.model.SalesInvoiceModel;
import me.ashif.repository.SalesInvoiceRepository;
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
public class SalesInvoiceService {


    Error error = new Error();
    Success success = new Success();

    @Autowired
    private SalesInvoiceRepository salesInvoiceRepository;

    public Object setSalesInvoice(SalesInvoiceModel s, BindingResult bindingResult){
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
            salesInvoiceRepository.save(s);
            success.setCode(1);
            success.setMessage("Data saved Successfully");
            return success;
        }
    }
    public Object getSalesInvoice(String customerName){
        List<SalesInvoiceModel> result = new ArrayList<>(salesInvoiceRepository.findBycustomerName(customerName));
        if (result.isEmpty()){
            error.setMessage("No result for that name");
            error.setCode(-3);
            return error;
        }
        return result;
    }

    public Object updateSalesInvoice(SalesInvoiceModel s, Long id, BindingResult bindingResult){
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
            SalesInvoiceModel model = salesInvoiceRepository.findOne(id);
            model = s;
            salesInvoiceRepository.save(s);
            success.setMessage("Updated Successfully");
            success.setCode(2);
            return success;
        }
    }

    public Object deleteSalesInvoice(Long id){
            SalesInvoiceModel model = salesInvoiceRepository.findOne(id);
            salesInvoiceRepository.delete(model);
            success.setMessage("Deleted Successfully");
            success.setCode(2);
            return success;
    }
}
