/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    Resume[] storage = new Resume[10000];
    int sizeWithoutNull = 0;

    public boolean exsists(String uuid) {
        for (int i = 0; i < sizeWithoutNull; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < sizeWithoutNull; i++) {
            storage[i] = null;
        }
        sizeWithoutNull = 0;
    }

    public void update(Resume resume) {
        if (exsists(resume.uuid)) {
            for (int i = 0; i < sizeWithoutNull; i++) {
                if (resume.uuid.equals(storage[i].uuid)) {
                    storage[i] = resume;
                }
            }
        } else {
            System.out.println("No resume in update");
        }
    }

    public void save(Resume r) {
        if (sizeWithoutNull < storage.length) {
            if (!exsists(r.uuid)) {
                storage[sizeWithoutNull] = r;
                sizeWithoutNull++;
            }
        } else {
            System.out.println("Will be exception");
        }
    }

    public Resume get(String uuid) {
        if (exsists(uuid)) {
            for (int i = 0; i < sizeWithoutNull; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    return storage[i];
                }
            }
        } else {
            System.out.println("No resume in get");
        }
        return null;
    }

    public void delete(String uuid) {
        if (exsists(uuid)) {
            for (int i = 0; i < sizeWithoutNull; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    sizeWithoutNull--;
                    System.arraycopy(storage, i + 1, storage, i, sizeWithoutNull - i);
                }
            }
        } else {
            System.out.println("No resume in delete");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[sizeWithoutNull];
        for (int i = 0; i < sizeWithoutNull; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    public int size() {
        return sizeWithoutNull;
    }
}
