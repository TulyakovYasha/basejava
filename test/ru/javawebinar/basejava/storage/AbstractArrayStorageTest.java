package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static ru.javawebinar.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;


public abstract class AbstractArrayStorageTest {
    private Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";


    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        Resume ourResume = storage.get("uuid1");
        Resume updateResume = new Resume("uuid1");
        Assert.assertEquals(updateResume, ourResume);
    }

    @Test
    public void getAll() throws Exception {
        Assert.assertEquals(storage.size(), storage.getAll().length);
    }

    @Test
    public void save() throws Exception {
        int expSize = storage.size() + 1;
        storage.save(new Resume("uuid4"));
        Assert.assertEquals(expSize, storage.size());
    }

    @Test
    public void delete() throws Exception {
        int expSize = storage.size() - 1;
        storage.delete("uuid1");
        Assert.assertEquals(expSize, storage.size());
    }

    @Test
    public void get() throws Exception {
        Resume resume = storage.get("uuid2");
        Assert.assertEquals(resume.toString(), UUID_2);
    }

    @Test
    public void overElement() throws Exception {
        try {
            storage.save(new Resume());
            storage.save(new Resume());
        } catch (ArrayIndexOutOfBoundsException e) {
            Assert.fail();
        }
        try {
            for (int i = storage.size(); i < STORAGE_LIMIT + 1; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.assertTrue(1 > 0);
        }
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }
}