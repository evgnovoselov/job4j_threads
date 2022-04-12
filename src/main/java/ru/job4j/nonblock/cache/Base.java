package ru.job4j.nonblock.cache;

public class Base {
    private final int id;
    private final int version;
    private String name;

    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Base of(Base base) {
        Base result = new Base(base.getId(), base.getVersion());
        result.setName(base.getName());
        return result;
    }

    public static Base of(Base base, int version) {
        Base result = new Base(base.getId(), version);
        result.setName(base.getName());
        return result;
    }
}
