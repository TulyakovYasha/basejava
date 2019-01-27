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
        while (true) {
            for (Resume resume : storage) {
                if (resume.toString().equals(uuid)) {
                    return resume;
                } else {
                    return null;
                }
            }
        }
    }

    void delete(String uuid) {
        Resume[] resumes = new Resume[storage.length - 1];
        for (int i = 0; i < storage.length && storage[i] != null; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
            }
        }
        for (int i = 1; i < resumes.length; i++) {
            resumes[i - 1] = storage[i];
        }
        storage = resumes;
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
        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    int size() {
        int size = 0;
        for (Resume resume : storage) {
            if (resume != null) {
                size++;
            }
        }
        return size;
    }
}