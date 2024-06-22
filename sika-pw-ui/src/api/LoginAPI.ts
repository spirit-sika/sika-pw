import request from "@/utils/request";
import type {CaptchaVO, UserDTO, UserRegisterDTO, UserVO} from "@/types/UserType";

export const getCaptchaAPI = () => {
    return request<CaptchaVO>({
        url: 'captcha-image',
        method: 'GET',
        notAuth: true
    })
}

export const loginAPI = (data: UserDTO) => {
    return request<UserVO>({
        url: 'login',
        method: 'POST',
        notAuth: true,
        data: data
    })
}

export const logoutAPI = () => {
    return request<string>({
        url: 'logout',
        method: 'POST',
        notAuth: false
    })
}

export const registerAPI = (user: UserRegisterDTO) => {
    return request<UserVO>({
        url: 'register',
        method: 'POST',
        notAuth: true,
        data: user
    })
}

export const checkLoginAPI = () => {
    return request<string>({
        url: 'check-login',
        method: 'GET',
        notAuth: false
    })
}