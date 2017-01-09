package me.ashif.controller;

import me.ashif.model.PurchaseInvoiceModel;
import me.ashif.service.PurchaseInvoiceService;
import me.ashif.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Ashif Ismail on 9/1/17.
 */
@RequestMapping("/p_invoice")
@RestController
public class PurchaseInvoiceController {

    @Autowired
    private PurchaseInvoiceService purchaseInvoiceService;

    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object setPurchaseInvoice(@Valid PurchaseInvoiceModel p, BindingResult bindingResult){
        return purchaseInvoiceService.setPurchaseInvoice(p,bindingResult);
    }

    @RequestMapping(value = "/invoice",method = RequestMethod.GET)
    public List<PurchaseInvoiceModel> getPurchaseInvoice(@RequestParam String supplierName){
        return (List<PurchaseInvoiceModel>) purchaseInvoiceService.getPurchaseInvoice(supplierName);
    }
}
