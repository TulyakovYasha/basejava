package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        return sqlHelper.execute("" +
                        "    SELECT * FROM resume r " +
                        " LEFT JOIN contact c " +
                        "        ON r.uuid = c.resume_uuid" +
                        " LEFT JOIN sections s  " +
                        "        ON r.uuid = s.uuid_section" +
                        "     WHERE r.uuid =? ",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume r = new Resume(uuid, rs.getString("full_name"));
                    do {
                        addContacts(rs, r);
                        addSections(rs, r);
                    } while (rs.next());

                    return r;
                });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, r.getUuid());
                if (ps.executeUpdate() != 1) {
                    throw new NotExistStorageException(r.getUuid());
                }
            }
            deleteContacts(conn, r);
            deleteSections(conn, r);
            insertSections(conn, r);
            insertContacts(conn, r);
            return null;
        });

    }


    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                ps.setString(1, r.getUuid());
                ps.setString(2, r.getFullName());
                ps.execute();
            }
            insertContacts(conn, r);
            insertSections(conn, r);
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
        return sqlHelper.transactionalExecute(conn -> {
            List<Resume> resumes = new ArrayList<>();
            try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM resume ORDER BY full_name, uuid ")) {
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    Resume resume = new Resume(rs.getString("uuid"), rs.getString("full_name"));
                    resumes.add(resume);
                }
            }
            try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM contact")) {
                ResultSet rs = preparedStatement.executeQuery();
                for (Resume resume : resumes) {
                    while (rs.next()) {
                        if (resume.getUuid().equals(rs.getString("resume_uuid"))) {
                            addContacts(rs, resume);
                        }
                    }
                }
            }
            try(PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM sections")) {
                ResultSet rs = preparedStatement.executeQuery();
                for (Resume resume : resumes){
                    while (rs.next()){
                        if(resume.getUuid().equals(rs.getString("uuid_section"))){
                            addSections(rs, resume);
                        }
                    }
                }
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

    private void insertContacts(Connection connection, Resume r) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void deleteContacts(Connection connection, Resume resume) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM contact WHERE resume_uuid = ?")) {
            preparedStatement.setString(1, resume.getUuid());
            preparedStatement.execute();
        }
    }

    private void addContacts(ResultSet resultSet, Resume resume) throws SQLException {
        if (resultSet.getString("value") != null) {
            resume.addContact(ContactType.valueOf(resultSet.getString("type")), resultSet.getString("value"));
        }
    }

    private void addSections(ResultSet rs, Resume r) throws SQLException {
        SectionType sectionType = renameSection(rs.getString("section_type"));
        String sectionValue = rs.getString("section_value");
        if (sectionType.equals(SectionType.PERSONAL) || sectionType.equals(SectionType.OBJECTIVE)) {
            TextSection textSection = new TextSection(sectionValue);
            r.addSection(sectionType, textSection);
        } else if (sectionType.equals(SectionType.QUALIFICATIONS) || sectionType.equals(SectionType.ACHIEVEMENT)) {
            ListSection listSection = new ListSection(sectionValue);
            r.addSection(sectionType, listSection);
        }
    }

    private void insertSections(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO sections (UUID_SECTION, SECTION_TYPE, SECTION_VALUE) VALUES (?, ?, ?)")) {
            for (Map.Entry<SectionType, AbstractSection> map : r.getSections().entrySet()) {
                if (map.getKey().equals(SectionType.PERSONAL) || map.getKey().equals(SectionType.OBJECTIVE)) {
                    TextSection textSection = (TextSection) map.getValue();
                    preparedStatement.setString(1, r.getUuid());
                    preparedStatement.setString(2, map.getKey().getTitle());
                    preparedStatement.setString(3, textSection.getText());
                } else if (map.getKey().equals(SectionType.ACHIEVEMENT) || map.getKey().equals(SectionType.QUALIFICATIONS)) {
                    ListSection listSection = (ListSection) map.getValue();
                    preparedStatement.setString(1, r.getUuid());
                    preparedStatement.setString(2, map.getKey().getTitle());
                    for (String s : listSection.getList()) {
                        preparedStatement.setString(3, s + "\n");
                    }
                }
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }

    private void deleteSections(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM sections WHERE uuid_section = ?")) {
            ps.setString(1, r.getUuid());
            ps.execute();
        }
    }

    public SectionType renameSection(String s) {
        switch (s) {
            case ("Личные качества"):
                return SectionType.PERSONAL;
            case ("Позиция"):
                return SectionType.OBJECTIVE;
            case ("Достижения"):
                return SectionType.ACHIEVEMENT;
            case ("Квалификация"):
                return SectionType.QUALIFICATIONS;
            default:
                return null;
        }
    }
}
