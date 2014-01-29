package order_management.common

import net.sf.json.JSONObject

/**
 * Created with IntelliJ IDEA.
 * User: sduncan
 * Date: 1/10/14
 * Time: 7:45 AM
 * To change this template use File | Settings | File Templates.
 */
class Login extends EZMessage{
    String user;
    String password;
    String ezinterface;
    String expirationInterval;

    static String TAG_USERNAME = "userName";
    static String TAG_PASSWORD = "password";
    static String TAG_INTERFACE = "interface";
    static String TAG_EXPIRATION_INTERVAL = "expirationInterval";

    Login(String user, String account, String password = "qa1234", String webInterface = "http://localhost:8081", String ezinterface = "TIGERHAWK", String expirationInterval = "1200"){
        super("auth.login",false);
        setAccount(account);
        setUser(user);
        setWebInterface(webInterface);
        setEzinterface(ezinterface);
        setPassword(password);
        //setExpirationInterval(expirationInterval);
    }

    void setUser(String user){
        this.user = user;
        data.put(TAG_USERNAME,this.user);
    }

    void setPassword(String password){
        this.password = password;
        data.put(TAG_PASSWORD,this.password);
    }

    void setEzinterface(String ezinterface) {
        this.ezinterface = ezinterface
        data.put(TAG_INTERFACE,this.ezinterface);
    }

    void setExpirationInterval(String expirationInterval) {
        this.expirationInterval = expirationInterval
        data.put(TAG_EXPIRATION_INTERVAL,this.expirationInterval);
    }

    JSONObject send(){
        JSONObject response = super.send();
        setAuthToken(response.EZMessage.data.authToken);
        return response
    }

    static void main(String[] args){
        Login login = new Login("test_sduncan","111111","qa1234","http://ovlchi6dqa48:8081")
        login.send()
        println "AUTH TOKEN: "  + s_authToken;
    }

}
