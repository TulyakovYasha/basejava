package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class ListStorage extends AbstractStorage {

    @Override
    protected void saveElement(Resume resume, int index) {
        listStorage.add(resume);
    }

    @Override
    protected void deleteElement(int index) {
        listStorage.remove(index);
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < listStorage.size(); i++) {
            if (listStorage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void updateResume(Resume resume, int index) {
        listStorage.set(index, resume);
    }

    @Override
    protected Resume returnElementIndex(int index) {
        return listStorage.get(index);
    }
}