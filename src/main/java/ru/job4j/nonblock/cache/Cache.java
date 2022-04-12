package ru.job4j.nonblock.cache;

import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (integer, base) -> {
            if (base.getVersion() != model.getVersion()) {
                throw new OptimisticException("Versions are not equal.");
            }
            Base tmp = Base.of(model, model.getVersion() + 1);
            memory.put(tmp.getId(), tmp);
            return tmp;
        }) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }

    public Base get(int id) {
        return memory.computeIfPresent(id, (integer, base) -> Base.of(base));
    }
}
