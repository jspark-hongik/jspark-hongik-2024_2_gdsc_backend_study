package com.example.todoapi.common.message;

public class ErrorMessage {
    public static final String MEMBER_NOT_EXISTS = "존재하지 않는 멤버입니다.";
    public static final String TODO_NOT_EXISTS = "존재하지 않는 할 일입니다.";
    public static final String LOGIN_ID_NOT_EXISTS = "존재하지 않는 ID입니다.";
    public static final String FRIEND_NOT_EXISTS = "존재하지 않는 친구 관계입니다.";
    public static final String TODO_CONTENT_MAX = "할 일 내용은 50자를 넘을 수 없습니다.";
    public static final String UNAUTHORIZED = "권한이 없습니다";
    public static final String LOGIN_ID_MIN = "로그인 ID는 4자 이상이어야 합니다.";
    public static final String PASSWORD_RANGE = "비밀번호는 8자 이상 20자 이하여야 합니다.";
    public static final String NAME_MIN = "이름은 2자 이상이어야 합니다.";
    public static final String WRONG_PASSWORD = "비밀번호가 일치하지 않습니다.";
    public static final String MEMBER_ID_MUST_BE_NOT_NULL = "멤버 ID가 있어야 합니다.";
    public static final String LOGIN_ID_MUST_BE_NOT_NULL = "ID가 있어야 합니다.";
    public static final String IMPORTANCE_RANGE = "중요도는 1~10의 범위로만 설정할 수 있습니다.";
    public static final String NOT_NULL = "값을 입력해야 합니다.";
}
