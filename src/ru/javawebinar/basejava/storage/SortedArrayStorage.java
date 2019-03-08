package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getKey(String uuid) {
        Resume searchKey = new Resume(uuid, "someName");
        return Arrays.binarySearch(storage, 0, size, searchKey, comparator );
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
    private class MyComparator implements Comparator<Resume>{
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    }
    Comparator<Resume> comparator = new MyComparator();
}
