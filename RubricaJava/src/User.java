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


}
