package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {
    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            updateResume(r, index);
        }
    }

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            saveElement(r, index);
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return returnElementIndex(index);
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteElement(index);
        }
    }

    protected abstract void saveElement(Resume resume, int index);

    protected abstract void deleteElement(int index);

    protected abstract int getIndex(String uuid);

    protected abstract void updateResume(Resume resume, int index);

    protected abstract Resume returnElementIndex(int index);

}