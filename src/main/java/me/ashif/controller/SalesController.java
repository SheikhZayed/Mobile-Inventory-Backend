package me.ashif.controller;

import me.ashif.model.PurchaseModel;
import me.ashif.model.SalesModel;
import me.ashif.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Ashif Ismail on 9/1/17.
 */

@RequestMapping("/sales")
@RestController
public class SalesController {

    @Autowired
    private SalesService salesService;

    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object setSalesEntry(@Valid SalesModel s, BindingResult bindingResult){
        return salesService.setSalesEntry(s,bindingResult);
    }

    @RequestMapping("/customers")
    public Object getAllCustomers(){
        return salesService.getAllCustomers();
    }

    @RequestMapping("/items")
    public Object getItemsforSupplier(@RequestParam String customerName){
        return salesService.getItemsByCustomerName(customerName);
    }

    @RequestMapping("details")
    public Object getDetails(@RequestParam String customerName,@RequestParam String itemName){
        return salesService.getDetails(customerName, itemName);
    }

    /**
     return's all sales's for a given customer
     */
    @RequestMapping(value = "/invoice",method = RequestMethod.GET)
    public Object getAllSales(@RequestParam String customerName){
        return salesService.getAllSales(customerName);
    }

    @ResponseBody
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public Object updateSales(@PathVariable Integer id, @Valid SalesModel s, BindingResult bindingResult){
        return salesService.updateSales(s,id,bindingResult);
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Object deleteSales(@PathVariable Integer id){
        return salesService.deleteSales(id);
    }

    @ResponseBody
    @RequestMapping("/customercodes")
    public Object getAllSupplierCodes(){
        return salesService.getAllCustomerCodes();
    }

    @ResponseBody
    @RequestMapping("/customercode")
    public Object getSupplierCodes(@RequestParam String customerName){
        return salesService.getCustomerCode(customerName);
    }

    @ResponseBody
    @RequestMapping("")
    public Object getAllSalesReport(){
        return salesService.getAllSalesReport();
    }
}
