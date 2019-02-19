package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{
    @Override
    public void clear() {

    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public void save(Resume r) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void saveElement(Resume resume, int index) {
        int saveElement = -index - 1;
        System.arraycopy(storage, saveElement, storage, saveElement + 1, size - saveElement);
        storage[saveElement] = resume;
    }

    @Override
    protected void deleteElement(int index) {
        int deleteElement = size - index - 1;
        if (deleteElement > 0) {
            System.arraycopy(storage, index + 1, storage, index, deleteElement);
        }
    }
}
