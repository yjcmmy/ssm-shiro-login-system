package entity;

public class Md5Password {
    private Integer id;

    private String pwMd5;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPwMd5() {
        return pwMd5;
    }

    public void setPwMd5(String pwMd5) {
        this.pwMd5 = pwMd5 == null ? null : pwMd5.trim();
    }

    @Override
    public String toString() {
        return "Md5Password{" +
                "id=" + id +
                ", pwMd5='" + pwMd5 + '\'' +
                '}';
    }

    public Md5Password() {
    }

    public Md5Password(Integer id, String pwMd5) {
        this.id = id;
        this.pwMd5 = pwMd5;
    }
}