package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {


    @Override
    protected Integer getKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }


    @Override
    protected void updateSave(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    protected void updateDelete(int index) {
        storage[index] = storage[size - 1];
    }

}
