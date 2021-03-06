package cf.dfder.ecpaytestproj.services;

import cf.dfder.ecpaytestproj.entity.Order;
import cf.dfder.ecpaytestproj.repository.OrderRepo;
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
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
        lastSerialNumber = getLastSerialNumber();
        
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
    
    
        
        
        obj.setMerchantTradeNo(UUID.randomUUID().toString().substring(0,20).replace("-",""));
//        obj.setMerchantTradeDate("2017/01/01 08:05:23");
        System.out.println("time : " + getNowTime());
        obj.setMerchantTradeDate(getNowTime());
        obj.setTotalAmount("25678");
        obj.setTradeDesc("????????? ???????????????");
        obj.setItemName("???????????????1000??? # !!???????????????1000???");
        obj.setReturnURL("http://211.23.128.214:5000");
        obj.setNeedExtraPaidInfo("N");
    
    
        String clientBackURL = "clientBackURL:Client??????????????????????????????";
        String ItemURL = "ItemURL:??????????????????";
        String remark = "remark:????????????";
        String OrderResultURL = "OrderResultURL:Client???????????????????????????";
        

        
        obj.setClientBackURL(addGoogleSearchPath(clientBackURL));
        obj.setItemURL(addGoogleSearchPath(ItemURL));
        obj.setRemark(addGoogleSearchPath(remark));
        obj.setOrderResultURL("https://www.google.com/search?q=OrderResultURL%3AClient%E7%AB%AF%E5%9B%9E%E5%82%B3%E4%BB%98%E6%AC%BE%E7%B5%90%E6%9E%9C%E7%B6%B2%E5%9D%80");
        
        String form = ecpaySDK.aioCheckOut(obj, null);
        
        orderrepo.save(order);
        
        System.out.println("from check out service" + form);
    
        System.out.println(obj.getMerchantTradeNo());
        System.out.println(order.getSerialNumber());
        return form;
    }
    
    
    public int getLastSerialNumber(){
        ArrayList<Order> orders = new ArrayList<>();
        orders = (ArrayList<Order>) orderrepo.findAll();
        
        
        // increase
        Comparator<Order> comparator = new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2)
            {
                return  o2.getSerialNumber() - o1.getSerialNumber();
            }
        };
        Collections.sort(orders,comparator);
    
    
        System.out.println(orders);
        
        int number = orders.get(0).getSerialNumber();
    
    
        System.out.println("now last number is " + number);
        return number;
    }
    
    public String addGoogleSearchPath(String keyword){
        String googleSearchPath ="https://www.google.com/search?q=";
        String newstr = googleSearchPath +  keyword.replace(":","+%3A+");
        return newstr;
    }
    
    public String getNowTime(){
        final LocalDateTime currentDateTime = LocalDateTime.now();
        final int year = currentDateTime.getYear();
        final int month = currentDateTime.getMonthValue();
        final Month m = currentDateTime.getMonth();
        final int day = currentDateTime.getDayOfMonth();
        final DayOfWeek w = currentDateTime.getDayOfWeek();
        final int hour = currentDateTime.getHour();
        final int minute = currentDateTime.getMinute();
        final int second = currentDateTime.getSecond();
    
        String time = String.format("%s/%02d/%02d %02d:%02d:%02d",year,month,day,hour,minute,second);
        return time;
    }
}