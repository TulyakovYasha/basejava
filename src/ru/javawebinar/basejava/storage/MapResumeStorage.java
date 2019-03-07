package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {
    Map<String, Resume> map = new HashMap<>();

    @Override
    protected boolean isExist(Object resume) {
        return map.containsKey(resume);
    }

    @Override
    protected void saveElement(Resume r, Object resume) {

        map.put(r.getUuid(), r);
    }

    @Override
    protected void deleteElement(Object resume) {
        map.remove(((Resume) resume).getUuid());
    }

    @Override
    protected Object getKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void updateResume(Resume r, Object resume) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected Resume getElement(Object resume) {
        return (Resume) resume;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }
}
