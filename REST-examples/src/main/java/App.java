public class App
{

    static GET client1 = new GET("52.164.220.230", 80);
    private static JSONParse  jparse = new JSONParse();
    private static POSTExample post = new POSTExample();


    public static void main(String[] args)
    {
        client1.doGet();
        //jparse.object();
        //jparse.array();
        //jparse.nestedJson();

    }


}
