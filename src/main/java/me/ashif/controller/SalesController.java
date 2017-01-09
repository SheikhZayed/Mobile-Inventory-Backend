package me.ashif.controller;

import me.ashif.model.SalesModel;
import me.ashif.service.SalesService;
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

}
