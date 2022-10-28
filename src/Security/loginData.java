package Security;

public class loginData {
    private static String namedata;
    private static String passworddata;

    public static String getName() {
        return namedata;
    }

    public static String getPassword() {
        return passworddata;
    }

    public static void setName(String name) {
        namedata = name;
    }

    public static void setPassword(String password) {
        passworddata = password;
    }
}
