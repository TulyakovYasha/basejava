/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        int i = 0;
        while (true) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            } else {
                i++;
            }
        }
    }

    Resume get(String uuid) {
        int i = 0;
        Resume resume = new Resume();
        while (storage[i] != null) {
            if (storage[i].toString().equals(uuid)) {
                resume = storage[i];
                return resume;
            } else i++;
        }
        return resume;
    }

    void delete(String uuid) {
        int i = 0;
        while (true) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = null;
                break;
            } else {
                i++;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int size = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                size++;
            }
        }
        Resume[] resumes = new Resume[size];
        int number = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                resumes[number] = storage[i];
                number++;
            }
        }
        return resumes;
    }

    int size() {
        return storage.length;
    }
}