package me.ashif.controller;

import me.ashif.model.PurchaseInvoiceModel;
import me.ashif.model.PurchaseModel;
import me.ashif.service.PurchaseInvoiceService;
import me.ashif.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Ashif Ismail on 9/1/17.
 */
@RequestMapping("/purchase")
@RestController
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object setPurchaseEntry(@Valid PurchaseModel p, BindingResult bindingResult) {
        return purchaseService.setPurchaseEntry(p,bindingResult);
    }
}
