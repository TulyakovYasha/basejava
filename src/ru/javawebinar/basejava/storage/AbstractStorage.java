package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        Object searchKey = existSearchKey(r.getUuid());
        updateResume(r, searchKey);
    }

    @Override
    public void save(Resume r) {
        Object searchKey = notExistSearchKey(r.getUuid());
        saveElement(r, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = existSearchKey(uuid);
        return getElement(searchKey);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = existSearchKey(uuid);
        deleteElement(searchKey);
    }

    private Object existSearchKey(String uuid) {
        Object searchKey = getKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            return searchKey;
        }
    }

    private Object notExistSearchKey(String uuid) {
        Object searchKey = getKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        } else {
            return searchKey;
        }
    }

    protected abstract boolean isExist(Object searchKey);

    protected abstract void saveElement(Resume resume, Object key);

    protected abstract void deleteElement(Object key);

    protected abstract Object getKey(String uuid);

    protected abstract void updateResume(Resume resume, Object key);

    protected abstract Resume getElement(Object key);

}