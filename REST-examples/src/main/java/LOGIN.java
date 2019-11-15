import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;

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
        getTask(3);
        taskThree();
        getTask(4);
        taskFour();
        getTask(2016);
        getResult();
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
        String echo = argument.getString(0);

        JSONObject task2 = new JSONObject();
        task2.put("sessionId", sessionID);
        task2.put("msg", echo);

        post.sendPost("dkrest/solve", task2);
        System.out.println("Sending: " + task2.toString() + " to server");

   }

   static public void taskThree()
   {

       int multiplier = 1;
       int answer = 1;
       int i;

       for ( i = 0; i < argument.length(); i++)
       {

           multiplier =  Integer.parseInt(argument.getString(i));
           answer = (answer * multiplier);

       }

       JSONObject task3 = new JSONObject();
       task3.put("sessionId", sessionID);
       task3.put("result", answer);

       post.sendPost("dkrest/solve", task3);
       System.out.println("Sending " + task3.toString() + " to server");


   }

   static public void taskFour()
   {
       int i;
       
       for ( i = 0; i <= 9999; i++)
       {
           String str = String.format("%04d", i);

           String code = DigestUtils.md5Hex(str);


           if(code.equals(argument.getString(0))) {
               int pinCode = Integer.parseInt(str);


               JSONObject task4 = new JSONObject();
               task4.put("sessionId", sessionID);
               task4.put("pin", pinCode);

               post.sendPost("dkrest/solve",task4);
           }
       }


   }

   static public void getResult()
   {
       get.sendGet("dkrest/results/" + sessionID);
   }


}
