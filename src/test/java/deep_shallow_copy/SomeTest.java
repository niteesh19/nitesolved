package deep_shallow_copy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SomeTest {

  @Test
  public void whenModifyingOriginalObject_thenCopyShouldNotChange() {
    Address address = new Address("Downing St 10", "London", "England");
    User pm = new User("Prime", "Minister", address);
    User deepCopy = new User(pm);

    address.setCountry("Great Britain");
    assertNotEquals(
        pm.getAddress().getCountry(),
        deepCopy.getAddress().getCountry());
  }
}
