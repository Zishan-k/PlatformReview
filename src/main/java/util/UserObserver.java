package util;

import modules.user.User;

public interface UserObserver {
    void promoteUserIfNeeded(User user);
}
