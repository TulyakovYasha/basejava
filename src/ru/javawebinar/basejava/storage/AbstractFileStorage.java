package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * gkislin
 * 22.07.2016
 */
public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;
    private int size;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    public void clear() {
        for (File file : directory.listFiles()) {
            file.delete();
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected File getKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void updateResume(Resume r, File file) {
        try {
            doWrite(r, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void saveElement(Resume r, File file) {
        try {
            file.createNewFile();
            doWrite(r, file);
            size++;
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }


    @Override
    protected Resume getElement(File file) {
        return doRead(file);
    }


    @Override
    protected void deleteElement(File file) {
        file.delete();
        size--;
    }

    @Override
    protected List<Resume> getAll() {
        List<Resume> list = new ArrayList<>();
        for(File file : directory.listFiles()){
          list.add(doRead(file));
        }
        return list;
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;

    protected abstract Resume doRead(File file);
}