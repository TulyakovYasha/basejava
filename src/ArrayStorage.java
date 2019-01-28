import java.util.Arrays;
import java.util.Collections;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    Resume[] storage = new Resume[10000];
    int sizeWithoutNull = 0;

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        for (int i = 0; i< storage.length; i++){
            if(storage[i] == null){
                storage[i] = r;
                break;
            }
        }
        sizeWithoutNull++;
    }

    Resume get(String uuid) {
        for(int i = 0; i < sizeWithoutNull; i++){
            if(storage[i].uuid.equals(uuid)){
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < sizeWithoutNull; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
            }
            System.arraycopy(storage, 1, storage, 0, storage.length-1);
            storage[storage.length-1] = null;
            sizeWithoutNull--;
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