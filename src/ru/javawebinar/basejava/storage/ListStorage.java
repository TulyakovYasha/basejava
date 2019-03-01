package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> listStorage = new ArrayList<>();

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

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    public Resume[] getAll() {
        Resume[] resumes = new Resume[listStorage.size()];
        resumes = listStorage.toArray(resumes);
        return resumes;
    }

    @Override
    public int size() {
        return listStorage.size();
    }
}