package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage<Integer> {
    private List<Resume> listStorage = new ArrayList<>();

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }

    @Override
    protected void saveElement(Resume resume, Integer key) {
        listStorage.add(resume);
    }


    @Override
    protected void deleteElement(Integer key) {
        listStorage.remove(key.intValue());
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
    protected void updateResume(Resume resume, Integer key) {
        listStorage.set(key, resume);
    }

    @Override
    protected Resume getElement(Integer key) {
        return listStorage.get(key);
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(listStorage);
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