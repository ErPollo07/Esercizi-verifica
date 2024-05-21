import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class User {
    private String username;
    private String password;
    private String passwordHiddenContact;
    private Contact[] contact;
    private Contact[] calls;

    public User(String username, String password, String passwordHiddenContact) {
        this.username = username;
        this.password = password;
        this.passwordHiddenContact = passwordHiddenContact;
        this.contact = new Contact[1];
        this.calls = new Contact[1];
    }

    public User(String username, String password, String passwordHiddenContact, Contact[] contact, Contact[] calls) {
        this.username = username;
        this.password = password;
        this.passwordHiddenContact = passwordHiddenContact;
        this.contact = contact;
        this.calls = calls;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordHiddenContact() {
        return passwordHiddenContact;
    }

    public void setPasswordHiddenContact(String passwordHiddenContact) {
        this.passwordHiddenContact = passwordHiddenContact;
    }

    public Contact[] getContact() {
        return contact;
    }

    public void setContact(Contact[] contact) {
        this.contact = contact;
    }

    public Contact[] getCalls() {
        return calls;
    }

    public void setCalls(Contact[] calls) {
        this.calls = calls;
    }

    public JSONObject toJSONObj() {
        JSONObject userJSONObj = new JSONObject();

        userJSONObj.put("username", this.username);
        userJSONObj.put("password", this.password);
        userJSONObj.put("pwHiddenContact", this.passwordHiddenContact);
        userJSONObj.put("contact", listToJSONArray(this.contact));
        userJSONObj.put("calls", listToJSONArray(this.calls));

        return userJSONObj;
    }

    private static JSONArray listToJSONArray(Contact[] arr) {
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) continue;

            JSONObject tempContact = new JSONObject();

            tempContact.put("name", arr[i].getName());
            tempContact.put("surname", arr[i].getSurname());
            tempContact.put("number", arr[i].getNumber());
            tempContact.put("hidden", arr[i].isHidden());

            jsonArray.add(tempContact);
        }

        return jsonArray;
    }
}
