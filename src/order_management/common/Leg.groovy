package order_management.common

/**
 * Created with IntelliJ IDEA.
 * User: sduncan
 * Date: 1/10/14
 * Time: 2:02 PM
 * To change this template use File | Settings | File Templates.
 */
import net.sf.json.*

class Leg {
    JSONObject leg;
    int index;
    String side;
    String position_type;
    String security_type;
    int multiplier;
    int quantity;
    String key;

    Leg(String side, int quantity, String key){
      this(0,JSONUtils.BUY, JSONUtils.POSITION_TYPE_OPENING_NAME, JSONUtils.SECURITY_TYPE_STOCK_NAME, 1, quantity, key)
    }

    Leg(int index, String side, String position_type, String security_type, int multiplier, int quantity, String key){
        leg = new JSONObject();
        setIndex(index);
        setSide(side);
        setPosition_type(position_type);
        setSecurity_type(security_type);
        setMultiplier(multiplier);
        setQuantity(quantity);
        setKey(key);
    }

    void setKey(String key) {
        this.key = key
        leg.put(JSONUtils.KEY,this.key);
    }

    void setIndex(int index) {
        this.index = index
        leg.put(JSONUtils.INDEX,this.index);
    }

    void setSide(String side) {
        this.side = side
        leg.put(JSONUtils.SIDE,this.side);
    }

    void setPosition_type(String position_type) {
        this.position_type = position_type
        leg.put(JSONUtils.POSITION_TYPE,this.position_type);
    }

    void setSecurity_type(String security_type) {
        this.security_type = security_type
        leg.put(JSONUtils.SECURITY_TYPE,this.security_type);
    }

    void setMultiplier(int multiplier) {
        this.multiplier = multiplier
        leg.put(JSONUtils.MULTIPLIER,this.multiplier);
    }

    void setQuantity(int quantity) {
        this.quantity = quantity
        leg.put(JSONUtils.QUANTITY,this.quantity);
    }

    JSONObject getJSON(){
        return leg;
    }
}
