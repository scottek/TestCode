package order_management.common

/**
 * Created with IntelliJ IDEA.
 * User: sduncan
 * Date: 1/10/14
 * Time: 4:13 PM
 * To change this template use File | Settings | File Templates.
 */
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import net.sf.json.groovy.JsonGroovyBuilder
import net.sf.json.groovy.JsonSlurper
import net.sf.json.*



class MessagePoster {
    String url;

    HttpClient client;
    PostMethod post;

    MessagePoster(String url){
        this.url = url;
        client = new HttpClient();
        client.getParams().setParameter("http.protocol.single-cookie-header", true);
        client.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
        post = new PostMethod();
        post.setPath(this.url);
    }

    JSONObject Send(EZMessage message){
        JSONObject result = null;
        post.setRequestBody(message.getJSON().toString())

        try{
            int resp = client.executeMethod(post);
            if(resp == HttpStatus.SC_OK){
                result = new JsonSlurper().parseText(post.getResponseBodyAsString())

            }
            else {
                println "error response to message"
            }

        }catch (def ex) {
            println "exception thrown while trying send message"
            println ex.toString();
        }

        return result;
    }

}
