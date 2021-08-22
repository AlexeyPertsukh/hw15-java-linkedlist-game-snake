package prog01;

public enum Gender {
    MAN("мужчина"),
    WOMAN("женщина");
    private String nameRus;

    Gender(String nameRus){
        this.nameRus = nameRus;
    }

    public String getNameRus() {
        return nameRus;
    }
}
