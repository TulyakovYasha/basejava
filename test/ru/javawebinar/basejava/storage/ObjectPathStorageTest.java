package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.Serializer.ObjectSerializer;

public class ObjectPathStorageTest extends AbstractStorageTest {

    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(), new ObjectSerializer()));
    }
}