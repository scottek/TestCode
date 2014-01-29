package order_management.common

/**
 * Created with IntelliJ IDEA.
 * User: sduncan
 * Date: 1/13/14
 * Time: 1:42 PM
 * To change this template use File | Settings | File Templates.
 */
import net.sf.json.*

class OrderDetails extends EZMessage {
    String order_id;
    String order_status;
    JSONObject orderDetails;

    OrderDetails(String order_id){
        super("order.details")
        orderDetails = new JSONObject();
        orderDetails.put("master_order_view","current");
        setOrder_id(order_id);
    }

    void setOrder_id(String order_id) {
        this.order_id = order_id
        orderDetails.put("master_order_id",order_id);
        data.put("order_details", orderDetails)
    }

    JSONObject send(){
        JSONObject results = super.send()
        order_status = results.EZMessage.data.order_details.status;

        return results
    }


}
