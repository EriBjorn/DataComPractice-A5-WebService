import org.json.JSONObject;

public class LOGIN
{
   static POST post = new POST("52.164.220.230", 80);

    public static void main(String[] args)
    {
        loginCredentials("eribjorn@stud.ntnu.no", "94013294");

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
        post.sendPost("dkrest/auth", jsonLogin);

    }

    static public void taskOne()
    {



    }


}
