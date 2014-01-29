package order_management.common

/**
 * Created with IntelliJ IDEA.
 * User: sduncan
 * Date: 1/10/14
 * Time: 9:43 AM
 * To change this template use File | Settings | File Templates.
 */
import net.sf.json.*

class SendOrder extends EZMessage {
    String order_type;
    String order_id;
    long m_order_id;
    String order_subtype;
    Float price;
    String price_type;
    String time_in_force;
    String symbol;
    String preferred_destination;
    String source;
    Boolean all_or_none
    long client_id;
    JSONObject order;
    JSONArray legs;



    SendOrder(String symbol, float price){
        this(JSONUtils.ORDER_TYPE_REGULAR,"",1,JSONUtils.ORDER_SUBTYPE_SINGLE,price,JSONUtils.PRICE_TYPE_LIMIT,JSONUtils.TIME_IN_FORCE_DAY,symbol,JSONUtils.PREFERRED_DESTINATION_BEST, "API-TEST",false,new Date().getTime())
    }

    SendOrder(String order_type, String order_id, long m_order_id, String order_subtype, float price, String price_type, String time_in_force, String symbol, String preferred_destination,
              String source, Boolean all_or_none, long client_id){
        super("order.create.json")
        order = new JSONObject();
        legs = new JSONArray();
        setOrder_type(order_type);
        setOrder_id(order_id)
        setOrder_subtype(order_subtype)
        setPrice(price);
        setPrice_type(price_type);
        setTime_in_force(time_in_force);
        setSymbol(symbol);
        setPreferred_destination(preferred_destination)
        setSource(source);
        setAll_or_none(all_or_none);
        setClient_id(client_id);
    }

    void setOrder_type(String order_type) {
        this.order_type = order_type
        order.put(JSONUtils.ORDER_TYPE,this.order_type);
        data.put(JSONUtils.ORDER, order);
    }

    void setOrder_id(String order_id) {
        this.order_id = order_id
        order.put(JSONUtils.ORDER_ID,this.order_id);
        data.put(JSONUtils.ORDER, order);
    }

    void setM_order_id(int m_order_id) {
        this.m_order_id = m_order_id
        order.put("m_order_id",this.m_order_id);
        data.put(JSONUtils.ORDER, order);
    }

    void setOrder_subtype(String order_subtype) {
        this.order_subtype = order_subtype
        order.put(JSONUtils.ORDER_SUBTYPE,this.order_subtype);
        data.put(JSONUtils.ORDER, order);
    }

    void setPrice(float price) {
        this.price = price
        order.put(JSONUtils.PRICE,this.price);
        data.put(JSONUtils.ORDER, order);
    }

    void setPrice_type(String price_type) {
        this.price_type = price_type
        order.put(JSONUtils.PRICE_TYPE,this.price_type);
        data.put(JSONUtils.ORDER, order);
    }

    void setTime_in_force(String time_in_force) {
        this.time_in_force = time_in_force
        order.put(JSONUtils.TIME_IN_FORCE,this.time_in_force);
        data.put(JSONUtils.ORDER, order);
    }

    void setSymbol(String symbol) {
        this.symbol = symbol
        order.put(JSONUtils.SYMBOL,this.symbol);
        data.put(JSONUtils.ORDER, order);
    }

    void setPreferred_destination(String preferred_destination) {
        this.preferred_destination = preferred_destination
        order.put(JSONUtils.PREFERRED_DESTINATION,this.preferred_destination);
        data.put(JSONUtils.ORDER, order);
    }

    void setSource(String source) {
        this.source = source
        order.put(JSONUtils.SOURCE,this.source);
        data.put(JSONUtils.ORDER, order);
    }

    void setAll_or_none(Boolean all_or_none) {
        this.all_or_none = all_or_none
        order.put(JSONUtils.ALL_OR_NONE,this.all_or_none);
        data.put(JSONUtils.ORDER, order);
    }

    void setClient_id(long client_id) {
        this.client_id = client_id
        order.put(JSONUtils.CLIENT_ID,this.client_id);
        data.put(JSONUtils.ORDER, order);
    }

    void addLeg(Leg leg){
        legs.add(leg.getJSON());
        order.put(JSONUtils.LEGS, this.legs);
        data.put(JSONUtils.ORDER, order);
    }

    JSONObject send(){
         JSONObject results = super.send();
         order_id = results.EZMessage.data.id;
         return results;
    }

    OrderDetails getOrderDetails(){
        if(order_id > ""){
            EZMessage order_details = new OrderDetails(order_id);
            order_details.send();
            return order_details;
        }
        else
            return null;
    }

    static void main(String[] args){
        Login login = new Login("optionstrader","571883","qa1234","http://ovlchi6dqa48:8081")
        login.send();

        SendOrder order = new SendOrder("OLED",35.0f);
        order.addLeg(new Leg("buy",1,"OLED:::S"))

        println order.toString();
        println order.send();
        println order.order_id;


        OrderDetails details =  order.getOrderDetails()
        while(details.order_status != "Filled"){
            println "ORDER STATUS: " + details.order_status;
            Thread.sleep(100)
            details.send();
        }
        println "ORDER STATUS: " + details.order_status;
    }
}
