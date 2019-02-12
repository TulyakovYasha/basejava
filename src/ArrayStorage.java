/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private Resume[] storage = new Resume[10_000];
    private int sizeWithoutNull = 0;


    public void clear() {
        for (int i = 0; i < sizeWithoutNull; i++) {
            storage[i] = null;
        }
        sizeWithoutNull = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
        } else {
            System.out.println("No resume in Update");
        }
    }

    public void save(Resume resume) {
        if (sizeWithoutNull < storage.length) {
            if (getIndex(resume.getUuid()) == -1) {
                storage[sizeWithoutNull] = resume;
                sizeWithoutNull++;
            } else {
                System.out.println("No this element in save");
            }
        } else {
            System.out.println("Arrays is full");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        } else {
            System.out.println("No this element in get");
            return null;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = storage[sizeWithoutNull - 1];
            storage[sizeWithoutNull - 1] = null;
            sizeWithoutNull--;
        } else {
            System.out.println("No element in delete");
        }
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[sizeWithoutNull];
        System.arraycopy(storage, 0, resumes, 0, sizeWithoutNull);
        return resumes;
    }

    public int size() {
        return sizeWithoutNull;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < sizeWithoutNull; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
