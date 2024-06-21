export interface UserVO {
    /* */
    userId: number;

    /* */
    nickname: string;

    /* */
    phoneNumber: string;

    /* */
    email: string;

    /* */
    sex: string;

    /* */
    token: string;
}

/**
 * 登录表单信息对象
 */
export interface UserDTO {
    /* */
    phoneNumber?: string;

    /* */
    password?: string;

    /* */
    codeKey?: string;

    /* */
    code?: string;

    /* */
    rememberMe?: boolean;
}

export interface CaptchaVO {
    /* */
    captchaKey: string;

    /* */
    base64: string;
}

export interface UserRegisterDTO {
    /* 用户昵称 */
    nickname: string;

    password: string;

    phoneNumber: string;

    email?: string;

    /* user sex, 0 is unknown, 1 is male, 2 is female */
    sex?: 0 | 1 | 2;
}
