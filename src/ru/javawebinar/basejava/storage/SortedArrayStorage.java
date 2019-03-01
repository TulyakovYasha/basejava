package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }


    @Override
    protected void saveElement(Resume resume, int index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            int saveElement = -index - 1;
            System.arraycopy(storage, saveElement, storage, saveElement + 1, size - saveElement);
            storage[saveElement] = resume;
            size++;
        }
    }

    @Override
    protected void deleteElement(int index) {
        int deleteElement = size - index - 1;
        if (deleteElement > 0) {
            System.arraycopy(storage, index + 1, storage, index, deleteElement);
        }
        size--;
    }
}
