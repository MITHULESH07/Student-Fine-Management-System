package mainpackage;

class Student {
    private String id, name, dep;
    private int age;

    public Student(String id, String name, String dep, int age) {
        this.id = id; this.name = name; this.dep = dep; this.age = age;
    }

    public void display() {
        System.out.println("=== STUDENT PROFILE ===");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Department: " + dep);
        System.out.println("Age: " + age);
    }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDep(String dep) { this.dep = dep; }
    public void setAge(int age) { this.age = age; }
}