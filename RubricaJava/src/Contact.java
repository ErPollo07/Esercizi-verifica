public class Contact {
    private String name;
    private String surname;
    private String number;
    private boolean hidden;

    public Contact(String name, String surname, String number, boolean hidden) {
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.hidden = hidden;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String toString() {
        return String.format("Name: %s Surname: %s", name, surname);
    }
}
