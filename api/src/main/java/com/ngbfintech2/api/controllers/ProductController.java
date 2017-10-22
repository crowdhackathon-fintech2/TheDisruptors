package com.ngbfintech2.api.controllers;

import com.ngbfintech2.api.dao.PaymentRequest;
import com.ngbfintech2.api.dao.PaymentResponse;
import com.ngbfintech2.api.dto.ResponseProductDetails;
import okhttp3.RequestBody;
import org.apache.el.parser.BooleanNode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "/product")
public interface ProductController {
    @RequestMapping(value = "/getDetails", method = RequestMethod.POST , produces = "application/json")
    public ResponseProductDetails getProductDetails(@RequestParam("id") String id);


    @RequestMapping(value = "/makeNBGPayment", method = RequestMethod.POST , produces = "application/json")
    public Boolean payProductNBG(@RequestParam("id") String id);

    @RequestMapping(value = "/payMC", method = RequestMethod.POST , produces = "application/json")
    public Boolean payMC(@RequestParam("id") String id);

    @RequestMapping(value = "/payBC", method = RequestMethod.POST , produces = "application/json")
    public Boolean payBC(@RequestParam("id") String id);

}
