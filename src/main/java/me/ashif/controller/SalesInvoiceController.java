package me.ashif.controller;

import me.ashif.model.PurchaseInvoiceModel;
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

    @ResponseBody
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public Object updateSalesInvoice(@PathVariable Long id, @Valid SalesInvoiceModel s, BindingResult bindingResult){
        return salesInvoiceService.updateSalesInvoice(s,id,bindingResult);
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Object deleteSalesInvoice(@PathVariable Long id){
        return salesInvoiceService.deleteSalesInvoice(id);
    }
}
