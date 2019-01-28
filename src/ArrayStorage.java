

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    Resume[] storage = new Resume[10000];
    int sizeWithoutNull = 0;

    void clear() {
        for (int i = 0; i < sizeWithoutNull; i++) {
            storage[i] = null;
        }
        sizeWithoutNull = 0;
    }

    void save(Resume r) {
        storage[sizeWithoutNull] = r;
        sizeWithoutNull++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < sizeWithoutNull; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int index = 0;
        for (int i = 0; i < sizeWithoutNull; i++) {
            if (storage[i].uuid.equals(uuid)) {
                index = i;
            }
        }
        System.arraycopy(storage, index + 1, storage, 0, sizeWithoutNull-1);
        sizeWithoutNull--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[sizeWithoutNull];
        for (int i = 0; i < sizeWithoutNull; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    int size() {
        return sizeWithoutNull;
    }
}