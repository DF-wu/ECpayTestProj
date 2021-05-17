package cf.dfder.ecpaytestproj.entity;

import ecpay.payment.integration.domain.AioCheckOutALL;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("Order")
public class Order {
    @Id
    private String id;

    // temp.
    @Field
    private int serialNumber;
    
    @Field
    private AioCheckOutALL aioCheckOutALL;
    
    public Order()
    {
    }
    
    
    
    public AioCheckOutALL getAioCheckOutALL()
    {
        return aioCheckOutALL;
    }
    
    public void setAioCheckOutALL(AioCheckOutALL aioCheckOutALL)
    {
        this.aioCheckOutALL = aioCheckOutALL;
    }
    
    public int getSerialNumber()
    {
        return serialNumber;
    }
    
    public void setSerialNumber(int serialNumber)
    {
        this.serialNumber = serialNumber;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    
    public String getId(){
        return id;
    }
}
