package me.ashif.service;

import me.ashif.model.PurchaseInvoiceModel;
import me.ashif.model.SalesInvoiceModel;
import me.ashif.repository.PurchaseInvoiceRepository;
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
public class PurchaseInvoiceService {

    Error error = new Error();
    Success success = new Success();

    @Autowired
    private PurchaseInvoiceRepository purchaseInvoiceRepository;

    public Object setPurchaseInvoice(PurchaseInvoiceModel p, BindingResult bindingResult){
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
            purchaseInvoiceRepository.save(p);
            success.setCode(1);
            success.setMessage("Data saved Successfully");
            return success;
        }
    }

    public Object getPurchaseInvoice(String supplierName) {
        List<PurchaseInvoiceModel> result = new ArrayList<>(purchaseInvoiceRepository.findBysupplierName(supplierName));
        if (result.isEmpty()){
            error.setMessage("No result for that name");
            error.setCode(-3);
            return error;
        }
        return result;
    }
}
