package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    private SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("SELECT * FROM resume r WHERE r.uuid =?", preparedStatement -> {
            preparedStatement.setString(1, uuid);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.execute("UPDATE resume SET full_name = ? WHERE uuid = ?", preparedStatement -> {
            preparedStatement.setString(1, r.getFullName());
            preparedStatement.setString(2, r.getUuid());
            if (preparedStatement.executeUpdate() == 0) {
                throw new NotExistStorageException(r.getUuid());
            }
            return null;
        });
    }


    @Override
    public void save(Resume r) {
        sqlHelper.<Void>execute("INSERT INTO resume (uuid, full_name) VALUES (?,?)", preparedStatement -> {
            preparedStatement.setString(1, r.getUuid());
            preparedStatement.setString(2, r.getFullName());
            if (preparedStatement.executeUpdate() == 0) {
                throw new NotExistStorageException(r.getUuid());
            }
            return null;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume r WHERE r.uuid = ?", preparedStatement -> {
            preparedStatement.setString(1, uuid);
            if (preparedStatement.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });

    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("SELECT * FROM resume WHERE uuid IS NOT NULL ", preparedStatement -> {
            List<Resume> resumes = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Resume resume = new Resume(resultSet.getString("uuid"), resultSet.getString("full_name"));
                resumes.add(resume);
            }
            return resumes;
        });
    }


    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) FROM resume", st -> {
            ResultSet rs = st.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }
}
