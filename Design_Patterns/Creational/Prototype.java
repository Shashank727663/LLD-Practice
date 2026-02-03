package Design_Patterns.Creational;
import java.util.*;

public class Prototype {

    public interface WelcomeEmail extends Cloneable {
        WelcomeEmail clone();
        void setContent(String content);
        void send(String to);
    }

    // static so it can be instantiated easily
    static class WelcomeEmailImpl implements WelcomeEmail {
        private String content;

        public WelcomeEmailImpl(String content) {
            this.content = content;
        }

        @Override
        public WelcomeEmailImpl clone() {
            try {
                return (WelcomeEmailImpl) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public void send(String to) {
            System.out.println("Sending to " + to + ": " + content);
        }
    }

    static class EmailTemplateRegistry {
        private static final Map<String, WelcomeEmail> templates = new HashMap<>();

        static {
            templates.put("welcome",
                    new WelcomeEmailImpl("Hi, welcome to our platform!"));
        }

        public static WelcomeEmail getTemplate(String type) {
            return templates.get(type).clone();
        }
    }

    public static void main(String[] args) {
        WelcomeEmail welcomeEmail1 = EmailTemplateRegistry.getTemplate("welcome");
        welcomeEmail1.setContent("Hi Alice, welcome to TUF Premium!");
        welcomeEmail1.send("alice@example.com");

        WelcomeEmail welcomeEmail2 = EmailTemplateRegistry.getTemplate("welcome");
        welcomeEmail2.setContent("Hi Bob, thanks for joining!");
        welcomeEmail2.send("bob@example.com");

    }
}
