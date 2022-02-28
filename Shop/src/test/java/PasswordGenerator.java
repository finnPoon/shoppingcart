import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args)  {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //enter the password for the user
        String rawPassword = "password";
        String encodedPassword = encoder.encode(rawPassword);
        //copy the encrypted password code from the console and store it in users table as password
        System.out.println(encodedPassword);
    }
}
