package order_management.common

import net.sf.json.*

/**
 * Created with IntelliJ IDEA.
 * User: sduncan
 * Date: 1/10/14
 * Time: 7:36 AM
 * To change this template use File | Settings | File Templates.
 */
class EZMessage{
    String action;
    JSONObject root;
    JSONObject ezMessage
    JSONObject data;
    boolean authorize;
    static String s_authToken;
    static String s_account;
    static String s_webInterface;
    static boolean s_loggedIn = false;

    EZMessage(String action, authorize = true){
        this.action = action;
        this.authorize = authorize;
        root = new JSONObject()
        ezMessage = new JSONObject();
        data = new JSONObject();
        ezMessage.put("action",action)
        root.put("EZMessage",ezMessage)
    }

    void setData(JSONObject data){
        this.data = data;
        ezMessage.put("data",this.data);
    }

    JSONObject getJSON(){
        if(authorize && s_authToken){
            data.put(JSONUtils.AUTHTOKEN, s_authToken);
            if(action=="order.details")
                data.put("account_id", s_account)
            else
                data.put(JSONUtils.ACCOUNT,s_account);
        }
        ezMessage.put("data",this.data);
        root.put("EZMessage",ezMessage);

        return root;
    }

    JSONObject send(){
        JSONObject results = null;
        println "SENDING: " + this.toString()
        if(action == "auth.login"){
            MessagePoster messagePoster = new MessagePoster(s_webInterface + "/m")
            results = messagePoster.Send(this);
        }

        else if(s_loggedIn){
            MessagePoster messagePoster = new MessagePoster(s_webInterface + "/j")
            results = messagePoster.Send(this);
        }
        println "RECV: " + results;
        return results
    }

    String toString(){
        return this.JSON.toString();
    }


    static void authorize(String token, String acct){
        s_authToken = token;
        s_account = acct;
    }

    static void setWebInterface(String webInterface) {
        EZMessage.s_webInterface = webInterface
    }

    static void setAccount(String account) {
        EZMessage.s_account = account
    }

    static void setAuthToken(String authToken) {
        EZMessage.s_authToken = authToken
        s_loggedIn = true;
    }

    static void main(String[] args){
        EZMessage test = new EZMessage("test.action")
        JSONObject testData = new JSONObject()
        testData.put("test1","12345");
        testData.put("test2","subject matter");
        test.setData(testData);

        println test.getJSON().toString();
    }
}
