package ray.example.bloodoxygen;

public class DataBean_information {
    public String name;
    public int age;
    public String sex;
    public Double height;
    public Double weight;

    public DataBean_information(String name, int age, String sex, Double height, Double weight) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
    }

    public DataBean_information() {
        this.name = "";
        this.age = 0;
        this.sex = "";
        this.height = 0.0;
        this.weight = 0.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
