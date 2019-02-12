/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private Resume[] storage = new Resume[10_000];
    private int sizeWithoutNull = 0;

    public int pos(String uuid) {
        for (int i = 0; i < sizeWithoutNull; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        for (int i = 0; i < sizeWithoutNull; i++) {
            storage[i] = null;
        }
        sizeWithoutNull = 0;
    }

    public void update(Resume resume) {
        if (pos(resume.getUuid()) != -1) {
            storage[pos(resume.getUuid())] = resume;
        } else {
            System.out.println("No resume in Update");
        }
    }

    public void save(Resume r) {
        if (sizeWithoutNull < storage.length) {
            if (pos(r.getUuid()) == -1) {
                storage[sizeWithoutNull] = r;
                sizeWithoutNull++;
            } else {
                System.out.println("No this element in save");
            }
        } else {
            System.out.println("Arrays is full");
        }
    }

    public Resume get(String uuid) {
        if (pos(uuid) != -1) {
            return storage[pos(uuid)];
        } else {
            System.out.println("No this element in get");
            return null;
        }
    }

    public void delete(String uuid) {
        if (pos(uuid) != -1) {
            storage[pos(uuid)] = storage[sizeWithoutNull - 1];
            storage[sizeWithoutNull] = null;
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
}
