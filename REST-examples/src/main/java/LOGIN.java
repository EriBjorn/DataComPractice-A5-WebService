import org.json.JSONArray;
import org.json.JSONObject;

public class LOGIN
{
    static int sessionID;
    static public JSONArray argument;

    static POST post = new POST("52.164.220.230", 80);
    static GET get = new GET("datakomm.work", 80);

    public static void main(String[] args)
    {

       loginCredentials("eribjorn@stud.ntnu.no", "94013294");

        getTask(1);
        taskOne();
        getTask(2);
        taskTwo();

    }

    static public void loginCredentials(String yourEmail, String yourNumber)
    {
        String email = yourEmail;
        String phone = yourNumber;

        JSONObject jsonLogin = new JSONObject();
        jsonLogin.put("email", email);
        jsonLogin.put("phone", phone);

        System.out.println("Sending credentials to server");
        System.out.println(jsonLogin.toString());

        String response = post.sendPost("dkrest/auth", jsonLogin);

        JSONObject responseFromServer = new JSONObject(response);
        sessionID = responseFromServer.getInt("sessionId");



    }

    static public void getTask(int taskNumber)
    {
        JSONObject response =  get.sendGet("dkrest/gettask/" + taskNumber + "?sessionId=" + sessionID);
        if (response != null)
        {
            argument = (JSONArray) response.get("arguments");
        }


    }


    static public void taskOne()
    {

        String message = "Hello";

        JSONObject task1 = new JSONObject();
        task1.put("sessionId", sessionID);
        task1.put("msg", message);

        System.out.println("Sending task1 to server" + task1.toString());

        post.sendPost("dkrest/solve", task1);

    }



    static public void taskTwo()
    {

        JSONObject task2 = new JSONObject();

        String echo = argument.getString(0);

        task2.put("sessionId", sessionID);
        task2.put("msg", echo);

        post.sendPost("dkrest/solve", task2);
        System.out.println("Sending: " + task2.toString() + " to server");

   }


}
