package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.containsKey(searchKey);
    }

    @Override
    protected void saveElement(Resume resume, Object key) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteElement(Object key) {
        map.remove(key);
    }

    @Override
    protected String getKey(String uuid) {
        return uuid;
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getElement(Object key) {
        return map.get(key);
    }

    @Override
    protected List<Resume> getAll() {
        List<Resume> list = new ArrayList<>(map.values());
        return list;
    }
}
