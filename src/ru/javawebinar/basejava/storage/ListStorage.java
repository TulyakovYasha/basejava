package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> listStorage = new ArrayList<>();

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void saveElement(Resume resume, Object key) {
        listStorage.add(resume);
    }


    @Override
    protected void deleteElement(Object key) {
        listStorage.remove(((Integer) key).intValue());
    }

    @Override
    protected Integer getKey(String uuid) {
        for (int i = 0; i < listStorage.size(); i++) {
            if (listStorage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        listStorage.set((Integer) key, resume);
    }

    @Override
    protected Resume getElement(Object key) {
        return listStorage.get((Integer) key);
    }

    @Override
    protected List<Resume> getAll() {
        return listStorage;
    }

    @Override
    public void clear() {
        listStorage.clear();
    }


    @Override
    public int size() {
        return listStorage.size();
    }
}