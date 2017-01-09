package me.ashif.controller;

import me.ashif.model.SalesInvoiceModel;
import me.ashif.service.SalesInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Ashif Ismail on 9/1/17.
 */

@RequestMapping("/s_invoice")
@RestController
public class SalesInvoiceController {

    @Autowired
    private SalesInvoiceService salesInvoiceService;

    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object setPurchaseInvoice(@Valid SalesInvoiceModel s, BindingResult bindingResult){
        return salesInvoiceService.setSalesInvoice(s,bindingResult);
    }

    @ResponseBody
    @RequestMapping(value = "/invoice",method = RequestMethod.GET)
    public Object getSalesInvoice(@RequestParam String customerName){
        return salesInvoiceService.getSalesInvoice(customerName);
    }

}
