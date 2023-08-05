import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryUserRepositoryTest {

    private static InMemoryUserRepository inMemoryUserRepository;

    @BeforeAll
    public static void init() {
        inMemoryUserRepository = new InMemoryUserRepository();
        System.out.println("=========================================================");
        final List<User> users = Arrays.asList(
                new User(null, "Admin", "admin@gmail.com", "admin", 2000, true, Arrays.asList(Role.values())),
                new User(null, "Din", "b@mail.ru", "din", 1900, true, List.of(Role.USER)),
                new User(null, "edikgagra", "edikgagra@gmail.com", "edikgagra", 2200, true, List.of(Role.USER)),
                new User(null, "Hrp123", "erp123@yandex.ru", "erp123", 2110, true,List.of(Role.USER)),
                new User(null, "Frp123", "hhh@gmail.com", "hhh", 3000, true, List.of(Role.USER)),
                new User(null, "Liza", "chulkova_liza@mail.ru", "liza", 2050, true, List.of(Role.USER)),
                new User(null, "123", "123@mail.com", "123", 2002, false, List.of(Role.USER)),
                new User(null, "Maksim", "gone@gmail.com", "321", 2001, true, List.of(Role.USER)),
                new User(null, "Maksim", "Maksim@gmail.com", "435", 2008, false, List.of(Role.USER)),
                new User(null, "Qikhail", "Mikhail@gmail.com", "677", 3009, true, List.of(Role.USER))
        );
        for (int i = 0; i < users.size(); i++) {
            inMemoryUserRepository.save(users.get(i));
        }
    }

    @Test
    void delete() {
        assertEquals(10, inMemoryUserRepository.getAll().size());
        assertTrue(inMemoryUserRepository.delete(9));
        assertNotEquals(10, inMemoryUserRepository.getAll().size());
    }

    @Test
    void save() {
        int sizeBefore = inMemoryUserRepository.getAll().size();
        inMemoryUserRepository.save(new User(null, "newSavedUser", "NEW", "NEW", 0, false, Arrays.asList(Role.values())));
        assertNotEquals(sizeBefore, inMemoryUserRepository.getAll().size());
        assertEquals(sizeBefore+1, inMemoryUserRepository.getAll().size());
    }

    @Test
    void get() {
        assertEquals("edikgagra", inMemoryUserRepository.get(3).getName());
    }

    @Test
    void getAll() {
        inMemoryUserRepository.save(new User(null, "Maksim", "Maksim@gmail.com", "435", 2008, false, Arrays.asList(Role.values())));
        assertEquals(10, inMemoryUserRepository.getAll().size());
    }

    @Test
    void getByEmail() {
        assertEquals("edikgagra@gmail.com", inMemoryUserRepository.get(3).getEmail());
    }
}