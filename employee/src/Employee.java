public class Employee {
    private String name;
    private String position;
    private String email;
    private String phone;

    public Employee(String name, String position, String email, String phone) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.phone = phone;
    }

    // Getters and setters...

    @Override
    public String toString() {
        return name;
    }


    public void setName(String text) {
    }

    public void setPosition(String text) {
    }

    public void setEmail(String text) {
    }

    public void setPhone(String text) {
    }

    public int getId() {
        return 0;
    }
}
