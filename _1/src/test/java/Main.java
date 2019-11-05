import org.json.JSONObject;

public class Main
{
    public static void main(String[] args)
    {
        Person p = new Person("Erik", 27, 1.71);
        Person p1 = new Person("Siggi", 39, 1.93);

        JSONObject obj = new JSONObject(p);
        JSONObject obj1 = new JSONObject(p1);

        System.out.println(obj);
        System.out.println(obj1);

    }
}
