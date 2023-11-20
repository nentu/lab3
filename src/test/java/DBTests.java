import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nentu.lab3.lab3.start.main.CoordinateBean;
import com.nentu.lab3.lab3.start.storage.db.DBStorage;
import com.nentu.lab3.lab3.start.utils.CoordinateUtils;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBTests {
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres"
    );
    DBStorage dbStorage;
    static List<CoordinateBean> list1 = new ArrayList<CoordinateBean>() {{
        add(new CoordinateBean(0f, 0f, 0f));
    }};

    static List<CoordinateBean> list2 = new ArrayList<CoordinateBean>() {{
        add(new CoordinateBean(0f, 0f, 1f));
        add(new CoordinateBean(0.3f, 2f, 1.5f));
        add(new CoordinateBean(1f, 0f, 3f));
    }};

    private Map<String, String> getProperties(JdbcDatabaseContainer container) {
        String url = container.getJdbcUrl();
        String name = container.getUsername();
        String pass = container.getPassword();
        return new HashMap<>() {{
            put("jakarta.persistence.jdbc.url", url);
            put("jakarta.persistence.jdbc.user", name);
            put("jakarta.persistence.jdbc.password", pass);
        }};
    }

    private static void checkCoordinateList(List<CoordinateBean> list) {
        for (var i : list) {
            i.setSuccess(
                    CoordinateUtils.check(
                            Float.parseFloat(i.getX()),
                            Float.parseFloat(i.getY()),
                            i.getR()
                    )
            );
        }
    }

    private boolean compareCoordinateList(
            List<CoordinateBean> list1,
            List<CoordinateBean> list2,
            boolean reversed) {
        if (list1.size() != list2.size())
            return false;

        for (int i = 0; i < list1.size(); i++) {
            int list2I = reversed ? (list2.size() - 1) - i : i;
            if (!CoordinateUtils.equals(
                    list1.get(i),
                    list2.get(list2I)
            )) {
                return false;
            }
        }
        return true;
    }

    private boolean checkListAdd(List<CoordinateBean> list) {
        for (var i : list) {
            dbStorage.add(i);
        }
        return compareCoordinateList(
                dbStorage.getList(),
                list,
                false
        );
    }

    @BeforeAll
    static void beforeAll() {
        checkCoordinateList(list1);
        checkCoordinateList(list2);
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    void setUp() {
        dbStorage = new DBStorage(getProperties(postgres));
        dbStorage.clear();
    }

    @Test
    void cleanTest() {
        dbStorage.add(new CoordinateBean());
        dbStorage.clear();
        assertEquals(dbStorage.getList().size(), 0);
    }

    @Test
    void addSingleTest() {
        CoordinateBean newBean = new CoordinateBean(11f, 11f, 11f);
        newBean.setSuccess(
                CoordinateUtils.check(11, 11, 11)
        );
        dbStorage.add(newBean);
        var resList = dbStorage.getList();
        assertEquals(resList.size(), 1);
        assertTrue(CoordinateUtils.equals(resList.get(0), newBean));
    }

    @Test
    void addSingle() {
        assertTrue(checkListAdd(list1));
    }

    @Test
    void addBigList() {
        assertTrue(checkListAdd(list2));
    }

    @Test
    void getReversedList() {
        addBigList();

        assertTrue(compareCoordinateList(
                list2,
                dbStorage.getReversedList(),
                true
        ));

    }

}
