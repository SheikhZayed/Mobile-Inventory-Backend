package me.ashif.service;

import me.ashif.model.PurchaseModel;
import me.ashif.model.SalesModel;
import me.ashif.repository.PurchaseRepository;
import me.ashif.repository.SalesRepository;
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
}
