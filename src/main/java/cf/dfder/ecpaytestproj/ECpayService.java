package cf.dfder.ecpaytestproj;

import cf.dfder.ecpaytestproj.entity.Order;
import cf.dfder.ecpaytestproj.repository.OrderRepo;
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

@Service
public class ECpayService {
    private OrderRepo orderrepo;
    
    private AllInOne ecpaySDK;
    private int lastSerialNumber;
    
    @Autowired
    public ECpayService(OrderRepo orderrepo){
        this.orderrepo = orderrepo;
        lastSerialNumber = 0;
        ecpaySDK = new AllInOne("");
    }
    
    
    public String init(){
        Order order = new Order();
        order.setSerialNumber(10);
        
        AioCheckOutALL obj = new AioCheckOutALL();
        UUID uuid = UUID.randomUUID();
        obj.setMerchantTradeNo(uuid.toString().substring(0,18).replace("-", ""));
        obj.setMerchantTradeDate("2017/01/01 08:05:23");
        obj.setTotalAmount("50");
        obj.setTradeDesc("test Description");
        obj.setItemName("TestItem");
        obj.setReturnURL("http://211.23.128.214:5000");
        obj.setNeedExtraPaidInfo("N");
        System.out.println(uuid);
        order.setAioCheckOutALL(obj);
        orderrepo.save(order);
        
        String form = ecpaySDK.aioCheckOut(obj, null);
        
        System.out.println("init order");
        return form;
    }
    
    public String checkOut(){
        
        Order order = new Order();
        lastSerialNumber++;
        order.setSerialNumber(lastSerialNumber);
        
        AioCheckOutALL obj = new AioCheckOutALL();
        obj.setMerchantTradeNo(String.valueOf(lastSerialNumber));
        obj.setMerchantTradeDate("2017/01/01 08:05:23");
        obj.setTotalAmount("50");
        obj.setTradeDesc("test Description");
        obj.setItemName("TestItem");
        obj.setReturnURL("http://211.23.128.214:5000");
        obj.setNeedExtraPaidInfo("N");
        String form = ecpaySDK.aioCheckOut(obj, null);
    
        orderrepo.save(order);
        
        System.out.println("from check out service" + form);
        return form;
    }
    
    
    public String getLastSerialNumber(){
        ArrayList<Order> orders = new ArrayList<>();
        orders = (ArrayList<Order>) orderrepo.findAll();
    
    
        Comparator<Order> comparator = new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2)
            {
                return o1.getSerialNumber() - o2.getSerialNumber();
            }
        };
        Collections.sort(orders,comparator);
    
    
        System.out.println(orders);
        
        return orders.get(0).toString();
    }
    
    

}