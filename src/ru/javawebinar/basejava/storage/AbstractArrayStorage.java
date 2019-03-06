package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<Resume> getAllSorted() {
        Resume[] resumesWithoutNull = Arrays.copyOfRange(storage, 0, size);
        List<Resume> list = new ArrayList<>();
        Collections.addAll(list, resumesWithoutNull);
        Collections.sort(list, Resume.comparator);
        return list;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }


    @Override
    protected void updateResume(Resume resume, Object key) {
        storage[(Integer) key] = resume;
    }

    @Override
    protected Resume getElement(Object key) {
        return storage[(Integer) key];
    }

    @Override
    protected void saveElement(Resume resume, Object key) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            updateSave(resume, (Integer) key);
            size++;
        }
    }

    @Override
    protected void deleteElement(Object key) {
        updateDelete((Integer) key);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    protected abstract void updateSave(Resume resume, int index);

    protected abstract void updateDelete(int index);

    protected abstract Integer getKey(String uuid);
}