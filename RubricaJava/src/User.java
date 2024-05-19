import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class User {
    private String username;
    private String password;
    private String passwordHiddenContact;
    private Contact[] visibleContact;
    private Contact[] nonVisibleContact;
    private Contact[] callsToVisibleContact;
    private Contact[] callsToNonVisibleContact;

    public User(String username, String password, String passwordHiddenContact) {
        this.username = username;
        this.password = password;
        this.passwordHiddenContact = passwordHiddenContact;
        this.visibleContact = new Contact[1];
        this.nonVisibleContact = new Contact[1];
        this.callsToVisibleContact = new Contact[1];
        this.callsToNonVisibleContact = new Contact[1];
    }

    public User(String username, String password, String passwordHiddenContact, Contact[] visibleContact, Contact[] nonVisibleContact, Contact[] callsToVisibleContact, Contact[] callsToNonVisibleContact) {
        this.username = username;
        this.password = password;
        this.passwordHiddenContact = passwordHiddenContact;
        this.visibleContact = visibleContact;
        this.nonVisibleContact = nonVisibleContact;
        this.callsToVisibleContact = callsToVisibleContact;
        this.callsToNonVisibleContact = callsToNonVisibleContact;
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

    public Contact[] getVisibleContact() {
        return visibleContact;
    }

    public void setVisibleContact(Contact[] visibleContact) {
        this.visibleContact = visibleContact;
    }

    public Contact[] getNonVisibleContact() {
        return nonVisibleContact;
    }

    public void setNonVisibleContact(Contact[] nonVisibleContact) {
        this.nonVisibleContact = nonVisibleContact;
    }

    public Contact[] getCallsToVisibleContact() {
        return callsToVisibleContact;
    }

    public void setCallsToVisibleContact(Contact[] callsToVisibleContact) {
        this.callsToVisibleContact = callsToVisibleContact;
    }

    public Contact[] getCallsToNonVisibleContact() {
        return callsToNonVisibleContact;
    }

    public void setCallsToNonVisibleContact(Contact[] callsToNonVisibleContact) {
        this.callsToNonVisibleContact = callsToNonVisibleContact;
    }

    public JSONObject toJSONObj() {
        JSONObject userJSONObj = new JSONObject();

        userJSONObj.put("username", this.username);
        userJSONObj.put("password", this.password);
        userJSONObj.put("pwHiddenContact", this.passwordHiddenContact);
        userJSONObj.put("visibleContact", listToJSONArray(this.visibleContact));
        userJSONObj.put("nonVisibleContact", listToJSONArray(this.nonVisibleContact));
        userJSONObj.put("callsToVisibleContact", listToJSONArray(this.callsToVisibleContact));
        userJSONObj.put("callsToNonVisibleContact", listToJSONArray(this.callsToNonVisibleContact));

        return userJSONObj;
    }

    private static JSONArray listToJSONArray(Contact[] arr) {
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) continue;

            JSONObject tempContact = new JSONObject();

            tempContact.put("name", arr[i].getName());
            tempContact.put("surname", arr[i].getSurname());

            jsonArray.add(tempContact);
        }

        return jsonArray;
    }
}
