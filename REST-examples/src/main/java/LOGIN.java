import org.json.JSONObject;

public class LOGIN
{
   static POST post = new POST("52.164.220.230", 80);

    public static void main(String[] args)
    {

//        boolean login = loginCredentials("eribjorn@stud.ntnu.no", "94013294");
//        if (login)
//        {
//            System.out.println("Logged in successfully");
//        }
//        else
//        {
//            System.out.println("login failed");
//        }

        boolean taskSent = taskOne("223655733", "Hello");
        if (taskSent)
        {
            System.out.println("Task one successfully sent");
        }
        else
        {
            System.out.println("Failed to send task one");
        }

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

        boolean success = responsFromServer.getBoolean("success");
        return success;

    }


    static public boolean taskOne(String sessionID, String msg)
    {

        String sessionId = sessionID;
        String message = msg;


        JSONObject task1 = new JSONObject();
        task1.put("sessionID", sessionID);
        task1.put("msg", msg);

        System.out.println("Sending task1 to server" + task1.toString());


        String respons = post.sendPost("dkrest/gettask/1?sessionId=223655733", task1);
        JSONObject responsFromServer = new JSONObject(respons);



        responsFromServer.getString("sessionId");


        boolean success = responsFromServer.getBoolean("success");
        return success;



    }


}
