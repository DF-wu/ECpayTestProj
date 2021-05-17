package cf.dfder.ecpaytestproj.Controller;


import cf.dfder.ecpaytestproj.ECpayService;
import cf.dfder.ecpaytestproj.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin("*")
@RestController
public class Controller {
    ECpayService ecpayservice ;
    
    @Autowired
    public Controller(ECpayService ecpayservice){
        this.ecpayservice = ecpayservice;
    }
    
    @GetMapping("/test")
    public ResponseEntity<?> getCheckOut(){
        String form = ecpayservice.checkOut();
        System.out.println(form);
        return new ResponseEntity<>(form,HttpStatus.OK);
    }
    
    @GetMapping("/init")
    public ResponseEntity<?> Init(){
        Order order = new Order();
        return new ResponseEntity<>(ecpayservice.init(), HttpStatus.OK);
    }
    
    

}
