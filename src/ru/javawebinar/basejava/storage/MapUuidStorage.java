package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {
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
    protected boolean isExist(String searchKey) {
        return map.containsKey(searchKey);
    }

    @Override
    protected void saveElement(Resume resume, String key) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteElement(String key) {
        map.remove(key);
    }

    @Override
    protected String getKey(String uuid) {
        return uuid;
    }

    @Override
    protected void updateResume(Resume resume, String key) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getElement(String key) {
        return map.get(key);
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(map.values());
    }
}
