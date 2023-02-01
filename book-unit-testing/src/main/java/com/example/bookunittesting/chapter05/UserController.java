package com.example.bookunittesting.chapter05;

public class UserController {

    public void renameUser(final int userId, final String newName) {
        final User user = getUserFromDatabase(userId);

        final String normalizedName = user.normalizeName(newName);
        user.name = normalizedName;

        saveUserToDatabase(user);
    }

    private User getUserFromDatabase(final int userId) {
        /* 데이터베이스에서 userId로 User를 조회하는 로직이 있다고 가정 */
        return new User();
    }

    private void saveUserToDatabase(final User user) {
        /* User를 데이터베이스에 저장하는 로직이 있다고 가정 */
    }
}
