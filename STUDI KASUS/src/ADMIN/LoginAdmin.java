package ADMIN;

public class LoginAdmin {

    private String username;
    private String password;

    public LoginAdmin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean login() {
        if (username == null || username.isEmpty()) {
            System.out.println("Anda belum memasukkan username.");
            return false;
        }

        if (password == null || password.isEmpty()) {
            System.out.println("Anda belum memasukkan password.");
            return false;
        }

        if (!username.matches("[a-zA-Z0-9]+")) {
            System.out.println("Username hanya boleh terdiri dari huruf dan angka.");
            return false;
        }

        return username.equals("NOEL") && password.equals("07502");
    }
}
