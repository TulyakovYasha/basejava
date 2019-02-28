package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void update(Resume r) {
        storage.add(getIndex(r.getUuid()), r);
    }

    @Override
    public void save(Resume r) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i) == null) {
                storage.add(i, r);
            }
        }
    }

    @Override
    public Resume get(String uuid) {
        return storage.get(getIndex(uuid));
    }

    @Override
    public void delete(String uuid) {
        storage.remove(getIndex(uuid));
    }

    @Override
    public Resume[] getAll() {
        Resume[] resumes = new Resume[storage.size()];
        resumes = storage.toArray(resumes);
        return resumes;
    }

    @Override
    public int size() {
        return storage.size();
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
