import org.json.JSONObject;

public class LOGIN
{
    static int sessionID;
    static POST post = new POST("52.164.220.230", 80);
    static GET get = new GET("datakomm.work", 80);

    public static void main(String[] args)
    {

        boolean login = loginCredentials("eribjorn@stud.ntnu.no", "94013294");
        if (login)
        {
            System.out.println("Logged in successfully");
        }
        else
        {
            System.out.println("login failed");
        }

        getTask(1);
        taskOne();
        getTask(2);
       // taskTwo();

    }

    static public boolean loginCredentials(String yourEmail, String yourNumber)
    {
        String email = yourEmail;
        String phone = yourNumber;

        JSONObject jsonLogin = new JSONObject();
        jsonLogin.put("email", email);
        jsonLogin.put("phone", phone);

        System.out.println("Sending credentials to server");
        System.out.println(jsonLogin.toString());

        String respons = post.sendPost("dkrest/auth", jsonLogin);

        JSONObject responsFromServer = new JSONObject(respons);
        sessionID = responsFromServer.getInt("sessionId");


        boolean success = responsFromServer.getBoolean("success");
        return success;

    }

    static public void getTask(int taskNumber)
    {
        get.sendGet("dkrest/gettask/" + taskNumber + "?sessionId=" + sessionID);
    }


    static public void taskOne()
    {

        int sessionId = sessionID;
        String message = "Hello";


        JSONObject task1 = new JSONObject();
        task1.put("sessionId", sessionID);
        task1.put("msg", message);

        System.out.println("Sending task1 to server" + task1.toString());


        post.sendPost("dkrest/solve", task1);


        //JSONObject responsFromServer = new JSONObject(respons);
       // boolean success = responsFromServer.getBoolean("success");
        // return success;



    }

//    static public void taskTwo()
//    {
//        String respons = post.sendPost("dkrest/auth", jsonLogin);
//
//        JSONObject responsFromServer = new JSONObject(respons);
//        responsFromServer.getString("0");
//        System.out.println(responsFromServer);
//    }


}
