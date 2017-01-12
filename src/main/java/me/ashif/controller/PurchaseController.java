package me.ashif.controller;

import me.ashif.model.PurchaseModel;
import me.ashif.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/suppliers")
    public Object getAllSuppliers(){
        return purchaseService.getAllSuppliers();
    }

    @RequestMapping("/items")
    public Object getItemsforSupplier(@RequestParam String supplierName){
        return purchaseService.getItemsBySupplierName(supplierName);
    }

    @RequestMapping("details")
    public Object getDetails(@RequestParam String supplierName,@RequestParam String itemName){
        return purchaseService.getDetails(supplierName, itemName);
    }

    /**
    return's all purchase's from a given supplier
     */
    @RequestMapping(value = "/invoice",method = RequestMethod.GET)
    public Object getAllPurchases(@RequestParam String supplierName,@RequestParam String supplierCode){
        return purchaseService.getPurchase(supplierName,supplierCode);
    }

    @ResponseBody
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public Object updatePurchase(@PathVariable Integer id, @Valid PurchaseModel p, BindingResult bindingResult){
        return purchaseService.updatePurchase(p,id,bindingResult);
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Object deletePurchase(@PathVariable Integer id){
        return purchaseService.deletePurchase(id);
    }

    @ResponseBody
    @RequestMapping("/suppliercode")
    public Object getSupplierCodes(@RequestParam String supplierName){
        return purchaseService.getSupplierCode(supplierName);
    }

    @ResponseBody
    @RequestMapping("/suppliercodes")
    public Object getAllSupplierCodes(){
        return purchaseService.getAllSupplierCode();
    }


}
