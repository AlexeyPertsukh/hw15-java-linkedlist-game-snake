package prog02;

public enum Command {
    END("выход"),
    PRINT_PEOPLE("распечатать список людей"),
    PRINT_AVERAGE_AGE("распечатать средний возраст"),
    PRINT_MAILS("люди с почтой"),
    PRINT_WANT_WOMAN("сотрудницы 18-30 лет"),
    ADD("добавить запись"),
    DEL("удалить запись"),
    ;


    private String nameRus;

    Command( String nameRus) {
        this.nameRus = nameRus;
    }

    public String getKey() {
        return Integer.toString(ordinal());
    }

    public String getNameRus() {
        return nameRus;
    }
}
