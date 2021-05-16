package cf.dfder.ecpaytestproj;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ECpayService {
    private AllInOne ecpaySDK;
    
    
    public ECpayService(){
        ecpaySDK = new AllInOne("");
    }
    
    public String checkOut(){
        AioCheckOutALL obj = new AioCheckOutALL();
        obj.setMerchantTradeNo("testCompany0004");
        obj.setMerchantTradeDate("2017/01/01 08:05:23");
        obj.setTotalAmount("50");
        obj.setTradeDesc("test Description");
        obj.setItemName("TestItem");
        obj.setReturnURL("http://211.23.128.214:5000");
        obj.setNeedExtraPaidInfo("N");
        String form = ecpaySDK.aioCheckOut(obj, null);
    
        System.out.println("from check out service" + form);
        return form;
    }
    
    

}