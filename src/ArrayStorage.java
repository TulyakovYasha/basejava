

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
        for (int i = 0; i < sizeWithoutNull; i++) {
            if(storage[i].uuid.equals(uuid)){
                sizeWithoutNull--;
                System.arraycopy(storage, i+1, storage, i, sizeWithoutNull - i);
            }
        }

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