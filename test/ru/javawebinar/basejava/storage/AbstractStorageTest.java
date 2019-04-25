package ru.javawebinar.basejava.storage;


import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.javawebinar.basejava.ResumeTestData.addInfo;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected Storage storage;

    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();

    public static Resume R1;
    public static Resume R2;
    public static Resume R3;
    public static Resume R4;

    static {
        R1 = addInfo("Name1", UUID_1);
        R2 = addInfo("Name2", UUID_2);
        R3 = addInfo("Name3", UUID_3);
        R4 = addInfo("Name4", UUID_4);
    }
    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
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
        resume.addContact(ContactType.PHONE, "12312313");
        resume.addContact(ContactType.MAIL, "mail");
        resume.addContact(ContactType.SKYPE, "Skype");
        storage.update(resume);
        assertTrue(resume.equals(storage.get(UUID_3)));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("SomeNme"));
    }

    @Test
    public void getAll() throws Exception {
        List<Resume> resumes = storage.getAllSorted();
        assertEquals(3, resumes.size());
        List<Resume> ourResumes = new ArrayList<>();
        ourResumes.add(R1);
        ourResumes.add(R2);
        ourResumes.add(R3);
        Collections.sort(ourResumes);
        assertEquals(ourResumes, resumes);
    }


    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(R1);
    }

    @Test
    public void save() throws Exception {
        int size = storage.size() + 1;
        storage.save(R4);
        assertEquals(R4, storage.get(UUID_4));
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
        assertEquals(R2, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(UUID_4);
    }

}