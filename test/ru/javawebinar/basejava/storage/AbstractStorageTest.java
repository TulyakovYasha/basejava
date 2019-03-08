package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";


    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1, "someName1");
        RESUME_2 = new Resume(UUID_2, "someName2");
        RESUME_3 = new Resume(UUID_3, "someName3");
        RESUME_4 = new Resume(UUID_4, "someName4");
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() throws Exception {
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        Resume resume = new Resume(UUID_3, "someNewName");
        storage.update(resume);
        assertSame(resume, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("SomeNme"));
    }

    @Test
    public void getAll() throws Exception {
        List<Resume> resumes = storage.getAllSorted();
        assertEquals(storage.size(), resumes.size());
        List<Resume> ourResumes = new ArrayList<>();
        ourResumes.add(RESUME_1);
        ourResumes.add(RESUME_2);
        ourResumes.add(RESUME_3);
        assertEquals(ourResumes, resumes);
    }


    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void save() throws Exception {
        int size = storage.size() + 1;
        storage.save(RESUME_4);
        assertEquals(RESUME_4, storage.get(UUID_4));
        assertEquals(size, storage.size());
    }


    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        int expSize = storage.size() - 1;
        storage.delete(UUID_3);
        assertEquals(expSize, storage.size());
        storage.get(UUID_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test
    public void get() throws Exception {
        assertEquals(RESUME_2, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(UUID_4);
    }

}