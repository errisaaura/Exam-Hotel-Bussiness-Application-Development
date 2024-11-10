/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel;

/**
 *
 * @author erisa
 */
public class Session {
    private static int id;
    private static String name;
    private static String email;
    private static String alamat;
    private static String role;
    

    public static void setUser(String name, int id, String email, String role, String alamat) {
        Session.name = name;
        Session.id = id;
        Session.email = email;
        Session.role = role;
        Session.alamat = alamat;
    }

    public static int getId() {
        return id;
    }

    public static String getName() {
        return name;
    }

    public static String getEmail() {
        return email;
    }

    public static String getRole() {
        return role;
    }

    public static String getAlamat() {
        return alamat;
    }
    
    public static boolean isSessionValid() {
        return name != null && id != -1;
    }

    public static void clearSession() {
        name = null;
        id = -1;
        email = null;
        role = null;
        alamat = null;
    }
}
