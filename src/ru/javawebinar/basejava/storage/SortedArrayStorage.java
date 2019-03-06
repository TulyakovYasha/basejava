package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getKey(String uuid) {
        Resume searchKey = new Resume(uuid, "someName");
        return Arrays.binarySearch(storage, 0, size, searchKey, Resume.comparatorUuid);
    }


    @Override
    protected void updateSave(Resume resume, int index) {
        int saveElement = -index - 1;
        System.arraycopy(storage, saveElement, storage, saveElement + 1, size - saveElement);
        storage[saveElement] = resume;
    }

    @Override
    protected void updateDelete(int index) {
        int deleteElement = size - index - 1;
        if (deleteElement > 0) {
            System.arraycopy(storage, index + 1, storage, index, deleteElement);
        }
    }
}
